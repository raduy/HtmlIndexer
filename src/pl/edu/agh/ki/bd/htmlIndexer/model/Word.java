package pl.edu.agh.ki.bd.htmlIndexer.model;

import java.util.Set;

/**
 * Created by raduy on 01.12.14.
 */
public class Word {
    private String content;
    private Set<Sentence> sentenceSet;
    private String foo;


    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
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
}
