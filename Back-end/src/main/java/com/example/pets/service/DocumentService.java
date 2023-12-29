package com.example.pets.service;

import com.example.pets.entity.Document;
import com.example.pets.entity.Pet;
import com.example.pets.repository.DocumentRepository;
import com.example.pets.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class DocumentService implements IDocumentService{
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private PetRepository petRepository;

    @Override
    public Document saveDocument(Document document, Long petId) {
        Pet pet= petRepository.findById(petId).orElse(null);
        if(pet==null) return null;
        document.setPet(pet);
        return documentRepository.save(document);
    }
    @Override
    public List<Document> getDocumentsByPetId(Long petId) {
        Pet pet= petRepository.findById(petId).orElse(null);
        if(pet==null) return null;
        return  pet.getDocuments();
    }
    @Override
    public void deleteDocument(Long documentId) {
        documentRepository.deleteById(documentId);
    }

}
