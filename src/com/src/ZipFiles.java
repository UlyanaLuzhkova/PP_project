package com.src;

import java.io.*;
import java.nio.file.NotDirectoryException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipFiles { //упаковка и распаковка зип файлов

    public static PathCheck Zip(PathCheck file, String outputDir, String zipName) throws IOException { //метод упаковки

        PathCheck archive = new PathCheck(outputDir + zipName);
        archive.setOutputDir(file.OutputDir());
        archive.setTempDir(file.TempDir());

        //инициализация потока для записи в архив
        try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(archive.getFilePath()));
            FileInputStream fis = new FileInputStream(file.getFilePath())) {

            ZipEntry entry = new ZipEntry(file.getFileName());
            zout.putNextEntry(entry);

            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);

            zout.write(buffer);
            zout.closeEntry();

        }

        return archive;

    }
    //метод для извлечения файлов из архива
    public static PathCheck Unzip(PathCheck archive, String outputDir) throws IOException {

        PathCheck file = new PathCheck();

        try(ZipInputStream zin = new ZipInputStream(new FileInputStream(archive.getFilePath()))) {

            ZipEntry entry;
            String filename;

            entry=zin.getNextEntry();
            if(entry == null) throw new NotDirectoryException("This file isn't zip");

            while(entry != null){

                filename = entry.getName();

                file = new PathCheck(outputDir + filename);
                file.setOutputDir(archive.OutputDir());
                file.setTempDir(archive.TempDir());

                FileOutputStream fout = new FileOutputStream(file.getFilePath());
                for(int c = zin.read(); c != -1; c = zin.read()) fout.write(c);

                fout.flush();
                zin.closeEntry();
                fout.close();

                entry=zin.getNextEntry();

            }

        }

        return file;

    }

}