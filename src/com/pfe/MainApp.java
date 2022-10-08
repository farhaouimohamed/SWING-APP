package com.pfe;

import com.pfe.selectionFiles.Frame;

import java.io.*;
import java.util.Arrays;

public class MainApp {

    public static void main(String[] args) throws Exception{

        Frame f = new Frame();
        /*String antChoosed = "i=A";
        String concSelected = "i=A";
        int numberOfElementWithAntSelected = 3;
        int numberOfElementWithConSelected = 3;
        //Frame f = new Frame();

        InputStream in = MainApp.class.getResourceAsStream("./files/reglesAssociationsApproximatives.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        InputStream in1 = MainApp.class.getResourceAsStream("./files/reglesAssociationsExactes.txt");
        BufferedReader br1 = new BufferedReader(new InputStreamReader(in1));

        String st;
        String st1;

        PrintWriter writer = new PrintWriter("src/com/pfe/files/resultVisualisationRegAss.txt", "UTF-8");
        writer.println("Resultat du fichier des régles approximatives :");
        while ((st = br.readLine()) != null){
            // Print the string
            String[] reg = st.split("->");
            String[] conclusions = reg[1].trim().split("]")[0]
                    .replace("[","").split("\t");
            if (Arrays.asList(conclusions).contains(antChoosed)
                    && conclusions.length == numberOfElementWithAntSelected){
                writer.println(st);
            }
        }
        writer.println("Resultat du fichier des régles exactes :");
        while ((st1 = br1.readLine()) != null){
            // Print the string
            String[] reg = st1.split("=>");
            String[] conclusions = reg[1].trim().split("]")[0]
                    .replace("[","").split("\t");
            if (Arrays.asList(conclusions).contains(concSelected)
                    && conclusions.length == numberOfElementWithConSelected){
                writer.println(st1);
            }
        }
        writer.close();*/

    }

    private void validateAntecedentButton() throws IOException {
        String antChoosed = "c=-";
        String concSelected = "c=-";
        int numberOfElementWithAntSelected = 1;
        int numberOfElementWithConSelected = 1;
        //Frame f = new Frame();

        InputStream in = MainApp.class.getResourceAsStream("./files/reglesAssociationsApproximatives.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        InputStream in1 = MainApp.class.getResourceAsStream("./files/reglesAssociationsExactes.txt");
        BufferedReader br1 = new BufferedReader(new InputStreamReader(in1));

        String st;
        String st1;
        // Condition holds true till
        // there is character in a string
        PrintWriter writer = new PrintWriter("src/com/pfe/files/resultVisualisationRegAss.txt", "UTF-8");
        writer.println("Resultat du fichier des régles approximatives :");
        while ((st = br.readLine()) != null){
            // Print the string
            String[] reg = st.split("->");
            String[] antecedents = reg[0].trim()
                    .replace("[","").replace("]","")
                    .split("\t");
            String[] conclusions = reg[1].trim().split("]")[0]
                    .replace("[","").split("\t");
            if (Arrays.asList(antecedents).contains(antChoosed)
                    && antecedents.length == numberOfElementWithAntSelected){
                writer.println(st);
            }

        }
        writer.println("Resultat du fichier des régles exactes :");
        while ((st1 = br1.readLine()) != null){
            // Print the string
            String[] reg = st1.split("=>");
            String[] antecedents = reg[0].trim()
                    .replace("[","").replace("]","")
                    .split("\t");
            if (Arrays.asList(antecedents).contains(concSelected)
                    && antecedents.length == numberOfElementWithConSelected){
                writer.println(st1);
            }
        }
        writer.close();
        //String[] conclusions = reg[1].trim().split("]")[0]
        //        .replace("[","").split("\t");
    }
}
