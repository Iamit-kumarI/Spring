package com.engineeringdigest.journalAPP.Scheduler;

import com.engineeringdigest.journalAPP.cache.AppCache;
import com.engineeringdigest.journalAPP.entity.JournalEntry;
import com.engineeringdigest.journalAPP.entity.User;
import com.engineeringdigest.journalAPP.enums.Sentiment;
import com.engineeringdigest.journalAPP.model.SentimentData;
import com.engineeringdigest.journalAPP.repository.UserRepositoryImpl;
import com.engineeringdigest.journalAPP.service.EmailService;
import com.engineeringdigest.journalAPP.service.SentimentAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private SentimentAnalysis sentimentAnalysis;

    @Autowired
    private AppCache appCache;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUserAndSendEmail(){
        List<User> users = userRepository.getUserForSA();
        for(User user:users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).toList();
            Map<Sentiment,Integer> sentimentCount=new HashMap<>();
            for(Sentiment sentiment:sentiments){
                if(sentiment!=null)sentimentCount.put(sentiment,sentimentCount.getOrDefault(sentiment,0)+1);
            }
            Sentiment mostFrequenctSentiment=null;
            int maxCount=0;
            for(Map.Entry<Sentiment, Integer>entry:sentimentCount.entrySet()){
                if(entry.getValue()>maxCount){
                    maxCount=entry.getValue();
                    mostFrequenctSentiment=entry.getKey();
                }
            }
            if(mostFrequenctSentiment!=null){
                SentimentData sentimentData = SentimentData.builder().email(user.getEmail()).sentiment("Sentiment For Last 7 Days" + mostFrequenctSentiment).build();
                kafkaTemplate.send("weekly sentiment",sentimentData.getEmail(),sentimentData);
            }
        }
    }
    @Scheduled(cron = "0 */10 * ? * *")//after every ten minutes
    public void clearAppCache(){
        appCache.init();
    }
}
