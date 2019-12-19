package com.jurijz.client.pages.component.fileupload;

import com.giffing.wicket.spring.boot.starter.app.WicketBootStandardWebApplication;
import com.jurijz.client.DecathlonAppConfig;
import org.apache.wicket.Application;
import org.apache.wicket.util.file.Folder;

/**
 * Created by jurijz on 2/17/2019.
 */
public class FileUploadUtils {

    public static Folder getUploadFolderFromConfig() {
        DecathlonAppConfig config = (DecathlonAppConfig) ((WicketBootStandardWebApplication) Application.get()).getConfigurations()
                .stream().filter(cfg -> cfg instanceof DecathlonAppConfig).findAny()
                .orElseThrow(() -> new RuntimeException("DecathlonAppConfig is not initialized."));
        return config.getUploadFolder();
    }
}
