package com.engineeringdigest.journalAPP.Scheduler;

import com.engineeringdigest.journalAPP.cache.AppCache;
import com.engineeringdigest.journalAPP.entity.JournalEntry;
import com.engineeringdigest.journalAPP.entity.User;
import com.engineeringdigest.journalAPP.repository.UserRepositoryImpl;
import com.engineeringdigest.journalAPP.service.EmailService;
import com.engineeringdigest.journalAPP.service.SentimentAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

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

    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUserAndSendEmail(){
        List<User> users = userRepository.getUserForSA();
        for(User user:users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredEnteries = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getContent()).toList();
            String entry=String.join(" ",filteredEnteries);
            String sentiment = sentimentAnalysis.getSentiment(entry);
            emailService.sendEmail(user.getEmail(),"Sentiment For Last 7 Days",sentiment);
        }
    }
    @Scheduled(cron = "0 */10 * ? * *")//after every ten minutes
    public void clearAppCache(){
        appCache.init();
    }
}
