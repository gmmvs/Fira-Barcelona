package Exceptions;

/**
 * Created by Marc Espinosa on 01/05/2017.
 */
public class UringCredentials extends Exception {

    public UringCredentials() {}

    //Constructor that accepts a message
    public UringCredentials(String message)
    {
        super(message);
    }

}
