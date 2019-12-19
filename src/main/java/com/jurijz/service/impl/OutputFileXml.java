package com.jurijz.service.impl;


import com.jurijz.datamodel.FinalResults;
import com.jurijz.datamodel.ResultRecord;
import com.jurijz.service.OutputFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;

/**
 * Created by jurijz on 2/27/2017.
 */
@Service
public class OutputFileXml implements OutputFile {

    private static final Logger LOG = LoggerFactory.getLogger(OutputFileXml.class);

    private static final String XSL_FILE_NAME = "decathlon_results.xsl";
    private static final String MARSHALLER_XML_HEADER = "com.sun.xml.bind.xmlHeaders";

    private final static JAXBContext JAXB_CONTEXT;

    static {
        try {
            JAXB_CONTEXT = JAXBContext.newInstance(FinalResults.class);
        } catch (JAXBException e) {
            LOG.error("Cannot create Jaxb context");
            throw new RuntimeException("Cannot create Jaxb context");
        }
    }

    /**
     * Creates output file, and writes record results there
     * @param fileName - output filename
     * @param sortedList - result records
     * @throws IOException - Exception is rethrown from provideXslFile method or if file cannot be read
     */
    @Override
    public void writeFile(String fileName, List<ResultRecord> sortedList) throws IOException {
        File file = new File(fileName);

        FinalResults results = new FinalResults(sortedList);
        LOG.info("Creating output xml file: {}", fileName);
        try (FileOutputStream outputFile = new FileOutputStream(file)) {
            Marshaller marshaller = JAXB_CONTEXT.createMarshaller();

            String xslFilePath = XSL_FILE_NAME;
            //if output file has directories, provide the same directory for creating xsl file copy
            if (file.toPath().getParent() != null) {
                xslFilePath = file.toPath().getParent() + "/" + xslFilePath;
            }
            String header = "\n<?xml-stylesheet type=\"text/xsl\" href=\"" + xslFilePath + "\"?>";
            marshaller.setProperty(MARSHALLER_XML_HEADER, header);

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(results, outputFile);
            provideXslFile(xslFilePath);
        } catch (JAXBException e) {
            LOG.error("Cannot write XML file {}", e.getMessage());
        }
    }

    /**
     * Provides XSL file together with the output file
     * @param xslFilePath - output file path
     * @throws IOException - Exception is thrown if files cannot be copied and created
     */
    private void provideXslFile(String xslFilePath) throws IOException {
        URL url = this.getClass().getClassLoader().getResource(XSL_FILE_NAME);
        if (url != null) {
            File file = new File(url.getFile());

            File outFile = new File(xslFilePath);

            try (FileOutputStream outputStream = new FileOutputStream(outFile)) {
                Files.copy(file.toPath(), outputStream);
                outputStream.flush();
            }
        }
    }
}
