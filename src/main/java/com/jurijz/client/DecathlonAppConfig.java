package com.jurijz.client;

import com.giffing.wicket.spring.boot.context.extensions.ApplicationInitExtension;
import com.giffing.wicket.spring.boot.context.extensions.WicketApplicationInitConfiguration;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.file.Folder;

/**
 * Created by jurijz on 2/16/2019.
 */
@ApplicationInitExtension
public class DecathlonAppConfig implements WicketApplicationInitConfiguration {

    private Folder uploadFolder;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public void init(WebApplication webApplication) {
        webApplication.getResourceSettings().setThrowExceptionOnMissingResource(false);

        uploadFolder = new Folder(System.getProperty("java.io.tmpdir"), "wicket-uploads");
        // Ensure folder exists
        uploadFolder.mkdirs();

        webApplication.getApplicationSettings().setUploadProgressUpdatesEnabled(true);
    }

    public Folder getUploadFolder() {
        return uploadFolder;
    }
}
