package com.src;

public class FileNameControl {

    public static String FileNameOnly(String fileName) {

        String output;

        int fileNameEnd;

        fileNameEnd = fileName.lastIndexOf('.');
        output = fileName.substring(0, fileNameEnd);

        return output;

    }
    public static String FileExtensionOnly(String fileName) {
        String output;

        int fileNameEnd;

        fileNameEnd = fileName.lastIndexOf('.');
        output = fileName.substring(fileNameEnd);

        return output;
    }

    public static String MakeTxtName(String fileName) {
        return FileNameOnly(fileName) + ".txt";
    }
    public static String MakeZipName(String fileName) {
        return FileNameOnly(fileName) + ".zip";
    }
    public static String MakeEncName(String fileName) {
        return FileNameOnly(fileName) + ".enc";
    }
    public static String MakeXmlName(String fileName) {
        return FileNameOnly(fileName) + ".xml";
    }

}