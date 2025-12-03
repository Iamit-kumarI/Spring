package com.engineeringdigest.journalAPP.repository;

import com.engineeringdigest.journalAPP.entity.JournalEntry;
import com.engineeringdigest.journalAPP.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String username);
}
