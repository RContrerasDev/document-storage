package com.roboto.storage.handler;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roboto.storage.dao.DocumentRepository;
import com.roboto.storage.models.Document;

@Component
public class DocumentHandler {
	
	@Autowired
	private DocumentRepository documentRepository;
	
	/**
	 * createDocument is a Handler method to create a document.
	 * 
	 * @param content String
	 * @return String
	 */
	public String createDocument(String content) {
		Document document = new Document();
		document.setId(this.generateId());
		document.setContent(content);
		this.documentRepository.insert(document);
		return document.getId();
	}
	
	/**
	 * generateId is a Handler method to generate a ransom alphanumeric id.
	 * 
	 * @return generatedString String
	 */
	private String generateId() {		
		int leftLimit = 48;
	    int rightLimit = 122;
	    int targetStringLength = 20;
	    Random random = new Random();	 
	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
		
		return generatedString;
	}
	
	/**
	 * isDocumentExisting is a Handler method to verify if a product with a given docId exists.
	 * 
	 * @param docId String
	 * @return boolean
	 */
	public boolean isDocumentExisting(String docId) {
		return this.documentRepository.isExisting(docId);		
	}
	
	/**
	 * getById is a Handler method to get a document by a given docId.
	 * @param docId String
	 * @return String
	 */
	public String getById(String docId) {
		Document document = this.documentRepository.findById(docId);
		return document.getContent();
	}
	
	/**
	 * updateDocument ios a Handler method to update a document by a given docId.
	 * 
	 * @param document Document
	 */
	public void updateDocument(Document document) {
		this.documentRepository.update(document);
	}
	
	/**
	 * deleteDocumentById is a Handler method to to delete a document by a given docId.
	 * 
	 * @param docId String
	 */
	public void deleteDocumentById(String docId) {
		this.documentRepository.delete(docId);
	}

}
