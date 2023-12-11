import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Написать класс с двумя методами:
 * 1. принимает объекты, имплементирующие интерфейс serializable, и сохраняющие их в файл. Название файл - class.getName() + "_" + UUID.randomUUID().toString()
 * 2. принимает строку вида class.getName() + "_" + UUID.randomUUID().toString() и загружает объект из файла и удаляет этот файл.
 * Что делать в ситуациях, когда файла нет или в нем лежит некорректные данные - подумать самостоятельно.
 */

public class Main {
    public static void main(String[] args) {
        // Создаем объекты
        Car car1 = new Car("BMW");
        Car car2 = new Car("MERCEDES");
        Car car3 = new Car("VOLVO");
        List<Car> carList = new ArrayList<>(List.of(car1, car2, car3));

        // Сохраняем в память UUID и создаем файлы с сохранениями
        List<UUID> uuidList = new ArrayList<>();
        for (Car car : carList) {
            uuidList.add(Saver.saveCreator(car));
        }

        // Восстанавливаем объекты из файлов и сравниваем с ранее сохраняемыми
        int count = 0;
        for (UUID uuid : uuidList) {
            if (uuid != null) {
                Car car = Saver.saveLoader(String.format("%s_%s.txt", Car.class.getName(), uuid));
                if (car != null) {
                    System.out.println(car.equals(carList.get(count)));
                }
            }
            count++;
        }
    }
}