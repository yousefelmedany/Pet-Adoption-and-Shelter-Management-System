package com.example.pets.service;

import com.example.pets.entity.Document;

import java.util.List;

public interface IDocumentService {
    Document saveDocument(Document document, Long petId);
    List<Document> getDocumentsByPetId(Long petId);
    void deleteDocument(Long documentId);
}
