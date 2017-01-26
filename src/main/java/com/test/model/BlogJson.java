package com.test.model;

import java.sql.Date;
import java.util.List;

/**
 * Created by egguncle on 17-1-13.
 */
public class BlogJson {
    private Boolean error;
    private List<BlogEntity> results;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<BlogEntity> getResults() {
        return results;
    }

    public void setResults(List<BlogEntity> results) {
        this.results = results;
    }


}
