package com.haulmont.testtask.view.subs;

import com.haulmont.testtask.controller.Controller;
import com.haulmont.testtask.model.Author;
import com.haulmont.testtask.model.Genre;
import com.haulmont.testtask.view.validator.StringValidator;
import com.vaadin.data.Validator;
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

        name.addValidator(new StringValidator("Название жанра должно содержать только буквы" +
                " и не может быть пустым"));
        name.setValidationVisible(false);
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setMargin(true);
        buttons.setSpacing(true);

        save = new Button("OK");
        save.addStyleName(ValoTheme.BUTTON_FRIENDLY);

        Button cancel = new Button("Отменить");
        cancel.addStyleName(ValoTheme.BUTTON_DANGER);
        cancel.addClickListener(clickEvent -> {
            close();
            controller.updateUI();
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
            this.genre = new Genre();
            if (fieldsValidation()) {
                controller.addGenre(genre);
                close();
                controller.updateUI();
            }
        });
    }

    private void initEdit(){
        name.setValue(genre.getName());
        save.addClickListener(clickEvent -> {
            if (fieldsValidation()){
                controller.setGenre(genre);
                close();
                controller.updateUI();
            }
        });
    }

    private boolean fieldsValidation(){
        boolean validation = true;

        name.setValidationVisible(false);
        try {
            name.validate();
            genre.setName(name.getValue());
        }catch (Validator.InvalidValueException e){
            name.setValidationVisible(true);
            validation = false;
        }

        return validation;
    }

}