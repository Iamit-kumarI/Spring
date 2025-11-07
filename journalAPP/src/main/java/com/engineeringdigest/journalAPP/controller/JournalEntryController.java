//package com.engineeringdigest.journalAPP.controller;
//
//import com.engineeringdigest.journalAPP.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.List;
//
//
//@RestController
//@RequestMapping("/journal")
//public class JournalEntryController {
//    private Map<Long,JournalEntry> journalEntries=new HashMap<>();
//    @GetMapping
//    public List<JournalEntry> getAll(){
//        return new ArrayList<>(journalEntries.values());
//    }
//    @PostMapping
//    public boolean createEntry(@RequestBody JournalEntry myEntry) {
//        journalEntries.put(myEntry.getId(),myEntry);
//        return true;
//    }
//    @GetMapping("id/{idval}")
//    public JournalEntry getJournalById(@PathVariable Long idval){
//        return journalEntries.get(idval);
//    }
//    @DeleteMapping("id/{idval}")
//    public JournalEntry deleteJournalById(@PathVariable Long idval){
//        return journalEntries.remove(idval);
//    }
//    @PutMapping("id/{idval}")
//    public JournalEntry updateJournalById(@PathVariable Long idval,@RequestBody JournalEntry myEntry){
//        return journalEntries.put(idval,myEntry);
//    }
//}
