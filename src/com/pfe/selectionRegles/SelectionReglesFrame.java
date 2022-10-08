package com.pfe.selectionRegles;

import com.pfe.Utils;
import com.pfe.selectionFiles.Frame;
import com.pfe.visualisation.VisualisationAssociationRegles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class SelectionReglesFrame extends JFrame{
    private Container c;
    private JPanel MainPanel;
    private JButton retourButton;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JButton exécuterButton;
    private JButton ajouterButton;
    private JRadioButton toutSeulRadioButton;
    private JButton annulerButton;
    private JRadioButton seulOuAvecAutresRadioButton;
    private JRadioButton seulOuAvecSeulementRadioButton;
    private JComboBox comboBox1RegleAntecedent;
    private JComboBox comboBox2RegleAntecedent;
    private JButton validerButton;
    private JButton ajouterButton1;
    private JRadioButton toutSeulRadioButton1;
    private JButton annulerButton1;
    private JRadioButton avecSeulementUnAutreRadioButton;
    private JRadioButton avecPlusieursAutresÉlémentsRadioButton;
    private JButton validerButton1;
    private JComboBox comboBox3RegleConclusion;
    private JComboBox comboBox4RegleConclusion;
    private JFileChooser regAssAppFile;
    private JFileChooser regAssExaFile;

    public void initialiseComboboxs() throws IOException {
        comboBox1RegleAntecedent.addItem("Antécedent :       ");
        Set<String> listAntecedents = getListAntecedent(regAssAppFile.getSelectedFile(),regAssExaFile.getSelectedFile());
        for (String a:listAntecedents){
            comboBox2RegleAntecedent.addItem(a);
        }
        comboBox3RegleConclusion.addItem("Conclusion :        ");
        Set<String> listConclusions = getListConclusions(regAssAppFile.getSelectedFile(),regAssExaFile.getSelectedFile());
        for (String c:listConclusions){
            comboBox4RegleConclusion.addItem(c);
        }
    }

    public SelectionReglesFrame(JFileChooser regAssAppFile,JFileChooser regAssExaFile) throws IOException {
        setTitle("Sélection des régles d'associations");
        setBounds(300, 90, 800, 700);
        setResizable(false);

        c = getContentPane();
        c.add(MainPanel);
        this.regAssAppFile = regAssAppFile;
        this.regAssExaFile = regAssExaFile;
        initialiseComboboxs();
        comboBox1RegleAntecedent.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                System.out.println("* Combobox1 antécédent listener.");
            }
        });
        comboBox2RegleAntecedent.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                System.out.println("* Combobox2 antécédent listener.");
            }
        });
        comboBox3RegleConclusion.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                System.out.println("* Combobox1 conclusion listener.");
            }
        });
        comboBox4RegleConclusion.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                System.out.println("* Combobox2 conclusion listener.");
            }
        });
        // Buttons - Antécédent
        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("- Ajouter antécédent.");
            }
        });
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("- Annuler antécédent.");
                textArea1.setText("");
            }
        });
        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("- Valider antécédent.");
                textArea1.append("Antécédent selectionné : "+comboBox2RegleAntecedent.getSelectedItem().toString()+"\n");
                if(toutSeulRadioButton.isSelected()){
                    textArea1.append(toutSeulRadioButton.getText()+"\n");
                    textArea1.append("1"+"\n");
                } else if (seulOuAvecAutresRadioButton.isSelected()){
                    textArea1.append(seulOuAvecAutresRadioButton.getText()+"\n");
                    textArea1.append("3"+"\n");
                } else if (seulOuAvecSeulementRadioButton.isSelected()){
                    textArea1.append(seulOuAvecSeulementRadioButton.getText()+"\n");
                    textArea1.append("2"+"\n");
                }
            }
        });
        // Buttons - Conclusion
        ajouterButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("- Ajouter conclusion.");
            }
        });
        annulerButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("- Annuler conclusion.");
                textArea2.setText("");
            }
        });
        validerButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("- Valider conclusion.");
                textArea2.append("Conclusion selectionné : "+comboBox4RegleConclusion.getSelectedItem().toString()+"\n");
                if(toutSeulRadioButton1.isSelected()){
                    textArea2.append(toutSeulRadioButton1.getText()+"\n");
                    textArea2.append("1"+"\n");
                } else if (avecSeulementUnAutreRadioButton.isSelected()){
                    textArea2.append(avecSeulementUnAutreRadioButton.getText()+"\n");
                    textArea2.append("2"+"\n");
                } else if (avecPlusieursAutresÉlémentsRadioButton.isSelected()){
                    textArea2.append(avecPlusieursAutresÉlémentsRadioButton.getText()+"\n");
                    textArea2.append("3"+"\n");
                }
            }
        });
        // Button - Exécution
        exécuterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("- Exécution de la requette.");
                if (!textArea1.getText().equals("") && textArea2.getText().equals("")){
                    String selectedAnt = textArea1.getText().split("\n")[0].trim().split(":")[1];
                    String selectedRadioAnt = textArea1.getText().split("\n")[2];
                    try {
                        String[] resultat = Utils.executeVisualisationAntecedent(selectedAnt,Integer.parseInt(selectedRadioAnt),
                                regAssAppFile.getSelectedFile(),regAssExaFile.getSelectedFile());
                        String label = "Résultat des régles d'associations - Antécedent selectionné :"+resultat[0];
                        VisualisationAssociationRegles visualisationFrame = new VisualisationAssociationRegles(label, resultat[1], resultat[2]);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (!textArea2.getText().equals("") && textArea1.getText().equals("")){
                    String selectedCon = textArea2.getText().split("\n")[0].trim().split(":")[1];
                    String selectedRadioCon = textArea2.getText().split("\n")[2];
                    try {
                        String[] resultat = Utils.executeVisualisationConclusion(selectedCon,Integer.parseInt(selectedRadioCon),
                                regAssAppFile.getSelectedFile(),regAssExaFile.getSelectedFile());
                        String label = "Résultat des régles d'associations - Conclusion selectionné :"+resultat[0];
                        VisualisationAssociationRegles visualisationFrame = new VisualisationAssociationRegles(label, resultat[1], resultat[2]);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (!textArea1.getText().equals("") && !textArea2.getText().equals("")){
                    String selectedAnt = textArea1.getText().split("\n")[0].trim().split(":")[1];
                    String selectedRadioAnt = textArea1.getText().split("\n")[2];
                    String selectedCon = textArea2.getText().split("\n")[0].trim().split(":")[1];
                    String selectedRadioCon = textArea2.getText().split("\n")[2];
                    try {
                        String[] resultat = Utils.executeVisualisationAntecedentAndConclusion(
                                selectedAnt,Integer.parseInt(selectedRadioAnt),
                                selectedCon, Integer.parseInt(selectedRadioCon),
                                regAssAppFile.getSelectedFile(),regAssExaFile.getSelectedFile());
                        String label = "Résultat des régles d'associations \n" +
                                "\t - Antécédent selectionné : "+resultat[0]+
                                "\t / Conclusion selectionné : "+resultat[2];
                        VisualisationAssociationRegles visualisationFrame = new VisualisationAssociationRegles(label, resultat[1], resultat[3]);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    System.out.println("");
                }
            }
        });
        // Button - Retour
        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("- Retour regles d'associations.");
                Frame f = new Frame();
            }
        });
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private Set<String> getListAntecedent(File regAssAppFile, File regAssExaFile) throws IOException {
        Set<String> listAntecedent = new HashSet<>();
        BufferedReader br = new BufferedReader(new FileReader(regAssAppFile.getPath()));
        BufferedReader br1 = new BufferedReader(new FileReader(regAssExaFile.getPath()));
        String st;
        String st1;
        while ((st = br.readLine()) != null){
            String[] reg = st.split("->");
            String[] antecedents = reg[0].trim()
                    .replace("[","").replace("]","")
                    .split("\t");
            for (String a:antecedents){
                listAntecedent.add(a);
            }
        }
        while ((st1 = br1.readLine()) != null){
            String[] reg = st1.split("=>");
            String[] antecedents = reg[0].trim()
                    .replace("[","").replace("]","")
                    .split("\t");
            for (String a:antecedents){
                listAntecedent.add(a);
            }
        }
        return listAntecedent;
    }
    private Set<String> getListConclusions(File regAssAppFile, File regAssExaFile) throws IOException {
        Set<String> listConclusions = new HashSet<>();
        BufferedReader br = new BufferedReader(new FileReader(regAssAppFile.getPath()));
        BufferedReader br1 = new BufferedReader(new FileReader(regAssExaFile.getPath()));
        String st;
        String st1;
        while ((st = br.readLine()) != null){
            String[] reg = st.split("->");
            String[] conclusions = reg[1].trim().split("]")[0]
                   .replace("[","").split("\t");
            for (String c:conclusions){
                listConclusions.add(c);
            }
        }
        while ((st1 = br1.readLine()) != null){
            String[] reg = st1.split("=>");
            String[] conclusions = reg[1].trim().split("]")[0]
                    .replace("[","").split("\t");
            for (String c:conclusions){
                listConclusions.add(c);
            }
        }
        return listConclusions;
    }
}
