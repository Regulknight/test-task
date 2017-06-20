package com.haulmont.testtask.view;

import com.haulmont.testtask.controller.Controller;
import com.haulmont.testtask.model.Book;
import com.vaadin.ui.Window;

/**
 * Created by zelh on 19.06.17.
 */
public class SubBookUI extends Window{
    private final Controller controller;
    private Book book;


    public SubBookUI(Controller controller, Book book) {
        super();
        this.controller = controller;

    }


}
