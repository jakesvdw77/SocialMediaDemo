echo off
java -cp .\target\TwitterSample-0.0.1-SNAPSHOT.jar com.socialmedia.SocialMediaFeed user.txt tweet.txt
rem java -cp .\target\TwitterSample-0.0.1-SNAPSHOT.jar com.socialmedia.SocialMediaFeed user.txt tweet.txt users

rem Uncomment the option that you like to run
rem To show all the default options
rem java -cp .\target\TwitterSample-0.0.1-SNAPSHOT.jar com.socialmedia.SocialMediaFeed	
 
rem To show the messages from current directory
rem java -cp .\target\TwitterSample-0.0.1-SNAPSHOT.jar com.socialmedia.SocialMediaFeed user.txt tweet.txt


rem To show the messages from other directory
rem java -cp .\target\TwitterSample-0.0.1-SNAPSHOT.jar com.socialmedia.SocialMediaFeed "c:\mydir\user.txt" "c:\mydir\tweet.txt"
rem java -cp .\target\TwitterSample-0.0.1-SNAPSHOT.jar com.socialmedia.SocialMediaFeed "c:\mydir\user.txt" "c:\mydir\tweet.txt" users
rem java -cp .\target\TwitterSample-0.0.1-SNAPSHOT.jar com.socialmedia.SocialMediaFeed "c:\mydir\user.txt" "c:\mydir\tweet.txt" messages
rem java -cp .\target\TwitterSample-0.0.1-SNAPSHOT.jar com.socialmedia.SocialMediaFeed "c:\mydir\user.txt" "c:\mydir\tweet.txt" messages twitter

pause