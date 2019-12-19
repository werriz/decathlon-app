package com.jurijz.client.pages.component.fileupload;

import com.jurijz.datamodel.FinalResults;
import org.apache.wicket.extensions.ajax.markup.html.form.upload.UploadProgressBar;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.file.Folder;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by jurijz on 1/17/2019.
 */
public class FileUploadPanel extends Panel {

    public FileUploadPanel(String id, Model<FinalResults> model) {
        super(id, model);
        init(model);
    }

    private void init(Model<FinalResults> model) {
        final Folder uploadFolder = FileUploadUtils.getUploadFolderFromConfig();

        // Create feedback panels
        final FeedbackPanel uploadFeedback = new FeedbackPanel("uploadFeedback");

        // Add uploadFeedback to the page itself
        add(uploadFeedback);

        // Add folder view
        add(new Label("dir", uploadFolder.getAbsolutePath()));
        final FileListView fileListView = new FileListView("fileList", new LoadableDetachableModel<>()
        {
            @Override
            protected List<File> load()
            {
                return Arrays.asList(Objects.requireNonNull(FileUploadUtils.getUploadFolderFromConfig().listFiles()));
            }
        });
        add(fileListView);

        // Add upload form with progress bar
        final FileUploadForm fileUploadForm = new FileUploadForm("progressUpload", model) {
            @Override
            protected void onModelChanged() {
                panelModelChanged();
            }
        };

        fileUploadForm.add(new UploadProgressBar("progress", fileUploadForm,
                fileUploadForm.getFileUploadField()));
        add(fileUploadForm);

        add(new Label("panelMsg", "Panel Message"));
    }

    private void panelModelChanged() {
        super.modelChanged();
    }
}
