package Exceptions;

/**
 * Created by Marc Espinosa on 01/05/2017.
 */
public class ExceptionNotAnUser extends Exception {

    public ExceptionNotAnUser() {}

    //Constructor that accepts a message
    public ExceptionNotAnUser(String message)
    {
        super(message);
    }

}
