import java.awt.*;
import java.util.ArrayList;

public class CarTransport extends Truck {

    private boolean isRampUp;
    private ArrayList<Vehicle> carsOnRamp = new ArrayList<Vehicle>();
    private final int rampSize;

    public CarTransport() {
        super(300, 300, 0, 2, 200, Color.yellow, "CarTransport", 0);
        isRampUp = true;
        rampSize = 4;
    }


    public void loadCar(Vehicle carToLoad){
        assert(carsOnRamp.size() + 1 <= rampSize);
        assert(carToLoad.getClass() != CarTransport.class);
        assert(getPosX() - carToLoad.getPosX() < 30 && getPosX() - carToLoad.getPosX() > -30 &&
                getPosY() - carToLoad.getPosY() < 30 && getPosY() - carToLoad.getPosY() > -30);
        assert(!isRampUp);
        carToLoad.setPosX(getPosX());
        carToLoad.setPosY(getPosY());
        carsOnRamp.add(carToLoad);
    }

    public void unloadCar(){
        assert(!isRampUp);
        assert(!carsOnRamp.isEmpty());
        Vehicle unloadedCar = carsOnRamp.get(carsOnRamp.size() - 1);
        unloadedCar.setPosX(getPosX() + 10);
        carsOnRamp.remove(carsOnRamp.size() - 1);
    }

    public boolean getIsRampUp() {
        return isRampUp;
    }

    public ArrayList getCarsOnRamp(){
        return carsOnRamp;
    }

    public void raiseRamp(){
        assert(currentSpeed == 0);
        setFlatbedAngle(0);
        isRampUp = true;
    }
    public void lowerRamp(){
        assert(currentSpeed == 0);
        setFlatbedAngle(70);
        isRampUp = false;
    }

    @Override
    public void move(){
        assert(currentSpeed > 0 && currentSpeed < getEnginePower());
        if(getDirection() == 0){
            setPosX(getPosX() + currentSpeed);
        }else if(getDirection() == 1){
            setPosY(getPosY() + currentSpeed);
        }else if(getDirection() == 2){
            setPosX(getPosX() - currentSpeed);
        }else if(getDirection() == 3) {
            setPosY(getPosY() - currentSpeed);
        }
        for (Vehicle currentCar : carsOnRamp) {
            currentCar.currentSpeed = currentSpeed;
            currentCar.setPosX(getPosX());
            currentCar.setPosY(getPosY());
        }

    }

}