package com.haulmont.testtask.view.subs;

import com.haulmont.testtask.controller.Controller;
import com.haulmont.testtask.model.Author;
import com.haulmont.testtask.model.Book;
import com.haulmont.testtask.view.validator.PatronValidator;
import com.haulmont.testtask.view.validator.StringValidator;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Validator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by zelh on 19.06.17.
 */
public class SubAuthorUI extends Window {
    private final Controller controller;
    private VerticalLayout layout;
    private Author author;
    private TextField fname;
    private TextField lname;
    private TextField patron;
    private Button save;

    public SubAuthorUI(Controller controller, Author author){
        super();
        this.controller = controller;
        this.author = author;
        this.layout = new VerticalLayout();

        setClosable(false);
        setResizable(false);
        setModal(true);
        center();

        init();
        if (author == null)
            initAdd();
        else
            initEdit();

        setContent(layout);
    }

    private void init(){
        layout.setMargin(true);
        fname = new TextField("Имя");
        lname = new TextField("Фамилия");
        patron = new TextField("Отчество");

        fname.addValidator(new StringValidator("Имя должно содержать только буквы и" +
                " не может быть пустым"));
        lname.addValidator(new StringValidator("Фамилия должна содержать только буквы и" +
                "не может быть пустым"));
        patron.addValidator(new PatronValidator("Отчество должно содержать только буквы"));

        fname.setValidationVisible(false);
        lname.setValidationVisible(false);
        patron.setValidationVisible(false);

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

        layout.addComponent(fname);
        layout.addComponent(lname);
        layout.addComponent(patron);
        layout.addComponent(buttons);

        layout.setComponentAlignment(fname, Alignment.TOP_CENTER);
        layout.setComponentAlignment(lname, Alignment.TOP_CENTER);
        layout.setComponentAlignment(patron, Alignment.TOP_CENTER);
        layout.setComponentAlignment(buttons, Alignment.TOP_CENTER);
    }

    private void initAdd(){
        this.author = new Author();

        save.addClickListener(clickEvent -> {
            if (fieldsValidation()) {
                controller.addAuthor(author);
                close();
                controller.updateUI();
            }
        });
    }

    private void initEdit(){
        fname.setValue(author.getFname());
        lname.setValue(author.getLname());
        patron.setValue(author.getPatronymic());

        save.addClickListener(clickEvent -> {
            if (fieldsValidation()) {
                controller.setAuthor(author);
                close();
                controller.updateUI();
            }
        });
    }

    private boolean fieldsValidation(){
        boolean validation = true;

        fname.setValidationVisible(false);
        try {
            fname.validate();
            author.setFname(fname.getValue());
        }catch (Validator.InvalidValueException e){
            fname.setValidationVisible(true);
            validation = false;
        }

        lname.setValidationVisible(false);
        try{
            lname.validate();
            author.setLname(lname.getValue());
        }catch (Validator.InvalidValueException e){
            lname.setValidationVisible(true);
            validation = false;
        }

        patron.setValidationVisible(false);
        try {
            patron.validate();
            author.setPatronymic(patron.getValue());
        }catch (Validator.InvalidValueException e){
            patron.setValidationVisible(true);
            validation = false;
        }

        return validation;
    }

}
