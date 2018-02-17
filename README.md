# SocialMediaDemo
Sample application to demostrate a simple social media feed.
How to run

Using the run.bat file

Browse to the root directory
Copy a file called user.txt and tweet.txt into the root directory
Click on run.bat

To change the location or names off these file
Open the bat file in any text editor
remove the rem from the option you would like to use and change the names and the lation of the files

Using a Command Widow

Open a command window using the cmd command in Windows menu
change the directory to the location on the file system
ie
cd\socialmedia

To list the options available
java -cp .\target\TwitterSample-0.0.1-SNAPSHOT.jar com.socialmedia.SocialMediaFeed

To run application
java -cp .\target\TwitterSample-0.0.1-SNAPSHOT.jar com.socialmedia.SocialMediaFeed "c:\mydir\user.txt" "c:\mydir\tweet.txt"
This will run the application and load the files from a directory mydir

By default a sorted list of users and their message will be displayed
By added a parameter users to the end of the command the users and their followers will be printed

java -cp .\target\TwitterSample-0.0.1-SNAPSHOT.jar com.socialmedia.SocialMediaFeed "c:\mydir\user.txt" "c:\mydir\tweet.txt" users

Please note that Java 1.8 or later must be available to execute this program.
