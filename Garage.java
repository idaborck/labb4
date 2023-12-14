import java.util.ArrayList;


public class Garage<T extends Car>{
    private final int garageSize;
    private final int posX;
    private final int posY;

    ArrayList<T> carsInGarage = new ArrayList<T>();

    public Garage(int garageSize, int posX, int posY) {
        this.garageSize = garageSize;
        this.posX = posX;
        this.posY = posY;
    }

    public int getGarageSize() {
        return garageSize;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void loadCar(T carToLoad) {
        assert (carsInGarage.size() + 1 <= garageSize);
        assert (posX - carToLoad.getPosX() < 50 && posX - carToLoad.getPosX() > -50 &&
                posY - carToLoad.getPosY() < 50 && posY - carToLoad.getPosY() > -50);
        carToLoad.setPosX(posX);
        carToLoad.setPosY(posY);
        carsInGarage.add(carToLoad);
    }

    public void unloadCar(T carToUnload){
        assert(carsInGarage.contains(carToUnload));
        carToUnload.setPosX(getPosX() + 10);
        carsInGarage.remove(carToUnload);
    }
}