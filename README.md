Notes on v0.4
v0.4 has the following endpoints
look for tag oauth2

(A)-- ${hostname}:${port}/protected/user
(B)-- ${hostname}:${port}/protected/admin
(C)-- ${hostname}:${port}/oauth2protected/addressltlng

There are no open Urls.

(A) and (B) are protected by basic authentication protocol


(A)
username : sooraj
password : password

sample request:
curl -u sooraj:password http://localhost:8080/protected/user


(B)
username : arvind
password : password

sample request:
curl -u arvind:password http://localhost:8080/protected/admin

(C) is protected by oauth2 protocol

oauth2 - client credentials grant type has been implemented.

There are two steps to make an authorized request -
  first, request for an access token
  second, make an authorized request using the access token

clientID: oauth2shyam
secret: oauth2password

sample request:
use postman
-
type in the URL :  
1. ${hostname}:${port}/oauth2protected/addressltlng
2. change method to POST
3. In Authorization tab, change type to Oauth2
4. Click Get New Access token
5. Give the token a name for identification
6. auth URL is : http://${hostname}:${port}/oauth2protected/addressltlng
7. access token url is : http://${hostname}:${port}/oauth/token
8. clientid provided above
9. secret provided above
10. Scope, can be set to Read
11. Grant type is client credentials
12. Request access token locally is checked, but it's significance unknown.
13. Click on request token.
14. select the token on the left
15. click on use token on the right
16. click on send and the response will appear if everything is setup as required.
