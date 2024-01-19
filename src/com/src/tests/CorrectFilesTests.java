package com.src.tests;
import com.src.CorrectFiles;
import com.src.PathCheck;
import org.junit.Assert;
import org.junit.Test;

import java.security.InvalidKeyException;


public class CorrectFilesTests {

    @Test
    public void setEncryptionKey_WrongKeyLength() {

        try {
            CorrectFiles fileNormaliser = new CorrectFiles();
            fileNormaliser.setEncryptKey("LengthOfKeyIsWrong");
            Assert.fail("Wrong key length");

        } catch (InvalidKeyException ignored) {

        }

    }

    @Test
    public void prepareEncryptedFile_NoEncryptionKey() throws Exception {

        PathCheck EncryptedTxt = new PathCheck("/Users/ёзер/IdeaProjects/project_/TestFiles/NewTxtEnc.enc");
//C:\Users\ёзер\IdeaProjects\project_\TestFiles\NewTxtEnc.enc
        CorrectFiles fileNormaliser = new CorrectFiles();
        EncryptedTxt.CreateDirs();

        fileNormaliser.setInputFile(EncryptedTxt);
        try {
            fileNormaliser.PrepareFile();
            Assert.fail("No encryption Key");
        } catch (InvalidKeyException e) {

            EncryptedTxt.ClearTemp();
            EncryptedTxt.ClearOutput();

        }

    }

    @Test
    public void prepareEncryptedTxtFile() throws Exception {

        PathCheck EncryptedTxt = new PathCheck("/Users/ёзер/IdeaProjects/project_/TestFiles/NewTxtEnc.enc");
        PathCheck outputFile;

        CorrectFiles fileNormaliser = new CorrectFiles();
        fileNormaliser.setEncryptKey("KeyIsVerySecret1");
        EncryptedTxt.CreateDirs();

        fileNormaliser.setInputFile(EncryptedTxt);
        fileNormaliser.PrepareFile();
        outputFile = fileNormaliser.getOutputFile();

        EncryptedTxt.ClearTemp();
        EncryptedTxt.ClearOutput();

        Assert.assertEquals(outputFile.getExtension(), ".txt");

    }

    @Test
    public void prepareZippedTxtFile() throws Exception {

        PathCheck ZippedTxt = new PathCheck("/Users/ёзер/IdeaProjects/project_/TestFiles/ZipTxtFile.zip");
        PathCheck outputFile;

        CorrectFiles fileNormaliser = new CorrectFiles();
        ZippedTxt.CreateDirs();

        fileNormaliser.setInputFile(ZippedTxt);
        fileNormaliser.PrepareFile();
        outputFile = fileNormaliser.getOutputFile();

        ZippedTxt.ClearTemp();
        ZippedTxt.ClearOutput();

        Assert.assertEquals(outputFile.getExtension(), ".txt");

    }

    @Test
    public void prepareZippedEncryptedTxtFile() throws Exception {

        PathCheck ZippedEncryptedTxt = new PathCheck("/IdeaProjects/project_/TestFiles/testFile\\\\zippedEncryptedTxt.zip");
        PathCheck outputFile;

        CorrectFiles fileNormaliser = new CorrectFiles();
        fileNormaliser.setEncryptKey("KeyIsVerySecret1");
        ZippedEncryptedTxt.CreateDirs();

        fileNormaliser.setInputFile(ZippedEncryptedTxt);
        fileNormaliser.PrepareFile();
        outputFile = fileNormaliser.getOutputFile();

        ZippedEncryptedTxt.ClearTemp();
        ZippedEncryptedTxt.ClearOutput();

        Assert.assertEquals(outputFile.getExtension(), ".txt");

    }

    @Test
    public void prepareEncryptedZippedTxtFile() throws Exception {

        PathCheck EncryptedZippedTxt = new PathCheck("/IdeaProjects/project_/TestFiles/testFile\\\\encryptedZippedTxt.enc");
        PathCheck outputFile;

        CorrectFiles fileNormaliser = new CorrectFiles();
        fileNormaliser.setEncryptKey("KeyIsVerySecret1");
        EncryptedZippedTxt.CreateDirs();

        fileNormaliser.setInputFile(EncryptedZippedTxt);
        fileNormaliser.PrepareFile();
        outputFile = fileNormaliser.getOutputFile();

        EncryptedZippedTxt.ClearTemp();
        EncryptedZippedTxt.ClearOutput();

        Assert.assertEquals(outputFile.getExtension(), ".txt");

    }

    @Test
    public void prepareEncryptedXmlFile() throws Exception {

        PathCheck EncryptedTxt = new PathCheck("/IdeaProjects/project_/TestFiles/testFile\\\\encryptedXml.enc");
        PathCheck outputFile;

        CorrectFiles fileNormaliser = new CorrectFiles();
        fileNormaliser.setEncryptKey("KeyIsVerySecret1");
        EncryptedTxt.CreateDirs();

        fileNormaliser.setInputFile(EncryptedTxt);
        fileNormaliser.PrepareFile();
        outputFile = fileNormaliser.getOutputFile();

        EncryptedTxt.ClearTemp();
        EncryptedTxt.ClearOutput();

        Assert.assertEquals(outputFile.getExtension(), ".xml");

    }

    @Test
    public void prepareZippedXmlFile() throws Exception {

        PathCheck ZippedTxt = new PathCheck("/Users/ёзер/IdeaProjects/project_/TestFiles/XmlZipFile.zip");
        PathCheck outputFile;

        CorrectFiles fileNormaliser = new CorrectFiles();
        ZippedTxt.CreateDirs();

        fileNormaliser.setInputFile(ZippedTxt);
        fileNormaliser.PrepareFile();
        outputFile = fileNormaliser.getOutputFile();

        ZippedTxt.ClearTemp();
        ZippedTxt.ClearOutput();

        Assert.assertEquals(outputFile.getExtension(), ".xml");

    }

    @Test
    public void prepareZippedEncryptedXmlFile() throws Exception {

        PathCheck ZippedEncryptedTxt = new PathCheck("/IdeaProjects/project_/TestFiles/testFile\\\\zippedEncryptedXml.zip");
        PathCheck outputFile;

        CorrectFiles fileNormaliser = new CorrectFiles();
        fileNormaliser.setEncryptKey("KeyIsVerySecret1");
        ZippedEncryptedTxt.CreateDirs();

        fileNormaliser.setInputFile(ZippedEncryptedTxt);
        fileNormaliser.PrepareFile();
        outputFile = fileNormaliser.getOutputFile();

        ZippedEncryptedTxt.ClearTemp();
        ZippedEncryptedTxt.ClearOutput();

        Assert.assertEquals(outputFile.getExtension(), ".xml");

    }

    @Test
    public void prepareEncryptedZippedXmlFile() throws Exception {

        PathCheck EncryptedZippedTxt = new PathCheck("/IdeaProjects/project_/TestFiles/testFile\\\\encryptedZippedXml.enc");
        PathCheck outputFile;

        CorrectFiles fileNormaliser = new CorrectFiles();
        fileNormaliser.setEncryptKey("KeyIsVerySecret1");
        EncryptedZippedTxt.CreateDirs();

        fileNormaliser.setInputFile(EncryptedZippedTxt);
        fileNormaliser.PrepareFile();
        outputFile = fileNormaliser.getOutputFile();

        EncryptedZippedTxt.ClearTemp();
        EncryptedZippedTxt.ClearOutput();

        Assert.assertEquals(outputFile.getExtension(), ".xml");

    }
}


