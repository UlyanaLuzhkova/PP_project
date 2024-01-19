package com.src;

import java.security.InvalidKeyException;
import java.util.Objects;
import java.util.Scanner;

public class Interface {

    Scanner in = new Scanner(System.in);
    ReadWrite rw;

    public void Calculate() throws Exception {
        FilePreparation();
        RAndWSet();
        rw.Process();
        rw.End();
        System.out.println("The Calculations are in the output: ");
    }
    public void FilePreparation() throws Exception {
        boolean correctFile = false;
        while(!correctFile) {
            PathCheck filePath = PathInput();
            boolean correctPrepare = false;
            rw = new ReadWrite(filePath.getFilePath());
            while (!correctPrepare) {
                try {
                    rw.Prepare();
                    correctPrepare = true;
                    correctFile = true;
                } catch (InvalidKeyException e) {
                    rw.setEncryptKey(KeyInput());
                } catch (IllegalArgumentException e) {
                    System.out.println("This file extension is not supported, try another file");
                }
            }
        }
    }
    public void RAndWSet() {

        String extension = rw.inputFileExtension();

        if(Objects.equals(extension, ".txt")) {

            Reader r = new TxtReader();
            Writer w = new TxtWriter();

            rw.setWriter(w);
            rw.setReader(r);

        }
        else if(Objects.equals(extension, ".xml")) {

            Reader r = new XmlReader();
            Writer w = new XmlWriter();

            rw.setWriter(w);
            rw.setReader(r);

        }

    }

    public PathCheck PathInput() {
        PathCheck filePath = null;
        String filePathTemp;
        boolean correct = false;
        System.out.println("Input path to the file: ");
        while (!correct) {
            filePathTemp = in.nextLine();
            filePath = new PathCheck(filePathTemp);
            correct = filePath.isSupported();
            if(!correct && !Objects.equals(filePathTemp, "")) System.out.println("This extension is not supported, try again");
        }

        return filePath;
    }
    public String KeyInput() {
        System.out.println("Input encrypt key");
        String input = in.nextLine();;
        return input;
    }

}
