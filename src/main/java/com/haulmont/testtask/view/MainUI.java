package com.haulmont.testtask.view;

import com.haulmont.testtask.controller.Controller;
import com.haulmont.testtask.view.layouts.AuthorsLayout;
import com.haulmont.testtask.view.layouts.BooksLayout;
import com.haulmont.testtask.view.layouts.GenresLayout;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {
    private BooksLayout booksLayout;
    private AuthorsLayout authorsLayout;
    private GenresLayout genresLayout;

    @Override
    protected void init(VaadinRequest request) {
        Controller controller = new Controller(this);
        booksLayout = new BooksLayout(controller);
        authorsLayout = new AuthorsLayout(controller);
        setContent(authorsLayout.getLayout());

    }

    public void updateBooksLayout(){
        booksLayout.updateGrid();
    }

    public void updateAuthorsLayout(){
        authorsLayout.updateLayout();
    }

    public void updateGenresLayout(){
        genresLayout.updateLayout();
    }


}