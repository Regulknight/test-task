package com.haulmont.testtask.view.validator;

import com.vaadin.data.validator.AbstractStringValidator;

/**
 * Created by zelh on 22.06.17.
 */
public class PatronValidator extends AbstractStringValidator {
    public PatronValidator(String errorMessage) {
        super(errorMessage);
    }

    @Override
    protected boolean isValidValue(String s) {
        if (s.matches("[a-zA-Z-а-яА-ЯёЁ]*"))
            return true;
        return false;
    }
}
