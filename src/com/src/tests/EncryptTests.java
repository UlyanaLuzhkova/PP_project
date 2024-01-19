package com.src.tests;
import com.src.Encrypt;
import com.src.PathCheck;
import org.junit.Assert;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;


public class EncryptTests {

    @Test
    public void encryptionTest() throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, IOException, BadPaddingException, InvalidKeyException {

        PathCheck inputPath = new PathCheck("/Users/Юзер/IdeaProjects/project_/TestFiles/TxtFile.txt");
        inputPath.CreateDirs();
        PathCheck outputFile;

        FileReader fr = new FileReader(inputPath.getFilePath());
        Scanner scan = new Scanner(fr);

        String inputFileLine = scan.nextLine();
        fr.close();

        Encrypt encryptControl = new Encrypt("KeyIsVerySecret1");
        outputFile = encryptControl.EncryptFile(inputPath,inputPath.TempDir());
        outputFile = encryptControl.DecryptFile(outputFile,inputPath.TempDir());

        fr = new FileReader(outputFile.getFilePath());
        scan = new Scanner(fr);

        String outputFileLine = scan.nextLine();

        inputPath.ClearOutput();
        inputPath.ClearTemp();

        Assert.assertEquals(inputFileLine,outputFileLine);

    }
}