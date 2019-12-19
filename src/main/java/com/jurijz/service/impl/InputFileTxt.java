package com.jurijz.service.impl;


import com.jurijz.datamodel.ResultRecord;
import com.jurijz.service.InputFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by jurijz on 2/27/2017.
 */
@Service
public class InputFileTxt implements InputFile {

    private static final Logger LOG = LoggerFactory.getLogger(InputFileTxt.class);

    private static final String SPLIT_BY = ";";

    /**
     * Reads file and initiates transforming file data into <b>ResultRecord</b> objects
     * @param fileName - input filename
     * @return unsorted list of records from file
     * @throws IOException - Exception thrown if file cannot be parsed or if cannot find file
     */
    @Override
    public List<ResultRecord> readFile(String fileName) throws IOException {

        LOG.info("Reading file: {}", fileName);
        URL url = getClass().getClassLoader().getResource(fileName);
        File file;

        if (url != null) {
            file = new File(url.getFile());
        } else {
            LOG.error("Cannot read file: {}", fileName);
            throw new FileNotFoundException("There is no such file in resources folder: " + fileName);
        }
        return readFile(file);
    }

    /**
     * Reads file and initiates transforming file data into <b>ResultRecord</b> objects
     * @param file - input file
     * @return unsorted list of records from file
     * @throws IOException - Exception thrown if file cannot be parsed or if cannot find file
     */
    @Override
    public List<ResultRecord> readFile(File file) throws IOException {
        String fileName = file.getName();
        List<ResultRecord> resultRecords = new ArrayList<>();

        int lineNumber = 0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] resultsArr = line.split(SPLIT_BY);
                resultRecords.add(transform(resultsArr));
                lineNumber++;
            }
        } catch (IOException ioe) {
            LOG.error("Cannot parse file {} at line {}", fileName, lineNumber);
            throw new IOException(ioe.getMessage() + " - at line: " + lineNumber);
        }

        return resultRecords;
    }

    /**
     * Transforms array into <b>ResultRecord</b> object
     * @param resultsArray - string array read from file
     * @return <b>ResultRecord</b>
     * @throws IOException - Exception is thrown if there is not fixed amount of results
     * and if bad data type being parsed
     */
    private ResultRecord transform(String[] resultsArray) throws IOException {
        if (resultsArray.length < 11) {
            LOG.error("Not enough data to count result sum.");
            throw new IOException("Not enough data to count result sum.");
        }
        ResultRecord result = new ResultRecord();

        double[] parsedResults = new double[10];
        try {
            for (int i = 1; i < resultsArray.length - 1; i++) {
                parsedResults[i-1] = Double.parseDouble(resultsArray[i]);
            }
            parsedResults[9] = calculateSeconds(resultsArray[10]);
        } catch (NumberFormatException nfe) {
            LOG.error("Cannot parse entered data.");
            throw new IOException("Provided data is invalid.");
        }

        result.setName(resultsArray[0]);
        result.setResults(parsedResults);

        result.setRun100m(parsedResults[0]);
        result.setLongJump(parsedResults[1]);
        result.setShotPut(parsedResults[2]);
        result.setHighJump(parsedResults[3]);
        result.setRun400m(parsedResults[4]);
        result.setRun110mHurdles(parsedResults[5]);
        result.setDiscusThrow(parsedResults[6]);
        result.setPoleVault(parsedResults[7]);
        result.setJavelinThrow(parsedResults[8]);
        result.setRun1500m(parsedResults[9]);

        return result;
    }

    /**
     * transform <i>MM:SS</i> time format into seconds only
     * @param result minutes with seconds
     * @return seconds
     */
    private static double calculateSeconds(String result) {
        int firstIndex = result.indexOf(".");
        if (firstIndex != result.lastIndexOf(".")) {
            double minutes = Double.parseDouble(result.substring(0, firstIndex));
            double seconds = Double.parseDouble(result.substring(firstIndex + 1));
            return minutes * 60 + seconds;
        } else {
            return Double.parseDouble(result);
        }
    }
}
