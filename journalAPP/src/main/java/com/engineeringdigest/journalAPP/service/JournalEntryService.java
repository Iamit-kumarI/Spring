package com.engineeringdigest.journalAPP.service;

import com.engineeringdigest.journalAPP.entity.JournalEntry;
import com.engineeringdigest.journalAPP.entity.User;
import com.engineeringdigest.journalAPP.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Slf4j
@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;
    public void saveEntry(JournalEntry journalEntry, String userName){
       User user=userService.findByUserName(userName);
       if(user==null){
           throw new RuntimeException("User not found");
       }
       journalEntry.setUser(user);
       journalEntry.setDate(LocalDateTime.now());
       journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }
    public void deleteById(ObjectId id, String userName){
        
        journalEntryRepository.deleteById(id);
    }
}
