package graph;

import cmu.arktweetnlp.Tagger;

import java.util.ArrayList;
import java.util.SortedMap;
import java.util.Stack;

/**
 * Created by cagil on 26/05/14.
 */
public class NTweet {

    private SortedMap<Integer,NToken> tokens;
    private ArrayList<TToken> tTokens;
    public int OOV = 0;
    private final String text;
    private Stack<String> normalizedTexts = new Stack<String>();

    public String getText() {
        return text;
    }

    public NTweet(String text) {
        this.text = text;
        tTokens = new ArrayList<TToken>();
    }

    public SortedMap<Integer,NToken> getTokens() {
        return tokens;
    }

    public void setTokens(SortedMap<Integer,NToken> tokens) {
        this.tokens = tokens;
    }

    public ArrayList<TToken> gettTokens() {
        return tTokens;
    }

    public void settTokens(ArrayList<TToken> tTokens) {
        this.tTokens = tTokens;
    }

    public void setTaggedTokens(final ArrayList<Tagger.TaggedToken> tTokens) {
        for (Tagger.TaggedToken taggedToken : tTokens) {
            this.tTokens.add(new TToken(taggedToken.token,taggedToken.tag));
        }

    }

    public Stack<String> getNormalizedTexts() {
        return normalizedTexts;
    }

    public void setNormalizedTexts(Stack<String> normalizedTexts) {
        this.normalizedTexts = normalizedTexts;
    }

    public void addNormalizedText(String normalizedText) {
        this.normalizedTexts.push(normalizedText);
    }

    public String getLastNormalizedText() {
        if(normalizedTexts.empty())
            return "";
        return normalizedTexts.peek();
    }

    public static class TToken extends Tagger.TaggedToken{
        public String token;
        public String tag;
        public boolean isOOV;
        //private NTweet tweet;
        public SortedMap<Float,String> candidates;

        public TToken(String token, String tag) {
            this.token = token;
            this.tag = tag;
        }
    }
}
