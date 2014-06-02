package Preprocess;


import Feed.engInputFeed;
import cmu.arktweetnlp.Tagger;
import cmu.arktweetnlp.Twokenize;
import graph.Configuration;
import graph.NTweet;
import graph.RunTaggerExtended;
import graph.constants.Constants;
import graph.constants.OOVFunction;

import java.util.ArrayList;

import static cmu.arktweetnlp.RunTagger.wordsInCluster;
import static cmu.arktweetnlp.Tagger.TaggedToken;
import static graph.NTweet.TToken;

/**
 * Created by cagil on 26/05/14.
 */
public class engPreprocessor implements Preprocessor {
    private final engInputFeed feed;
    private final Configuration conf;
    RunTaggerExtended runTagger;

    public engPreprocessor(final engInputFeed feed, final Configuration conf) {
        this.feed = feed;
        this.conf = conf;
    }

    public void processMulti() throws Exception {
        Twokenize tokenizer = new Twokenize();
        Tagger tagger = new Tagger();
        tagger.loadModel(Constants.modelFilename);
        runTagger = new RunTaggerExtended();

//        if(conf.isJustTokenize())
//            runTagger.justTokenize = true;
        for (NTweet tweet : feed.getTweets()) {
            //runTagger.runTagger(tweet);
            ArrayList<TaggedToken> tTokens = (ArrayList)tagger.tokenizeAndTag(tweet.getText());
            //String tweetText_normalized = Twokenize.normalizeTextForTagger(tweet.getText());
            //        if(conf.isJustTokenize())
            //List<String> tweetText_tokenized = Twokenize.tokenizeRawTweetText(tweet.getText());
            tweet.setTaggedTokens(tTokens);
            detectOOV(tweet);

        }


    }

    public void detectOOV(NTweet tweet) throws Exception {
        OOVFunction func = new OOVFunction(tweet.getText(),"", null);
        ArrayList<TToken> tokens = tweet.gettTokens();
        TToken token;
        for (int i = 0; i < tokens.size(); i++) {
            token = tokens.get(i);
            func.setToken(token.token);
            token.setOOV(func.call());
            if(token.isOOV()) {
                tweet.OOV += 1;
                tweet.OOVTokens.add(i);
            }
            System.out.println(token.token+" "+token.tag+" "+(token.isOOV() ? "OOV" : "IV")+" "+wordsInCluster().contains(token)
            );
        }
    }


}
