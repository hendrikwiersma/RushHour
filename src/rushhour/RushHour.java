/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rushhour;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author gyrth
 */
public class RushHour {

    public static ArrayList<Panel> panelList = new ArrayList();
    public static ArrayList<Car> carList = new ArrayList();
    public static HashMap<Color, Car> carsByColor = new HashMap<Color, Car>();
    public static ArrayList<String> MovementArray = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
// TODO code application logic here
        RushHourFrame1 mainframe = new RushHourFrame1();
        mainframe.setVisible(true);
        createPanelList(mainframe);
        recognizeCars();
        MoveWhichWay();
    }

    public static void createPanelList(RushHourFrame1 mainframe) {
        for (Component component : mainframe.jPanel1.getComponents()) {
//System.out.println("Print "+ component.getName());
            panelList.add(new Panel(component, component.getBackground(), Character.getNumericValue(component.getName().charAt(0)), Character.getNumericValue(component.getName().charAt(1))));
        }
    }

    public static class Panel {

        private Component name;
        private Color color;
        private int x, y;

        public Panel(Component name, Color color, int y, int x) {
            this.name = name;
            this.color = color;
            this.x = x;
            this.y = y;
        }
    }

    public static class Car {

        private Panel[] panels;
        private String movement;

        public Car(String movement, Panel... panels) {
            this.panels = panels;
            this.movement = movement;
        }

        public Panel[] getPanels() {
            return this.panels;
        }

        public void addPanel(Panel p) {
            Panel[] temp = new Panel[this.panels.length];
            for (int i = 0; i < this.panels.length; i++) {
                temp[i] = this.panels[i];
            }
            temp[temp.length - 1] = p;
            this.panels = temp;
        }
    }

    public static void recognizeCars() {
        for (Panel panel : panelList) {
            if (!panel.color.equals(Color.white)) {
                if (carsByColor.get(panel.color) == null) {
                    carsByColor.put(panel.color, new Car(null, panel));
                } else {
                    if (carsByColor.get(panel.color).getPanels().length == 1) {
                        Panel temp = carsByColor.get(panel.color).panels[0];
                        carsByColor.remove(panel.color);
                        Car newcar = new Car(null, temp, panel);
                        carsByColor.put(panel.color, newcar);
                    }
                    if (carsByColor.get(panel.color).getPanels().length == 2) {
                        Panel temp = carsByColor.get(panel.color).panels[0];
                        Panel temp2 = carsByColor.get(panel.color).panels[1];
                        carsByColor.remove(panel.color);
                        Car newcar = new Car(null, temp, temp2, panel);
                        carsByColor.put(panel.color, newcar);
                    }
                }
            }
        }
    }

    public static void MoveWhichWay() {
        for (Car car : carsByColor.values()) {
            if (car.panels[0].x == car.panels[1].x) {
                car.movement = "horizontal";

            } else {
                car.movement = "vertical";
            }
            System.out.println(car);
        }
    }
}
