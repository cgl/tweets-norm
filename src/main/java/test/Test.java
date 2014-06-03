package test;

import graph.Mongo;

import java.net.UnknownHostException;

/**
 * Created by cagil on 27/05/14.
 */
public class Test {


    public static void main(String[] args) throws UnknownHostException {
        mongoTest();
    }

    public static void mongoTest() {
        System.out.print("Hello");
        try {
            Mongo mongo = new Mongo();
            System.out.print(mongo.dic.count());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
