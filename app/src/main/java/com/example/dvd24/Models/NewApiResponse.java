package com.example.dvd24.Models;

import java.io.Serializable;
import java.util.List;

public class NewApiResponse implements Serializable {
    String status;
    int totalResults;
    List<NewHeadlines> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<NewHeadlines> getArticles() {
        return articles;
    }

    public void setArticles(List<NewHeadlines> articles) {
        this.articles = articles;
    }
}
