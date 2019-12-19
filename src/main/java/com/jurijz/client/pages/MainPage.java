package com.jurijz.client.pages;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import com.jurijz.client.pages.component.fileupload.FileUploadPanel;
import com.jurijz.client.pages.component.result.ResultRecordsPanel;
import com.jurijz.datamodel.FinalResults;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

//@WicketInternalErrorPage TODO:
@WicketHomePage
@MountPath("main")
@AuthorizeInstantiation("USER")
public class MainPage extends WebPage {

    public MainPage() {

        // Add the simplest type of label
        add(new Label("message", "If you see this message wicket is properly configured and running"));
        Model<FinalResults> model = new Model<>();


        ResultRecordsPanel resultPanel = new ResultRecordsPanel("resultRecordPanel", model);
        resultPanel.setVisible(false);
        add(resultPanel);
        FileUploadPanel fileUploadPanel = new FileUploadPanel("fileUploadPanel", model) {
            @Override
            protected void onModelChanged() {
                resultPanel.setVisible(true);
                model.getObject();
            }
        };
        add(fileUploadPanel);
    }
}
