package com.example.mongodb.entity;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attachment {
    @Id
    private String id;
    @NonNull
    private String apiKey;
    @NonNull
    private String fileName;
    @NonNull
    private String fileHash;
    @NonNull
    private String createdDate;
    private String updateDate;
}

