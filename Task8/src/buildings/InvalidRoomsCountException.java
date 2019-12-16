package buildings;
//Ошибка некорретного количества комнат в помещении
public class InvalidRoomsCountException extends IllegalArgumentException {
    public InvalidRoomsCountException() {
    }

    public InvalidRoomsCountException(String message) {
        super(message);
    }

    public InvalidRoomsCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRoomsCountException(Throwable cause) {
        super(cause);
    }
}
