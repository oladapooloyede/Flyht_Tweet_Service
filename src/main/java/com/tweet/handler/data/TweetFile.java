package com.tweet.handler.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

public class TweetFile {
    private static final String HOME_DIR = System.getProperty("user.home");
    private static final String DIR_NAME = "Tweets";

    private final String name;

    public static void init() throws IOException {
        Path dirPath = java.nio.file.Paths.get(HOME_DIR, DIR_NAME);
        //Deletes Directory - Tweets
        if(Files.isDirectory(dirPath)) {
            Files.walk(dirPath)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
        Files.createDirectory(java.nio.file.Paths.get(HOME_DIR, DIR_NAME));
    }

    public TweetFile(String name) {
        this.name = java.nio.file.Paths.get(HOME_DIR, DIR_NAME, name).toString();
    }

    public boolean create() throws IOException {
        File file = new File(this.name);
        return file.createNewFile();
    }

    public void write(String message) throws IOException {
        FileWriter fw = new FileWriter(this.name, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(message);
        bw.newLine();
        bw.close();
    }
}
