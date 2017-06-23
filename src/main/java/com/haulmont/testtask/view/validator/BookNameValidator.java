package com.haulmont.testtask.view.validator;

import com.vaadin.data.validator.AbstractStringValidator;

/**
 * Created by zelh on 23.06.17.
 */
public class BookNameValidator extends AbstractStringValidator {

    public BookNameValidator(String errorMessage) {
        super(errorMessage);
    }

    @Override
    protected boolean isValidValue(String s) {
        s.trim();
        if (s.matches("[a-zA-Z-а-яА-ЯёЁ0-9]+"))
            return true;
        return false;
    }
}
