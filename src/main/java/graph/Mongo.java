package graph;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

/**
 * Created by cagil on 02/06/14.
 */
public class Mongo {
    MongoClient mongoClient;
    public DBCollection dic;

    public Mongo() throws UnknownHostException {
        mongoClient = new MongoClient("79.123.177.251");
        DB database = mongoClient.getDB( "dictionary2" );
        dic = database.getCollection("dic");
    }


}
