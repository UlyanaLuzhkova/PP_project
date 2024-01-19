package com.src;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public interface Reader {

    void Open(String fileName) throws Exception;
    String ReadLine() throws Exception;
    boolean HasNextLine() throws Exception;
    void Close() throws Exception;

}

class TxtReader implements Reader{

    FileReader fr;
    Scanner scan;

    @Override
    public void Open(String fileName) throws FileNotFoundException {

        fr = new FileReader(fileName);
        scan = new Scanner(fr);
    }

    @Override
    public String ReadLine() {

        String temp;
        temp = scan.nextLine();
        return temp;

    }

    @Override
    public boolean HasNextLine() {
        return scan.hasNextLine();
    }

    @Override
    public void Close() throws IOException {
        fr.close();
    }

}

class XmlReader implements Reader{

    XMLStreamReader xmlr;

    @Override
    public void Open(String fileName) throws FileNotFoundException, XMLStreamException {
        xmlr = XMLInputFactory.newInstance().createXMLStreamReader(fileName,
                new FileInputStream(fileName));
    }

    @Override
    public String ReadLine() throws XMLStreamException {

        String output = null;
        xmlr.next();
        if (xmlr.isStartElement()) {
            output = "\\" + xmlr.getLocalName();
            if(xmlr.getAttributeLocalName(0) != null) {
                output = output + " " + xmlr.getAttributeLocalName(0) + "=" + xmlr.getAttributeValue(0);
            }
        }
        else if (xmlr.isEndElement()) {
            output = "/" + xmlr.getLocalName();
        }
        else if (xmlr.hasText() && xmlr.getText().trim().length() > 0) {
            output = xmlr.getText();
        }

        return output;
    }

    @Override
    public  boolean HasNextLine() throws XMLStreamException {
        return xmlr.hasNext();
    }

    @Override
    public void Close() throws XMLStreamException {
        xmlr.close();
    }
}


