package Challengesemester2024.Exception.collections.InputValid;

import org.springframework.validation.FieldError;
import java.util.List;
import java.util.stream.Collectors;

public class BindingErrors extends InvalidRequestException{
    public BindingErrors(List<FieldError> fieldErrors) {
        super(fieldErrors.stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList()));
    }
}
