package com.example.pets.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "document")
public class Document {
    @Id
    @GeneratedValue
    private Long documentId;
    private String documentName;
    private String documentType;
    @Lob
    @Column(name = "documentUrl", columnDefinition = "longblob")
    private byte[] documentUrl;
    @ManyToOne
    @JoinColumn(name = "petId")
    private Pet pet;
}
