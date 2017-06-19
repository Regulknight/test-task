package com.haulmont.testtask;

import com.haulmont.testtask.controller.Controller;
import com.haulmont.testtask.model.Book;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.List;

@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(true);
        Controller controller = new Controller();
        List<Book> res = controller.getAllBooks();
        if (res.get(0) != null){
            layout.addComponent(new Label(res.get(0).toString()));
        }else{
            layout.addComponent(new Label("Hello World"));
        }

        layout.addComponent(new FormLayout());

        setContent(layout);
    }
}