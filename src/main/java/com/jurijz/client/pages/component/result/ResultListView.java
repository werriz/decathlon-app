package com.jurijz.client.pages.component.result;

import com.jurijz.datamodel.ResultRecord;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;

import java.util.List;

public class ResultListView extends ListView<ResultRecord> {


    public ResultListView(String id, IModel<? extends List<ResultRecord>> model) {
        super(id, model);
    }

    @Override
    protected void populateItem(ListItem<ResultRecord> listItem) {

        final ResultRecord item = listItem.getModelObject();

        listItem.add(new Label("name", item.getName()));
        listItem.add(new Label("run100m", item.getRun100m()));
        listItem.add(new Label("longJump", item.getLongJump()));
        listItem.add(new Label("shotPut", item.getShotPut()));
        listItem.add(new Label("highJump", item.getHighJump()));
        listItem.add(new Label("run400m", item.getRun400m()));
        listItem.add(new Label("run110mHurdles", item.getRun110mHurdles()));
        listItem.add(new Label("discusThrow", item.getDiscusThrow()));
        listItem.add(new Label("poleVault", item.getPoleVault()));
        listItem.add(new Label("javelinThrow", item.getJavelinThrow()));
        listItem.add(new Label("run1500m", item.getRun1500m()));

    }
}
