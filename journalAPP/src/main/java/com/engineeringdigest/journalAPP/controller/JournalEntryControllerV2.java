package com.engineeringdigest.journalAPP.controller;

import com.engineeringdigest.journalAPP.entity.JournalEntry;
import com.engineeringdigest.journalAPP.repository.JournalEntryRepository;
import com.engineeringdigest.journalAPP.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll();
    }
    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry) {
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return myEntry;
    }
    @GetMapping("id/{idval}")
    public JournalEntry getJournalById(@PathVariable ObjectId idval){
        return journalEntryService.findById(idval).orElse(null);
    }
    @DeleteMapping("id/{idval}")
    public boolean deleteJournalById(@PathVariable ObjectId idval){
        journalEntryService.deleteById(idval);
        return true;
    }
    @PutMapping("id/{idval}")
    public JournalEntry updateJournalById(@PathVariable ObjectId idval,@RequestBody JournalEntry newEntry){
        JournalEntry old=journalEntryService.findById(idval).orElse(null);
        if(old!=null){
            old.setTitle(newEntry.getTitle()!=null&&!newEntry.getTitle().equals("")?newEntry.getTitle():old.getTitle());
            old.setContent(newEntry.getContent()!=null&&!newEntry.getContent().equals("")?newEntry.getContent():old.getContent());
        }
        journalEntryService.saveEntry(old);
        return old;
    }
}
