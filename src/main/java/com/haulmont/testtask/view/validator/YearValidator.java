package com.haulmont.testtask.view.validator;

import com.vaadin.data.Validator;
import com.vaadin.data.validator.AbstractStringValidator;

/**
 * Created by zelh on 21.06.17.
 */
public class YearValidator extends AbstractStringValidator{
    public YearValidator(String errorMessage) {
        super(errorMessage);
    }

    @Override
    protected boolean isValidValue(String s) {
        if (s.matches("[0-9]+"))
            return true;
        return false;
    }
}
