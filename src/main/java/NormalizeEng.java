import Feed.engInputFeed;
import graph.Configuration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by cagil on 27/05/14.
 */
class NormalizeEngThread implements Runnable{
    private Thread t;
    private String threadName;
    private Configuration conf ;
    private engInputFeed feed ;

    NormalizeEngThread(String name, Configuration conf, engInputFeed feed){
        threadName = name;
        this.conf = conf;
        this.feed = feed;
        System.out.println("Creating " +  threadName );
        run();
    }
    public void run()  {
        feed.normalize();
        System.out.println(feed.toString());
    }

    public void start ()
    {
        System.out.println("Starting " +  threadName );
        if (t == null)
        {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}

public class NormalizeEng {
    public static void main(String args[]) throws Exception {
        String filename = "";
        Configuration conf = new Configuration();
        //processFile(filename,conf);
        processSingle(conf);
    }

    public static void processSingle(Configuration conf) throws Exception {
        engInputFeed feed = new engInputFeed(conf);
        feed.process("This is a tweet which has sme mstakes");
        String sentence = "A sentence " + "with a error in the Hitchhiker's Guide tot he Galaxy";
        feed.process(sentence);
        NormalizeEngThread normalizer = new NormalizeEngThread("1", conf, feed);
    }

    public static void processFile(String filename,Configuration conf) throws Exception {
        BufferedReader bReader = null;
        engInputFeed feed = new engInputFeed(conf);
        int lineCount=0;
        try {
            bReader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = bReader.readLine()) != null) {
                feed.process(line);
                lineCount++;
                if(lineCount % 10 == 0){
                    NormalizeEngThread normalizer = new NormalizeEngThread(String.valueOf(lineCount),conf,feed);
                    feed =  new engInputFeed(conf);
                }

            }
        } catch (FileNotFoundException e) {
            throw new Exception(String.format("File not found: %s", filename));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bReader != null)
                bReader.close();
        }

    }
}