package main.java.fr.mickael.exceptions;

public class CodeLengthException extends Exception {
    private int codeLengthError;

    public CodeLengthException(int codeLengthError){
        codeLengthError = codeLengthError;
    }
}
