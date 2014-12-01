package pl.edu.agh.ki.bd.htmlIndexer.command;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.edu.agh.ki.bd.htmlIndexer.persistence.HibernateUtils;

import java.util.Iterator;

import static java.lang.String.*;

/**
 * Created by raduy on 01.12.14.
 */
public class PrintProcessedUrlsTableCommand implements ICommand {

    @Override
    public void execute() {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();

        String SQL_QUERY = "SELECT sentence.processedUrl.url, COUNT(*) FROM Sentence sentence GROUP BY sentence.processedUrl ORDER BY sentence.processedUrl.sentences.size desc";
        Query query = session.createQuery(SQL_QUERY);

        prettyPrintTable(query);

        transaction.commit();
        session.close();
    }

    private void prettyPrintTable(Query query) {

        int shift = computeMaxProcessedUrlLength(query);

        String header = format("%-" + shift + "s | Sentences Count:", "ProcessedUrls");
        System.out.println(header);

        for (int i = 0; i < header.length(); i++) {
            System.out.print('-');
        }
        System.out.println();

        for (Iterator it = query.iterate(); it.hasNext(); ) {
            Object[] row = (Object[]) it.next();

            System.out.println(format("%-" + shift + "s | %s", row[0], row[1]));
        }
        System.out.println();
    }

    private int computeMaxProcessedUrlLength(Query query) {
        int max = 0;

        for (Iterator it = query.iterate(); it.hasNext(); ) {
            max = Math.max(max, ((Object[])it.next())[0].toString().length());
        }

        return max;
    }
}
