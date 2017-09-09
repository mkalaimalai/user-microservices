package com.mkalaimalai.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kalaimam on 9/5/17.
 */
@Data
public class ErrorVO {

    private static final long serialVersionUID = 1L;

    private String message;
    private String description;
    private List<FieldError> fieldErrors;

    public ErrorVO(String message, String description) {
        this.message = message;
        this.description = description;
    }

    public ErrorVO(String message, String description, List<FieldError> fieldErrors) {
        this.message = message;
        this.description = description;
        this.fieldErrors = fieldErrors;
    }

    public void add(String objectName, String field, String message) {
        if (fieldErrors == null) {
            fieldErrors = new ArrayList<>();
        }
        fieldErrors.add(new FieldError(objectName, field, message));
    }


}
