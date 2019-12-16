package buildings;
//Ошибка некорретной площади помещения
public class InvalidSpaceAreaException extends IllegalArgumentException {
    public InvalidSpaceAreaException() {
    }

    public InvalidSpaceAreaException(String message) {
        super(message);
    }

    public InvalidSpaceAreaException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidSpaceAreaException(Throwable cause) {
        super(cause);
    }
}
