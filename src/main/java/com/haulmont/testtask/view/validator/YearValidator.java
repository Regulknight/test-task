package com.haulmont.testtask.view.validator;

import com.vaadin.data.Validator;
import com.vaadin.data.validator.AbstractStringValidator;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by zelh on 21.06.17.
 */
public class YearValidator extends AbstractStringValidator{
    public YearValidator(String errorMessage) {
        super(errorMessage);
    }

    @Override
    protected boolean isValidValue(String s) {
        int year = 0;

        try {
            year = Integer.parseInt(s);
        }catch (NumberFormatException e){
            return false;
        }

        if (year > Calendar.getInstance().get(Calendar.YEAR))
            return false;

        return true;
    }
}
