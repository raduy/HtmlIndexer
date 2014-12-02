package pl.edu.agh.ki.bd.htmlIndexer.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by raduy on 01.12.14.
 */
public class Word {
    private String content;
    private Set<Sentence> sentenceSet;
    private static Map<String, Word> words = new HashMap<>();

    public Word() {
    }

    public Word(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Sentence> getSentenceSet() {
        return sentenceSet;
    }

    public void setSentenceSet(Set<Sentence> sentenceSet) {
        this.sentenceSet = sentenceSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        if (!content.equals(word.content)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }

    @Override
    public String toString() {
        return "Word{" +
                "content='" + content + '\'' +
                '}';
    }

    public static Word of(String word) {
        Word retrieved = words.get(word);
        if (retrieved == null) {
            Word newWord = new Word(word);
            words.put(word, newWord);
            return newWord;
        }
        return retrieved;
    }
}
