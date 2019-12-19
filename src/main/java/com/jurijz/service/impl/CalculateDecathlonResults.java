package com.jurijz.service.impl;


import com.jurijz.datamodel.Constants;
import com.jurijz.datamodel.ResultRecord;
import com.jurijz.service.CalculateResults;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jurijz on 2/27/2017.
 */
@Service
public class CalculateDecathlonResults implements CalculateResults {

    /**
     * Method calculates result sum of each list element, and then sorts it accordingly.
     * Also, sets <i>place</i> value.
     * @param resultRecordList - unsorted recordList
     */
    @Override
    public void calculate(List<ResultRecord> resultRecordList) {
        resultRecordList.forEach(record -> {
            var index = new AtomicInteger();
            record.setResultSum(Arrays.stream(record.getResults())
                .mapToInt(result -> {
                    var constant = Constants.getByIndex(index.getAndIncrement());
                    return calculatePoints(constant.getA(), constant.getB(),
                            constant.getC(), result);
                })
                .sum());
        });
        resultRecordList.sort(Collections.reverseOrder());
        setPositions(resultRecordList);
    }

    /**
     * calculates by given formula
     * @return INT(a*(b-p)^c)
     */
    private int calculatePoints(double a, double b, double c, double p) {
        double first = Math.abs(b - p);
        double second = Math.pow(first, c);
        return (int) Math.floor(second * a);
    }

    /**
     * Method takes result sum and calculates place to assign to current participant.
     * If there are several participants with the same result sum, assigns divided place
     * @param resultRecords sorted recordList
     */
    private void setPositions(List<ResultRecord> resultRecords) {
        for (int i = 0; i < resultRecords.size(); i++) {
            var record = resultRecords.get(i);
            if (record.getPlace() == null) {
                var place = (i + 1) + "";
                if (i + 1 < resultRecords.size() && resultRecords.get(i + 1).getResultSum() == record.getResultSum()) {
                    setMultiplePositions(resultRecords, i, place);
                } else {
                    record.setPlace(place);
                }
            }
        }
    }

    /**
     * Method is responsible for making multiple positions
     * @param resultRecords - list of results
     * @param index - first element with equal resultSum
     * @param place - first place with equal resultSum
     */
    private void setMultiplePositions(List<ResultRecord> resultRecords, int index, String place) {
        int lastIndex = index + 1;
        // loop through next elements, until one with different sum found
        for (int j = index + 1; j < resultRecords.size(); j++) {
            if (resultRecords.get(index).getResultSum() == resultRecords.get(j).getResultSum()) {
                place = place + "-" + (j + 1);
                lastIndex = j;
            } else {
                break;
            }
        }
        for (int k = index; k <= lastIndex; k++) {
            resultRecords.get(k).setPlace(place);
        }
    }
}
