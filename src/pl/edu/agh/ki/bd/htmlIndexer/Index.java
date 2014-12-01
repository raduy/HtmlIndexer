package pl.edu.agh.ki.bd.htmlIndexer;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import pl.edu.agh.ki.bd.htmlIndexer.model.ProcessedUrl;
import pl.edu.agh.ki.bd.htmlIndexer.model.Sentence;
import pl.edu.agh.ki.bd.htmlIndexer.persistence.HibernateUtils;

public class Index {
    public void indexWebPage(String url) throws IOException {

        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.body().select("*");
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        ProcessedUrl processedUrl = new ProcessedUrl();
        processedUrl.setUrl(url);
        processedUrl.setDate(new Date());

        for (Element element : elements) {
            if (element.ownText().trim().length() > 1) {
                for (String sentenceContent : element.ownText().split("\\. ")) {

                    Sentence sentence = new Sentence(sentenceContent, processedUrl);
                    processedUrl.getSentences().add(sentence);

                }
            }
        }
        System.out.println(processedUrl);
        session.persist(processedUrl);

        transaction.commit();
        session.close();
    }

    public List<Sentence> findSentencesByWords(String words) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();

        String query = "%" + words.replace(" ", "%") + "%";
        List<Sentence> result = session.createQuery("select s from Sentence s where s.content like :query").setParameter("query", query).list();

        transaction.commit();
        session.close();

        return result;
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
