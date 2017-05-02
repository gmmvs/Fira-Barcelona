package Exceptions;

/**
 * Created by Marc Espinosa on 02/05/2017.
 */
public class WrongCredentials extends Exception {

    public WrongCredentials() {}

    //Constructor that accepts a message
    public WrongCredentials(String message)
    {
        super(message);
    }

}
