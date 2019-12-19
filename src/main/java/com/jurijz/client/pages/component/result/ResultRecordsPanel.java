package com.jurijz.client.pages.component.result;

import com.jurijz.client.facade.DecathlonFacade;
import com.jurijz.datamodel.FinalResults;
import com.jurijz.datamodel.ResultRecord;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

public class ResultRecordsPanel extends Panel {

    @SpringBean
    private DecathlonFacade facade;

    public ResultRecordsPanel(String id, Model<FinalResults> model) {
        super(id, model);
        init(model);
    }

    private void init(Model<FinalResults> model) {

        // Create feedback panels
        final FeedbackPanel feedback = new FeedbackPanel("feedback");

        // Add feedback to the page itself
        add(feedback);

        final ResultListView resultListView = new ResultListView("resultList", new LoadableDetachableModel<>()
        {
            @Override
            protected List<ResultRecord> load()
            {
                return model.getObject().getResultRecords();
            }
        });
        add(resultListView);
        WebMarkupContainer panel = new WebMarkupContainer("calculatedResultsPanel");
        final CalculatedResultsListView calculatedResults = new CalculatedResultsListView("calculatedResultsList",
                new LoadableDetachableModel<>()
                {
                    @Override
                    protected List<ResultRecord> load()
                    {
                        return model.getObject().getResultRecords();
                    }
                });
        calculatedResults.setOutputMarkupPlaceholderTag(true);
        panel.setOutputMarkupPlaceholderTag(true);
        panel.setVisible(false);
        panel.add(calculatedResults);
        add(panel);

        AjaxLink<Void> calculateResultBtn = new AjaxLink<>("calculateResultBtn") {

            @Override
            public void onClick(AjaxRequestTarget target) {
                facade.calculateResults(model.getObject().getResultRecords());
                panel.setVisible(true);
                target.add(panel);
            }
        };
        add(calculateResultBtn);

        add(new Label("panelMsg", "Result Records"));
    }
}
