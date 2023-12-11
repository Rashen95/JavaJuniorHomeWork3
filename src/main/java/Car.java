import java.io.Serializable;

public class Car implements Serializable {
    private final String carMake;
    private final int year;
    private final int power;

    public Car(String carMake) {
        this(carMake, 2000, 150);
    }

    public Car(String carMake, int year, int power) {
        this.carMake = carMake;
        this.year = year;
        this.power = power;
    }

    @Override
    public String toString() {
        return this.carMake + " " + this.year + " года выпуска " + this.power + " л.с.";
    }

    @Override
    public boolean equals(Object obj) {
        return this.toString().equals(obj.toString());
    }
}