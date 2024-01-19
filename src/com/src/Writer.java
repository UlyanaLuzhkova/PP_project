package com.src;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;

public interface Writer {

    void Open(String fileName) throws Exception;
    void WriteLine(String line) throws Exception;
    void Close() throws Exception;

}

class TxtWriter implements Writer {

    FileWriter fw;

    @Override
    public void Open(String fileName) throws IOException {
        fw = new FileWriter(fileName);
    }

    @Override
    public void WriteLine(String line) throws Exception {
        fw.write(line + "\n");
    }

    @Override
    public void Close() throws IOException {
        fw.close();
    }

}

class XmlWriter implements Writer {

    XMLStreamWriter xmlw;

    boolean startBefore;
    boolean textBefore;
    int tabNum;

    @Override
    public void Open(String fileName) throws IOException, XMLStreamException {

        XMLOutputFactory output = XMLOutputFactory.newInstance();
        xmlw = output.createXMLStreamWriter(new FileWriter(fileName));
        xmlw.writeStartDocument("Cp1251","1.0");
        xmlw.writeCharacters("\n");

        startBefore = false;
        textBefore = false;
        tabNum = 0;
    }

    @Override
    public void WriteLine(String line) throws Exception {

        if(line != null) {

            if ( line.charAt(0) == '\\') {

                if(startBefore){
                    xmlw.writeCharacters("\n");
                }

                for(int i = 0; i < tabNum; i++) xmlw.writeCharacters("\t");
                int end = line.indexOf(' ');
                if(end != -1) {
                    String out;
                    line = line.substring(1);
                    out = line.substring(0,end-1);
                    xmlw.writeStartElement(out);
                    int nameEnd = line.lastIndexOf('=');
                    xmlw.writeAttribute(line.substring(end,nameEnd),line.substring(nameEnd+1));
                }
                else {
                    line = line.substring(1);
                    xmlw.writeStartElement(line);
                }


                startBefore = true;
                textBefore = false;
                tabNum++;

            }
            else if ( line.charAt(0) == '/') {

                tabNum--;
                if(!textBefore) {
                    for(int i = 0; i < tabNum; i++) xmlw.writeCharacters("\t");
                }
                xmlw.writeEndElement();

                xmlw.writeCharacters("\n");
                startBefore = false;
                textBefore = false;

            }
            else {
                xmlw.writeCharacters( line);
                startBefore = false;
                textBefore = true;
            }

        }

    }

    @Override
    public void Close() throws Exception {
        xmlw.writeEndDocument();
        xmlw.flush();
    }

}
