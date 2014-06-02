package graph.constants;

import com.swabunga.spell.engine.SpellDictionary;
import com.swabunga.spell.engine.SpellDictionaryHashMap;
import com.swabunga.spell.event.SpellChecker;
import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.RuleMatch;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;

/**
 * Created by cagil on 27/05/14.   ,
 *
 * http://wiki.languagetool.org/java-api
 */

public class OOVFunction implements Callable<Boolean> {
    private String token;
    private String sentence;
    JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());
    List<RuleMatch> matches;
    private static String dictFile = Constants.dictionary_jazzy_en_us;//"dict/english.0";

    private SpellChecker spellCheck = null;
    public OOVFunction(final String sentence, final String token, final Label label ) throws IOException {
        this.token = token;
        this.sentence = sentence;
        SpellDictionary dictionary = new SpellDictionaryHashMap(new File(dictFile));// new File(phonetFile));
        spellCheck = new SpellChecker(dictionary);
    }

    public void test() throws IOException {
        langTool.activateDefaultPatternRules();
        // "A sentence " + "with a error in the Hitchhiker\'s Guide tot he Galaxy"
        matches = langTool.check(sentence);

        for (RuleMatch match : matches) {
            System.out.println("Potential error at line " +
                    match.getLine() + ", column " +
                    match.getColumn() + ": " + match.getMessage());
            System.out.println("Suggested correction: " +
                    match.getSuggestedReplacements());
        }
    }
    @Override
    public Boolean call() throws Exception {
        //System.out.println(spellCheck.checkSpelling(new StringWordTokenizer(sentence)));
        //final JazzyTest2 test2 = new JazzyTest2();
        //test2.check(sentence,token);
        if (Pattern.matches("\\p{Punct}", token)
                | token.startsWith("@")
                | token.startsWith("#"))
            return false;


        return  !spellCheck.isCorrect(token);


    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}