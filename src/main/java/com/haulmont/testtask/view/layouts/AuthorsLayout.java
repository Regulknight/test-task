package com.haulmont.testtask.view.layouts;

import com.haulmont.testtask.controller.Controller;
import com.haulmont.testtask.controller.DeleteException;
import com.haulmont.testtask.model.Author;
import com.haulmont.testtask.view.MainUI;
import com.haulmont.testtask.view.subs.SubAuthorUI;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by zelh on 21.06.17.
 */

public class AuthorsLayout {
    private final Controller controller;
    private Grid authorsGrid;

    public AuthorsLayout(Controller controller) {
        this.controller = controller;
        authorsGrid = new Grid("Список авторов");
    }

    public Layout getLayout(){
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSizeFull();

        BeanItemContainer<Author> container = new BeanItemContainer<Author>(Author.class, controller.getAllAuthors());
        authorsGrid.setContainerDataSource(container);
        authorsGrid.setSizeFull();
        authorsGrid.setWidth("95%");
        authorsGrid.setColumnOrder("id", "fname", "lname", "patronymic");

        layout.addComponent(authorsGrid);
        layout.setComponentAlignment(authorsGrid, Alignment.TOP_CENTER);

        Button addButton = new Button("Добавить");
        addButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        addButton.addClickListener(clickEvent -> {
            SubAuthorUI subAuthorUI = new SubAuthorUI(controller, null);
            MainUI.getCurrent().addWindow(subAuthorUI);
        });

        Button editButton = new Button("Изменить");
        editButton.addClickListener(clickEvent -> {
            Author author = (Author) authorsGrid.getSelectedRow();
            SubAuthorUI subAuthorUI = new SubAuthorUI(controller, author);
            MainUI.getCurrent().addWindow(subAuthorUI);
        });

        Button deleteButton = new Button("Удалить");
        deleteButton.addStyleName(ValoTheme.BUTTON_DANGER);
        deleteButton.addClickListener(clickEvent -> {
            if (authorsGrid.getSelectedRow() != null)
                try {
                    controller.deleteAuthor(((Author) authorsGrid.getSelectedRow()).getId());
                } catch (DeleteException e) {
                    new Notification("Невозможно удалить автора", Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
                }
            else{
                new Notification("Выберите автора", Notification.Type.WARNING_MESSAGE).show(Page.getCurrent());
            }
        });

        HorizontalLayout horizontalLayout = new HorizontalLayout();

        horizontalLayout.addComponent(addButton);
        horizontalLayout.addComponent(editButton);
        horizontalLayout.addComponent(deleteButton);
        horizontalLayout.setSpacing(true);
        horizontalLayout.setMargin(true);

        layout.addComponent(horizontalLayout);
        layout.setComponentAlignment(horizontalLayout, Alignment.TOP_RIGHT);

        return layout;
    }

    public void updateLayout(){
        authorsGrid.setContainerDataSource(new BeanItemContainer<Author>(Author.class, controller.getAllAuthors()));
    }


}
