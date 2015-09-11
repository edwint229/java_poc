package org.edwin.vote.mvc.to;

import java.util.List;

public class VoteResult {

    private List<String> labels;

    private List<VoteData> datasets;

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<VoteData> getDatasets() {
        return datasets;
    }

    public void setDatasets(List<VoteData> datasets) {
        this.datasets = datasets;
    }

}
