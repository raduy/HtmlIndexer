package pl.edu.agh.ki.bd.htmlindexer;

import pl.edu.agh.ki.bd.htmlindexer.command.api.ICommand;
import pl.edu.agh.ki.bd.htmlindexer.command.impl.*;
import pl.edu.agh.ki.bd.htmlindexer.persistence.HibernateUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HtmlIndexerApp {

    public static void main(String[] args) throws IOException {
        HibernateUtils.getSession().close();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("HtmlIndexer [? for help] > : ");
            String cmd = bufferedReader.readLine();
            long startAt = System.nanoTime();

            ICommand command;
            if (cmd.startsWith("?")) {
                command = new PrintHelpCommand();
                command.execute();
            } else if (cmd.startsWith("x")) {
                System.out.println("HtmlIndexer terminated.");
                HibernateUtils.shutdown();
                break;
            } else if (cmd.startsWith("i ")) {
                command = new IndexWebPageCommand(cmd);
                command.execute();
            } else if (cmd.startsWith("f ")) {
                command = new FindWordInIndexCommand(cmd);
                command.execute();
            } else if (cmd.startsWith("l ")) {
                command = new ListSentencesWithAtLeastLength(cmd);
                command.execute();
            } else if (cmd.startsWith("t")) {
                command = new PrintProcessedUrlsTableCommand();
                command.execute();
            } else if (cmd.startsWith("c")) {
                command = new CountWordsOccurrencesInIndexCommand(cmd);
                command.execute();
            } else if (cmd.trim().equals("")) {
                continue;
            }

            System.out.println("took " + ((System.nanoTime() - startAt) / 1000000 + " ms"));
        }
    }
}