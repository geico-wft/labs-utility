package com.revature.Exception;

/**
 * Exception thrown when the user's git configuration would make it possible to perform Lab operations.
 */
public class GitConfigException extends Exception{
    public GitConfigException(String message){
        super(message);
    }
}
