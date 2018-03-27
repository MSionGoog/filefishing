package io.msiongoog.filefishing.resources;

import java.awt.datatransfer.MimeTypeParseException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dropbox.core.DbxApiException;
import com.dropbox.core.DbxException;

import io.msiongoog.filefishing.domains.HttpMessage;
import io.msiongoog.filefishing.utils.DropBoxIntegrationUtils;

@RestController
@RequestMapping(path = "/protected/dropbox/files")
public class FileUploadHandlerDropBoxResource implements FileHandlerResource {

	// http://localhost:8080/protected/dropbox/files/listuploadedfiles

	// Enter, "Dropbox"
	@Autowired
	DropBoxIntegrationUtils dropBoxIntegrationUtils;



	
	@Override
	public ResponseEntity<HttpMessage> handleFileUpload(MultipartFile uploadFile)
			throws IOException, MimeTypeParseException {
		// TODO Auto-generated method stub
		return null;
	}

	@GetMapping(path = "/listuploadedfiles")
	@Override
	public ResponseEntity<HttpMessage> listAllUploadedFiles() throws IOException {
		HttpMessage message = new HttpMessage();

		try {
			dropBoxIntegrationUtils.dropBoxConnection();

			message.setMessage(dropBoxIntegrationUtils.dropBoxFileListing());
		} catch (DbxException e) {
			e.printStackTrace();
			message.setMessage(e.getMessage());
			return new ResponseEntity<HttpMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<HttpMessage>(message, HttpStatus.OK);
	}

	@GetMapping(path="/filecontent/{filename}")
	@Override
	public ResponseEntity<HttpMessage> getFileContent(String fileName) throws IOException {
		HttpMessage message = new HttpMessage();
		
		message.setMessage(dropBoxIntegrationUtils.fetchFileContent(fileName));
		
		return new ResponseEntity<HttpMessage>(message, HttpStatus.OK);
	}

	@GetMapping(path="/filemove/from/{filename}/to/{newfilename}")
	@Override
	public ResponseEntity<HttpMessage> renameFile(String fileName, String newFileName) throws IOException {
		return null;
	}

	@DeleteMapping(path="/filedelete/{filename}")
	@Override
	public ResponseEntity<HttpMessage> deleteFile(String fileName) throws IOException {
		return null;
	}

}
