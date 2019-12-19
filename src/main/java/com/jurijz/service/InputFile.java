package com.jurijz.service;

import com.jurijz.datamodel.ResultRecord;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by jurijz on 2/27/2017.
 */
public interface InputFile {

    List<ResultRecord> readFile(String fileName) throws IOException;

    List<ResultRecord> readFile(File file) throws IOException;

}
