package com.pfe.visualisation;

import javax.swing.*;
import java.awt.*;

public class VisualisationAssociationRegles extends JFrame{
    private Container c;
    private JPanel MainPanel;
    private JTextArea texAreaVisualisation;
    private JLabel labelVisualisation;
    private JLabel labelTempsExec;

    public VisualisationAssociationRegles(String label, String resultat, String label2){
        setTitle("Visualisation des régles d'associations");
        setBounds(300, 90, 800, 700);
        setResizable(false);

        c = getContentPane();
        c.add(MainPanel);
        labelVisualisation.setText(label);
        texAreaVisualisation.setText(resultat);
        labelTempsExec.setText(label2);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
