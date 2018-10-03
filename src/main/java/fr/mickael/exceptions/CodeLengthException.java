package main.java.fr.mickael.exceptions;

public class CodeLengthException extends Exception {
    private int codeLengthError;

    public CodeLengthException(String msg){
        super(msg);
    }
}
