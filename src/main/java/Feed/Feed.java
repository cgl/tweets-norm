package Feed;

import graph.Configuration;
import graph.NTweet;

import java.util.ArrayList;

/**
 * Created by cagil on 26/05/14.
 */
public interface Feed {
    Configuration conf = null;
    ArrayList<NTweet> tweets = null;
    ArrayList<String> tweet_texts = null;

    void normalize();
}
