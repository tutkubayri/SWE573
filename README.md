**COMIN**

This repository is the repository of SWE573 project.

To start the project in local, these steps should be taken:
1. Install IntelliJ Ultimate from https://www.jetbrains.com/idea/download/#section=windows
2. Install Visual Studio Code from https://code.visualstudio.com/
3. Install Postgresql 12 from https://www.postgresql.org/download/
! The username that wil be used in backend project is “postgres” and password is “4630”.
  
After installing these programs, first step is to stop the ports that localhost:8080 and localhost:4200 listen. 
These steps should be followed:
1.Write **netstat -ano | findstr :8080** in the Command Prompt and run.
2.There will be a port number next to **Listening:** title. Write **taskkill /PID typeyourPortNumberHere /F** in the Commant Prompt and run.

Next, open the backend project. To do this, follow these steps:
* Download the rar extentioned file of the project 
* Create new project in IntelliJ with Java Version 11.0.3, group as "com.swe", artifact as "comin", type as Maven Project, Language as Java, packaging as Jar, name as "comin" and description as "SWE573 Comin Project".
* Add Spring Boot DevTools, Lombok, Spring Configuration Processor, Spring Web and PostgreSQL Driver as selected dependencies.
* Copy all the folders in SWE573-Software-Development-Practice\comin\src\main\java\com\swe\comin and paste them in the same path in your folder. A warning will appear about having two files with same name. Change the existing file with the new one.
* Copy pom.xml in the rar extentioned file and paste it in the same place in your folder.
* Copy application.properties in the rar extentioned file and paste it in the same place in your folder.
* Copy the files in text folder in the rar extentioned file and paste it in your test folder.

! If you get a warning like "The method's class, javax.persistence.JoinColumn, is available from the following locations: jar:file:/C:/Users/Tutku%20BAYRI/Desktop/Yeni%20klas%c3%b6r/lib/javax.persistence.jar!/javax/persistence/JoinColumn.class jar:file:/C:/Users/Tutku%20BAYRI/.m2/repository/javax/persistence/javax.persistence-api/2.2/javax.persistence-api-2.2.jar!/javax/persistence/JoinColumn.class It was loaded from the following location: file:/C:/Users/Tutku%20BAYRI/Desktop/Yeni%20klas%c3%b6r/lib/javax.persistence.jar" when you try to start the project, go to the lib folder in your project folder and delete javax.persistence.jar file.
	
  To open the frontend project, 
  * Open Visual Studio Code and import the project folder called “comin-client”. 
  * Copy folders in node_modules.rar file in your frontend project folder. 
  * Open new terminal from the menu above and write “ng serve –open” to the terminal. 
  After these steps are taken, the project will be opened in browser.
	
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
