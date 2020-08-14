package com.roboto.storage.controllers;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roboto.storage.handler.DocumentHandler;
import com.roboto.storage.models.Document;

@RestController
@RequestMapping(value = "/storage")
public class StorageController {
	
	@Autowired
	DocumentHandler documentHandler;
	
	/**
	 * create is an API handler to create a document.
	 * 
	 * @param content String
	 * @return ResponseEntity<String>
	 */
	@PostMapping("/documents")
	public ResponseEntity<String> create(String content) {
		String docId = this.documentHandler.createDocument(content);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		headers.setAcceptCharset(Collections.singletonList(StandardCharsets.US_ASCII));
		headers.setContentLength(docId.length());
		return new ResponseEntity<String>(docId, headers, HttpStatus.CREATED);
	}
	
	/**
	 * getById is an API handler to fetch a document for a given docId.
	 * 
	 * @param docId String
	 * @return ResponseEntity<String>
	 */
	@GetMapping("/documents/{docId}")
	public ResponseEntity<String> getById(@PathVariable("docId") String docId) {
		if(!this.documentHandler.isDocumentExisting(docId)) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		String content = this.documentHandler.getById(docId);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentLength(content.length());
		return new ResponseEntity<String>(content, headers, HttpStatus.OK);
	}
	
	/**
	 * update is an API handler to update a document with a given docId.
	 * 
	 * @param docId String
	 * @param content String
	 * @return ResponseEntity<String>
	 */
	@PutMapping("/documents/{docId}")
	public ResponseEntity<String> update(@PathVariable("docId") String docId, String content) {
		if(!this.documentHandler.isDocumentExisting(docId)) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		this.documentHandler.updateDocument(new Document(docId, content));
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);		
	}
	
	/**
	 * delete is an API handler to delete a document by a given docId.
	 * 
	 * @param docId String
	 * @return ResponseEntity<String>
	 */
	@DeleteMapping("/documents/{docId}")
	public ResponseEntity<String> delete(@PathVariable("docId") String docId) {
		if(!this.documentHandler.isDocumentExisting(docId)) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		this.documentHandler.deleteDocumentById(docId);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);				
	}

}
