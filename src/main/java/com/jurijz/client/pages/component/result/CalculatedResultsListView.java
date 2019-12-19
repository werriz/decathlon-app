package com.jurijz.client.pages.component.result;

import com.jurijz.datamodel.ResultRecord;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;

import java.util.List;

public class CalculatedResultsListView extends ListView<ResultRecord> {


    public CalculatedResultsListView(String id, IModel<? extends List<ResultRecord>> model) {
        super(id, model);
    }

    @Override
    protected void populateItem(final ListItem<ResultRecord> listItem) {

        final ResultRecord item = listItem.getModelObject();

        listItem.add(new Label("place", item.getPlace()));
        listItem.add(new Label("name", item.getName()));
        listItem.add(new Label("resultSum", item.getResultSum()));
    }
}
