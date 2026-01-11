package com.engineeringdigest.journalAPP.cache;

import com.engineeringdigest.journalAPP.entity.ConfigJournalAppEntity;
import com.engineeringdigest.journalAPP.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }

    public Map<String,String> appCache;

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    @PostConstruct
    public void init(){
        appCache=new HashMap<>();//this supports even anything changes it will reload it from dp again in next time when it is used
        List<ConfigJournalAppEntity>all=configJournalAppRepository.findAll();
        for(ConfigJournalAppEntity configJournalAppEntity:all){
            appCache.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());
        }
    }
}
