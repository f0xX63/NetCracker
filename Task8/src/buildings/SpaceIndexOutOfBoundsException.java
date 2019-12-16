package buildings;
//Ошибка выхода за границы номеров помещений
public class SpaceIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public SpaceIndexOutOfBoundsException() {
    }

    public SpaceIndexOutOfBoundsException(String message) {
        super(message);
    }
}
