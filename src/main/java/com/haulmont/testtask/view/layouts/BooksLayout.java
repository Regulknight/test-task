package com.haulmont.testtask.view.layouts;

import com.haulmont.testtask.controller.Controller;
import com.haulmont.testtask.model.Book;
import com.haulmont.testtask.view.MainUI;
import com.haulmont.testtask.view.subs.SubBookUI;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by zelh on 21.06.17.
 */
public class BooksLayout {
    private final Controller controller;
    private Grid booksGrid = new Grid();

    public BooksLayout(Controller controller) {
        this.controller = controller;
    }

    public Layout getLayout(){
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSizeFull();

        BeanItemContainer<Book> container = new BeanItemContainer<Book>(Book.class, controller.getAllBooks());
        booksGrid.setContainerDataSource(container);
        booksGrid.setSizeFull();
        booksGrid.setWidth("95%");
        booksGrid.setColumnOrder("id", "name", "year", "city", "author", "genre", "publisher");

        layout.addComponent(booksGrid);
        layout.setComponentAlignment(booksGrid, Alignment.TOP_CENTER);

        Button addButton = new Button("Добавить");
        addButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        addButton.addClickListener(clickEvent -> {
            SubBookUI subBookUI = new SubBookUI(controller, null);
            MainUI.getCurrent().addWindow(subBookUI);
        });

        Button editButton = new Button("Изменить");
        editButton.addClickListener(clickEvent -> {
            Book book = (Book) booksGrid.getSelectedRow();
            SubBookUI subBookUI = new SubBookUI(controller, book);
            MainUI.getCurrent().addWindow(subBookUI);
        });

        Button deleteButton = new Button("Удалить");
        deleteButton.addStyleName(ValoTheme.BUTTON_DANGER);
        deleteButton.addClickListener(clickEvent -> {
            controller.deleteBook(((Book) booksGrid.getSelectedRow()).getId());
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

    public void updateGrid(){
        booksGrid.setContainerDataSource(new BeanItemContainer<>(Book.class, controller.getAllBooks()));
    }
}
