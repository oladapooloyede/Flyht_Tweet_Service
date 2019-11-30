package com.tweet.handler;

import com.tweet.handler.data.Topic;
import com.tweet.handler.service.MessageProcessor;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

public class CommandLineRunner {

    private static final Logger logger = Logger.getLogger(MessageProcessor.class.getName());
    private static String STARTUP_MESSAGE_TEMPLATE = "Kindly Supply Topic %d :";
    private static final String TWEET_MESSAGE_TEMPLATE = "Kindly Supply message in format - #topic message :";
    private static final String TERMINATION_STRING = "END";

    public static void main (String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        for (int i=0; i < Topic.getMaxSize(); i++) {
            System.out.print(String.format(STARTUP_MESSAGE_TEMPLATE, i+1));
            Topic.add(scanner.next());
        }

        //Create Tweet Files
        MessageProcessor.init();

        String message;
        while(!(message = scanner.nextLine()).equalsIgnoreCase(TERMINATION_STRING)) {
            try {
                MessageProcessor.saveToFile(message);
                System.out.println(TWEET_MESSAGE_TEMPLATE);
            } catch (IOException ex) {
                logger.severe(String.format("Error saving to file with message -  {}", ex.getMessage()));
            }
        }

    }
}
