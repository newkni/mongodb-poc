package com.example.mongodb.repository;

import com.example.mongodb.entity.Attachment;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AttachmentRepo extends MongoRepository<Attachment, String> {
  Optional<Attachment> findAttachmentByFileName(String fileName);

}
