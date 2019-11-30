package com.tweet.handler.service;

import com.tweet.handler.data.Topic;
import com.tweet.handler.data.TweetFile;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageProcessor {

    private static final Logger logger = Logger.getLogger(MessageProcessor.class.getName());
    private static final Pattern TOPIC_HASHTAG_FORMAT = Pattern.compile("#(\\S+)");

    /**
     * The method creates files for the topics.
     */
    public static void init() throws IOException {
        TweetFile.init();
        Topic.getTopics().stream().forEach(topic -> createFile((String)topic));
    }

    /**
     * Saves tweet to topic file.
     *
     * @param message - supplied console message.
     * @throws IOException
     */
    public static void saveToFile(String message) throws IOException {
        String topic = parseTopic(message);

        if (topic != null) {
            String info = parseMessage(message, topic);
            TweetFile file = new TweetFile(topic);
            file.write(info);
        }
    }

    /**
     * This method creates different files per tweet.
     *
     * @param fileName - which is topic name in lowercase.
     */
    private static void createFile(String fileName) {
        try {
            TweetFile file = new TweetFile(fileName);
            file.create();
        } catch (Exception ex) {
            logger.severe(String.format("Error Message - %s while creating file %s", ex.getMessage(), fileName));
            System.exit(1);
        }

    }

    /**
     * This parses the topic from a message.
     * @param message - message and topic(i.e. #topic)
     * @return
     */
    private static String parseTopic(String message) {
        Matcher matcher = TOPIC_HASHTAG_FORMAT.matcher(message);
        String topic = null;

        if (matcher.find()) {
            topic = matcher.group(1);

            if (!Topic.getTopics().contains(topic.toLowerCase()))
            {
                logger.severe(String.format("Topic %s not found", topic));
                return null;
            }
        }

        return topic;
    }

    /**
     * This method will parse the info from the message
     * @param message - the message passed with the topic
     * @param topic - parsed topic
     * @return info
     */
    private static String parseMessage(String message, String topic) {
        return message.substring(topic.length()+2);
    }
}
