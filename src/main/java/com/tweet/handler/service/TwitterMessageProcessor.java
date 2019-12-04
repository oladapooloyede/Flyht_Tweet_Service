package com.tweet.handler.service;

import com.tweet.handler.data.Topic;
import com.tweet.handler.data.TweetFile;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.util.Set;
import java.util.logging.Logger;

public class TwitterMessageProcessor {

    private static final Logger logger = Logger.getLogger(TwitterMessageProcessor.class.getName());
    private static TwitterStream twitterStream;

    /**
     * The setup method.
     */
    public static void init() throws IOException {
        TweetFile.init();
        Topic.getTopics().stream().forEach(topic -> createFile((String)topic));
        twitterStream = new TwitterStreamFactory(authentication().build()).getInstance();
        start();
    }

    /**
     * Start reading tweets.
     */
    private static void start() {
        addCallback();
        twitterStream.filter(addQuery());
    }

    /**
     * The method authenticates with twitter dev account.
     */
    private static ConfigurationBuilder authentication() {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();

        return configurationBuilder.setOAuthConsumerKey("_consumerKey")
                .setOAuthConsumerSecret("_consumerSecret")
                .setOAuthAccessToken("_accessToken")
                .setOAuthAccessTokenSecret("_accessTokenSecret");
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
     * Tweets search query.
     */
    private static FilterQuery addQuery() {
        FilterQuery tweetFilterQuery = new FilterQuery();
        Set<String> topics = Topic.getTopics();
        tweetFilterQuery.track(topics.toArray(new String[0]));
        tweetFilterQuery.language(new String[]{"en"}); // Note that language does not work properly on Norwegian tweets

        return tweetFilterQuery;
    }

    /**
     * Listener for tweets.
     */
    private static void addCallback() {
        twitterStream.addListener(new StatusListener() {
            public void onStatus(Status status) {
                try {
                    for(HashtagEntity hashTag: status.getHashtagEntities()) {
                        if (!Topic.getTopics().contains(hashTag.getText()))
                        {
                            continue;
                        }
                        TweetFile file = new TweetFile(hashTag.getText());
                        file.write(status.getText()); // save to file
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                // No Implementation
            }

            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                // No Implementation
            }

            public void onScrubGeo(long userId, long upToStatusId) {
                // No Implementation
            }

            public void onStallWarning(StallWarning warning) {
                // No Implementation
            }

            public void onException(Exception ex) {
                logger.severe(String.format("Twitter listener exception - %s ", ex.getMessage()));
            }
        });
    }


}
