package com.jurijz.client.facade;

import com.jurijz.datamodel.ResultRecord;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface DecathlonFacade {

    List<ResultRecord> uploadFile(File file) throws IOException;

    void calculateResults(List<ResultRecord> records);
}
