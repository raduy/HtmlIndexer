package pl.edu.agh.ki.bd.htmlIndexer.model;

import java.util.*;

/**
 * Created by raduy on 26.11.14.
 */
public class ProcessedUrl {
    private long processedUrlId;
    private String url;
    private Date date;
    private List<Sentence> sentences = new ArrayList<Sentence>();

    public ProcessedUrl() {
    }

    public long getProcessedUrlId() {
        return processedUrlId;
    }

    public void setProcessedUrlId(long id) {
        this.processedUrlId = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public void setSentences(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    @Override
    public String toString() {
        return "ProcessedUrl{" +
                "url='" + url + '\'' +
                ", date=" + date +
                '}';
    }
}
