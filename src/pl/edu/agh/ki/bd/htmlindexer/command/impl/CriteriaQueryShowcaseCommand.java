package pl.edu.agh.ki.bd.htmlindexer.command.impl;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import pl.edu.agh.ki.bd.htmlindexer.command.api.ICommand;
import pl.edu.agh.ki.bd.htmlindexer.model.Sentence;
import pl.edu.agh.ki.bd.htmlindexer.persistence.HibernateUtils;

import java.util.List;

/**
 * Created by raduy on 07.12.14.
 */
public class CriteriaQueryShowcaseCommand implements ICommand {

    public CriteriaQueryShowcaseCommand() {
    }

    @Override
    public void execute() {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();

        /**
         *  create a join query between Sentence and ProcessedUrl classes - example of Hibernate Criteria API
         */
        Criteria criteria = session.createCriteria(Sentence.class, "sentence");
        criteria.setFetchMode("sentence.processedUrl", FetchMode.JOIN);
        criteria.createAlias("sentence.processedUrl", "processedUrl");

        ProjectionList columns = Projections.projectionList()
                .add(Projections.property("content"))
                .add(Projections.property("processedUrl.url"));
        criteria.setProjection(columns);

        List<Object[]> list = criteria.list();

        for (Object[] tuple : list) {
            System.out.println(String.format("Sentence '%s' found in '%s' url.", tuple[0], tuple[1]));
        }

        transaction.commit();
        session.close();
    }
}
