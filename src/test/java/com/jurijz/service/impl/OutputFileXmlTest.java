package com.jurijz.service.impl;

import com.jurijz.TestHelper;
import com.jurijz.service.OutputFile;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by jurijz on 3/2/2017.
 */
public class OutputFileXmlTest {

    private static final String TEST_OUTPUT_FILE = "src/test/resources/testOutput.xml";
    private static final String TEST_XSL_FILE = "src/test/resources/decathlon_results.xsl";

    private final OutputFile outputFile = new OutputFileXml();

    @Test
    public void firstListOutputTest() throws Exception {
        outputFile.writeFile(TEST_OUTPUT_FILE, TestHelper.getFirstNormalList());
        Assert.assertTrue(new File(TEST_OUTPUT_FILE).isFile());
        Assert.assertTrue(new File(TEST_XSL_FILE).isFile());
    }

    @After
    public void after() throws Exception {
        Path file = Paths.get(TEST_OUTPUT_FILE);
        Path xslFile = Paths.get(TEST_XSL_FILE);
        Files.delete(file);
        Files.delete(xslFile);
    }
}
