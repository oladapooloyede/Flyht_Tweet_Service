package com.tweet.handler.data;

import java.util.HashSet;
import java.util.Set;

public class Topic {

    private static final Set<String> TOPIC_SET = new HashSet<>();
    private static final int MAX_TOPIC_SIZE = 5;

    public static void add(String message) {
        TOPIC_SET.add(message.toLowerCase());
    }

    public static Set getTopics() {
        return TOPIC_SET;
    }

    public static int getMaxSize() {
        return MAX_TOPIC_SIZE;
    }
}
