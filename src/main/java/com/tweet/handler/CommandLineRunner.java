package com.tweet.handler;

import com.tweet.handler.data.Topic;
import com.tweet.handler.service.TwitterMessageProcessor;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

public class CommandLineRunner {

    private static final Logger logger = Logger.getLogger(CommandLineRunner.class.getName());
    private static String STARTUP_MESSAGE_TEMPLATE = "Kindly Supply Topic %d :";

    public static void main (String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        for (int i=0; i < Topic.getMaxSize(); i++) {
            System.out.print(String.format(STARTUP_MESSAGE_TEMPLATE, i+1));
            Topic.add(scanner.next());
        }

        //Start Streaming
        TwitterMessageProcessor.init();
    }
}
