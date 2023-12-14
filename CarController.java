
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CarController {
    private final int delay = 50;
    private CarView frame;
    public ArrayList<Vehicle> vehicles = new ArrayList<>();

    public static void main(String[] args) {
        CarController cc = new CarController();
        cc.initVehicles();
        cc.frame = new CarView("CarSim 1.0", cc.vehicles);
        cc.frame.drawPanel.addVehicle(cc.vehicles.get(0));
        cc.frame.drawPanel.addVehicle(cc.vehicles.get(1));
        cc.frame.drawPanel.addVehicle(cc.vehicles.get(2));
        cc.frame.timer.start();
    }
    private void initVehicles() {
        vehicles.add(CarFactory.createVolvo240());
        vehicles.add(CarFactory.createSaab95());
        vehicles.add(CarFactory.createScania());
    }
    private void registerActionListeners() {
        frame.gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gas(frame.gasAmount);
            }
        });

        frame.brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                brake(frame.gasAmount);
            }
        });

        frame.turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Vehicle vehicle : vehicles) {
                    if (vehicle instanceof Saab95) {
                        ((Saab95) vehicle).setTurboOn();
                    }
                }
            }
        });

        frame.turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Vehicle vehicle : vehicles) {
                    if (vehicle instanceof Saab95) {
                        ((Saab95) vehicle).setTurboOff();
                    }
                }
            }
        });

        frame.liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Vehicle vehicle : vehicles) {
                    if (vehicle instanceof Scania) {
                        ((Scania) vehicle).raiseFlatbed();
                    }
                }
            }
        });

        frame.lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Vehicle vehicle : vehicles) {
                    if (vehicle instanceof Scania) {
                        ((Scania) vehicle).lowerFlatbed();
                    }
                }
            }
        });

        frame.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Vehicle vehicle : vehicles) {
                    vehicle.startEngine();
                }
            }
        });

        frame.stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Vehicle vehicle : vehicles) {
                    vehicle.stopEngine();
                }
            }
        });

        frame.addCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vehicles.size() < 10) {
                    Vehicle newCar = CarFactory.createVolvo240();
                    vehicles.add(newCar);
                    frame.drawPanel.addVehicle(newCar);

                    System.out.println("Added car!");
                } else {
                    System.out.println("Cannot add more cars. Garage is full.");
                }
            }
        });

        frame.removeCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!vehicles.isEmpty()) {
                    Vehicle removedCar = vehicles.remove(vehicles.size() - 1);
                    frame.drawPanel.removeVehicle(removedCar);
                    System.out.println("Removed car!");
                } else {
                    System.out.println("No cars to remove. Garage is empty.");
                }
            }
        });
    }

    void gas(int amount) {
        double gas = ((double) amount) / 10;
        for (Vehicle vehicle : vehicles) {
            vehicle.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 10;
        for (Vehicle vehicle : vehicles) {
            vehicle.brake(brake);
        }
    }
}