package main.java.fr.mickael.exceptions;

/**
 * Class that handles the exception when the code is invalid.
 * @author M. COZ
 *
 */
public class CodeInvalidException extends Exception {

    private static final long serialVersionUID = -2734692701890973370L;

    public CodeInvalidException(String msg){
        super(msg);
    }
}
