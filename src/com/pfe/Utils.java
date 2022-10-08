package com.pfe;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.*;

public class Utils {

    private static Map<String,String> visRegAss(BufferedReader regAssAppBr, BufferedReader regAssExaBr,
                                                boolean ante, boolean con) throws IOException {
        try {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb1 = new StringBuilder();

            String regA = regAssExaBr.readLine();
            while (regA != null) {
                String[] reg = regA.split("=>");
                if(ante && !con){

                } else if(con && !ante){

                } else if(con && ante){

                }
            }

            String regE = regAssAppBr.readLine();
            while (regE != null) {
                String[] reg = regE.split("->");
                if(ante && !con){

                } else if(con && !ante){

                } else if (con && ante){

                }
            }
        }finally {
            regAssAppBr.close();
            regAssExaBr.close();
        }
        return null;
    }

    public static String[] executeVisualisationAntecedent(String selectedAnt, int numberOfElementWithAntSelected,
                                                File regAssAppFile, File regAssExaFile) throws IOException {

        long start= System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader(regAssAppFile.getPath()));
        BufferedReader br1 = new BufferedReader(new FileReader(regAssExaFile.getPath()));

        String st;
        String st1;
        String resultatTextArea = "";
        String[] resultat = new String[3];
        resultat[0] = selectedAnt;

        PrintWriter writer = new PrintWriter("src/com/pfe/files/resultVisualisationRegAss.txt", "UTF-8");
        writer.println("Resultat du fichier des régles approximatives :");
        resultatTextArea = resultatTextArea + "Resultat du fichier des régles approximatives :" + "\n";
        while ((st = br.readLine()) != null){
            System.out.print(st);
            // Print the string
            String[] reg = st.split("->");
            String[] antecedents = reg[0].trim()
                    .replace("[","").replace("]","")
                    .split("\t");
            if (Arrays.asList(antecedents).contains(selectedAnt.trim())
                    && antecedents.length <= numberOfElementWithAntSelected){
                writer.println(st);
                resultatTextArea = resultatTextArea + st + "\n";
            }

        }
        writer.println("Resultat du fichier des régles exactes :");
        resultatTextArea = resultatTextArea + "Resultat du fichier des régles exactes :" + "\n";
        while ((st1 = br1.readLine()) != null){
            // Print the string
            String[] reg = st1.split("=>");
            String[] antecedents = reg[0].trim()
                    .replace("[","").replace("]","")
                    .split("\t");
            if (Arrays.asList(antecedents).contains(selectedAnt.trim())
                    && antecedents.length <= numberOfElementWithAntSelected){
                writer.println(st1);
                resultatTextArea = resultatTextArea + st1 + "\n";
            }
        }
        writer.close();
        resultat[1] = resultatTextArea;
        long end = System.currentTimeMillis();
        System.out.println("\nLe temps d'execution de la visualisation de l'antecedent est : "+(end-start)+" millisecondes");
        resultat[2] = "Le temps d'execution de la visualisation de l'antecedent est : "+(end-start)+" millisecondes";
        return resultat;
    }

    public static String[] executeVisualisationConclusion(String selectedCon, int numberOfElementWithConSelected,
                                                          File regAssAppFile, File regAssExaFile) throws IOException{
        long start= System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader(regAssAppFile.getPath()));
        BufferedReader br1 = new BufferedReader(new FileReader(regAssExaFile.getPath()));

        String st;
        String st1;
        String resultatTextArea = "";
        String[] resultat = new String[3];
        resultat[0] = selectedCon;

        PrintWriter writer = new PrintWriter("src/com/pfe/files/resultVisualisationRegAss.txt", "UTF-8");
        writer.println("Resultat du fichier des régles approximatives :");
        resultatTextArea = resultatTextArea + "Resultat du fichier des régles approximatives :" + "\n";
        while ((st = br.readLine()) != null){
            System.out.print(st);
            String[] reg = st.split("->");
            String[] conclusions = reg[1].trim().split("]")[0]
                    .replace("[","").split("\t");
            if (Arrays.asList(conclusions).contains(selectedCon.trim())
                    && conclusions.length <= numberOfElementWithConSelected){
                writer.println(st);
                resultatTextArea = resultatTextArea + st + "\n";
            }

        }
        writer.println("Resultat du fichier des régles exactes :");
        resultatTextArea = resultatTextArea + "Resultat du fichier des régles exactes :" + "\n";
        while ((st1 = br1.readLine()) != null){
            String[] reg = st1.split("=>");
            String[] conclusions = reg[1].trim().split("]")[0]
                    .replace("[","").split("\t");
            if (Arrays.asList(conclusions).contains(selectedCon.trim())
                    && conclusions.length <= numberOfElementWithConSelected){
                writer.println(st1);
                resultatTextArea = resultatTextArea + st1 + "\n";
            }
        }
        writer.close();
        resultat[1] = resultatTextArea;
        long end = System.currentTimeMillis();
        System.out.println("\nLe temps d'execution de la visualisation de conclusion est : "+(end-start)+" millisecondes");
        resultat[2] = "Le temps d'execution de la visualisation de conclusion est : "+(end-start)+" millisecondes";
        return resultat;
    }

    public static String[] executeVisualisationAntecedentAndConclusion(String selectedAnt, int numberOfElementWithAntSelected,
                                                                       String selectedCon, int numberOfElementWithConSelected,
                                                                       File regAssAppFile, File regAssExaFile) throws IOException {
        long start= System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader(regAssAppFile.getPath()));
        BufferedReader br1 = new BufferedReader(new FileReader(regAssExaFile.getPath()));

        String st;
        String st1;
        String resultatTextArea = "";
        String[] resultat = new String[4];
        resultat[0] = selectedAnt;
        resultat[2] = selectedCon;

        PrintWriter writer = new PrintWriter("src/com/pfe/files/resultVisualisationRegAss.txt", "UTF-8");
        writer.println("Resultat du fichier des régles approximatives :");
        resultatTextArea = resultatTextArea + "Resultat du fichier des régles approximatives :" + "\n";
        while ((st = br.readLine()) != null){
            System.out.print(st);
            String[] reg = st.split("->");
            String[] conclusions = reg[1].trim().split("]")[0]
                    .replace("[","").split("\t");
            String[] antecedents = reg[0].trim()
                    .replace("[","").replace("]","")
                    .split("\t");
            if (Arrays.asList(conclusions).contains(selectedCon.trim())
                    && conclusions.length <= numberOfElementWithConSelected
                    && Arrays.asList(antecedents).contains(selectedAnt.trim())
                    && antecedents.length <= numberOfElementWithAntSelected ){
                writer.println(st);
                resultatTextArea = resultatTextArea + st + "\n";
            }

        }
        writer.println("Resultat du fichier des régles exactes :");
        resultatTextArea = resultatTextArea + "Resultat du fichier des régles exactes :" + "\n";
        while ((st1 = br1.readLine()) != null){
            String[] reg = st1.split("=>");
            String[] conclusions = reg[1].trim().split("]")[0]
                    .replace("[","").split("\t");
            String[] antecedents = reg[0].trim()
                    .replace("[","").replace("]","")
                    .split("\t");
            if (Arrays.asList(conclusions).contains(selectedCon.trim())
                    && conclusions.length <= numberOfElementWithConSelected
                    && Arrays.asList(antecedents).contains(selectedAnt.trim())
                    && antecedents.length <= numberOfElementWithAntSelected ){
                writer.println(st1);
                resultatTextArea = resultatTextArea + st1 + "\n";
            }
        }
        writer.close();
        resultat[1] = resultatTextArea;
        long end = System.currentTimeMillis();
        System.out.println("\nLe temps d'execution de la visualisation de conclusion et antécédent est : "+(end-start)+" millisecondes");
        resultat[3] = "Le temps d'execution de la visualisation de conclusion et antécédent est : "+(end-start)+" millisecondes";
        return resultat;
    }

    public static String[] executeBiClusterSelection(File classeEquivalenceFile, String[] itemsChoisis) throws IOException {
        long start= System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader(classeEquivalenceFile.getPath()));
        String st;
        String[] resultat = new String[3];
        String premierCluster = "";
        String dexiemeCluster = "";
        while ((st = br.readLine()) != null){
            if (verifyExistingOfItemsInLinePart1(itemsChoisis, st)){
                premierCluster += st.replaceAll("\\t", "    ") + "\n";
            } else if (verifyExistingOfItemsInLinePart2(itemsChoisis, st)){
                dexiemeCluster += st.replaceAll("\\t", "    ") + "\n";
            }
        }
        resultat[0] = tri(premierCluster);
        resultat[1] = tri(dexiemeCluster);
        long end = System.currentTimeMillis();
        System.out.println("\nLe temps d'execution de la visualisation de bi cluster est : "+(end-start)+" millisecondes");
        resultat[2] = "Le temps d'execution de la visualisation de bi cluster est : "+(end-start)+" millisecondes";
        return resultat;
    }

    private static boolean verifyExistingOfItemsInLinePart1(String[] itemsChoisis, String line){
        String[] linePart1 = line.split("]")[0].replace("[","").split("\t");
        for(String item:itemsChoisis){
            if(!Arrays.asList(linePart1).contains(item)){
                return false;
            }
        }
        return true;
    }

    private static boolean verifyExistingOfItemsInLinePart2(String[] itemsChoisis, String line){
        String[] linePart2 = line.split("]")[1].replace("[","").split("\t");
        for(String item:itemsChoisis){
            if(!Arrays.asList(linePart2).contains(item)){
                return false;
            }
        }
        return true;
    }

    private static String tri(String linesInString){
        // 1- Le tri selon le nombre de items dans le générateur
        String[] sortedListWithNumberOfGen = triBasedOnNumberOfGenerator(linesInString);
        String content = "";
        String firstResult = "";

        for(int i=0; i<sortedListWithNumberOfGen.length - 1; i++){
            if(!Arrays.stream(content.split("\n")).anyMatch(sortedListWithNumberOfGen[i]::equals)){
                content += sortedListWithNumberOfGen[i];
                content += "\n";
            }
            if(StringUtils.isNotBlank(sortedListWithNumberOfGen[i+1])
                    && (getLenghOfGenerator(sortedListWithNumberOfGen[i])
                    == getLenghOfGenerator(sortedListWithNumberOfGen[i+1]))){
                content += sortedListWithNumberOfGen[i+1];
                content += "\n";
            }else{
                // 2- Le content contient une liste des lignes qui contiennent le même nombre de items dans le
                // générateur et on veut les trier selon le nombre dans le troisième colonne de la ligne.
                firstResult += triBasedOnTheNumberOfThirdColumn(content);
                content = "";
            }
        }
        firstResult += triBasedOnTheNumberOfThirdColumn(content);
        content = "";

        String[] sortedListWithNumberOfGenAndTheNumberOfTheThirdColumn = firstResult.split("\n");
        String finalResult = "";
        for(int i=0; i<sortedListWithNumberOfGenAndTheNumberOfTheThirdColumn.length - 1; i++){
            if(!Arrays.stream(content.split("\n"))
                    .anyMatch(sortedListWithNumberOfGenAndTheNumberOfTheThirdColumn[i]::equals)){
                content += sortedListWithNumberOfGenAndTheNumberOfTheThirdColumn[i];
                content += "\n";
            }
            if(StringUtils.isNotBlank(sortedListWithNumberOfGenAndTheNumberOfTheThirdColumn[i+1])
                    && (getNumberOfThirdColumn(sortedListWithNumberOfGenAndTheNumberOfTheThirdColumn[i])
                    == getNumberOfThirdColumn(sortedListWithNumberOfGenAndTheNumberOfTheThirdColumn[i+1]))){
                content += sortedListWithNumberOfGenAndTheNumberOfTheThirdColumn[i+1];
                content += "\n";
            }else{
                // 3- Le content contient une liste des lignes qui contiennent le même nombre dans le
                // troisième colonne et on veut les trier selon le nombre des items dans le cluster.
                finalResult += triBasedOnLengthOfCluster(content);
                content = "";
            }
        }
        finalResult += triBasedOnLengthOfCluster(content);
        return finalResult;
    }

    private static int getLenghOfGenerator(String line){
        return line.split("]")[0].replace("[","").split("\\s+").length;
    }

    private static int getNumberOfThirdColumn(String line){
        return Integer.valueOf(line.split("]")[2].trim().split(":")[0].trim());
    }

    private static String[] triBasedOnNumberOfGenerator(String clusterString){
        Map<String,Integer> map = new HashMap<>();
        Map<String,Integer> resultMap = new HashMap<>();
        String result = "";
        String[] arr = clusterString.split("\n");
        for(String s:arr){
            int lengthOfCluster = s.split("]")[0].replace("[","").trim().split("\\s").length;
            map.put(s,lengthOfCluster);
        }
        resultMap = sortByValue(map);
        for(Map.Entry<String, Integer> entry : resultMap.entrySet()) {
            result += entry.getKey();
            result += "\n";
        }
        return result.split("\n");
    }

    private static String triBasedOnTheNumberOfThirdColumn(String linesWithSameNumberOfGenerator){
        Map<String,Integer> map = new HashMap<>();
        Map<String,Integer> resultMap = new HashMap<>();
        String result = "";
        String[] arr = linesWithSameNumberOfGenerator.split("\n");
        for(String s:arr){
            int numberThirdColumn = Integer.parseInt(
                    s.split("]")[2].split(":")[0].replaceAll(" ","")
            );
            map.put(s,numberThirdColumn);
        }
        resultMap = sortByValueRev(map);
        for(Map.Entry<String, Integer> entry : resultMap.entrySet()) {
            result += entry.getKey();
            result += "\n";
        }
        return result;
    }

    private static String triBasedOnLengthOfCluster(String linesWithSameNumberOfThirdColumn){
        Map<String,Integer> map = new HashMap<>();
        Map<String,Integer> resultMap = new HashMap<>();
        String result = "";
        String[] arr = linesWithSameNumberOfThirdColumn.split("\n");
        for(String s:arr){
            int lengthOfCluster = s.split("]")[1].replace("[","").trim().split("\\s").length;
            map.put(s,lengthOfCluster);
        }
        resultMap = sortByValueRev(map);
        for(Map.Entry<String, Integer> entry : resultMap.entrySet()) {
            result += entry.getKey();
            result += "\n";
        }
        return result;
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValueRev(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Collections.reverse(list);
        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
