import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class Saver {
    public static <T extends Serializable> UUID saveCreator(T t) {
        UUID uuid = UUID.randomUUID();
        Path p = Path.of(String.format("%s_%s.txt", t.getClass().getName(), uuid));
        try (ObjectOutputStream oIS = new ObjectOutputStream(Files.newOutputStream(p))) {
            oIS.writeObject(t);
        } catch (IOException e) {
            System.out.printf("Не удалось сохранить в файл %s\n", t);
            return null;
        }
        return uuid;
    }

    public static Car saveLoader(String p) {
        Car car;
        Path path = Path.of(p);
        try (ObjectInputStream oIS = new ObjectInputStream(Files.newInputStream(path))) {
            car = (Car) oIS.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.printf("Файл с названием %s не был загружен. Проверьте корректность файла.\n", p);
            car = null;
        }
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return car;
    }
}