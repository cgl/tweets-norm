import Feed.engInputFeed;
import cmu.arktweetnlp.io.JsonTweetReader;
import cmu.arktweetnlp.util.BasicFileIO;
import graph.Configuration;
import graph.constants.Constants;

import java.io.*;

/**
 * Created by cagil on 27/05/14.
 */


public class NormalizeEng {
    public static String inputFormat = "auto";

    public static void main(String args[]) throws Exception {
        Configuration conf = new Configuration();

        processFile(Constants.tweets_text,conf);
        //processSingle(conf);
    }

    public static void processSingle(Configuration conf) throws Exception {
        engInputFeed feed = new engInputFeed(conf);
        feed.process("This is a tweet which has sme mstakes");
        String sentence = "A sentence " + "with a error in the Hitchhiker's Guide tot he Galaxy";
        feed.process(sentence);
        feed.normalize();
        System.out.println(feed.toString());
        //NormalizeEngThread normalizer = new NormalizeEngThread("1", conf, feed);
    }

    public static void processFile(String inputFilename, Configuration conf) throws Exception {
        engInputFeed feed;
        int inputField = 1;
        JsonTweetReader jsonTweetReader = new JsonTweetReader();
        LineNumberReader reader = new LineNumberReader(BasicFileIO.openFileToReadUTF8(inputFilename));
        String line;
        int lineCount = 0;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\t");
            String tweetData = parts[inputField - 1];

            if (reader.getLineNumber() == 1) {
                if (inputFormat.equals("auto")) {
                    detectAndSetInputFormat(tweetData);
                }
            }
            String text;
            if (inputFormat.equals("json")) {
                text = jsonTweetReader.getText(tweetData);
                if (text == null) {
                    System.err.println("Warning, null text (JSON parse error?), using blank string instead");
                    text = "";
                }
            } else {
                text = tweetData;
            }
            feed = new engInputFeed(conf, text);
            feed.normalize();
            System.out.println(feed.toString());
            //NormalizeEngThread normalizer = new NormalizeEngThread(String.valueOf(lineCount++),conf,feed);
        }
    }

    public static void detectAndSetInputFormat(String tweetData) throws IOException {
        JsonTweetReader jsonTweetReader = new JsonTweetReader();
        if (jsonTweetReader.isJson(tweetData)) {
            System.err.println("Detected JSON input format");
            inputFormat = "json";
        } else {
            System.err.println("Detected text input format");
            inputFormat = "text";
        }
    }

}