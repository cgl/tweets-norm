package Extraction;

import graph.Configuration;
import graph.NTweet;

import static graph.NTweet.TToken;

/**
 * Created by cagil on 30/05/14.
 */
public class engExtractor {
    Configuration conf;
    NTweet tweet;
    private TToken OOVToken;

    public engExtractor(NTweet tweet, Configuration conf, int tokenInd) {
        this.conf = conf;
        this.tweet = tweet;
        this.OOVToken = tweet.gettTokens().get(tokenInd);
    }

    public void process() {
        extractUsingSlang();

    }

    public void extractUsingGraph(){

    }

    public void extractUsingSlang(){
        if(conf.getSlangDict().containsKey(OOVToken.token)) {
            OOVToken.addCandidate(conf.getSlangDict().get(OOVToken.token));
        }

    }

    public void extractUsingLexicalElements(){

    }

}
