package com.LMS.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestNotUsableException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ================= USER =================

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(
            UserNotFoundException ex) {

        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage(), 404),
                HttpStatus.NOT_FOUND);
    }

    // ================= COURSE =================

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCourseNotFound(
            CourseNotFoundException ex) {

        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage(), 404),
                HttpStatus.NOT_FOUND);
    }

    // ================= ENROLLMENT =================

    @ExceptionHandler(EnrollmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEnrollmentNotFound(
            EnrollmentNotFoundException ex) {

        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage(), 404),
                HttpStatus.NOT_FOUND);
    }

    // ================= ASSIGNMENT =================

    @ExceptionHandler(AssignmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAssignmentNotFound(
            AssignmentNotFoundException ex) {

        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage(), 404),
                HttpStatus.NOT_FOUND);
    }

    // ================= QUIZ =================

    @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleQuizNotFound(
            QuizNotFoundException ex) {

        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage(), 404),
                HttpStatus.NOT_FOUND);
    }

    // ================= DUPLICATE RESOURCE =================

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleResourceExists(
            ResourceAlreadyExistsException ex) {

        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage(), 409),
                HttpStatus.CONFLICT);
    }

    // ================= UNAUTHORIZED =================

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorized(
            UnauthorizedException ex) {

        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage(), 401),
                HttpStatus.UNAUTHORIZED);
    }

    // ================= VALIDATION =================

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()));

        return new ResponseEntity<>(
                errors,
                HttpStatus.BAD_REQUEST);
    }

    // ================= CLIENT DISCONNECT =================

    @ExceptionHandler(AsyncRequestNotUsableException.class)
    public void handleAsyncDisconnect(
            AsyncRequestNotUsableException ex) {

        System.out.println(
                "Client disconnected while downloading/streaming file."
        );
    }

    // ================= RUNTIME =================

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(
            RuntimeException ex) {

        return new ResponseEntity<>(
                new ErrorResponse(
                        ex.getMessage(),
                        400
                ),
                HttpStatus.BAD_REQUEST);
    }

    // ================= GENERIC =================

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(
            Exception ex) {

        // Ignore browser disconnect errors
        if (ex.getClass().getName()
                .contains("ClientAbortException")) {

            System.out.println(
                    "Client disconnected while downloading file."
            );

            return ResponseEntity.ok().build();
        }

        ex.printStackTrace();

        return new ResponseEntity<>(
                new ErrorResponse(
                        ex.getMessage(),
                        500
                ),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}