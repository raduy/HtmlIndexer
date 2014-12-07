package pl.edu.agh.ki.bd.htmlindexer.command.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.edu.agh.ki.bd.htmlindexer.command.api.ICommand;
import pl.edu.agh.ki.bd.htmlindexer.persistence.HibernateUtils;

import java.util.List;

/**
 * Created by raduy on 07.12.14.
 */
public class ListSentencesWithAtLeastLength implements ICommand {

    private final String cmd;

    public ListSentencesWithAtLeastLength(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute() {
        for (String sentence : findSentencesByLength(Integer.parseInt(cmd.substring(2)))) {
            System.out.println("Found in sentence: " + sentence);
        }
    }

    public List<String> findSentencesByLength(Integer limit) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();

        List<String> result = session.createQuery("select s.content from Sentence s where length(s.content) > :limit").setParameter("limit", limit).list();

        transaction.commit();
        session.close();

        return result;
    }
}
