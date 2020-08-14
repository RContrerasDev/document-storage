package com.roboto.storage.constants;

public interface IDocumentConstants {
	
	public static final String SQL_CREATE_DOCUMENT = "INSERT INTO document (id, content) VALUES (?,?)";
	public static final String SQL_GET_DOCUMENT_BY_ID = "SELECT * FROM document WHERE id = ?";
	public static final String SQL_GET_COUNT_FOR_DOCUMENT_ID = "SELECT COUNT(*) FROM document WHERE id = ?";
	public static final String SQL_UPDATE_DOCUMENT_BY_ID = "UPDATE document SET content = ? WHERE id = ?";
	public static final String SQL_DELETE_DOCUMENT_BY_ID = "DELETE FROM document WHERE id = ?";

}
