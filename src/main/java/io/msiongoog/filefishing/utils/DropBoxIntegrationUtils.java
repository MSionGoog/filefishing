package io.msiongoog.filefishing.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dropbox.core.DbxApiException;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderErrorException;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;

@Component
public class DropBoxIntegrationUtils {
	
	
	public DropBoxIntegrationUtils() throws DbxApiException, DbxException {
		generateToken();
		TestDropBoxConnection();
	}
	private String apiKey;
	private String apiSecret;
	 private DbxClientV2 client;
	
	private String accessToken;
	
	public void generateToken() {
		accessToken = HiddenConstants.dropboxAppAccesstoken;
		apiKey = HiddenConstants.dropboxAppKey;
		apiSecret = HiddenConstants.dropboxAppSecret;
		
	}
	
	public void TestDropBoxConnection() throws DbxApiException, DbxException {
		 DbxRequestConfig config = DbxRequestConfig.newBuilder("filefishing").build();
		 client = new DbxClientV2(config, accessToken);
	        
	     // Get current account info
	        FullAccount account = client.users().getCurrentAccount();
	        System.out.println(account.getName().getDisplayName());
	      
	}
	
	public String TestDropBoxFileListing() throws ListFolderErrorException, DbxException {
		TestDropBoxConnection();
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
	
	
}
