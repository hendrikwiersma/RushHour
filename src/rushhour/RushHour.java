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
    public static HashMap<Color, Car> carsByColor = new HashMap<>();
    public static HashMap<Integer, Panel> panelByCoordinates = new HashMap<>();
    public static ArrayList<Move> PossibleMoves = new ArrayList<>();
    public static RushHourFrame1 mainframe = new RushHourFrame1();
    public static int choise;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
// TODO code application logic here
        Picker newpicker = new Picker();
        newpicker.setVisible(true);

    }

    public static void startapp() {

        if (choise == -1) {
            mainframe.dispose();
        }
        if (choise == 0) {
            RushHourFrame2 newframe = new RushHourFrame2();
            for (Component panel : mainframe.jPanel1.getComponents()) {
                for (Component panelcompare : newframe.jPanel1.getComponents()) {
                    if (panel.getName().equals(panelcompare.getName())) {
                        panel.setBackground(panelcompare.getBackground());
                    }
                }
            }
            newframe.dispose();
            mainframe.setVisible(true);
        }
        if (choise == 1) {
            RushHourFrame3 newframe = new RushHourFrame3();
            for (Component panel : mainframe.jPanel1.getComponents()) {
                for (Component panelcompare : newframe.jPanel1.getComponents()) {
                    if (panel.getName().equals(panelcompare.getName())) {
                        panel.setBackground(panelcompare.getBackground());
                    }
                }
            }
            newframe.dispose();
            mainframe.setVisible(true);
        }
        if (choise == 2) {
            RushHourFrame4 newframe = new RushHourFrame4();
            for (Component panel : mainframe.jPanel1.getComponents()) {
                for (Component panelcompare : newframe.jPanel1.getComponents()) {
                    if (panel.getName().equals(panelcompare.getName())) {
                        panel.setBackground(panelcompare.getBackground());
                    }
                }
            }
            newframe.dispose();
            mainframe.setVisible(true);
        }

    }

    public static void search() {
        while (!panelByCoordinates.get(new Coordinates(6, 3).coordinates).color.equals(Color.RED)) {
            createPanelList();
            linkcoordinates();
            recognizeCars();
            MoveWhichWay();
            CanMoveWhere();
            MakeMove();
        }
    }

    public static void breadthfirstsearch() {
        for (Move move : PossibleMoves) {
            ArrayList<Move> takenRoute = new ArrayList<>();
            takenRoute.add(move);
        }

    }

    public static void info() {
        System.out.println("Number of cars: " + carsByColor.size());
        for (Car car : carsByColor.values()) {
            System.out.println("Car " + car.getPanels().length + car.panels[0].color.getRed() + " " + car.getPanels().length + car.panels[0].color.getGreen() + " " + car.getPanels().length + car.panels[0].color.getBlue() + " at position ");
            for (Panel panel : car.getPanels()) {
                System.out.print(panel.x + ":" + panel.y + " ");
            }
            System.out.println(" can move " + car.movement);
        }

    }

    public static void createPanelList() {
        panelList.clear();
        for (Component component : mainframe.jPanel1.getComponents()) {
            Panel panel = new Panel(component, component.getBackground(), Character.getNumericValue(component.getName().charAt(0)), Character.getNumericValue(component.getName().charAt(1)));
            panelList.add(panel);

//            System.out.println("x" + panel.x + ":" + "y" + panel.y + panel.color);
        }
//        Coordinates newcod = new Coordinates(1, 1);
//        System.out.println("Panel at 5:5 = " + panelByCoordinates.get(new Coordinates(5, 5).coordinates));
    }

    public static void linkcoordinates() {
        panelByCoordinates.clear();
        for (Panel panel : panelList) {
            panelByCoordinates.put(new Coordinates(panel.x, panel.y).coordinates, panel);
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

    public static class Coordinates {

        private int coordinates;

        public Coordinates(Integer x, Integer y) {
            String a = String.valueOf(x) + String.valueOf(y);
            Integer b = Integer.parseInt(a);
            this.coordinates = b;
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

        public void replacePanel(Integer index, Panel replacewith) {
            this.panels[index] = replacewith;
        }
    }

    public static class Move {

        private Car car;
        private String direction;

        public Move(Car car, String direction) {
            this.car = car;
            this.direction = direction;
        }
    }

    public static void recognizeCars() {
        carsByColor.clear();
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

                    } else {
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
        String b;
        for (Car car : carsByColor.values()) {
            if (car.panels[0].x == car.panels[1].x) {
                car.movement = "vertical";

            } else {
                car.movement = "horizontal";
            }
        }
    }

    public static void CanMoveWhere() {
        PossibleMoves.clear();
        int posmoves = PossibleMoves.size();
        for (Car car : carsByColor.values()) {
            if (car.movement.equals("horizontal")) {

                for (Panel panel : car.getPanels()) {
                    if (panelByCoordinates.get(new Coordinates((panel.x + 1), panel.y).coordinates) != null && panelByCoordinates.get(new Coordinates(panel.x + 1, panel.y).coordinates).color.equals(Color.WHITE)) {
                        System.out.println("Car at " + panel.x + ":" + panel.y + " can move to the right.");
                        PossibleMoves.add(new Move(car, "right"));
                    }
                    if (panelByCoordinates.get(new Coordinates((panel.x - 1), panel.y).coordinates) != null && panelByCoordinates.get(new Coordinates(panel.x - 1, panel.y).coordinates).color.equals(Color.WHITE)) {
                        System.out.println("Car at " + panel.x + ":" + panel.y + " can move to the left.");
                        PossibleMoves.add(new Move(car, "left"));
                    } else {
                    }
                }

            }
            if (car.movement.equals("vertical")) {
                for (Panel panel : car.getPanels()) {
                    if (panelByCoordinates.get(new Coordinates(panel.x, (panel.y - 1)).coordinates) != null && panelByCoordinates.get(new Coordinates(panel.x, panel.y - 1).coordinates).color.equals(Color.WHITE)) {
                        System.out.println("Car at " + panel.x + ":" + panel.y + " can move up.");
                        PossibleMoves.add(new Move(car, "up"));
                    }
                    if (panelByCoordinates.get(new Coordinates(panel.x, (panel.y + 1)).coordinates) != null && panelByCoordinates.get(new Coordinates(panel.x, panel.y + 1).coordinates).color.equals(Color.WHITE)) {
                        System.out.println("Car at " + panel.x + ":" + panel.y + " can move down.");
                        PossibleMoves.add(new Move(car, "down"));
                    } else {
                    }
                }
            }
        }
        System.out.println("Done");
        System.out.println("Pos moves: ");
        for (Move string : PossibleMoves) {
            System.out.print(" " + string.direction);
        }
        System.out.println(".");
    }

    public static void MakeMove() {
        int R = (int) ((Math.random() * (PossibleMoves.size() - 0)) + 0);
        Car car = PossibleMoves.get(R).car;
        Color color = PossibleMoves.get(R).car.panels[0].color;
        String directions = PossibleMoves.get(R).direction;
        for (Panel panel : car.panels) {
            panel.name.setBackground(Color.WHITE);
            panel.color = Color.WHITE;
        }
        switch (directions) {
            case "left":
                for (int i = 0; i < car.panels.length; i++) {
                    Panel replacementpanel = panelByCoordinates.get(new Coordinates((car.panels[i].x - 1), car.panels[i].y).coordinates);
                    car.replacePanel(i, replacementpanel);

                }
                break;
            case "right":
                for (int i = 0; i < car.panels.length; i++) {
                    Panel replacementpanel = panelByCoordinates.get(new Coordinates((car.panels[i].x + 1), car.panels[i].y).coordinates);
                    car.replacePanel(i, replacementpanel);
                }
                break;
            case "up":
                for (int i = 0; i < car.panels.length; i++) {
                    Panel replacementpanel = panelByCoordinates.get(new Coordinates(car.panels[i].x, (car.panels[i].y - 1)).coordinates);
                    car.replacePanel(i, replacementpanel);

                }
                break;
            case "down":
                for (int i = 0; i < car.panels.length; i++) {
                    Panel replacementpanel = panelByCoordinates.get(new Coordinates(car.panels[i].x, (car.panels[i].y + 1)).coordinates);
                    car.replacePanel(i, replacementpanel);

                }
                break;
            default:
                break;
        }
        System.out.println("Lenth: " + car.panels.length);
        for (Panel panel : car.panels) {
            panel.name.setBackground(color);
            panel.color = color;
            System.out.println("Paint: " + panel.x + ":" + panel.y);
        }
    }
}
