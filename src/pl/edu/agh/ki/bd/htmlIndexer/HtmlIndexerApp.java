package pl.edu.agh.ki.bd.htmlIndexer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import pl.edu.agh.ki.bd.htmlIndexer.command.ICommand;
import pl.edu.agh.ki.bd.htmlIndexer.command.PrintProcessedUrlsTableCommand;
import pl.edu.agh.ki.bd.htmlIndexer.model.Sentence;
import pl.edu.agh.ki.bd.htmlIndexer.persistence.HibernateUtils;

import static java.lang.String.*;

public class HtmlIndexerApp {

    public static void main(String[] args) throws IOException {
        HibernateUtils.getSession().close();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Index indexer = new Index();

        while (true) {
            System.out.println("HtmlIndexer [? for help] > : ");
            String cmd = bufferedReader.readLine();
            long startAt = new Date().getTime();

            if (cmd.startsWith("?")) {
                System.out.println("'?'      	- print this help");
                System.out.println("'x'      	- exit HtmlIndexer");
                System.out.println("'i URLs'  	- index URLs, space separated");
                System.out.println("'f WORDS'	- find sentences containing all WORDs, space separated");
                System.out.println("'l <limit>  - find sentences with size at least limit'");
            } else if (cmd.startsWith("x")) {
                System.out.println("HtmlIndexer terminated.");
                HibernateUtils.shutdown();
                break;
            } else if (cmd.startsWith("i ")) {
                for (String url : cmd.substring(2).split(" ")) {
                    try {
                        indexer.indexWebPage(url);
                        System.out.println("Indexed: " + url);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error indexing: " + e.getMessage());
                    }
                }
            } else if (cmd.startsWith("f ")) {
                for (Sentence sentence : indexer.findSentencesByWords(cmd.substring(2))) {
                    System.out.println(format("Found in sentence: %s.\n\t In url: %s", sentence.getContent(), sentence.getProcessedUrl()));
                }
            } else if (cmd.startsWith("l ")) {
                for (String sentence : indexer.findSentencesByLength(Integer.valueOf(cmd.substring(2)))) {
                    System.out.println("Found in sentence: " + sentence);
                }
            } else if (cmd.startsWith("t")) {
                ICommand command = new PrintProcessedUrlsTableCommand();
                command.execute();
            } else if (cmd.trim().equals("")) {
                continue;
            }

            System.out.println("took " + (new Date().getTime() - startAt) + " ms");

        }
    }
}
