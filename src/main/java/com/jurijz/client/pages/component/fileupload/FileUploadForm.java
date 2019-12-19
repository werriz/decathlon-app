package com.jurijz.client.pages.component.fileupload;

import com.jurijz.client.facade.DecathlonFacade;
import com.jurijz.datamodel.FinalResults;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.lang.Bytes;

import java.io.File;
import java.io.IOException;

/**
 * Created by jurijz on 2/14/2019.
 */
public class FileUploadForm extends Form<FinalResults> {

    @SpringBean
    private DecathlonFacade facade;

    private final FileUploadField fileUploadField;

    public FileUploadForm(String id, Model<FinalResults> fileModel) {
        super(id, fileModel);

        setMultiPart(false);

        add(fileUploadField = new FileUploadField("fileUploadField"));

        setMaxSize(Bytes.kilobytes(2048));

        setFileMaxSize(Bytes.kilobytes(2048));
    }

    @Override
    protected void onSubmit() {

        final FileUpload upload = fileUploadField.getFileUpload();
        if (upload != null) {
            // Create a new file
            final File newFile = new File(FileUploadUtils.getUploadFolderFromConfig(), upload.getClientFileName());

            // Check new file, delete if it already existed
            checkFileExists(newFile);
            try {
                // Save to new file
                if (newFile.createNewFile()) {
                    upload.writeTo(newFile);
                }
                FinalResults results = new FinalResults();
                results.setResultRecords(facade.uploadFile(newFile));
                getModel().setObject(results);
                modelChanged();
            } catch (IOException ioe) {
                upload.delete();
            } catch (Exception e) {
                upload.delete();
                throw new IllegalStateException("Unable to write file", e);
            }
        }

    }

    public FileUploadField getFileUploadField() {
        return fileUploadField;
    }

    private void checkFileExists(final File newFile) {
        if (newFile.exists()) {
            // Try to delete the file
            if (!Files.remove(newFile)) {
                throw new IllegalStateException("Unable to overwrite " + newFile.getAbsolutePath());
            }
        }
    }
}