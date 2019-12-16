package buildings;
//Ошибка выхода за границы номеров этажей
public class FloorIndexOutOfBoundsException extends IndexOutOfBoundsException {

    public FloorIndexOutOfBoundsException() {
    }

    public FloorIndexOutOfBoundsException(String message) {
        super(message);
    }
}
