package com.src.tests;
import com.src.Commands;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.security.InvalidKeyException;

public class CommandsTests {

    @Test
    public void Commands_UnsupportedExtension() throws Exception {

        String[] args = {"/IdeaProjects/project_/TestFiles/testFile.rtf"};

        try {
            Commands.Process(args);
            Assert.fail("Extension is not supported");
        } catch (UnsupportedOperationException ignored) {

        }

    }

    @Test
    public void Commands_PathNotExists() throws Exception {

        String[] args = {"/IdeaProjects/project_/TestFiles/testFile1.txt"};

        try {
            Commands.Process(args);
            Assert.fail("Path is incorrect");
        } catch (FileNotFoundException ignored) {

        }

    }

    @Test
    public void Commands_InputPathIsNull() throws Exception {

        String[] args = {null};

        try {
            Commands.Process(args);
            Assert.fail("Input path is wrong");
        } catch (IllegalArgumentException ignored) {

        }

    }

    @Test
    public void Commands_ArgsIsEmpty() throws Exception {

        String[] args = new String[0];

        try {
            Commands.Process(args);
            Assert.fail("Input path is wrong");
        } catch (IllegalArgumentException ignored) {

        }

    }


    @Test
    public void Commands_NoEncryptionKey() throws Exception {

        String[] args = {"/IdeaProjects/project_/TestFiles/testFile\\\\encryptedTxt.enc"};

        try {
            Commands.Process(args);
            Assert.fail("No encryption key");
        } catch (InvalidKeyException ignored) {

        }

    }

    @Test
    public void Commands_KeyIsWrong() throws Exception {

        String[] args = {"/IdeaProjects/project_/TestFiles/testFile\\\\encryptedTxt.enc","123456789"};

        try {
            Commands.Process(args);
            Assert.fail("Key is wrong");
        } catch (InvalidKeyException ignored) {

        }

    }

    @Test
    public void Commands_WrongWrapArgument() throws Exception {

        String[] args = {"/IdeaProjects/project_/TestFiles/testFile\\\\encryptedTxt.enc","KeyIsVerySecret1","e"};

        try {
            Commands.Process(args);
            Assert.fail("Wrap argument is incorrect");
        } catch (IllegalArgumentException ignored) {

        }

    }

}