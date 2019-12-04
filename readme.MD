#Pre-compilation
Kindly add Twitter credentials to TwitterMessageProcessor#authentication method.

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
4) Tweets can be viewed in separate files in ${user.home}/Tweets folder (i.e a file is created in each topic name)

Assumptions for execution:-
a) The ${user.home}/Tweets folder is always recreated at every program startup.


