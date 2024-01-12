package com.revature.Exception;

/**
 * Exception thrown when the user inputs a command that the program can not
 * interpret.
 *
 * Exceptions are events (usually from an external source) that the program can
 * not be expected to anticipate. The 'extends' keyword is used to make this class
 * inherit Exception behavior and therefore become a custom Exception type. The super
 * keyword, seen within this class's constructor, just refers to the constructor of
 * the parent class (Exception).
 */
public class BadCommandException extends Exception{
    public BadCommandException(String message){
        super(message);
    }
}
