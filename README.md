# Twitter-app

###Pre-requisites:
Install Docker:
* For Windows : https://github.com/docker/toolbox/releases
* For Linux : https://docs.docker.com/engine/install/
	
### Running the application:
For Windows:
    ```run_twitter_interview_app.bat```
    
For linux run this command:
    ```./run_twitter_interview_app.sh```
    
All the api's are packaged into postman-collection json file, can access it inside postman-collection folder.


###Supported API's:
* GET http://localhost:8080/user/login?username=testuser@123&pwd=test
    * This API is used to login the user
    * Returns token which will be required to access all other APIs
    * No other APIs can be accessed without this token.
    
* GET http://localhost:8080/user/logout?username=testuser@123
    * This API is used to logout the user.
    
* POST http://localhost:8080/user/find
    * This API is used to find user by passing the user handle into the json payload.
    * Sample payload:
    
    ```
  {
  	"user_name" : "testuser@123",
  	"token":"dac255c1-6d38-4786-acc2-7f46bb901e8c",
  	"find_user_handle" : "testuser001"
  }
  ```
* POST http://localhost:8080/user/follow
    * This API is used to follow user.
    * Sample Payload:
    
    ```
  {
  	"user_name" : "testuser@123",
  	"token":"dac255c1-6d38-4786-acc2-7f46bb901e8c",
  	"follow_user_id" : "testuser002"
  }
  ```
* POST http://localhost:8080/posts/like
    * This API is used to like any post
    * Sample Payload:
    ```
  {
  	"user_name" : "testuser@123",
  	"token":"dac255c1-6d38-4786-acc2-7f46bb901e8c",
  	"post_id" : "4"
  }
  ```
* POST http://localhost:8080/posts/get
    * This API is used to list all posts by passing a user handle in the payload
    * Sample Payload:
    ```
  {
  	"user_name" : "testuser@123",
  	"token":"8d356a49-e126-4a58-af2a-1a762d6ab410",
  	"list_user_name" : "testuser@123"
  }
  ```
 * POST http://localhost:8080/posts/create
    * This API is used to create a new post, the post created should be having content not more than 140 characters
    * Sample Request:
    ```
   {
   	"user_name" : "testuser@123",
   	"token":"8d356a49-e126-4a58-af2a-1a762d6ab410",
   	"post_content" : "First post by testuser@123!!!"
   }
   ```
    
    
###Notes for this project:
1. 3 users are added for this project with username, password as following:

       | Username     |    Password  | 
       
       | testuser001  |   test@1     |
       | testuser002  |   test@2     |
       | testuser@123 |   test       |
       
 * All the users are logged out.
 * No API to add new user, can be only added by accessing the database.
       
2. No post data is there.

3. No post like data is there.

4. No user follow data is there.