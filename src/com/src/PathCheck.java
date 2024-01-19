package com.src;

import java.io.File;

public class PathCheck {

    private String filePath;
    private String path;
    private String fileName;
    private String tempDir;
    private String outputDir;

    public PathCheck(String filePath){
        this.filePath = filePath;

        int fileNameStart = 0;
        fileNameStart = this.filePath.lastIndexOf('/');
        if(fileNameStart != -1) {
            this.fileName = filePath.substring(fileNameStart + 1);
            this.path = filePath.substring(0, fileNameStart + 1);
        }
        else if(fileNameStart == filePath.length()-1) {
            this.fileName = "";
            this.path = filePath;
        }
        else {
            this.fileName = filePath;
            this.path = "";
        }

        this.tempDir = this.path + "temp/";
        this.outputDir = this.path + "output/";
    }
    PathCheck(PathCheck filePathCheck) {
        this.filePath = filePathCheck.filePath;
        this.path = filePathCheck.path;
        this.fileName = filePathCheck.fileName;
        this.tempDir = filePathCheck.tempDir;
        this.outputDir = filePathCheck.outputDir;
    }
    PathCheck(){
    }

    public void CreateDirs() {

        boolean created;

        File temp = new File(tempDir);
        created = temp.mkdir();
        File output = new File(outputDir);
        created = output.mkdir();

    }
    public void fileRename(String fileName) {
        File file = new File(filePath);
        File newFile = new File(path + fileName);
        file.renameTo(newFile);

        this.fileName = fileName;
        this.filePath = this.path + fileName;
    }
    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }
    public void setTempDir(String tempDir) {
        this.tempDir = tempDir;
    }

    public String getFileName() {
        return this.fileName;
    }
    public String getFilePath() {
        return filePath;
    }
    public String getPath() {
        return  path;
    }
    public String TempDir(){
        return this.tempDir;
    }
    public String OutputDir() {
        return this.outputDir;
    }
    public String getExtension()    {
        String output;

        int extensionEnd;

        extensionEnd = fileName.lastIndexOf('.');
        output = fileName.substring(extensionEnd);

        return output;
    }

    public boolean isTxt() {
        return fileName.endsWith(".txt");
    }
    public boolean isZip() {
        return fileName.endsWith(".zip");
    }
    public boolean isEnc() {
        return fileName.endsWith(".enc");
    }
    public boolean isXml() {
        return fileName.endsWith(".xml");
    }
    public boolean isSupported() {
        return isEnc() || isTxt() || isZip() || isXml() ;
    }

    public void ClearTemp(boolean needToDelTemp) {

        File temp = new File(tempDir);
        String[] entries = temp.list();
        int i = 0;
        while( entries != null && i < entries.length) {
            File currentFile = new File(temp.getPath(), entries[i]);
            currentFile.delete();
            i = i + 1;
        }
        if(needToDelTemp) temp.delete();

    }
    public void ClearTemp() {
        ClearTemp(true);
    }
    public void ClearOutput() {

        File temp = new File(outputDir);
        String[] entries = temp.list();
        int i = 0;
        while( entries != null && i < entries.length) {
            File currentFile = new File(temp.getPath(), entries[i]);
            currentFile.delete();
            i = i + 1;
        }
        temp.delete();

    }

    public static void DeleteDirectory(String partsDir,boolean needToDelTemp) {

        File dir = new File(partsDir);
        String[] entries = dir.list();
        int i = 0;
        while( entries != null && i < entries.length) {
            File currentFile = new File(dir.getPath(), entries[i]);
            currentFile.delete();
            i = i + 1;
        }
        if(needToDelTemp) dir.delete();

    }
    public static void DeleteDirectory(String partsDir) {
        DeleteDirectory(partsDir,true);
    }
}

