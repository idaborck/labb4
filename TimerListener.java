import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TimerListener implements ActionListener {

    private DrawPanel drawPanel;
    ArrayList<Vehicle> vehicles;

    public TimerListener(ArrayList<Vehicle> vehicles, DrawPanel drawPanel) {
        this.vehicles = vehicles;
        this.drawPanel = drawPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Vehicle vehicle : vehicles) {
            vehicle.move();
            int carX = (int) Math.round(vehicle.getPosX());
            int carY = (int) Math.round(vehicle.getPosY());

            drawPanel.moveVehicle(vehicle, carX, carY);
            if (vehicle.getPosX() + 118 > drawPanel.getWidth()) {
                vehicle.setPosX(682);
                vehicle.stopEngine();
                vehicle.turnRight();
                vehicle.turnRight();
                vehicle.startEngine();
            } else if (vehicle.getPosX() < 0) {
                vehicle.setPosX(0);
                vehicle.stopEngine();
                vehicle.turnRight();
                vehicle.turnRight();
                vehicle.startEngine();
            }
        }
        drawPanel.repaint();
    }
}

