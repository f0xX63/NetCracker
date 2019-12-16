package buildings;
import java.io.*;

public class CloneHelper {

    public static Object cloneObject(Object obj) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (IOException e) {
            System.out.println("Ошибка при клонировании!");
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка! Класс не найден!");
            return null;
        }
    }
}
