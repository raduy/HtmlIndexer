package pl.edu.agh.ki.bd.htmlindexer.command.impl;

import java.io.IOException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import pl.edu.agh.ki.bd.htmlindexer.model.ProcessedUrl;
import pl.edu.agh.ki.bd.htmlindexer.model.Sentence;
import pl.edu.agh.ki.bd.htmlindexer.persistence.HibernateUtils;

public class Index {
    public void indexWebPage(String url) throws IOException {

        System.out.println(url);

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
                    sentence.splitContentToWords();
                    processedUrl.getSentences().add(sentence);

                    session.saveOrUpdate(sentence);
                }
            }
        }
        transaction.commit();
        session.close();
    }
}
