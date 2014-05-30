package graph;

import com.google.common.base.Charsets;
import com.swabunga.spell.engine.SpellDictionaryHashMap;
import com.swabunga.spell.event.SpellChecker;
import graph.constants.Constants;
import graph.constants.Language;
import graph.constants.Type;
import zemberek.spelling.SingleWordSpellChecker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * Created by cagil on 27/05/14.
 */
public class Configuration {
    private SingleWordSpellChecker zemberekSpellChecker = null;
    private SpellChecker jazzySpellChecker = null;
    private Type type = Type.MULTI;
    private Language lang = Language.ENG;
    private boolean justTokenize = false;


    public SpellChecker getJazzySpellChecker() {
        return jazzySpellChecker;
    }


    private void setJazzySpellChecker()
    {
        File dict = new File(Constants.dictionary_jazzy_en_us);
        try
        {
            jazzySpellChecker = new SpellChecker(new SpellDictionaryHashMap(dict));
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Dictionary File '" + dict + "' not found! Quitting. " + e);
            System.exit(1);
        }
        catch (IOException ex)
        {
            System.err.println("IOException occurred while trying to read the dictionary file: " + ex);
            System.exit(2);
        }
    }

    public SingleWordSpellChecker getZemberekSpellChecker() {
        return zemberekSpellChecker;
    }

    public void setZemberekSpellChecker() throws IOException {
        this.zemberekSpellChecker = new SingleWordSpellChecker(1.4, true);
        System.out.println("Loading vocabulary");
        //List<String> list = Files.readAllLines(new File("/Users/cagil/Documents/zemberek-nlp/500.txt").toPath(), Charsets.UTF_8);
        List<String> list = Files.readAllLines(new File(Constants.dictionary).toPath(), Charsets.UTF_8);

        //List<String> list = Files.readAllLines(new File("/Users/cagil/Documents/allvoc.txt").toPath(), Charsets.UTF_8);
        System.out.println("Building tree");
        zemberekSpellChecker.buildDictionary(list);
        System.out.println("Tree is ready");
    }

    public Type getType() {
        return type;
    }

    public void setType(final Type type) {
        this.type = type;
    }

    public Language getLang() {
        return lang;
    }

    public void setLang(final Language lang) {
        this.lang = lang;
    }
    public boolean isJustTokenize() {
        return justTokenize;
    }

    public void setJustTokenize(boolean justTokenize) {
        this.justTokenize = justTokenize;
    }
}
