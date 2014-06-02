package graph;

import Evaluation.engEvaluator;
import Extraction.engExtractor;
import Feed.engInputFeed;
import Rank.engRanker;

/**
 * Created by cagil on 30/05/14.
 */
public class NormalizeTweetThread implements Runnable{
    private Thread t;
    private String threadName;
    private Configuration conf ;
    private engInputFeed feed ;
    private NTweet tweet;

    public NormalizeTweetThread(String name, Configuration conf, engInputFeed feed, NTweet tweet){
        threadName = name;
        this.conf = conf;
        this.feed = feed;
        this.tweet = tweet;
        System.out.println("Creating " +  threadName );
    }
    public void run()  {
        normalizeTweet(tweet);
    }

    private void normalizeTweet(NTweet tweet) {
        for (int i = 0; i < tweet.OOV ; i++) {
            engExtractor ext = new engExtractor(tweet,conf, i);
            ext.process();
            engEvaluator evaluator = new engEvaluator(tweet,conf);
            evaluator.process();
            engRanker ranker = new engRanker(tweet,conf);
            ranker.process();
        }
    }

    public void start ()
    {
        System.out.println("Starting " +  threadName );
        if (t == null)
        {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}
