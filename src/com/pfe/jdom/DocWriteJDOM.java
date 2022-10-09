package com.pfe.jdom;


import com.pfe.Utils;
import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class DocWriteJDOM {

    public static void makeDocc(String[] contentClusterLines) throws Exception {

        SAXBuilder sax = new SAXBuilder();
        Document doc = sax.build(new File("src/com/pfe/files/jdom/form.xml"));

        Element rootNode = doc.getRootElement();
        Element latticeElement = rootNode.getChild("Lattices").getChild("Lattice");

        Element elementsElement = latticeElement.getChild("Elements");
        String[] sortedContent = triBasedOnLengthOfCluster(contentClusterLines);
        constructElementsBloc(sortedContent, elementsElement);

        Element orderElement = latticeElement.getChild("Order");
        Map<String, String> mapp = getGreaterLessArr(sortedContent);
        String[] greaterArr = mapp.keySet().toArray(new String[0]);
        String[] lessArr = mapp.values().toArray(new String[0]);
        constructOrderBloc(greaterArr, lessArr, orderElement);

        Element diagramElement = latticeElement.getChild("Diagram");
        String[] result = getFromToNumberForEdges(sortedContent);
        Map<String, String> map1 = getXandYforEdges(result[0].split(",").length);
        String[] xArr = map1.keySet().toArray(new String[0]);
        String[] yArr = map1.values().toArray(new String[0]);
        String[] fromArr = result[0].split(",");
        String[] toArr = result[1].split(",");
        constructDiagramBloc(xArr, yArr, fromArr, toArr, diagramElement);

        XMLOutputter xmlOutputter = new XMLOutputter();
        // pretty print
        xmlOutputter.setFormat(Format.getPrettyFormat());
        xmlOutputter.output(doc, System.out);
    }

    //Elements bloc => Done
    private static void constructElementsBloc(String[] content, Element elementsElement){
        for(int i=0;i<content.length; i++){
            Element elementElement = new Element("Element");
            elementElement.setAttribute("id", String.valueOf(i+1));
            elementElement.setAttribute("name", content[i]);
            elementsElement.addContent(elementElement);
        }
    }
    // Order bloc => Done
    private static void constructOrderBloc(String[] greaterArr, String[] lessArr, Element orderElement){
        for(int i=0;i<greaterArr.length; i++){
            Element pairElement = new Element("Pair");
            pairElement.setAttribute("greater", greaterArr[i]);
            pairElement.setAttribute("less",lessArr[i]);
            orderElement.addContent(pairElement);
        }
    }
    // Order Diagram => Done
    private static void constructDiagramBloc(String[] xArr, String[] yArr, String[] fromArr, String[] toArr, Element diagramElement)
            throws IOException, JDOMException {

        Element vertecesElement = new Element("Verteces");
        Element edgeElement = new Element("Edges");
        for(int i=0; i<xArr.length; i++){
            vertecesElement.addContent(constructVertexElement(xArr[i], yArr[i], String.valueOf(i+1)));
            edgeElement.addContent(constuctEdgeElement(fromArr[i], toArr[i]));
        }
        diagramElement.addContent(vertecesElement);
        diagramElement.addContent(edgeElement);
        diagramElement.addContent(constructStyleDiagramElement());
    }
    private static Element constructVertexElement(String x, String y, String elem) throws IOException, JDOMException {
        SAXBuilder sax = new SAXBuilder();
        Document doc = sax.build(new File("src/com/pfe/files/jdom/vertex.xml"));
        Element rootNodeVertex = doc.getRootElement();
        rootNodeVertex.getChild("X").setText(x);
        rootNodeVertex.getChild("Y").setText(y);
        rootNodeVertex.getAttribute("elem").setValue(elem);
        Element vertexElement = (Element) rootNodeVertex.clone();
        return vertexElement;
    }
    private static Element constuctEdgeElement(String from, String to) throws IOException, JDOMException {
        SAXBuilder sax = new SAXBuilder();
        Document doc = sax.build(new File("src/com/pfe/files/jdom/edge.xml"));
        Element rootNodeEdge = doc.getRootElement();
        rootNodeEdge.getAttribute("from").setValue(from);
        rootNodeEdge.getAttribute("to").setValue(to);
        Element edgeElement = (Element) rootNodeEdge.clone();
        return edgeElement;
    }
    private static Element constructStyleDiagramElement() throws IOException, JDOMException {
        SAXBuilder sax = new SAXBuilder();
        Document doc = sax.build(new File("src/com/pfe/files/jdom/style.xml"));
        return (Element) doc.getRootElement().clone();
    }


    private static String[] getFromToNumberForEdges(String[] content){
        Map<String, String> map = new HashMap<>();
        String[] result = new String[2];
        String fromResult = "";
        String toResult = "";
        for(int i=0; i<content.length-1; i++){
            for(int j=i+1; j<content.length; j++){
                String line = content[i].split("]")[1].trim().replace("[","");
                String[] nextLine = content[j].split("]")[1].trim().replace("[","").split("\\s");
                if(verifyExistingOfItemsOfNextLineInLine(line, nextLine)){
                    fromResult = fromResult + String.valueOf(i+1) + ",";
                    toResult = toResult + String.valueOf(j+1) + ",";
                }
            }
        }
        result[0] = fromResult;
        result[1] = toResult;
        return result;
    }

    private static boolean verifyExistingOfItemsOfNextLineInLine(String line, String[] itemsNextLine){
        for(String s:itemsNextLine){
            if(!line.contains(s)){
                return false;
            }
        }
        return true;
    }
    private static Map<String, String> getXandYforEdges(int numberOfEdges){
        Map<String, String> map = new HashMap<>();
        int x = 50;
        int y = 50;
        for(int i=0; i<numberOfEdges; i++){
            x+=60;
            y+=60;
            map.put(String.valueOf(x), String.valueOf(y));
        }
        return map;
    }

    private static Map<String, String> getGreaterLessArr(String[] content){
        Map<String, String> map = new HashMap<>();
        for (int i=0; i<content.length; i++){
            map.put(String.valueOf(i),
                    String.valueOf(content.length-i));
        }
        return map;
    }

    public static String[] triBasedOnLengthOfCluster(String[] linesWithSameNumberOfThirdColumn){
        Map<String,Integer> map = new HashMap<>();
        Map<String,Integer> resultMap = new HashMap<>();
        String result = "";
        for(String s:linesWithSameNumberOfThirdColumn){
            int lengthOfCluster = s.split("]")[1].replace("[","").trim().split("\\s").length;
            map.put(s,lengthOfCluster);
        }
        resultMap = sortByValueRev(map);
        for(Map.Entry<String, Integer> entry : resultMap.entrySet()) {
            result += entry.getKey();
            result += "\n";
        }
        return result.split("\n");
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

}
