package com.haulmont.testtask.view.subs;


import com.haulmont.testtask.controller.Controller;
import com.haulmont.testtask.model.Genre;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.*;

/**
 * Created by zelh on 22.06.17.
 */
public class StatUI extends Window {
    private final Controller controller;

    public StatUI(Controller controller){
        super();
        this.controller = controller;

        setClosable(false);
        setResizable(false);
        setModal(true);
        center();


        VerticalLayout verticalLayout = new VerticalLayout();

        Grid grid = new Grid();
        grid.addColumn("Id", Long.class);
        grid.addColumn("name", String.class);
        grid.addColumn("stat", Integer.class);

        for(Object[] data: controller.getGenresStats()){
            Genre genre = controller.getGenre((Long) data[0]);
            grid.addRow(genre.getId(), genre.getName(), data[1]);
        }

        Button closeButton = new Button("Закрыть");
        closeButton.addClickListener(clickEvent -> close());

        verticalLayout.addComponent(grid);
        verticalLayout.addComponent(closeButton);

        verticalLayout.setComponentAlignment(closeButton, Alignment.BOTTOM_RIGHT);

        setContent(verticalLayout);
    }
}
