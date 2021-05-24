package com.converter.models;


import org.springframework.stereotype.Component;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedInputStream;

import java.io.IOException;

import java.net.URL;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Component
public class Parser {

    private static final List<Valute> list=new ArrayList<>();

    public static List<Valute> getList() {
        return list;
    }

    private final static SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");

    private final static String url="https://www.cbr.ru/scripts/XML_daily.asp?date_req=";




    public static void downloadUsingStream() throws IOException, ParserConfigurationException, SAXException {
        Date date=new Date();
        String urlStr= url+simpleDateFormat.format(date);
        URL url=new URL(urlStr);

        BufferedInputStream bis=new BufferedInputStream(url.openStream());


        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder=factory.newDocumentBuilder();

        Document document=builder.parse(bis);
        Element element=document.getDocumentElement();
        NodeList nodeList= element.getChildNodes();

        list.clear();
        printElements(nodeList);
        for(int i=0;i< list.size();i++)
            list.get(i).setData(simpleDateFormat.format(date));
    }








    private static void printElements(NodeList nodeList){
        String id;
        Date date=new Date();
        Valute valute=new Valute(simpleDateFormat.format(date));
        list.add(valute);
        for(int i=0;i<nodeList.getLength();i++){
            Valute valuate=new Valute();
            if(nodeList.item(i) instanceof  Element){
                if(((Element) nodeList.item(i)).hasAttribute("ID")){
                    id= (((Element)nodeList.item(i)).getAttribute("ID"));
                    valuate.setID(id);
                }
            }

            if(nodeList.item(i).hasChildNodes()) {
                getTextContentFromChildNodes(nodeList.item(i).getChildNodes(),valuate);
            }
            list.add(valuate);
        }


    }
    private static void getTextContentFromChildNodes(NodeList nodeList,Valute valute){

        String numCode;
        String charCode;
        int nominal;
        String name;
        double value;

        for (int i=0;i<nodeList.getLength();i++) {
            if (nodeList.item(i).getNodeName().equals("NumCode")) {
                numCode = nodeList.item(i).getTextContent();
                valute.setNumCode(numCode);
            }
            if (nodeList.item(i).getNodeName().equals("CharCode")) {
                charCode= nodeList.item(i).getTextContent();
                valute.setCharCode(charCode);
            }
            if (nodeList.item(i).getNodeName().equals("Nominal")) {
                nominal= Integer.parseInt(nodeList.item(i).getTextContent());
                valute.setNominal(nominal);
            }
            if (nodeList.item(i).getNodeName().equals("Name")) {
                name = nodeList.item(i).getTextContent();
                valute.setName(name);
            }
            if (nodeList.item(i).getNodeName().equals("Value")) {
                value = Double.parseDouble(nodeList.item(i).getTextContent().replace(',','.'));
                valute.setValue(value);
            }
        }
    }


}
