package com.example.pets.controller;

import com.example.pets.entity.Document;
import com.example.pets.service.IDocumentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentController {
    @Autowired
    private IDocumentService documentService;

    @PostMapping("/save")
    public Document saveDocument(@RequestParam("file") MultipartFile file, @RequestParam("document") String documentJson,@RequestParam("petid") Long pet_id) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Document document = objectMapper.readValue(documentJson, Document.class);
        document.setDocumentUrl(file.getBytes());
        return documentService.saveDocument(document, pet_id);

    }
    @GetMapping("/getDocumentsByPetId")
    public List<Document> getDocumentsByPetId(@RequestParam("petid") Long petId){
        return documentService.getDocumentsByPetId(petId);
    }
    @DeleteMapping("/delete")
    public void deleteDocument(@RequestParam("documentid") Long documentId){
        documentService.deleteDocument(documentId);
    }

}
