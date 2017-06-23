package com.haulmont.testtask.view.validator;

import com.haulmont.testtask.controller.Controller;
import com.haulmont.testtask.model.Author;
import com.haulmont.testtask.model.Genre;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.AbstractStringValidator;
import com.vaadin.data.validator.NullValidator;

/**
 * Created by zelh on 23.06.17.
 */
public class GenreValidator implements Validator{
    private final Controller controller;
    private String message;

    public GenreValidator(String message, Controller controller){
        this.controller = controller;
        this.message = message;
    }

    @Override
    public void validate(Object o) throws InvalidValueException {
        if (o == null)
            throw new InvalidValueException(message);
        if (controller.getGenre(((Genre) o).getId()) == null)
            throw  new InvalidValueException("Жанр был удалён");
    }

}
