package com.engineeringdigest.journalAPP.controller;

import com.engineeringdigest.journalAPP.entity.JournalEntry;
import com.engineeringdigest.journalAPP.entity.User;
import com.engineeringdigest.journalAPP.repository.JournalEntryRepository;
import com.engineeringdigest.journalAPP.service.JournalEntryService;
import com.engineeringdigest.journalAPP.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;//connecting journal

    @GetMapping({"{userName}"})
    public ResponseEntity<?> getAllJournalEnteriesOfUser(@PathVariable String userName){
        User user=userService.findByUserName(userName);
        List<JournalEntry> all =user.getJournalEntries();
        if(all!=null&&!all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry,@PathVariable String userName) {
        try {
//            myEntry.setDate(LocalDateTime.now());
            User user=userService.findByUserName(userName);
            journalEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{idval}")
    public ResponseEntity<JournalEntry> getJournalById(@PathVariable ObjectId idval){
        Optional<JournalEntry> journalEntry = journalEntryService.findById(idval);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{idval}")
    public ResponseEntity<?> deleteJournalById(@PathVariable ObjectId idval){
        journalEntryService.deleteById(idval);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{idval}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId idval,@RequestBody JournalEntry newEntry){
        JournalEntry old=journalEntryService.findById(idval).orElse(null);
        if(old!=null){
            old.setTitle(newEntry.getTitle()!=null&&!newEntry.getTitle().equals("")?newEntry.getTitle():old.getTitle());
            old.setContent(newEntry.getContent()!=null&&!newEntry.getContent().equals("")?newEntry.getContent():old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
