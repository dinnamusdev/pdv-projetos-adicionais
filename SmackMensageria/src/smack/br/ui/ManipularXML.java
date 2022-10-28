/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smack.br.ui;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author DSWM
 */
public class ManipularXML {

     DocumentBuilderFactory factory ;
    DocumentBuilder builder; 
    Document doc;
    public ManipularXML(File xml) throws ParserConfigurationException, IOException, SAXException {
         factory = DocumentBuilderFactory.newInstance();
         builder = factory.newDocumentBuilder();
         doc = builder.parse(xml);
    }
    
     public ManipularXML(String xml) throws ParserConfigurationException, SAXException, IOException {
         factory = DocumentBuilderFactory.newInstance();
         builder = factory.newDocumentBuilder();
         doc = builder.parse(new ByteArrayInputStream(xml.getBytes()));
    }

    

    public NodeList localizarTagXML( String busca) {
        NodeList nodes = null;
        try {

            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();

            Object result = xpath.compile(busca).evaluate(doc, XPathConstants.NODESET);

            nodes = (NodeList) result;

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return nodes;
    }
}
