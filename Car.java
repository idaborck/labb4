import java.awt.*;
import java.util.ArrayList;

abstract class Car extends Vehicle implements Movable {
    public Car(double posX, double posY, int direction, int nrDoors, double enginePower, Color color, String modelName) {
        super(posX, posY, direction, nrDoors, enginePower, color, modelName);
    }

    public int getNrDoors() {
        return nrDoors;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color clr) {
        color = clr;
    }
}