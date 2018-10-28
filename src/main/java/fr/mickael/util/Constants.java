package main.java.fr.mickael.util;

import java.util.Collections;

/**
 * Class that contains constants used
 * in multiple classes.
 * @author M. COZ
 */
public class Constants {


    /**
     * Static method that print a view separator
     */
    public static final String SEPARATOR = "\n\n" + String.join("*", Collections.nCopies(15, "*"))
            +  "  %s  " + String.join("*", Collections.nCopies(15, "*")) + "\n\n";
}
