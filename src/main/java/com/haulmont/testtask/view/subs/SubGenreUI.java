package com.haulmont.testtask.view.subs;

import com.haulmont.testtask.controller.Controller;
import com.haulmont.testtask.model.Author;
import com.haulmont.testtask.model.Genre;
import com.haulmont.testtask.view.validator.StringValidator;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by zelh on 19.06.17.
 */
public class SubGenreUI extends Window {
    private final Controller controller;
    private Genre genre;
    private VerticalLayout layout;
    private TextField name;
    private Button save;

    public SubGenreUI(Controller controller, Genre genre) {
        super();
        this.controller = controller;
        this.genre = genre;
        this.layout = new VerticalLayout();

        setClosable(false);
        setResizable(false);
        setModal(true);
        center();

        init();
        if (genre == null)
            initAdd();
        else
            initEdit();

        setContent(layout);
    }

    private void init(){
        layout.setMargin(true);
        name = new TextField("Название");

        name.addValidator(new StringValidator("Название жанра должно содержать только буквы"));

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setMargin(true);
        buttons.setSpacing(true);

        save = new Button("OK");
        save.addStyleName(ValoTheme.BUTTON_FRIENDLY);

        Button cancel = new Button("Отменить");
        cancel.addStyleName(ValoTheme.BUTTON_DANGER);
        cancel.addClickListener(clickEvent -> {
            close();
        });

        buttons.addComponent(save);
        buttons.addComponent(cancel);

        layout.addComponent(name);
        layout.addComponent(buttons);

        layout.setComponentAlignment(name, Alignment.TOP_CENTER);
        layout.setComponentAlignment(buttons, Alignment.TOP_CENTER);
    }

    private void initAdd(){
        save.addClickListener(clickEvent -> {
            Genre genre = new Genre();

            name.validate();

            genre.setName(name.getValue());
            controller.addGenre(genre);
            close();
        });
    }

    private void initEdit(){
        name.setValue(genre.getName());
        save.addClickListener(clickEvent -> {
            name.validate();
            genre.setName(name.getValue());
            controller.addGenre(genre);
            close();
        });
    }

}