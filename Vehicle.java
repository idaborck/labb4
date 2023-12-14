import java.awt.*;

abstract public class Vehicle implements Movable {
    protected double posX;
    protected double posY;
    protected int direction;
    protected int nrDoors;
    protected double enginePower;
    protected Color color;
    protected String modelName;
    protected double currentSpeed;

    public Vehicle(double posX, double posY, int direction, int nrDoors, double enginePower, Color color, String modelName) {
        this.posX = posX;
        this.posY = posY;
        this.direction = direction;
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.currentSpeed = 0;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int newDirection) {
        direction = newDirection;
    }


    public void startEngine() {
        currentSpeed = 0.1;
    }
    public void stopEngine() {
        currentSpeed = 0;
    }

    public abstract double speedFactor();

    public double getEnginePower() {
        return enginePower;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public double getPosX() {
        return posX;
    }
    public double getPosY() {
        return posY;
    }
    public void setPosX(double pos) {
        posX = pos;
    }
    public void setPosY(double pos) {
        posY = pos;
    }

    public void incrementSpeed(double amount) {
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, getEnginePower());
    }

    public void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }
    public void gas(double amount) {
        double speedBeforeGas = getCurrentSpeed();
        if (amount > 0 && amount <= 1 && currentSpeed > 0) {
            incrementSpeed(amount);

            if (speedBeforeGas > getCurrentSpeed()) {
                currentSpeed = speedBeforeGas;
            }
        }
    }
    public void brake(double amount) {
        double speedBeforeBrake = getCurrentSpeed();
        if (amount > 0 && amount <= 1) {
            decrementSpeed(amount);

            if (speedBeforeBrake < getCurrentSpeed()) {
                currentSpeed = speedBeforeBrake;
            }
        }
    }
    public void move() {
        if (currentSpeed <= 0) {
            currentSpeed = 0;
        } else {
            if (currentSpeed > enginePower) {
                currentSpeed = enginePower;
            }

            if (direction == 0) {
                posX += currentSpeed;
            } else if (direction == 1) {
                posY += currentSpeed;
            } else if (direction == 2) {
                posX -= currentSpeed;
            } else if (direction == 3) {
                posY -= currentSpeed;
            }
        }
    }

    public void turnLeft() {
        if (direction == 0) {
            direction = 3;
        } else {
            direction -= 1;
        }
    }

    public void turnRight() {
        if (direction == 3) {
            direction = 0;
        } else {
            direction += 1;
        }
    }


}