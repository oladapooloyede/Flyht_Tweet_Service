#Compilation

The code was compiled with jdk1.8.0_211 (However any Jdk8 version will be fine).

To compile kindly run the following:-

For Windows:- gradlew clean build
For linux :- ./gradlew clean build


#Execution

To execute kindly do the following on the console:-

1) Change directory to Flyht_Tweet_Service/build/libs (i.e cd Flyht_Tweet_Service/build/libs)
2) java -jar tweets-1.0-SNAPSHOT.jar
3) supply the 5 topics
4) Start supplying the messages in the format - #topic message (i.e. #bird I am good).
5) Tweets can be viewed in separate files in ${user.home}/Tweets folder (i.e a file was created in each topic name)

Assumptions for execution:-
a) The topic is expected at the start of each message(i.e. #bird I am good).
b) One topic is expected per message.
c) The ${user.home}/Tweets folder is always recreated at every program startup.


