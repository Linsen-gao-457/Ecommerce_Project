package com.ecommerce.sportscenter.model;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class CustomErrorResponseTest {

    @Test
    void testCustomErrorResponseGettersAndSetters() {
        // Create a new CustomErrorResponse instance using the default constructor
        CustomErrorResponse errorResponse = new CustomErrorResponse();

        // Test setter and getter for status
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getStatus(), "The status should be BAD_REQUEST");

        // Test setter and getter for error
        errorResponse.setError("Invalid Request");
        assertEquals("Invalid Request", errorResponse.getError(), "The error message should be 'Invalid Request'");

        // Test setter and getter for message
        errorResponse.setMessage("The request parameters are invalid.");
        assertEquals("The request parameters are invalid.", errorResponse.getMessage(), "The message should match the provided string");
    }

    @Test
    void testCustomErrorResponseConstructor() {
        // Create a new CustomErrorResponse instance using the all-args constructor
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Server Error",
                "An unexpected error occurred on the server."
        );

        // Test constructor assignment
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, errorResponse.getStatus(), "The status should be INTERNAL_SERVER_ERROR");
        assertEquals("Server Error", errorResponse.getError(), "The error message should be 'Server Error'");
        assertEquals("An unexpected error occurred on the server.", errorResponse.getMessage(), "The message should match the provided string");
    }

    @Test
    void testCustomErrorResponseBuilder() {
        // Create a new CustomErrorResponse using the builder (if Lombok's builder annotation were used)
        CustomErrorResponse errorResponse = new CustomErrorResponse(
                HttpStatus.NOT_FOUND,
                "Not Found",
                "The resource you are looking for could not be found."
        );

        // Test builder assignment
        assertEquals(HttpStatus.NOT_FOUND, errorResponse.getStatus(), "The status should be NOT_FOUND");
        assertEquals("Not Found", errorResponse.getError(), "The error message should be 'Not Found'");
        assertEquals("The resource you are looking for could not be found.", errorResponse.getMessage(), "The message should match the provided string");
    }
}
