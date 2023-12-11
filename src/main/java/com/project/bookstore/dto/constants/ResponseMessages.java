package com.project.bookstore.dto.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessages {
	
	CATEGORY_CREATED("Category created"),
    CATEGORY_UPDATED("Category updated"),
    CATEGORY_DELETED("Category deleted"),

    BOOK_CREATED("Book created"),
    BOOK_UPDATED("Book updated"),
    BOOK_DELETED("Book deleted"),
    
    USER_CREATED("User created"),
    USER_UPDATED("User updated"),
    USER_DELETED("User deleted"),
    
    PROFİLE_UPDATED("Profile updated"),
	

    LOGIN_SUCCESS("Login Successful"),
    USER_IN_USE("Username already in use."),
    REGISTERED("User successfully registered."),
    TOKEN_REFRESHED("Token successfully refreshed."),
    TOKEN_NOT_VALID("Refresh token is not valid.");

    private final String Message;

}
