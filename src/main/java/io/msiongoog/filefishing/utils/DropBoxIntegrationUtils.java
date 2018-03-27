package io.msiongoog.filefishing.utils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.stereotype.Component;

import com.dropbox.core.DbxApiException;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.util.StringUtil;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderErrorException;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;
import com.google.common.io.Files;

@Component
public class DropBoxIntegrationUtils {
	
	
	public DropBoxIntegrationUtils() throws DbxApiException, DbxException {
		generateToken();
		dropBoxConnection();
	}
	private String apiKey;
	private String apiSecret;
	 private DbxClientV2 client;
	
	private String accessToken;
	
	public void generateToken() {
		accessToken = HiddenConstants.dropboxAppAccesstoken;
		apiKey = HiddenConstants.dropboxAppKey;
		apiSecret = HiddenConstants.dropboxAppSecret;
		
		//to remove warning, does nothing
		StringUtil.base64Encode(new StringBuilder().append(apiKey.getBytes()).append(apiSecret.getBytes()).toString().getBytes());
		
	}
	
	public void dropBoxConnection() throws DbxApiException, DbxException {
		 DbxRequestConfig config = DbxRequestConfig.newBuilder("filefishing").build();
		 client = new DbxClientV2(config, accessToken);
	        
	     // Get current account info
	        FullAccount account = client.users().getCurrentAccount();
	        System.out.println(account.getName().getDisplayName());
	      
	}
	
	public String dropBoxFileListing() throws ListFolderErrorException, DbxException {
		dropBoxConnection();
		StringBuilder sb = new StringBuilder();
        ListFolderResult result = client.files().listFolder("");
        while (true) {
            for (Metadata metadata : result.getEntries()) {
                sb.append(metadata.getPathLower()).append("  ");
            }

            if (!result.getHasMore()) {
                break;
            }

            result = client.files().listFolderContinue(result.getCursor());
        }
        return sb.toString();
	}

	// TODO incomplete
	public Object fetchFileContent(String fileName) throws ListFolderErrorException, DbxException, IOException {
		ListFolderResult folderContents = client.files().listFolder("");
		FileMetadata fileMetaData;
		OutputStream ous = null;
		while(true) {
			
			for(Metadata metadata : folderContents.getEntries()) {
				if(metadata.getName().equals(fileName)) {
					 fileMetaData = client.files().download(metadata.getPathLower()).download(ous);
					break;
				}
			}
			break;
		}
		
		return null;
}
	
	
	
	
}
