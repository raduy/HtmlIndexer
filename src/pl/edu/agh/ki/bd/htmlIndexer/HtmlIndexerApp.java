package pl.edu.agh.ki.bd.htmlIndexer;

import pl.edu.agh.ki.bd.htmlIndexer.command.*;
import pl.edu.agh.ki.bd.htmlIndexer.persistence.HibernateUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HtmlIndexerApp {

    public static void main(String[] args) throws IOException {
        HibernateUtils.getSession().close();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Index indexer = new Index();

        while (true) {
            System.out.println("HtmlIndexer [? for help] > : ");
            String cmd = bufferedReader.readLine();
            long startAt = System.nanoTime();

            if (cmd.startsWith("?")) {
                System.out.println("'?'      	- print this help");
                System.out.println("'x'      	- exit HtmlIndexer");
                System.out.println("'i URLs'  	- index URLs, space separated");
                System.out.println("'f WORDS'	- find sentences containing all WORDs, space separated");
                System.out.println("'l <limit>  - find sentences with size at least limit'");
                System.out.println("'c          - count word occurrences in word index'");
                System.out.println("'t          - draw indexed urls table'");
            } else if (cmd.startsWith("x")) {
                System.out.println("HtmlIndexer terminated.");
                HibernateUtils.shutdown();
                break;
            } else if (cmd.startsWith("i ")) {
                ICommand command = new IndexWebPageCommand(cmd);
                command.execute();

            } else if (cmd.startsWith("f ")) {
                ICommand command = new FindWordInIndexCommand(cmd);
                command.execute();

            } else if (cmd.startsWith("l ")) {
                for (String sentence : indexer.findSentencesByLength(Integer.valueOf(cmd.substring(2)))) {
                    System.out.println("Found in sentence: " + sentence);
                }
            } else if (cmd.startsWith("t")) {
                ICommand command = new PrintProcessedUrlsTableCommand();
                command.execute();
            } else if (cmd.startsWith("c")) {
                ICommand command = new CountWordsOccurrencesInIndexCommand(cmd);
                command.execute();
            } else if (cmd.trim().equals("")) {
                continue;
            }

            System.out.println("took " + ((System.nanoTime() - startAt) / 1000000 + " ms"));

        }
    }
}