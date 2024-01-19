package com.src;

import java.security.InvalidKeyException;
import java.util.Objects;

public class ReadWrite {

    private Reader reader;
    private Writer writer;

    private PathCheck outputFile;
    private PathCheck inputFile;
    private PathCheck inputPath;

    private CorrectFiles correctFiles;

    private String filePath;

    public ReadWrite(String filePath){
        this.filePath = filePath;
        inputPath = new PathCheck(this.filePath);
        correctFiles = new CorrectFiles();
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }
    public void setWriter(Writer writer) {
        this.writer = writer;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public void setNeedToWrap(boolean needToWrap) {
        correctFiles.setNeedToWrapBack(needToWrap);
    }
    public void setEncryptKey(String encryptKey) throws InvalidKeyException {
        correctFiles.setEncryptKey(encryptKey);
    }
    public void setOutputPath(String outputPath) {
        inputPath.setOutputDir(outputPath);
    }
    public void setTempPath(String tempPath) {
        inputPath.setTempDir(tempPath);
    }
    public String inputFileExtension() {

        int fileExtensionStart = 0;
        String output = null;
        fileExtensionStart = this.inputFile.getFileName().lastIndexOf('.');
        if(fileExtensionStart != -1) {
            output = inputFile.getFileName().substring(fileExtensionStart);
        }
        return output;

    }

    public void Prepare() throws Exception {

        inputPath.CreateDirs();

        inputFile = inputPath;
        correctFiles.setInputFile(inputFile);
        correctFiles.PrepareFile();
        outputFile = correctFiles.getOutputFile();
        inputFile = correctFiles.getInputFile();

        if(outputFile == null) {
            outputFile = new PathCheck(inputFile.OutputDir() + inputFile.getFileName());
        }

        if(!inputFile.isSupported()) throw new IllegalArgumentException("This exception of file is not supported");

    }
    public void RandWSet() {

        String extension = inputFileExtension();
        Reader r = null;
        Writer w = null;

        if (Objects.equals(extension, ".txt")) {

            r = new TxtReader();
            w = new TxtWriter();

        } else if (Objects.equals(extension, ".xml")) {

            r = new XmlReader();
            w = new XmlWriter();

        }

        setWriter(w);
        setReader(r);
    }
    public void Process() throws Exception {

        String buffer;
        String output;

        reader.Open(inputFile.getFilePath());
        writer.Open(outputFile.getFilePath());

        while(reader.HasNextLine()) {

            buffer = reader.ReadLine();
            if(buffer != null ) {
                output = Calculator.Process(buffer);
                writer.WriteLine(output);
            }

        }

        reader.Close();
        writer.Close();

    }
    public PathCheck End(boolean needToDelTemp) throws Exception {
        PathCheck output = correctFiles.ReturnFile();
        inputPath.ClearTemp(needToDelTemp);
        return output;
    }
    public PathCheck End() throws Exception {
        return End(false);
    }

}