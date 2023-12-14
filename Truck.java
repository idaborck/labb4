import java.awt.*;

abstract public class Truck extends Vehicle {

    private final static double trimFactor = 1.25;
    private int flatbedAngle;

    public Truck(double posX,double posY, int direction, int nrDoors, double enginepower, Color color, String modelName, int flatbedAngle){
        super(posX, posY, direction, nrDoors, enginepower, color, modelName);
        this.flatbedAngle = flatbedAngle;

    }

    public void startEngine() {
        if(flatbedAngle == 0) {
            currentSpeed = 0.1;
        }
    }

    public double speedFactor() {
        return getEnginePower() * 0.01 * trimFactor;
    }


    public int getFlatbedAngle() {
        return flatbedAngle;
    }

    public void setFlatbedAngle(int angle) {
        if(angle >= 0 && angle <= 70) {
            flatbedAngle = angle;
        }
    }

    public void raiseFlatbed() {
        assert (currentSpeed == 0);
        if (flatbedAngle < 70) {
            flatbedAngle += 1;
        }
    }

    public void lowerFlatbed() {
        if(currentSpeed == 0) {
            if(flatbedAngle > 0) {
                flatbedAngle = 0;
            }
        }

    }

}