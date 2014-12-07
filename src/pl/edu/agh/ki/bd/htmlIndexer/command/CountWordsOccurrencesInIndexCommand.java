package pl.edu.agh.ki.bd.htmlIndexer.command;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.edu.agh.ki.bd.htmlIndexer.persistence.HibernateUtils;

/**
 * Created by raduy on 04.12.14.
 */
public class CountWordsOccurrencesInIndexCommand implements ICommand {

    private final String cmd;

    public CountWordsOccurrencesInIndexCommand(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute() {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();

        String SQL_QUERY = "SELECT word, COUNT(*) FROM Sentence sentence join sentence.words as word group by word order by count(*) desc";
        Query query = session.createQuery(SQL_QUERY);

        for (Object object : query.list()) {
            Object[] tuple = (Object[]) object;

            System.out.println(tuple[0] + " " + tuple[1]);

        }

        System.out.println(query.list());

        transaction.commit();
        session.close();

    }
}