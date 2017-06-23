package com.haulmont.testtask.view.validator;

import com.haulmont.testtask.controller.Controller;
import com.haulmont.testtask.controller.DeleteException;
import com.haulmont.testtask.model.Author;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.AbstractStringValidator;
import com.vaadin.data.validator.NullValidator;

/**
 * Created by zelh on 23.06.17.
 */
public class AuthorValidator implements Validator {
    private final Controller controller;
    private String message;

    public AuthorValidator(String message, Controller controller){
        this.controller = controller;
        this.message = message;
    }

    @Override
    public void validate(Object o) throws InvalidValueException {
        if (o == null)
            throw new InvalidValueException(message);
        if (controller.getAuthor(((Author) o).getId()) == null)
            throw new InvalidValueException("Автор был удалён");
    }
}
