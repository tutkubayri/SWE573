**COMIN**

This repository is the repository of SWE573 project.

To start the project in local, these steps should be taken:
1. Install IntelliJ Ultimate from https://www.jetbrains.com/idea/download/#section=windows
2. Install Visual Studio Code from https://code.visualstudio.com/
3. Install Postgresql 12 from https://www.postgresql.org/download/
	The username that wil be used in backend project is “postgres” and password is “4630”.
  
After installing these programs, first step is to stop the ports that localhost:8080 and localhost:4200 listen. 
These steps should be followed:
1. Write **netstat -ano | findstr :8080** in the Command Prompt and run.
2. There will be a port number next to **Listening:** title. Write **taskkill /PID typeyourPortNumberHere /F** in the Commant Prompt and run.

	Next, open the backend project. To do this, download the rar extentioned file of the project, export it and import the file called 
  “comin” to the IntelliJ. Then, click the Run ‘CominApplication’ button.
	
  To open the frontend project, open Visual Studio Code and import the project folder called “comin-client”. To start the project, 
  open new terminal from the menü above and write “ng serve –open” to the terminal. After this process, the project will be opened 
  in browser.
	
  To add a community called "Science Community", post types and form areas that are belong to this community to local database, 
  run the queries below one by one in Postgresql 12.
  
* INSERT INTO community (name, description, semantic_tag, selected_tags, banner_url)
VALUES ("ScienceCommunity", "Community for science lovers", "science", "[Q336,science]", 
"https://ichef.bbci.co.uk/childrens-responsive-ichef-live/r/640/1x/cbbc/science-onward-journey_v3.png");
* INSERT INTO post_types (name, usage, community_id, selected_tags, semantic_tag) VALUES 
("Famous Scientists", "Information about famous scientists", 1,  "[Q901,scientist]", "scientist")
* INSERT INTO form_area(data_type, label, requirement, post_type_id) VALUES ("text", "Name", "true",1)
* INSERT INTO form_area(data_type, label, requirement, post_type_id) VALUES ("enum", "Areas", "true",1)
* INSERT INTO enum(value, form_area_id) VALUES ("physics;biology;chemistry;astronomy", 2)
