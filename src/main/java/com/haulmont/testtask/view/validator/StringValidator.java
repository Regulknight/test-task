package com.haulmont.testtask.view.validator;

import com.vaadin.data.validator.AbstractStringValidator;

/**
 * Created by zelh on 21.06.17.
 */
public class StringValidator extends AbstractStringValidator {

    public StringValidator(String errorMessage) {
        super(errorMessage);
    }

    @Override
    protected boolean isValidValue(String s) {
        s.trim();
        if (s.matches("[a-zA-Z-а-яА-ЯёЁ]+"))
            return true;
        return false;
    }
}
