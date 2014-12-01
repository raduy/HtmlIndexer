package pl.edu.agh.ki.bd.htmlIndexer.model;


import java.util.Set;

public class Sentence {

    private long sentenceId;
    private String content;
    private ProcessedUrl processedUrl;
    private Set<Word> words;

    public Sentence() {
    }

    public Sentence(String content, ProcessedUrl processedUrl) {
        this.setContent(content);
        this.setProcessedUrl(processedUrl);
    }

    public ProcessedUrl getProcessedUrl() {
        return processedUrl;
    }

    public void setProcessedUrl(ProcessedUrl processedUrl) {
        this.processedUrl = processedUrl;
    }

    public long getSentenceId() {
        return sentenceId;
    }

    public void setSentenceId(long id) {
        this.sentenceId = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Word> getWords() {
        return words;
    }

    public void setWords(Set<Word> words) {
        this.words = words;
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "id=" + sentenceId +
                ", content='" + content + '\'' +
                ", url='" + processedUrl + '\'' +
                '}';
    }
}
//criteria api
//        inny silnik
// skomplikowany join