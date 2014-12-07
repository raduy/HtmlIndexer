package pl.edu.agh.ki.bd.htmlindexer.model;


import java.util.HashSet;
import java.util.Set;

public class Sentence {

    private long sentenceId;
    private String content;
    private ProcessedUrl processedUrl;
    private Set<Word> words = new HashSet<>();

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

    public void splitContentToWords() {
        String[] split = this.content.split("\\s+");
        for (String word : split) {
            if ("optimization".equals(word)) {
                System.out.println("kuj");
            }
            this.words.add(Word.of(word));
        }
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