import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CarView extends JFrame {
    private static final int X = 800;
    private static final int Y = 600;

    DrawPanel drawPanel = new DrawPanel(X, Y - 240);
    JPanel controlPanel = new JPanel();
    JPanel gasPanel = new JPanel();
    JSpinner gasSpinner = new JSpinner();
    int gasAmount = 0;
    JLabel gasLabel = new JLabel("Amount of gas");
    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Saab Turbo on");
    JButton turboOffButton = new JButton("Saab Turbo off");
    JButton liftBedButton = new JButton("Scania Lift Bed");
    JButton lowerBedButton = new JButton("Lower Lift Bed");
    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");
    JButton addCarButton = new JButton("Add new car");
    JButton removeCarButton = new JButton("Remove car");
    Timer timer;
    ArrayList<Vehicle> vehicles;

    public CarView(String framename, ArrayList<Vehicle> vehicles) {
        initComponents(framename);
        this.vehicles = vehicles;
        registerActionListeners();

        timer = new Timer(50, new TimerListener(vehicles, drawPanel));
        timer.start();
    }

    private void initComponents(String title) {
        this.setTitle(title);
        this.setPreferredSize(new Dimension(X, Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.add(drawPanel);

        SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(e -> gasAmount = (int) gasSpinner.getValue());

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        this.add(gasPanel);

        controlPanel.setLayout(new GridLayout(2, 4));
        controlPanel.add(gasButton);
        controlPanel.add(turboOnButton);
        controlPanel.add(liftBedButton);
        controlPanel.add(addCarButton);
        controlPanel.add(brakeButton);
        controlPanel.add(turboOffButton);
        controlPanel.add(lowerBedButton);
        controlPanel.add(removeCarButton);
        controlPanel.setPreferredSize(new Dimension((X / 2) + 4, 200));
        this.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);

        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(X / 5 - 15, 200));
        this.add(startButton);

        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(X / 5 - 15, 200));
        this.add(stopButton);


        this.pack();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        this.setVisible(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void registerActionListeners() {
        gasButton.addActionListener(e -> gas(gasAmount));
        brakeButton.addActionListener(e -> brake(gasAmount));
        turboOnButton.addActionListener(e -> setTurboOn());
        turboOffButton.addActionListener(e -> setTurboOff());
        liftBedButton.addActionListener(e -> raiseFlatbed());
        lowerBedButton.addActionListener(e -> lowerFlatbed());
        startButton.addActionListener(e -> startAllCars());
        stopButton.addActionListener(e -> stopAllCars());
        addCarButton.addActionListener(e -> addNewCar());
        removeCarButton.addActionListener(e -> removeCar());
    }

    private void gas(int amount) {
        double gas = ((double) amount) / 10;
        for (Vehicle vehicle : vehicles) {
            vehicle.gas(gas);
        }
    }

    private void brake(int amount) {
        double brake = ((double) amount) / 10;
        for (Vehicle vehicle : vehicles) {
            vehicle.brake(brake);
        }
    }

    private void setTurboOn() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Saab95) {
                ((Saab95) vehicle).setTurboOn();
            }
        }
    }

    private void setTurboOff() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Saab95) {
                ((Saab95) vehicle).setTurboOff();
            }
        }
    }

    private void raiseFlatbed() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Scania) {
                ((Scania) vehicle).raiseFlatbed();
            }
        }
    }

    private void lowerFlatbed() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Scania) {
                ((Scania) vehicle).lowerFlatbed();
            }
        }
    }

    private void startAllCars() {
        for (Vehicle vehicle : vehicles) {
            vehicle.startEngine();
        }
    }

    private void stopAllCars() {
        for (Vehicle vehicle : vehicles) {
            vehicle.stopEngine();
        }
    }

    private void addNewCar() {
        if (vehicles.size() < 10) {
            Vehicle newCar = CarFactory.createVolvo240();
            vehicles.add(newCar);
            drawPanel.addVehicle(newCar);
            System.out.println("Added car!");
        } else {
            System.out.println("Cannot add more cars. Garage is full.");
        }
    }

    private void removeCar() {
        if (!vehicles.isEmpty()) {
            Vehicle removedCar = vehicles.remove(vehicles.size() - 1);
            drawPanel.removeVehicle(removedCar);
            System.out.println("Removed car!");
        } else {
            System.out.println("No cars to remove. Garage is empty.");
        }
    }
}