package com.haulmont.testtask.view.layouts;

import com.haulmont.testtask.controller.Controller;
import com.haulmont.testtask.controller.DeleteException;
import com.haulmont.testtask.model.Book;
import com.haulmont.testtask.view.MainUI;
import com.haulmont.testtask.view.subs.SubBookUI;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by zelh on 21.06.17.
 */
public class BooksLayout {
    private final Controller controller;
    private Grid booksGrid;
    TextField nameFilter;
    TextField authorFilter;
    TextField publisherFilter;
    Button applyFilters;

    public BooksLayout(Controller controller) {
        this.controller = controller;
        booksGrid = new Grid("Cписок книг");
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

        HorizontalLayout filters = new HorizontalLayout();
        filters.setMargin(true);
        filters.setSpacing(true);
        filters.setSizeFull();

        nameFilter = new TextField("Название");
        authorFilter = new TextField("Автор");
        publisherFilter = new TextField("Издатель");
        applyFilters = new Button("Применить");
        applyFilters.addStyleName(ValoTheme.BUTTON_FRIENDLY);

        addFilters(container);

        filters.addComponent(nameFilter);
        filters.addComponent(authorFilter);
        filters.addComponent(publisherFilter);
        filters.addComponent(applyFilters);

        filters.setComponentAlignment(applyFilters, Alignment.BOTTOM_RIGHT);

        Panel panel = new Panel("Фильтр");
        filters.setSizeUndefined();

        panel.setContent(filters);
        panel.setWidth("95%");

        layout.addComponent(panel);
        layout.setComponentAlignment(panel, Alignment.TOP_CENTER);

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
            if (booksGrid.getSelectedRow() != null)
                try {
                    controller.deleteBook((Book) booksGrid.getSelectedRow());
                } catch (DeleteException e) {
                    new Notification("Невозможно удалить книгу", Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
                }
            else{
                new Notification("Выберите книгу для удаления").show(Page.getCurrent());
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
        BeanItemContainer<Book> container = new BeanItemContainer<Book>(Book.class, controller.getAllBooks());
        booksGrid.setContainerDataSource(container);
        addFilters(container);
    }

    private void addFilters(BeanItemContainer<Book> container){
        applyFilters.addClickListener(clickEvent -> {
            container.removeContainerFilters("name");
            if (!nameFilter.getValue().isEmpty())
                container.addContainerFilter(new SimpleStringFilter("name", nameFilter.getValue(),
                        true, false));
            container.removeContainerFilters("author");
            if (!nameFilter.getValue().isEmpty())
                container.addContainerFilter(new SimpleStringFilter("author", authorFilter.getValue(),
                        true, false));
            container.removeContainerFilters("publisher");
            if (!nameFilter.getValue().isEmpty())
                container.addContainerFilter(new SimpleStringFilter("publisher", publisherFilter.getValue(),
                        true, false));
        });


    }
}
