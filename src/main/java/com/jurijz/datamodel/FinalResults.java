package com.jurijz.datamodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by jurijz on 3/1/2017.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="results")
public class FinalResults implements Serializable {

    @XmlElement(name = "result")
    private List<ResultRecord> resultRecords;

    public FinalResults() {

    }

    public FinalResults(List<ResultRecord> resultRecords) {
        this.resultRecords = resultRecords;
    }

    public List<ResultRecord> getResultRecords() {
        return resultRecords;
    }

    public void setResultRecords(List<ResultRecord> resultRecords) {
        this.resultRecords = resultRecords;
    }
}
