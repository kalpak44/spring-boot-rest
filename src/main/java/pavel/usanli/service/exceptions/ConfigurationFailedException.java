package pavel.usanli.service.exceptions;

/**
 * Created by Pavel Usanli on 27.11.2016.
 */
public class ConfigurationFailedException extends RuntimeException {
    public ConfigurationFailedException(final String message) {
        super(message);
    }
}
