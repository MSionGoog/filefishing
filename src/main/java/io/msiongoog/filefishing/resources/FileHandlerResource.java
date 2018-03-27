package io.msiongoog.filefishing.resources;

import java.awt.datatransfer.MimeTypeParseException;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import io.msiongoog.filefishing.domains.HttpMessage;

public interface FileHandlerResource {

	public ResponseEntity<HttpMessage> handleFileUpload(@RequestParam("file") MultipartFile uploadFile) throws IOException, MimeTypeParseException;
	
	public ResponseEntity<HttpMessage> listAllUploadedFiles() throws IOException;
	
	public ResponseEntity<HttpMessage> getFileContent(@PathVariable("filename") String fileName) throws IOException;
	
	public ResponseEntity<HttpMessage> renameFile(@PathVariable("filename") String fileName, @PathVariable("newfilename") String newFileName) throws IOException;
	
	public ResponseEntity<HttpMessage> deleteFile(@PathVariable("filename") String fileName) throws IOException;
	
	
}
