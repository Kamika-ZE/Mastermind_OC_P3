# Mastermind_P3_OC

WELCOME TO BRAINWRECKER
This is my More or Less and Mastermind's versions for the third project of OC's courses.

#Presentation :
This application contains two games.

More or Less : Try to find opponent's secret combination. The opponent (or defender) give clues to attacker (or challenger) by answering if secret numbers are greater (+), smaller(-) or if it is the right number(=).

MASTERMIND : Try to find opponent's secret combination. The opponent (or defender) gives clues to attacker (or challenger) by answering if some given numbers are present in the secret code or if some are well placed.

For each game, three different mode are available :

CHALLENGER MODE : the human player try to find the secret code of the computer.

DEFENDER MODE : The computer try to find the human player secret code.

DUAL MODE : The human player fight against the computer. The first to find the opponant's secret code win the game.

#Development:
This application was develop with IntelliJ IDEA 2018 and the java JDK 8 (1.8.0_162).

#Installation and Launch :
Import MasterMind folder from gitHub to your IDE
Install Java JDK 8 (1.8.0_162 version minimum).

Run Configuration required :

The main class is "Main"
Source code is contained in "src/main/java"
Resources are contained in "src/main/resources"

#IMPORTANT INFORMATIONS:
Please add "src/main/resources" folder in your classpath ! 
Application WILL NOT RUN if "src/main/resources" is not added ! 
(This folder contains "config.properties" and "log4j2.xml" files ABSOLUTLY NECESSARY for runing this application).

Config.properties file : You can change games configuration by setting wanted values All informations are given in this file (read comments).

Log4j2.xml file : After each launch of application "logFile.log" file is generated in "./log/" folder This file gives you information about last use of application.

#To launch the game :

Standard mode : clic on the Mastermind.bat

Developer mode : you have 3 options.

Option 1 : run the application with your IDE while passing "modeDev" in argument.
Option 2 : open the application with the Mastermind.bat and in the launcher view, write "modeDev" before pressing ENTER to play.
Option 3 : modify the file config.properties with modeDev = "true".


#Version : 1.0

#Author : M. COZ