package com.src;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Paths;
import java.security.InvalidKeyException;

public class CorrectFiles {

    private boolean wasZipped;
    private boolean wasZippedEncrypted;
    private boolean wasEncrypted;

    private boolean needToWrapBack;

    private PathCheck inputPath;
    private PathCheck outputFile;
    private PathCheck inputFile;

    private Encrypt encrypt;
    private String encryptKey;

    private PathCheck isZip(PathCheck inputFile) throws IOException {

        PathCheck outputFile;

        try {
            outputFile = ZipFiles.Unzip(inputFile, inputFile.TempDir());
            wasZippedEncrypted = true;
        } catch (NotDirectoryException e) {
            outputFile = inputFile;
        }

        return outputFile;
    }
    private boolean isXml(PathCheck inputFile) throws Exception {

        boolean isXml = false;
        String buffer;

        Reader r = new TxtReader();
        Finder f = new FindHeaderOfXmlFiles();

        r.Open(inputFile.getFilePath());
        if (r.HasNextLine()) {
            buffer = r.ReadLine();
            isXml = f.exists(buffer);
        }

        r.Close();
        return isXml;
    }
    public PathCheck getInputFile() {
        return inputFile;
    }
    public PathCheck getOutputFile() {
        return outputFile;
    }

    public void setInputFile(PathCheck inputFile) {
        this.inputFile = inputFile;
    }
    public void setEncryptKey(String key) throws InvalidKeyException {
        if(key == null || key.length() == 16 || key.length() == 24 || key.length() == 32) encryptKey = key;
        else {
            throw new InvalidKeyException("You entered a wrong key");
        }
    }
    public void setNeedToWrapBack(boolean needToWrapBack) {
        this.needToWrapBack = needToWrapBack;
    }

    private PathCheck NormaliseEncryptedFile(PathCheck inputFile) throws Exception {

        PathCheck outputFile;

        outputFile = isZip(inputFile);

        if (!wasZippedEncrypted) {
            inputFile.fileRename(FileNameControl.MakeTxtName(inputFile.getFileName()));

            if (isXml(inputFile)) {
                inputFile.fileRename(FileNameControl.MakeXmlName(inputFile.getFileName()));
            }
            outputFile = inputFile;
        }


        return outputFile;
    }
    public void PrepareFile() throws Exception {

        inputPath = new PathCheck(inputFile);

        while (inputFile.isZip() || inputFile.isEnc()) {
            if (inputFile.isZip()) {

                wasZipped = true;
                inputFile = ZipFiles.Unzip(inputFile, inputFile.TempDir());
                outputFile = new PathCheck(inputFile);
                if(!wasEncrypted)inputFile.fileRename("temp_" + inputFile.getFileName());

            }

            if (inputFile.isEnc()) {

                if (encryptKey == null) throw new InvalidKeyException("Input encrypt Key and encrypt Extension");
                encrypt = new Encrypt(encryptKey);

                wasEncrypted = true;
                inputFile = encrypt.DecryptFile(inputFile, inputFile.TempDir());
                inputFile = NormaliseEncryptedFile(inputFile);
                outputFile = new PathCheck(inputFile);
                if(!wasZipped) inputFile.fileRename("temp_" + inputFile.getFileName());

            }
        }

        if(!needToWrapBack) {

            String fileName;
            if(isXml(inputFile)) {
                fileName = FileNameControl.MakeXmlName(inputPath.getFileName());
            }
            else fileName = FileNameControl.MakeTxtName(inputPath.getFileName());

            outputFile = new PathCheck(inputPath.OutputDir()+fileName);
        }

    }
    public PathCheck ReturnFile() throws Exception {

        if(needToWrapBack) {
            if (wasEncrypted && wasZipped) {

                if (inputPath.isEnc()) {
                    outputFile = ZipFiles.Zip(outputFile, inputPath.TempDir(), FileNameControl.MakeZipName(inputPath.getFileName()));
                    wasZipped = false;
                    outputFile = encrypt.EncryptFile(outputFile, inputPath.OutputDir());
                    wasEncrypted = false;
                } else if (inputPath.isZip()) {
                    outputFile = encrypt.EncryptFile(outputFile, inputPath.TempDir());
                    wasEncrypted = false;
                    outputFile = ZipFiles.Zip(outputFile, inputPath.OutputDir(), FileNameControl.MakeZipName(inputPath.getFileName()));
                    wasZipped = false;
                }

            } else if (wasEncrypted) {
                outputFile = encrypt.EncryptFile(outputFile, inputPath.OutputDir());
                wasEncrypted = false;
            } else if (wasZipped) {
                outputFile = ZipFiles.Zip(outputFile, inputPath.OutputDir(), FileNameControl.MakeZipName(inputPath.getFileName()));
                wasZipped = false;
            }
            else {
                outputFile = new PathCheck(inputFile.TempDir() + inputFile.getFileName());
                Files.move(Paths.get(inputFile.getFilePath()),Paths.get(outputFile.getFilePath()));
            }
        }
        return outputFile;

    }

}