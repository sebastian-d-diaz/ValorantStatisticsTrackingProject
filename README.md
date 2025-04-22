# Valorant Esports Tracking Program

## About
Authors: Brian Chhan, Sebastian Diaz, Daniel Zhang

Emails: brian.chhan@ucalgary.ca , sebastian.diaz@ucalgary.ca , daniel.zhang2@ucalgary.ca

Version: 3.0

Description: a tracking program designed to add and track statistics of players participating in the Pro valorant scene. Allows the tracking of individual statistics, as well as information regarding players and teams.

## Run this program

Use jar file or build source code yourself

Uses Java 23.0.1 JDK, JavaFX 23.0.2 SDK, JUnit 5.8.1

If you installed JDK windows your execution would look like this for the jar file

`java --module-path "PATHTOJAVAFX" --add-modules javafx.controls,javafx.fxml -jar CPSC233W25A3.jar`

where "PATHTOJAVAFX" is replaced by your path to the `lib` folder in your JavaFX SDK.
## Class Information
Data: 

Contains most data related to players, person, or team. Has functions that store specific inputs to a specific person,player,team object. Has functions that display the prior info to the user. Also has functions that allow a user to display specific players with a specific filter (such as players over the age of 25, or players that are American)

Main (Console display): 

a class that allows the user to use the data tracker via the output console of the IDE (Programs runs purely with text responses from the user)

Menu:

Contains displayed text via output console that is visible to the user. Displays different input/output options depending on user's choice in the console. 

Player:

Contains helper functions to get specific player related stats. Contains setter funcitons used in data to change fields of the player object. Contains functions to help display info of the specific player to user and to save info.

Person:

Contains helper functions to get specific person related info. Contains functions to help display info of the specific person to user and to save info.

Team:

Contains helper functions to get specific team related info. Contains functions to help save info.

Nationality:

Enums used to define all valid nationalities a player can be
Contains getter functions used to find the nationality of a player

Main (GUI display):

a class that allows the user to use the data tracker via GUI window (various buttons, inputs, and options to display/add certain data)

Player...Comparator (ADR/ACS/Kills/Assists/Deaths):

classes that cointain functions that compare ADR/ACS/Kills/Assists/Deaths of two players to find which player has a higher stat

Add...Controllers (ADR/ACS/Kills/Assists/Deaths):

classes that allow users to input the ADR/ACS/Kills/Assists/Deaths of a player in a popup window

FormTeamController:

class that forms and stores a team with 5 players based on user entered players into the database

GetOverAgeController:

a class that finds and displays all the players who are over a certain age entered by the user

HighestStatsController:

a class that finds and displays the player with the highest stat, chosen by the user

MainController:

a class that contains and displays all GUI elements (Buttons, text areas, etc.) that allows the user to access specific input/output options; links functions to buttons and items in the GUI window.

Reader:

a class that saves player,team, and person info from database into csv format.
Also allows user to load players, teams, and people from an existing csv save file.