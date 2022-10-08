package com.pfe.selectionFiles;

import com.pfe.selectionClusters.SelectionClustersFrame;
import com.pfe.selectionRegles.SelectionReglesFrame;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Frame extends JFrame{

    private Container c;
    private JPanel MainPanel;
    private JPanel ChooseFileForDataset = new JPanel();
    private JPanel ChooseFilesForSelectionOfAssociqtionRegles;
    private JPanel ChooseFileForClustersSelection;
    private JButton choisirDatasetFileButton;
    private JButton saveDatasetFileButton;
    private JLabel datasetSelectedFileLab;
    private JButton choisirRegExacButton;
    private JButton choisirRegAppButton;
    private JButton suivantRegAssButton;
    private JButton choisirClassEquiButton;
    private JButton suivantSecClusButton;
    private JLabel regAssExacSelectedFileLab;
    private JLabel regAssAppSelectedFileLab;
    private JLabel classEquiSelectedFileLab;
    private JFileChooser datasetFile;
    private JFileChooser regAssAppFile;
    private JFileChooser regAssExaFile;
    private JFileChooser classeEquivalenceFile;
    public Frame(){
        setTitle("Choisir des fichiers");
        setBounds(300, 90, 800, 700);
        setResizable(false);
        c = getContentPane();
        choisirDatasetFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("- Importer le fichier de jeu de données.");
                // create an object of JFileChooser class
                datasetFile = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                // invoke the showsSaveDialog function to show the save dialog
                int r = datasetFile.showSaveDialog(null);
                // if the user selects a file
                if (r == JFileChooser.APPROVE_OPTION)
                {
                    // set the label to the path of the selected file
                    datasetSelectedFileLab.setText(datasetFile.getSelectedFile().getAbsolutePath());
                }
                // if the user cancelled the operation
                else
                    datasetSelectedFileLab.setText("the user cancelled the operation");
            }
        });
        saveDatasetFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("- Sauvegarder le fichier de jeu de données.");
                if(datasetFile != null){
                    System.out.print(datasetFile.getSelectedFile().getAbsolutePath());
                }
            }
        });
        choisirRegExacButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("- Choisir des régles d'associations exactes.");
                // create an object of JFileChooser class
                regAssExaFile = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                // invoke the showsSaveDialog function to show the save dialog
                int r = regAssExaFile.showSaveDialog(null);
                // if the user selects a file
                if (r == JFileChooser.APPROVE_OPTION)
                {
                    // set the label to the path of the selected file
                    regAssExacSelectedFileLab.setText(regAssExaFile.getSelectedFile().getAbsolutePath());
                }
                // if the user cancelled the operation
                else
                    regAssExacSelectedFileLab.setText("the user cancelled the operation");
            }
        });
        choisirRegAppButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("- Choisir des régles d'associations approximatives.");
                // create an object of JFileChooser class
                regAssAppFile = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                // invoke the showsSaveDialog function to show the save dialog
                int r = regAssAppFile.showSaveDialog(null);
                // if the user selects a file
                if (r == JFileChooser.APPROVE_OPTION)
                {
                    // set the label to the path of the selected file
                    regAssAppSelectedFileLab.setText(regAssAppFile.getSelectedFile().getAbsolutePath());
                }
                // if the user cancelled the operation
                else
                    regAssAppSelectedFileLab.setText("the user cancelled the operation");
            }
        });
        suivantRegAssButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("- Suivant régles d'association.");
                try {
                    SelectionReglesFrame f2 = new SelectionReglesFrame(regAssAppFile, regAssExaFile);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        choisirClassEquiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("- Choisir des classe d'équivalence.");
                // create an object of JFileChooser class
                classeEquivalenceFile = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                // invoke the showsSaveDialog function to show the save dialog
                int r = classeEquivalenceFile.showSaveDialog(null);
                // if the user selects a file
                if (r == JFileChooser.APPROVE_OPTION)
                {
                    // set the label to the path of the selected file
                    classEquiSelectedFileLab.setText(classeEquivalenceFile.getSelectedFile().getAbsolutePath());
                }
                // if the user cancelled the operation
                else
                    classEquiSelectedFileLab.setText("the user cancelled the operation");
            }
        });
        suivantSecClusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("- Suivant classes d'équivalence.");
                try {
                    SelectionClustersFrame f1 = new SelectionClustersFrame(classeEquivalenceFile);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        c.add(MainPanel);
        setVisible(true);
        setLocationRelativeTo(null);
    }

}
