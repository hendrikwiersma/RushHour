/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rushhour;

import java.awt.Color;
import java.awt.Component;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

/**
 *
 * @author gyrth
 */
public class RushHour {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        RushHourFrame1 mainframe = new RushHourFrame1();
        mainframe.setVisible(true);
        recognizecars(mainframe);
    }

    public static void recognizecars(RushHourFrame1 mainframe) {
        ArrayList<panel> panelList = new ArrayList();
        int a = 1;
        int b = 1;
        for (Component component : mainframe.jPanel1.getComponents()) {
            //System.out.println("Print "+ component.getName());
            panelList.add(new panel(a, b, component));
            a++;
            if (a == 6){
              a=0;
              b++;
            }
        }
        for (panel component : panelList) {
            if (!component.name.getBackground().equals(Color.white)){
                System.out.println("Color: " + component.name.getBackground());
            }            
        }
        System.out.println("PanelList: " + panelList.size());
    }

    public static class panel {

        int x;
        int y;
        Component name;

        public panel(int x, int y, Component name) {
            this.name = name;
            this.x = x;
            this.y = y;
        }
    }
}
