package Feed;

import Preprocess.engPreprocessor;
import graph.Configuration;
import graph.NTweet;
import graph.NormalizeTweetThread;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cagil on 26/05/14.
 */
public class engInputFeed implements Feed {
    final private Configuration conf;
    private ArrayList<NTweet> tweets;

    public engInputFeed(final Configuration conf) {
        this.conf = conf;
        tweets = new ArrayList<NTweet>();
    }

    public engInputFeed(Configuration conf, String text) {
        this.conf = conf;
        tweets = new ArrayList<NTweet>();
        process(text);
    }

    public void process(String tweet) {
        tweets.add(new NTweet(tweet));
    }

    public ArrayList<NTweet> getTweets() {
        return tweets;
    }

    public void setTweets(ArrayList<NTweet> tweets) {
        this.tweets = tweets;
    }

    @Override
    public void normalize() throws Exception {
        engPreprocessor pre = new engPreprocessor(this, conf);
        pre.processMulti();

        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (NTweet tweet : tweets) {
            NormalizeTweetThread normalizer = new NormalizeTweetThread("1", conf, this, tweet);
            //normalizer.start();
            executor.execute(normalizer);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
    }


    @Override
    public String toString() {
        StringBuffer string = new StringBuffer();
        for (NTweet tweet : tweets) {
            string.append(tweet.getText() + " | " + tweet.getLastNormalizedText() + "\n");
        }
        return string.toString();
    }


}
