package Feed;

import Evaluation.engEvaluator;
import Extraction.engExtractor;
import Preprocess.engPreprocessor;
import Rank.engRanker;
import graph.Configuration;
import graph.NTweet;

import java.util.ArrayList;

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
    public void normalize() {
        engPreprocessor pre = new engPreprocessor(this, conf);
        try {
            pre.processMulti();

        } catch (Exception e) {
            e.printStackTrace();
        }
        for (NTweet tweet : tweets) {
            normalizeTweet(tweet);
        }

    }

    private void normalizeTweet(NTweet tweet) {
        for (int i = 0; i < tweet.OOV ; i++) {
            engExtractor ext = new engExtractor(this,conf);
            ext.process();
            engEvaluator evaluator = new engEvaluator(this,conf);
            evaluator.process();
            engRanker ranker = new engRanker(this,conf);
            ranker.process();

        }
    }

    @Override
    public String toString() {
        StringBuffer string = new StringBuffer();
        for (NTweet tweet : tweets) {
            string.append(tweet.getText() + " | " + tweet.getLastNormalizedText() + "\n");
        }
        return  string.toString();
    }


}
