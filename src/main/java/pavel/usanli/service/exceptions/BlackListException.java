package pavel.usanli.service.exceptions;

/**
 * Created by Pavel Usanli on 27.11.2016.
 */
public class BlackListException extends RuntimeException {
    public BlackListException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
    public BlackListException(final String message) {
        super(message);
    }
}
