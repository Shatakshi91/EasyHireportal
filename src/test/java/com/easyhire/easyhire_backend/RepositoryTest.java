package com.easyhire.easyhire_backend;

import com.easyhire.easyhire_backend.repository.JobRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RepositoryTest {

    @Autowired
    JobRepository jobRepository;

    @Test
    void contextLoads() {
        assertNotNull(jobRepository);
    }
}