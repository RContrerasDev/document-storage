package com.roboto.storage.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.roboto.storage.constants.IDocumentConstants;
import com.roboto.storage.models.Document;

@Repository
public class DocumentRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Document insert(Document document) {
		jdbcTemplate.update(IDocumentConstants.SQL_CREATE_DOCUMENT, 
				new Object[] {
				document.getId(),
				document.getContent()});
		return document;
	}
	
	public Document findById(String id) {
		return jdbcTemplate.queryForObject(
				IDocumentConstants.SQL_GET_DOCUMENT_BY_ID, 
				new Object[] {id}, 
				new BeanPropertyRowMapper<>(Document.class));
	}
	
	public boolean isExisting(String id) {
		Integer count = jdbcTemplate.queryForObject(
				IDocumentConstants.SQL_GET_COUNT_FOR_DOCUMENT_ID, 
				new Object[] {id},
				Integer.class);		
		return count > 0 ? Boolean.TRUE : Boolean.FALSE;
	}
	
	public Document update(Document document) {
		jdbcTemplate.update(IDocumentConstants.SQL_UPDATE_DOCUMENT_BY_ID,
		new Object[] {
				document.getContent(),
				document.getId()				
		});
		return document;
	}
	
	public void delete(String id) {
		jdbcTemplate.update(
				IDocumentConstants.SQL_DELETE_DOCUMENT_BY_ID, 
				new Object[] {id});
	}

}
