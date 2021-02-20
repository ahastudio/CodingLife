package com.ahastudio.propertyeditors;

import com.ahastudio.domain.FullName;

import java.beans.PropertyEditorSupport;

public class NamePropertyEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] parts = text.split("\\s");
        setValue(new FullName(parts[0], parts[1]));
    }
}
