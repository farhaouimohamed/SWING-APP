package com.pfe.selectionClusters;

import com.pfe.Utils;
import com.pfe.jdom.DocWriteJDOM;
import com.pfe.selectionFiles.Frame;
import com.pfe.visualisation.VisualisationBiCluster;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.*;

public class SelectionClustersFrame extends JFrame{
    private Container c;
    private JPanel MainPanel;
    private JButton retourButton;
    private JButton suivantButton;
    private JTextArea TextArea;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton ajouterButton;
    private JButton annulerButton;
    private JButton exécuterButton;
    private JLabel executeLabel;
    private JFileChooser biClusterFile;

    public void initialiseComboboxs() throws IOException {
        comboBox1.addItem("Item :       ");
        Set<String> listItems = getListItems(biClusterFile.getSelectedFile());
        for (String item:listItems){
            comboBox2.addItem(item);
        }
    }
    public SelectionClustersFrame(JFileChooser biClusterFile) throws IOException {
        setTitle("Sélection des clusters");
        setBounds(300, 90, 800, 700);
        setResizable(false);
        c = getContentPane();
        c.add(MainPanel);
        this.biClusterFile = biClusterFile;
        initialiseComboboxs();

        // Buttons
        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("- Ajouter un item.");
                TextArea.append(comboBox2.getSelectedItem().toString()+"\n");
            }
        });
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("- Annuler l'ajout d'un item.");
                TextArea.setText("");
            }
        });
        exécuterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("- Exécuter les items choisis.");
                if (!TextArea.equals("")){
                    try {
                        String[] result = Utils.executeBiClusterSelection(biClusterFile.getSelectedFile(), getSelectedItems());
                        DocWriteJDOM.makeDocc((result[0]+result[1]).split("\n"));
                        createFileWithBiclusterResult(result);
                        VisualisationBiCluster visualisationBiClusterFrame = new VisualisationBiCluster(result);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    executeLabel.setText("Item choisis : " + TextArea.getText());
                } else {
                    executeLabel.setText("Vous devez choisir au moins un item !");
                }
            }
        });
        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("- Retour sélection des clusters.");
                Frame f = new Frame();
            }
        });
        suivantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("- Suivant sélection des clusters.");

            }
        });

        setVisible(true);
        setLocationRelativeTo(null);
    }

    private Set<String> getListItems(File classEquivalenceFile) throws IOException {
        Set<String> listItems = new HashSet<>();
        BufferedReader br = new BufferedReader(new FileReader(classEquivalenceFile.getPath()));
        String st;
        while ((st = br.readLine()) != null){
            String[] premierCluster = st.split("]")[0].replace("[","").split("\t");
            String[] deuxiemeCluster = st.split("]")[1].replace("[","").split("\t");

            for (String itemCluster1:premierCluster){
                if (!itemCluster1.equals("")){
                    listItems.add(itemCluster1.trim());
                }
            }
            for (String itemCluster2:deuxiemeCluster){
                if(!itemCluster2.equals("")){
                    listItems.add(itemCluster2.trim());
                }
            }
        }
        return listItems;
    }

    private String[] getSelectedItems(){
        return TextArea.getText().split("\n");
    }

    private void createFileWithBiclusterResult(String[] result) throws IOException {
        PrintWriter writer = new PrintWriter("src/com/pfe/files/resultVisualisationBiClusters.txt", "UTF-8");
        writer.println("1ére cluster :");
        writer.println(result[0]);
        writer.println("2ème cluster :");
        writer.println(result[1]);
        writer.close();
    }

    private String[] aline(String[] result){
        String[] r = new String[3];
        r[2] = result[2];
        r[0] = alineResultText(result[0].split("\n"));
        r[1] = alineResultText(result[1].split("\n"));
        return r;
    }
    private String alineResultText(String[] text){
        int maxLengthGen = 0;
        int maxLengthCluster = 0;
        int maxLengthThirdPart = 0;
        String result = "";
        for(String line:text){
            if (line.split("]")[0].length() + 1 > maxLengthGen)
                maxLengthGen = line.split("]")[0].length() + 1;
            if (line.split("]")[1].trim().length() + 1 > maxLengthCluster)
                maxLengthCluster = line.split("]")[1].trim().length() + 1;
            if (line.split("]")[2].trim().length() +1 > maxLengthThirdPart)
                maxLengthThirdPart = line.split("]")[2].trim().length() +1;
        }
        for(int i=0; i<text.length; i++){
            result += text[i].split("]")[0]+"]"
                    + String.join("", Collections.nCopies(maxLengthGen - text[i].split("]")[0].trim().length() +1, " "))
                    + "\t"
                    + text[i].split("]")[1]+"]"
                    + String.join("", Collections.nCopies(maxLengthCluster - text[i].split("]")[1].trim().length() +1, " "))
                    + "\t"
                    + text[i].split("]")[2]+"]"
                    + String.join("", Collections.nCopies(maxLengthThirdPart - text[i].split("]")[2].trim().length() +1, " "));
        }
        return result;
    }

}
