package com.haulmont.testtask.view.layouts;

import com.haulmont.testtask.controller.Controller;
import com.haulmont.testtask.controller.DeleteException;
import com.haulmont.testtask.model.Author;
import com.haulmont.testtask.model.Genre;
import com.haulmont.testtask.view.MainUI;
import com.haulmont.testtask.view.subs.SubAuthorUI;
import com.haulmont.testtask.view.subs.SubGenreUI;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by zelh on 21.06.17.
 */
public class GenresLayout {
    private final Controller controller;
    private Grid genresGrid;

    public GenresLayout(Controller controller) {
        this.controller = controller;
        genresGrid = new Grid("Список жанров");
    }

    public Layout getLayout(){
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSizeFull();

        BeanItemContainer<Genre> container = new BeanItemContainer<Genre>(Genre.class, controller.getAllGenres());
        genresGrid.setContainerDataSource(container);
        genresGrid.setSizeFull();
        genresGrid.setWidth("95%");
        genresGrid.setColumnOrder("id", "name");

        layout.addComponent(genresGrid);
        layout.setComponentAlignment(genresGrid, Alignment.TOP_CENTER);

        Button addButton = new Button("Добавить");
        addButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        addButton.addClickListener(clickEvent -> {
            SubGenreUI subGenreUI = new SubGenreUI(controller, null);
            MainUI.getCurrent().addWindow(subGenreUI);
        });

        Button editButton = new Button("Изменить");
        editButton.addClickListener(clickEvent -> {
            Genre genre = (Genre) genresGrid.getSelectedRow();
            SubGenreUI subGenreUI = new SubGenreUI(controller, genre);
            MainUI.getCurrent().addWindow(subGenreUI);
        });

        Button deleteButton = new Button("Удалить");
        deleteButton.addStyleName(ValoTheme.BUTTON_DANGER);
        deleteButton.addClickListener(clickEvent -> {
            if (genresGrid.getSelectedRow() != null)
                try {
                    controller.deleteGenre(((Genre) genresGrid.getSelectedRow()).getId());
                } catch (DeleteException e) {
                    new Notification("Невозможно удалить жанр", Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
                }
            else{
                new Notification("Выберите жанр", Notification.Type.WARNING_MESSAGE).show(Page.getCurrent());
            }
        });

        Button statButton = new Button("Показать статистику");


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
        genresGrid.setContainerDataSource(new BeanItemContainer<Genre>(Genre.class, controller.getAllGenres()));
    }
}
