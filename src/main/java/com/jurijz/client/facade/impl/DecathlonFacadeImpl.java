package com.jurijz.client.facade.impl;

import com.jurijz.client.facade.DecathlonFacade;
import com.jurijz.datamodel.ResultRecord;
import com.jurijz.service.CalculateResults;
import com.jurijz.service.InputFile;
import com.jurijz.service.OutputFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by jurijz on 4/6/2019.
 */
@Component
public class DecathlonFacadeImpl implements DecathlonFacade {

    private final CalculateResults calculateResults;
    private final InputFile inputFile;
    private final OutputFile outputFile;

    @Autowired
    public DecathlonFacadeImpl(final CalculateResults calculateResults, final InputFile inputFile,
                               final OutputFile outputFile) {
        this.calculateResults = calculateResults;
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    @Override
    public List<ResultRecord> uploadFile(final File file) throws IOException {
        return inputFile.readFile(file);
    }

    @Override
    public void calculateResults(final List<ResultRecord> records) {
        calculateResults.calculate(records);
    }


}
