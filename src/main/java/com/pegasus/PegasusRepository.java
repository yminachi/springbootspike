package com.pegasus;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PegasusRepository extends MongoRepository<Pegasus, String> {
    Pegasus findByName(String name);
    List<Pegasus> findByTitle(String title);
}