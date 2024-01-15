package com.revature.Util;

/**
 * A utility class containing a static variable representing the source URL of our course content.
 * A static variable is one that is tied to the URLUtil class, and not any particular URLUtil
 * object. This is because it doesn't make sense to have to instantiate a URLUtil object prior to
 * leveraging code that should be immediately usable by any part of the program.
 * You can think of the 'static' variable as breaking away from the object-oriented paradigm.
 */
public class URLUtil {
    public static final String BASE_URL = "https://github.com/geico-wft-labs/";

    /**
     * A blank private constructor prevents unintended
     * instantiation of this class.
     */
    private URLUtil(){

    }
}
