package pl.edu.agh.ki.bd.htmlindexer.command.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.edu.agh.ki.bd.htmlindexer.command.api.ICommand;
import pl.edu.agh.ki.bd.htmlindexer.model.Sentence;
import pl.edu.agh.ki.bd.htmlindexer.model.Word;
import pl.edu.agh.ki.bd.htmlindexer.persistence.HibernateUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.String.format;

/**
 * Created by raduy on 02.12.14.
 */
public class FindWordInIndexCommand implements ICommand {
    private final String cmd;

    public FindWordInIndexCommand(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute() {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();

        Set<Word> words = extractWords();

        List<Object[]> result =
                session.createQuery(
                        "select s, word " +
                                "from Sentence as s " +
                                "left join s.words as word " +
                                "where word in (:lookingWords)").setParameterList("lookingWords", words).list();

        transaction.commit();
        session.close();

        prettyPrintResult(result);
    }

    private void prettyPrintResult(List<Object[]> result) {
        int itemNo = 1;

        System.out.println(format("--------------------%d matching sentences found-------------", result.size()));
        for (Object[] tuple : result) {

            Word word = (Word) tuple[1];
            Sentence sentence = (Sentence) tuple[0];

            System.out.println(format("%d. Found '%s' word in sentence: '%s'.\n\t In url: %s",
                    itemNo++, word.getContent(), sentence.getContent(), sentence.getProcessedUrl()));
        }
    }

    private Set<Word> extractWords() {
        Set<Word> result = new HashSet<>();

        String[] words = this.cmd.substring(2).split(",");
        for (String word : words) {
            result.add(Word.of(word.trim()));
        }
        return result;
    }
}
