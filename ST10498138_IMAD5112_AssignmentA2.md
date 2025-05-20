# IMAD5211 - Assignment A2

GitHub Link:
https://github.com/ST10498138/FlashcardsApp

Youtube Link:
https://youtube.com/shorts/6jpxxxzUiZc?feature=share

## Purpose of App
 The primary purpose of this application is to provide an interactive and engaging tool for users to test and enhance their general knowledge ,
 specifically on history facts . It aims to offer an an effective and accessible way for users to quiz themselves , refresh memory ,
 reinforce their learning through immediate feeback and review concepts they might find challenging . this application serves as a simple yer
 effective educational aid , highly pratical for students or anyone looking to quickly review course material and knowledge reinforecement .

## design considerations
 ### user experience (UX)
 The user experience was deigned to be simple , intuitive and effective . key focuses included "clear",guide navigation(E.g: conditionnal activatio of the next button),
 immediate and constructive feeback for answers and omissions and streamlided learning pathways through the quiz and review features .
 This ensures users can easily engages with the application and efficiently reinforce their knowledge.
 ### user interface (UI)
 The FlashcardsApp application's users interface was designed with simplicity and readability as core principles 
 To ensure precise alignement and consistencymof elements , a linearLayout in vertical orientation was utilized
   clear Layout : an uncluttered screen design to keep the user's focus on question and answer options,
   clear typography : Legible fonts and appropriate text sizes for easy comprehension of all information,
   Easy-to-use-controls : Buttons (submit,Next,Review,Restar,Quit) and radio buttons are cleary labeled and strategically placed for straightforward interaction
   I chose the colors pale purples and sky blues , for their association with concentration and serenity.Providing a unified and calming user experience across all screens.
## code Architecture
  The application's architecture is structured primarily around the Activity class, which serves as the controller in the Model-View-Controller (MVC) pattern.
  Activity Class
    MainActivity:
      The entry point of the application.
      Handles the initiation of the quiz.
    QuestionActivity:
      Manages the display of quiz questions and user interaction.
      Handles user input, answer validation, and navigation to the ScoreActivity.
    ScoreActivity:
      Displays the final score and provides options for reviewing, restarting, or quitting the quiz.
  View Layer
    XML layouts are used to define the user interface for each activity.
      activity_main.xml
      activity_question.xml
      activity_score.xml
    The views are manipulated programmatically within the Activity classes.
  Control Layer
    The Activity classes handle user interactions, update the views, and manage the flow of data between views.
  Data Management
    The application uses intents to pass data between activities.
    Questions and answers are managed within the Activity classes.


## Functionality
 this aplication is an interactive "true or False" flashcard quiz on facts from general history . it allows users to test their knowledge ,
 receive immediate feeback for each flashcard  and view their final score whith the option to review correctly and incorrectly answered questions 


## Functionality and Screenshots
 ### welcome screen 

 ![welcome_screen](<Screenshot 2025-05-19 141512.png>)
  This is the screen you will find upon opening the aplication .You will see a welcome phrase (textView), another textView showing you what you will do
  on this app and a button allowing you to switch to the next screen and start the quiz . 
 ### Activity question

 ![Activity_question1](<Screenshots/Screenshot 2025-05-19 141542.png>)
  On this screen , the various questions will be presented to you.
  You will select your answer using the radio buttons provides. 
  Regarding navigation , it's important to note that the "Next" button is initially disabled (Not cliclable).
  It only becomes active once the user has selected and submitted their answer for the current question.
 ![Activity_question2](<Screenshots/Screenshot 2025-05-19 141614.png>)
  Should the user attempt to submit their answer without having made a selection , a feedback message (textView) will immediately display,
  advising them to choose an option
 ![Activity_question3]
  Once a resonse has been selected and the the "submit" button is cliked , immediate feedback will appear indicating whether the answer is correct or incorrectly
  Concurrently, the "next" button will become accessible , allowing progressionto the next question.
  ![In_the_case_of_a_correct_answer](<Screenshots/Screenshot 2025-05-19 141639.png>)
  ![In_the_case_of_an_incorrect_answer](<Screenshots/Screenshot 2025-05-19 141709.png>)
 ### Activity score
  ![Activity_score1](<Screenshots/Screenshot 2025-05-19 141807.png>)
   After completing the quiz , the user is directed to this screen . Here , a summary of their performance is displayed.
   The user's total score is presented and a personalized appraisal is given based on the user's performance .
   This screen also offers three main action buttons : The Review button , the Restart button and the Quit button
  ![Activity_score2](<Screenshots/Screenshot 2025-05-20 122008.png>)
   The review button : allows the user to view a summary of the questions , distinguishing between those that were answered correctly and those that were not.
   The restart button : allows the user to restart the quiz from the beginning 
   The quit button : this button close the application 
## utilization of GitHub
  This project leveraged git for version control and cithub as the central repository, ensuring effective management and tracking of the application's development.
   ### Git version control
   Git was used to track all modifications to the codebase . Regular commits with descriptive messages documented each deature , fix or change 
   providing a clear history of the application's development.
   ### GitHub repository
   GitHub served as a primary platform for hosting the application's source code , offering a centralized and easly accessible repository
   This simplified shraring the project with my instructor for review and assessment.
   ### Github Actions 
   While github actions offers powerful automation , it was not utilized in this project . The project focused on core application development
   and manual testing.







