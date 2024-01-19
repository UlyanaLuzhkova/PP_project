package com.src;
import java.io.FileNotFoundException;
import java.security.InvalidKeyException;
import java.util.Objects;

public class Commands {

    private static void help() {
        System.out.println("This is help");
    }

    public static void Process(String[] args) throws Exception {

        ReadWrite readWrite;

        String inputPathString;
        String encryptionKey;
        String needToWrapString;

        boolean needToWrap;

        PathCheck inputPath;

        try {
            inputPathString = args[0];
            if(inputPathString == null) {
                throw new IllegalArgumentException("Input path is incorrect");
            }
            inputPath = new PathCheck(inputPathString);

            if (Objects.equals(inputPathString, "help")) {
                help();
                throw new Exception();
            } else if (!inputPath.isSupported()) {
                throw new UnsupportedOperationException("This file extension is not supported");
            }


        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Input path is incorrect");
        }

        try {
            encryptionKey = args[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            encryptionKey = null;
        }

        try {
            needToWrapString = args[2];

            if (Objects.equals(needToWrapString, "-w")) needToWrap = false;
            else if (Objects.equals(needToWrapString, "w")) needToWrap = true;
            else throw new IllegalArgumentException("Incorrect argument");

        } catch (ArrayIndexOutOfBoundsException e) {
            needToWrap = true;
        }

        try {
            readWrite = new ReadWrite(inputPathString);
            readWrite.setNeedToWrap(needToWrap);
            readWrite.setEncryptKey(encryptionKey);
            readWrite.Prepare();
        } catch (InvalidKeyException e) {
            throw new InvalidKeyException("Your file is encrypted, try again with encryption key");
        } catch (IllegalArgumentException e) {
            throw new UnsupportedOperationException("This file extension is not supported");
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Input path is incorrect. No such file");
        }

        readWrite.RandWSet();
        readWrite.Process();
        readWrite.End();

        System.out.println("Program output is written to output directory");

    }

}