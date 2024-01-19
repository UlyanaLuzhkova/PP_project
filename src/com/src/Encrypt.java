package com.src;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Encrypt {
    String secretKeyString;
    Cipher cipher;
    SecretKeySpec secretKey;

    public Encrypt(String secretKeyString) throws NoSuchPaddingException, NoSuchAlgorithmException {
        this.secretKeyString = secretKeyString;

        cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        secretKey = new SecretKeySpec(secretKeyString.getBytes(),"AES");

    }

    public PathCheck EncryptFile(PathCheck inputFile, String outputDir) throws InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {

        PathCheck outputFile = new PathCheck(outputDir + FileNameControl.MakeEncName(inputFile.getFileName()));
        outputFile.setOutputDir(inputFile.OutputDir());
        outputFile.setTempDir(inputFile.TempDir());

        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        try(FileInputStream fis = new FileInputStream(inputFile.getFilePath());
            BufferedInputStream in = new BufferedInputStream(fis);
            FileOutputStream fout = new FileOutputStream(outputFile.getFilePath());
            BufferedOutputStream out = new BufferedOutputStream(fout); ) {

            byte[] ibuf = new byte[1024];
            int len;

            while((len = in.read(ibuf)) != -1) {
                byte[] obuf = cipher.update(ibuf, 0 , len);
                if(obuf != null) out.write(obuf);
            }
            byte[] obuf = cipher.doFinal();
            if(obuf != null) out.write(obuf);
        }

        return outputFile;
    }

    public PathCheck DecryptFile(PathCheck inputFile, String outputDir) throws IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        PathCheck outputFile = new PathCheck(outputDir + FileNameControl.FileNameOnly(inputFile.getFileName())+".");
        outputFile.setOutputDir(inputFile.OutputDir());
        outputFile.setTempDir(inputFile.TempDir());

        cipher.init(Cipher.DECRYPT_MODE,secretKey);

        try(FileInputStream in = new FileInputStream(inputFile.getFilePath());
            FileOutputStream out = new FileOutputStream(outputFile.getFilePath()); ) {
            byte[] ibuf = new byte[1024];
            int len;

            while(( len = in.read(ibuf)) != -1) {
                byte[] obuf = cipher.update(ibuf,0,len);
                if (obuf != null) out.write(obuf);
            }
            byte[] obuf = cipher.doFinal();
            if (obuf != null) out.write(obuf);
        }

        return outputFile;
    }

}