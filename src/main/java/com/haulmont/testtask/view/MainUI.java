package com.haulmont.testtask.view;

import com.haulmont.testtask.controller.Controller;
import com.haulmont.testtask.model.Book;
import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.List;

@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        Controller controller = new Controller();

        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSizeFull();


        BeanItemContainer<Book> container = new BeanItemContainer<Book>(Book.class, controller.getAllBooks());
        Grid grid = new Grid(container);
        grid.setWidth("95%");
        grid.setColumnOrder("id", "name", "year", "city", "author", "genre", "publisher");
        layout.addComponent(grid);
        layout.setComponentAlignment(grid, Alignment.TOP_CENTER);

        Button addButton = new Button("Add new book");
        addButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
            addButton.addClickListener(clickEvent -> {

            });

        Button editButton = new Button("Edit book");
            editButton.addClickListener(clickEvent -> {
                grid.getSelectedRow();
            });

        Button deleteButton = new Button("Delete");
        deleteButton.addStyleName(ValoTheme.BUTTON_DANGER);
            deleteButton.addClickListener(clickEvent -> {
                grid.getSelectedRow();
            });

        HorizontalLayout horizontalLayout = new HorizontalLayout();

        horizontalLayout.addComponent(addButton);
        horizontalLayout.addComponent(editButton);
        horizontalLayout.addComponent(deleteButton);
        horizontalLayout.setSpacing(true);
        horizontalLayout.setMargin(true);



        layout.addComponent(horizontalLayout);
        layout.setComponentAlignment(horizontalLayout, Alignment.TOP_RIGHT);
        setContent(layout);
    }


}