import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class DrawPanel extends JPanel {

    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private ImageIcon volvoImage;
    private ImageIcon saabImage;
    private ImageIcon scaniaImage;

    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);

        try {
            volvoImage = new ImageIcon(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
            saabImage = new ImageIcon(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")));
            scaniaImage = new ImageIcon(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
        repaint();
    }
    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);
        repaint();
    }

    public void moveVehicle(Vehicle vehicle, int x, int y) {
        vehicle.setPosX(x);
        vehicle.setPosY(y);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Volvo240) {
                g.drawImage(volvoImage.getImage(), (int) vehicle.getPosX(), (int) vehicle.getPosY(), null);
            } else if (vehicle instanceof Saab95) {
                g.drawImage(saabImage.getImage(), (int) vehicle.getPosX(), (int) vehicle.getPosY(), null);
            } else if (vehicle instanceof Scania) {
                g.drawImage(scaniaImage.getImage(), (int) vehicle.getPosX(), (int) vehicle.getPosY(), null);
            }
        }
    }
}