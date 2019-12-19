package com.jurijz.service.impl;

import com.jurijz.TestHelper;
import com.jurijz.datamodel.ResultRecord;
import com.jurijz.service.CalculateResults;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by jurijz on 3/2/2017.
 */
public class CalculateDecathlonResultsTest {

    private final CalculateResults calculateResults = new CalculateDecathlonResults();

    @Test
    public void calculateFirstListTest() {
        List<ResultRecord> resultRecordList = TestHelper.getFirstNormalList();
        calculateResults.calculate(resultRecordList);
        Assert.assertTrue(resultRecordList.get(0).getResultSum() > resultRecordList.get(3).getResultSum());
        Assert.assertTrue((resultRecordList.get(3).getPlace() != null) && (resultRecordList.get(3).getPlace().equals("4")));
        Assert.assertTrue((resultRecordList.get(0).getName() != null) &&
                (resultRecordList.get(0).getName().equalsIgnoreCase("Siim Susi")));
    }

    @Test
    public void calculateSecondListTest() {
        List<ResultRecord> resultRecordList = TestHelper.getSecondNormalList();
        calculateResults.calculate(resultRecordList);
        Assert.assertTrue((resultRecordList.get(0).getName() != null) &&
                (resultRecordList.get(0).getName().equalsIgnoreCase("Human")));
        Assert.assertEquals(resultRecordList.get(1).getResultSum(), resultRecordList.get(3).getResultSum());
        Assert.assertTrue((resultRecordList.get(2).getPlace() != null) &&
                (resultRecordList.get(2).getPlace().equals("2-3-4")));
        Assert.assertTrue((resultRecordList.get(7).getName() != null) &&
                (resultRecordList.get(7).getName().contains("Robot")));
    }
}
