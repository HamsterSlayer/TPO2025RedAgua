/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reddistribucionagua2025.interfazGrafica;

import com.mycompany.reddistribucionagua2025.Ciudad;
import com.mycompany.reddistribucionagua2025.Lista;
import com.mycompany.reddistribucionagua2025.digrafo.MapaDigrafo;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author hamst
 */
public class GUI {
    
    public GUI(MapaDigrafo datos){
        
        /*Si no entendi mal:
        JFrame contiene todo -> Jpanel puede contener otros panel y componentes
        */
        JFrame mainFrame = new JFrame();
        CustomPanel panel = new CustomPanel(datos);
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(new GridLayout(10,10));
        
        
        mainFrame.add(panel, BorderLayout.CENTER);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("Red Distribucion Agua");
        mainFrame.pack();
        mainFrame.setVisible(true);
        
    }
    
    //CustomPanel--------------------------------------------------------------------------
    
    private class CustomPanel extends JPanel {
        //Atributos
        private MapaDigrafo datos;
        private Node[] panels;
        private int nodeRadius = 30;
        
        public CustomPanel(MapaDigrafo datos) {
            //Tomo datitos
            this.datos = datos;
            setLayout(new GridLayout(0, 1));
            setPreferredSize(new Dimension(600, 400));
        }
        
        //Drawing ---------------------------
        @Override
        protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (datos != null) {
            drawNodes(g2d,nodeRadius);
        }
        
        //drawNode(g2d,nodeRadius,20,20,"Prueba");
        //g2d.setColor(Color.BLACK);
        /*g2d.setStroke(new BasicStroke(2.0f));
        g2d.setColor(Color.BLUE);
        g2d.*/
        
        }
        //---------------------------
        
        
        private void drawNodes(Graphics g2d, int size) {
            //Variables
            Lista aux = datos.getCiudades();
            panels = new Node[aux.longitud()];
            
            int width = this.getWidth();
            int height = this.getHeight();
            int widthCursor = width / 10;
            int heightCursor = height / 10;
            
            //Genero los paneles
            for (int i = 1; i < aux.longitud(); i++) {
                Ciudad ciudad = (Ciudad) aux.recuperar(i);
                Node panelTemp = new Node(ciudad.getNombre());
                panels[i-1] = panelTemp;
                this.add(panelTemp);
            }
        }

        
    }

}
