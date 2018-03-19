package io.msiongoog.filefishing.resources;

import java.awt.datatransfer.MimeTypeParseException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.msiongoog.filefishing.domains.HttpMessage;
import io.msiongoog.filefishing.security.Oauth2ResourceServer;


@RestController
@RequestMapping(path=Oauth2ResourceServer.BASICAUTHPROTECTED+"/files")
public class FileUploadHandlerResource {
	
	private static final String FILESTORAGE_LOCATION = "/Users/shyam/apps/filestorage/";
	Set<String> acceptedMimeTypes;
	
	
	
	{
		acceptedMimeTypes = new HashSet<String>() {

			
			private static final long serialVersionUID = -2895507796430153112L;

			{
				add("application/octet-stream");
				add("application/x-sql");
			}
		};
		
	}

	private static final Logger log = LoggerFactory.getLogger(FileUploadHandlerResource.class);
	
	
	@PostMapping(path="/uploadfile")
	public ResponseEntity<HttpMessage> handleFileUpload(@RequestParam("file") MultipartFile uploadFile) throws IOException, MimeTypeParseException {
		
		if(uploadFile.isEmpty()) {
			return new ResponseEntity<HttpMessage> (new HttpMessage("file is empty"), HttpStatus.OK);
		}
		
		byte[] fileBytes = uploadFile.getBytes();
		String filelocation = FILESTORAGE_LOCATION + uploadFile.getOriginalFilename()+ "-TS-" + ZonedDateTime.now().toString();
		Path path = Paths.get(filelocation);
		
		String mimeType = uploadFile.getContentType();
		
		if(!acceptedMimeTypes.contains(mimeType)) {
			throw new MimeTypeParseException("mimetype " + mimeType + " not accepted");
		}
		
		log.info("mime type" + mimeType);
		
		Path writePath = Files.write(path, fileBytes);
		
		if(writePath.toUri().toString().equalsIgnoreCase(path.toUri().toString())) {
			return new ResponseEntity<HttpMessage>(new HttpMessage("ALL OK! fileuploaded to location " + filelocation), HttpStatus.OK);
		}
		return new ResponseEntity<HttpMessage>(new HttpMessage("upload failed"),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@GetMapping(path="/listuploadedfiles")
	public ResponseEntity<HttpMessage> listAllUploadedFiles() throws IOException {
		
		List<Path> files = Files.list(Paths.get(FILESTORAGE_LOCATION)).collect(Collectors.toList());
		if(files == null) {
			return new ResponseEntity<HttpMessage>(new HttpMessage("upload location does not exist"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(files.size() == 0) {
			return new ResponseEntity<HttpMessage>(new HttpMessage("location does not have any files"), HttpStatus.OK);
		}
		return new ResponseEntity<HttpMessage>(new HttpMessage(files.stream().map(path -> path.getFileName()).collect(Collectors.toList()).toString()), HttpStatus.OK);
	}
	
	
	@GetMapping(path="/filecontent/{filename}")
	public ResponseEntity<HttpMessage> getFileContent(@PathVariable("filename") String fileName) throws IOException {
		
		Path path = Paths.get(FILESTORAGE_LOCATION+"/"+fileName);
		

		String fileContentsAsString = new String(Files.readAllBytes(path));
		
		HttpMessage httpMessage = new HttpMessage(fileContentsAsString);
		ResponseEntity<HttpMessage> response = new ResponseEntity<HttpMessage>(httpMessage, HttpStatus.OK);
		
		return response;
	}
	
	
	@GetMapping(path="/filemove/from/{filename}/to/{newfilename}")
	public ResponseEntity<HttpMessage> renameFile(@PathVariable("filename") String fileName, @PathVariable("newfilename") String newFileName) throws IOException {
		
		Path pathfrom = Paths.get(FILESTORAGE_LOCATION+"/"+fileName);
		Path pathTo = Paths.get(FILESTORAGE_LOCATION+"/"+newFileName);


		Path finalPath = Files.move(pathfrom, pathTo, StandardCopyOption.REPLACE_EXISTING);
		
		if(finalPath == null) {
			return new ResponseEntity<HttpMessage>(new HttpMessage("new file location does not exist"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		HttpMessage httpMessage = new HttpMessage("file moved to new path : " + finalPath.toAbsolutePath());
		
		ResponseEntity<HttpMessage> response = new ResponseEntity<HttpMessage>(httpMessage, HttpStatus.OK);
		
		return response;
	}
	
	
	@DeleteMapping(path="/filedelete/{filename}")
	public ResponseEntity<HttpMessage> deleteFile(@PathVariable("filename") String fileName) throws IOException {
		
		Path path = Paths.get(FILESTORAGE_LOCATION+"/"+fileName);
		
		Files.delete(path);
		
		HttpMessage httpMessage = new HttpMessage("file with name " + fileName + " deleted from " + FILESTORAGE_LOCATION);
		ResponseEntity<HttpMessage> response = new ResponseEntity<HttpMessage>(httpMessage , HttpStatus.OK);
		
		return response;
	}
	
	
	
	
}

