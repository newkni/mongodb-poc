package com.example.mongodb.service;

import com.example.mongodb.entity.Attachment;
import com.example.mongodb.repository.AttachmentRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public record AttachmentService(AttachmentRepo repository) {

  public List<Attachment> getAllAttachment() {
    return repository.findAll();
  }

  public Optional<Attachment> findByFileName(String fileName) {
    return repository.findAttachmentByFileName(fileName);
  }

  public Optional<Attachment> findById(String id) {
    return repository.findById(id);
  }

  public Attachment add(Attachment attachment) {
    return repository.insert(attachment);
  }

  public void update(Attachment attachment) {
    attachment.setUpdateDate(now());
    repository.save(attachment);
  }

  public void deleteAttachment(Attachment attachment) {
    repository.delete(attachment);
  }

  private String now() {
    return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
  }
}
