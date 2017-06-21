package com.haulmont.testtask.view.subs;

import com.haulmont.testtask.controller.Controller;
import com.haulmont.testtask.model.Author;
import com.haulmont.testtask.model.Book;
import com.haulmont.testtask.model.Genre;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by zelh on 19.06.17.
 */
public class SubBookUI extends Window {
    private final Controller controller;
    private Layout layout;
    private Book book;
    private Button save;
    private TextField name;
    private TextField year;
    private TextField city;
    private ComboBox author;
    private ComboBox genre;
    private ComboBox publisher;

    public SubBookUI(Controller controller, Book book) {
        super();
        this.controller = controller;
        this.book = book;
        setClosable(false);
        setResizable(false);
        setModal(true);
        center();
        layout = init();
        if (book == null){
            initAdd();
        }else {
            initEdit();
        }
        setContent(layout);
    }

    private VerticalLayout init() {
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);


        name = new TextField("Name");
        year = new TextField("Year");
        city = new TextField("City");
        author = new ComboBox("Author", controller.getAllAuthors());
        genre = new ComboBox("Genre", controller.getAllGenres());
        publisher = new ComboBox("Publisher");
        publisher.addItem(new String("Москва"));
        publisher.addItem(new String("Питер"));
        publisher.addItem(new String("O'Really"));

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
        layout.addComponent(year);
        layout.addComponent(city);
        layout.addComponent(author);
        layout.addComponent(genre);
        layout.addComponent(publisher);
        layout.addComponent(buttons);

        layout.setComponentAlignment(name, Alignment.TOP_CENTER);
        layout.setComponentAlignment(year, Alignment.TOP_CENTER);
        layout.setComponentAlignment(city, Alignment.TOP_CENTER);
        layout.setComponentAlignment(author, Alignment.TOP_CENTER);
        layout.setComponentAlignment(genre, Alignment.TOP_CENTER);
        layout.setComponentAlignment(publisher, Alignment.TOP_CENTER);
        layout.setComponentAlignment(buttons, Alignment.MIDDLE_CENTER);

        return layout;
    }

    private void initAdd(){

        save.addClickListener(clickEvent -> {
            this.book = new Book();
            book.setName(name.getValue());
            book.setYear(Integer.valueOf(year.getValue()));
            book.setCity(city.getValue());
            book.setAuthor((Author) author.getValue());
            book.setGenre((Genre) genre.getValue());
            book.setPublisher((String) publisher.getValue());
            controller.addBook(book);
            close();
        });
    }

    private void initEdit(){
        name.setValue(book.getName());
        year.setValue(String.valueOf(book.getYear()));
        city.setValue(book.getCity());
        author.setValue(book.getAuthor());
        genre.setValue(book.getGenre());
        publisher.setValue(book.getPublisher());
        save.addClickListener(clickEvent -> {
            book.setName(name.getValue());
            book.setYear(Integer.valueOf(year.getValue()));
            book.setCity(city.getValue());
            book.setAuthor((Author) author.getValue());
            book.setGenre((Genre) genre.getValue());
            book.setPublisher((String) publisher.getValue());
            controller.setBook(book);
            close();
        });
    }

}
