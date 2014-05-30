package graph;

/**
 * Created by cagil on 28/05/14.
 */
public class NToken {
    private String word;
    private String index;
    private String tag;

    public NToken(String word, String index, String pos) {
        this.word = word;
        this.index = index;
        this.tag = pos;
    }

    public String getWord() {

        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
