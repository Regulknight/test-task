package com.haulmont.testtask.view.layouts;

import com.haulmont.testtask.controller.Controller;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Layout;

/**
 * Created by zelh on 21.06.17.
 */
public class GenresLayout {
    private final Controller controller;
    private Grid genresGrid;

    public GenresLayout(Controller controller) {
        this.controller = controller;
    }

    public Layout getLayout(){
        return null;
    }

    public void updateLayout(){

    }
}
