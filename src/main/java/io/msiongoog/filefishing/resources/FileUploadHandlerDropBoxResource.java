package io.msiongoog.filefishing.resources;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dropbox.core.DbxApiException;
import com.dropbox.core.DbxException;

import io.msiongoog.filefishing.domains.HttpMessage;
import io.msiongoog.filefishing.security.Oauth2ResourceServer;
import io.msiongoog.filefishing.utils.DropBoxIntegrationUtils;

@RestController
@RequestMapping(path=Oauth2ResourceServer.BASICAUTHPROTECTED+"/dropbox/files")
public class FileUploadHandlerDropBoxResource {
	
	//http://localhost:8080/protected/dropbox/files/listuploadedfiles

	// Enter, "Dropbox"
	@Autowired
	DropBoxIntegrationUtils dropBoxIntegrationUtils;
	
	@GetMapping(path="/listuploadedfiles")
	public ResponseEntity<HttpMessage> listAllUploadedFilesinDropBox() throws IOException, DbxApiException, DbxException {
		
		HttpMessage message = new HttpMessage();
		
		dropBoxIntegrationUtils.TestDropBoxConnection();
		message.setMessage(dropBoxIntegrationUtils.TestDropBoxFileListing());
		return new ResponseEntity<HttpMessage>(message,HttpStatus.OK);
		
		//	return new ResponseEntity<HttpMessage>(new HttpMessage("upload location does not exist"), HttpStatus.INTERNAL_SERVER_ERROR);
		//	return new ResponseEntity<HttpMessage>(new HttpMessage("location does not have any files"), HttpStatus.OK);
	//	return new ResponseEntity<HttpMessage>(new HttpMessage(files.stream().map(path -> path.getFileName()).collect(Collectors.toList()).toString()), HttpStatus.OK);
	}

}
