package com.revature.Exception;

/**
 * Exception for being unable to clone a particular lab. This may be because your
 * credentials for accessing our private organization are not configured, or because
 * the lab does not exist. We have no easy way to differentiate the two, so this
 * exception is used generally for both.
 *
 * Exceptions are events (usually from an external source) that the program can
 * not be expected to anticipate. The 'extends' keyword is used to make this class
 * inherit Exception behavior and therefore become a custom Exception type. The super
 * keyword, seen within this class's constructor, just refers to the constructor of
 * the parent class (Exception).
 */
public class LabOpenException extends LabException{
    public LabOpenException(String message){
        super(message);
    }
}
