package com.jurijz.service.impl;

import com.jurijz.datamodel.ResultRecord;
import com.jurijz.service.InputFile;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by jurijz on 3/2/2017.
 */
public class InputFileTxtTest {

    private final InputFile inputFile = new InputFileTxt();

    @Test
    public void firstListParseTest() throws Exception {
        List<ResultRecord> resultList = inputFile.readFile("Decathlon_input_test.txt");
        Assert.assertFalse(resultList.isEmpty());
        Assert.assertTrue((resultList.get(0).getName() != null) &&
                (resultList.get(0).getName().contains("Susi")));
    }

    @Test
    public void secondListParseTest() throws Exception {
        List<ResultRecord> resultList = inputFile.readFile("Decathlon_input_test2.txt");
        Assert.assertFalse(resultList.isEmpty());
        Assert.assertTrue((resultList.get(0).getName() != null) &&
                (resultList.get(0).getName().contains("Human2")));
        Assert.assertTrue((resultList.get(4).getName() != null) &&
                (resultList.get(4).getName().contains("Clone1")));
    }

    @Test(expected = FileNotFoundException.class)
    public void noInputFileNameProvidedTest() throws Exception {
        inputFile.readFile("unknownFileName.txt");
    }

    @Test(expected = IOException.class)
    public void inputFileWithErrorsTest() throws Exception {
        inputFile.readFile("Decathlon_input_error.txt");
    }
}
