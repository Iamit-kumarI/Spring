package com.engineeringdigest.journalAPP.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "config_journal_app")
//documents tell that this is mapped to the db like row, col
//@Getter
//@Setter
@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@EqualsAndHashCode
//@Builder
// or we can just use data annotation
@Data
public class ConfigJournalAppEntity {

    private String key;
    private String value;
}
