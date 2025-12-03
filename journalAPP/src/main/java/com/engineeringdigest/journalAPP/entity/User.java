package com.engineeringdigest.journalAPP.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collation = "en")
//documents tell that this is mapped to the db like row, col
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@EqualsAndHashCode
//@Builder
// or we can just use data annotation
@Data
public class User {
    @Id
    private ObjectId id;

    //make userName unique
    @Indexed(unique = true)
    @NonNull
    private String userName;
    //not null is provided by lambok when anotation processor values assign kar raha hoga
    // to wo not null check kar lega and exception through ho jayega
    @NonNull
    private String password;

    //we need to connect journal entry to the journalentry so a user can put data in
    // it seemlessly and we can use assosiate user class list which have all the journal entered
    //by user and i will have the feild like journal entry so the DBRef used to create ref of that
    //6:38 -> 44 min
    @DBRef
    private List<JournalEntry> journalEntries=new ArrayList<>();
}