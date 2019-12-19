package com.jurijz.service;

import com.jurijz.datamodel.ResultRecord;

import java.util.List;

/**
 * Created by jurijz on 2/28/2017.
 */
public interface CalculateResults {

    void calculate(List<ResultRecord> resultRecordList);
}
