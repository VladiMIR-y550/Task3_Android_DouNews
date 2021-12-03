package com.mironenko.dounews.model.remote;

import java.util.ArrayList;
import java.util.List;

public class ArticlesListResponse {

    private List<ArticlesListItems> results = new ArrayList<>();

    public List<ArticlesListItems> getResults() {
        return results;
    }

    public void setResults(List<ArticlesListItems> results) {
        this.results = results;
    }
}
