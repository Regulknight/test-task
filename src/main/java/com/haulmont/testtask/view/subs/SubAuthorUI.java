package com.haulmont.testtask.view.subs;

import com.haulmont.testtask.controller.Controller;
import com.haulmont.testtask.model.Author;
import com.haulmont.testtask.model.Book;
import com.haulmont.testtask.view.validator.PatronValidator;
import com.haulmont.testtask.view.validator.StringValidator;
import com.vaadin.annotations.VaadinServletConfiguration;
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

        fname.addValidator(new StringValidator("Имя должно содержать только буквы"));
        lname.addValidator(new StringValidator("Фамилия должна содержать только буквы"));
        patron.addValidator(new PatronValidator("Отчество должно содержать только буквы"));

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
        save.addClickListener(clickEvent -> {
            fname.validate();
            lname.validate();
            patron.validate();

            Author author = new Author();
            author.setFname(fname.getValue());
            author.setLname(lname.getValue());
            author.setPatronymic(patron.getValue());
            controller.addAuthor(author);
            close();
        });
    }

    private void initEdit(){
        fname.validate();
        lname.validate();
        patron.validate();

        fname.setValue(author.getFname());
        lname.setValue(author.getLname());
        patron.setValue(author.getPatronymic());

        save.addClickListener(clickEvent -> {
            author.setFname(fname.getValue());
            author.setLname(lname.getValue());
            author.setPatronymic(patron.getValue());
            controller.setAuthor(author);
            close();
        });
    }

}
