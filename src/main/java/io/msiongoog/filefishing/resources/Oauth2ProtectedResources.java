package io.msiongoog.filefishing.resources;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import io.msiongoog.filefishing.domains.HttpMessage;
import io.msiongoog.filefishing.security.Oauth2ResourceServer;

@RestController
@RequestMapping(path=Oauth2ResourceServer.OAUTH2PROTECTED)
public class Oauth2ProtectedResources {
	
	
	private static final String apiUrl = "https://geocoding.geo.census.gov/geocoder/locations/onelineaddress"
			+ "?"
			+ "address=701%20N%208th%20Street,%20Philadelphia,%20PA%2019123"
			+ "&"
			+ "benchmark=9"
			+ "&"
			+ "format=json";
	
	
	
	@PostMapping(path="/addressltlng")
	public ResponseEntity<HttpMessage> getLatLngOfAddress() throws RestClientException, URISyntaxException {
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(new URI(apiUrl), HttpMethod.GET	, new RequestEntity<>(HttpMethod.GET, new URI(apiUrl)),String.class);
		
		String responseString = new String(response.getBody());
		
		return new ResponseEntity<HttpMessage>(new HttpMessage(responseString), HttpStatus.OK);
		
		
	}

}
