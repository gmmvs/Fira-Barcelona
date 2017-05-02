package Exceptions;

/**
 * Created by Marc Espinosa on 02/05/2017.
 */
public class ExceptionNotAnOption extends Exception{

    public ExceptionNotAnOption() {}

    //Constructor that accepts a message
    public ExceptionNotAnOption(String message)
    {
        super(message);
    }

}
