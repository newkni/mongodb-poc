package com.example.mongodb;

import com.example.mongodb.repository.AttachmentRepo;
import com.example.mongodb.service.AttachmentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MongodbApplication {

  public static void main(String[] args) {
    SpringApplication.run(MongodbApplication.class, args);
  }
}
