import graph.Mongo;

import java.net.UnknownHostException;

/**
 * Created by cagil on 27/05/14.
 */
public class Test {


    public static void main(String[] args) throws UnknownHostException {
        mongoTest();
    }

    public static void mongoTest() throws UnknownHostException {
        Mongo mongo = new Mongo();
        System.out.print(mongo.dic.count());
    }
}
