package com.jurijz.service;


import com.jurijz.datamodel.ResultRecord;

import java.io.IOException;
import java.util.List;

/**
 * Created by jurijz on 2/27/2017.
 */
public interface OutputFile {

    void writeFile(String fileName, List<ResultRecord> sortedList) throws IOException;
}
