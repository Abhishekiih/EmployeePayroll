package com.bridgelabz.employeepayrollapp.exceptions;

import com.bridgelabz.employeepayrollapp.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class EmployeePayrollExceptionalHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        List<String> errMsg = errorList.stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());

        ResponseDTO responseDTO = new ResponseDTO("Exception while processing REST Request", errMsg);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleEmployeeNotFoundException(EmployeeNotFoundException exception) {
        ResponseDTO responseDTO = new ResponseDTO("Employee Not Found", exception.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleGlobalException(Exception exception) {
        ResponseDTO responseDTO = new ResponseDTO("Internal Server Error", exception.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

