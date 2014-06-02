package graph.constants;

import zemberek.core.DoubleValueSet;
//import zemberek.spelling.SingleWordSpellChecker;

import java.io.IOException;

/**
 * Created by cagil on 27/05/14.
 */
public class OOVFunctionTr extends OOVFunction {
    private String token;
    private Object[] args;
    private String sentence;
    //private SingleWordSpellChecker dt;
    public OOVFunctionTr(final String sentence, final String token, final Label label) throws IOException {
        super(sentence, token,label);

    }

    @Override
    public Boolean call() throws Exception{
        //DoubleValueSet<String> res = dt.decode(this.token.toLowerCase(Constants.localeTr));
        DoubleValueSet<String> res = null;
        for (String re : res) {
            double v = res.get(re);
            if(v == 0.0){
                System.out.println(this.token);
                return true;
            }
        }

        return false;
    }


}

