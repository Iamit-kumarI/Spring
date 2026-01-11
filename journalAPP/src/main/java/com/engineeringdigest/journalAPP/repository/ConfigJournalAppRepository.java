package com.engineeringdigest.journalAPP.repository;

import com.engineeringdigest.journalAPP.entity.ConfigJournalAppEntity;
import com.engineeringdigest.journalAPP.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {

}
