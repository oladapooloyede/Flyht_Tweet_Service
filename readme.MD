#Compilation

The code was compiled with jdk1.8.0_211 (However any Jdk8 version will be fine).

To compile kindly run the following:-

For Windows:- gradlew clean build
For linux :- ./gradlew clean build


#Execution

To execute kindly do the following on the console:-

1) change directory to Flyht_Tweet_Service/build/libs (i.e cd Flyht_Tweet_Service/build/libs)
2) java -jar tweets-1.0-SNAPSHOT.jar
3) supply the 5 topics
4) thn start supplying the messages in the format - #topic message (i.e. #bird I am good).
Assumptions:-
a) The topic is expected at the start of each message.
b) One topic is expected per message.


