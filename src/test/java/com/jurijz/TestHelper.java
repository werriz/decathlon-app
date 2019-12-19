package com.jurijz;


import com.jurijz.datamodel.ResultRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jurijz on 3/2/2017.
 */
public class TestHelper {

    public static List<ResultRecord> getFirstNormalList() {
        List<ResultRecord> recordList = new ArrayList<>();
        ResultRecord record = new ResultRecord();
        record.setName("Siim Susi");
        setResultsToFields(record, new double[]{12.61, 5.0, 9.22, 1.5, 60.39, 16.43, 21.6, 2.6, 35.81, 325.72});
        recordList.add(record);

        record = new ResultRecord();
        record.setName("Beata Kana");
        setResultsToFields(record, new double[]{13.04, 4.53, 7.79, 1.55, 64.72, 18.74, 24.2, 2.4, 28.2, 410.76});
        recordList.add(record);

        record = new ResultRecord();
        record.setName("Jaana Lind");
        setResultsToFields(record, new double[]{13.75, 4.48, 10.12, 1.5, 68.44, 19.18, 30.85, 2.8, 33.88, 382.75});
        recordList.add(record);

        record = new ResultRecord();
        record.setName("Anti Loop");
        setResultsToFields(record, new double[]{13.43, 4.35, 8.64, 1.5, 66.06, 19.05, 24.89, 2.2, 33.48, 411.01});
        recordList.add(record);

        return recordList;
    }

    public static List<ResultRecord> getSecondNormalList() {
        List<ResultRecord> recordList = new ArrayList<>();
        ResultRecord record = new ResultRecord();
        record.setName("Human2");
        setResultsToFields(record, new double[]{13.50, 4.0, 9.00, 1.5, 66.00, 18.50, 20.0, 2.0, 34.00, 336.00});
        recordList.add(record);

        record = new ResultRecord();
        record.setName("Human");
        setResultsToFields(record, new double[]{12.50, 5.0, 9.50, 1.5, 60.00, 16.50, 21.5, 2.5, 36.00, 330.00});
        recordList.add(record);

        for (int i = 0; i < 3; i++) {
            record = new ResultRecord();
            record.setName("Robot" + i);
            setResultsToFields(record, new double[]{14.00, 4.0, 9.00, 1.5, 67.00, 18.50, 20.0, 2.0, 34.00, 337.00});
            recordList.add(record);
        }

        for (int i = 0; i < 3; i++) {
            record = new ResultRecord();
            record.setName("Clone" + i);
            setResultsToFields(record, new double[]{13.00, 4.0, 9.00, 1.5, 65.00, 18.50, 20.5, 2.0, 35.00, 335.00});
            recordList.add(record);
        }

        return recordList;
    }

    private static void setResultsToFields(ResultRecord record, double[] results) {
        record.setResults(results);
        record.setRun100m(results[0]);
        record.setLongJump(results[1]);
        record.setShotPut(results[2]);
        record.setHighJump(results[3]);
        record.setRun400m(results[4]);
        record.setRun110mHurdles(results[5]);
        record.setDiscusThrow(results[6]);
        record.setPoleVault(results[7]);
        record.setJavelinThrow(results[8]);
        record.setRun1500m(results[9]);
    }
}
