package com.revature.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Singleton is a design pattern in which a class is made to manage a single object
 * intended to be shared across the application - the first time the object is requested,
 * it is instantiated, and all future accesses of that object retrieve the already
 * instantiated object.
 */
public class LoggerSingleton {

    private static Logger logger;

    /**
     * Method returns a logger singleton, intended for use in logging across the entire application.
     * @return logger singleton
     */
    public static Logger getLogger(){
        if(logger == null){
            logger = LoggerFactory.getLogger(LoggerSingleton.class);
        }
        return logger;
    }
    /**
     * Following convention for the singleton design pattern, a blank private constructor prevents unintended
     * instantiation of this class.
     */
    private LoggerSingleton(){

    }
}
