package com.pfe.visualisation;

import javax.swing.*;
import java.awt.*;

public class VisualisationBiCluster extends JFrame{
    private Container c;
    private JPanel mainPanel;
    private JTextArea textArea1;
    private JLabel labelTempsExec;

    public VisualisationBiCluster(String resultat[]){
        setTitle("Visualisation des régles d'associations");
        setBounds(300, 90, 800, 700);
        setResizable(false);

        c = getContentPane();
        c.add(mainPanel);
        appendTextAreaWithResult(resultat);
        labelTempsExec.setText(resultat[2]);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void appendTextAreaWithResult(String[] result){
        textArea1.append("1ére cluster : \n");
        textArea1.append(result[0]);
        textArea1.append("\n");
        textArea1.append("2éme cluster : \n");
        textArea1.append(result[1]);
    }
}
