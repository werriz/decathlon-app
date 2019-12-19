package com.jurijz.client.pages.component.fileupload;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.file.Files;

import java.io.File;
import java.util.List;

/**
 * Created by jurijz on 2/12/2019.
 */
public class FileListView extends ListView<File> {

    FileListView(final String id, final IModel<? extends List<File>> model) {
        super(id, model);
    }

    @Override
    protected void populateItem(final ListItem<File> listItem) {

        final File file = listItem.getModelObject();

        listItem.add(new Label("fileName", file.getName()));
        listItem.add(new Link<Void>("fileDelete") {

            @Override
            public void onClick() {
                Files.remove(file); //TODO: on remove hide ListView
                info("Removed " + file.getName());
            }
        });
    }
}
