package com.example.mongodb;

import com.example.mongodb.entity.Attachment;
import com.example.mongodb.service.AttachmentService;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@SpringBootTest
class MongodbApplicationTests {

  @Autowired private AttachmentService attachmentService;

  @Test
  public void testGetAttachments() {
    List<Attachment> attachments = attachmentService.getAllAttachment();
    attachments.forEach(System.out::println);
    Assertions.assertNotNull(attachments);
  }

  @Test
  public void testCreate() {
    Attachment attachment = getRandomAttachment();
    System.out.println("===== add attachment: " + attachment);
    Assertions.assertNotNull(attachmentService.add(attachment));
  }

  @Test
  public void testRetrieve() {
    attachmentService
        .findByFileName("104好食光輕食菜單.xlsx")
        .ifPresentOrElse(Assertions::assertNotNull, () -> System.out.println("找不到資料"));
  }
  @Test
  public void testRetrieveById() {
    attachmentService
        .findById("646440c17aff1e477454bb34")
        .ifPresentOrElse(System.out::println, () -> System.out.println("找不到資料"));
  }

  @Test
  public void testUpdate() {
    String originalName = "b13b02db-f9c3-407d-aa15-bee23c83f31e.txt";
    String updateName = "Daniel-Test.txt";
    attachmentService.findByFileName(originalName).ifPresent(attachment -> {
      attachment.setFileName(updateName);
      attachmentService.update(attachment);
    });
  }

  @Test
  public void testDelete() {
    String fileName = "1e9aee1c-aa87-417d-9305-2e9d49273a53.txt";
    attachmentService
        .findByFileName(fileName)
        .ifPresent(
            attachment -> {
              attachmentService.deleteAttachment(attachment);
              System.out.println("deleted: " + fileName);
            });
    Assertions.assertFalse(attachmentService.findByFileName(fileName).isPresent());
  }

  private Attachment getRandomAttachment() {
    String now = now();
    return Attachment.builder()
        .fileName(getFakeFileName())
        .apiKey(getFakeApiKey())
        .fileHash(getFakeHash())
        .createdDate(now)
        .updateDate(now)
        .build();
  }

  private String now() {
    return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
  }

  private String getFakeHash() {
    try {
      return Hex.encodeHexString(MessageDigest.getInstance("SHA-256").digest(getUUID().getBytes()));
    } catch (NoSuchAlgorithmException e) {
      return Integer.toString(Math.abs(Objects.hashCode(getUUID())));
    }
  }

  private String getFakeFileName() {
    return RandomStringUtils.randomAlphabetic(8) + ".txt";
  }

  private String getFakeApiKey() {
    return getUUID().replaceAll("-", StringUtils.EMPTY);
  }

  private String getUUID() {
    return UUID.randomUUID().toString();
  }
}
