package com.haulmont.testtask.view.subs;

import com.haulmont.testtask.controller.Controller;
import com.haulmont.testtask.model.Author;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

/**
 * Created by zelh on 19.06.17.
 */
public class SubAuthorUI extends Window {
    private final Controller controller;
    private Layout layout;
    private Author author;

    public SubAuthorUI(Controller controller, Author author){
        super();
        this.controller = controller;
        this.layout = new VerticalLayout();
        this.author = author;
    }

}
