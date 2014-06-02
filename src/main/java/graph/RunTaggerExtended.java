package graph;

import cmu.arktweetnlp.RunTagger;
import cmu.arktweetnlp.Tagger;
import cmu.arktweetnlp.Twokenize;
import cmu.arktweetnlp.impl.ModelSentence;
import cmu.arktweetnlp.impl.Sentence;
import graph.constants.Constants;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by cagil on 30/05/14.
 */
public class RunTaggerExtended extends RunTagger {
    private Tagger tagger;
    public boolean showConfidence = true;


    public RunTaggerExtended() throws UnsupportedEncodingException {
        super();

    }

    public void runTagger(NTweet tweet) throws IOException, ClassNotFoundException {
        System.out.println("Hello runtagger()");

        this.tagger = new Tagger();
        if (!justTokenize) {
            tagger.loadModel(Constants.modelFilename);
        }

        String text = tweet.getText();

        Sentence sentence = new Sentence();

        sentence.tokens = Twokenize.tokenizeRawTweetText(text);
        ModelSentence modelSentence = null;

        if (sentence.T() > 0 && !justTokenize) {
            modelSentence = new ModelSentence(sentence.T());
            tagger.featureExtractor.computeFeatures(sentence, modelSentence);
            this.goDecode(modelSentence);
        }
        System.out.println(sentence.T());
        System.out.println(sentence.tokens);
        System.out.println(sentence.labels);
        System.out.println(Twokenize.normalizeTextForTagger(text));




    }


}
