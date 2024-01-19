package com.src.tests;
import com.src.Calculator;
import org.junit.Assert;
import org.junit.Test;

public class CalculatorTests {

    @Test
    public void CalculatorTest1() {
        Assert.assertEquals(Calculator.Process("2+3"),"5.0");
    }

    @Test
    public void CalculatorTest2() {
        Assert.assertEquals(Calculator.Process("5-3"),"2.0");
    }

    @Test
    public void CalculatorTest3() {
        Assert.assertEquals(Calculator.Process("3*3"),"9.0");
    }

    @Test
    public void CalculatorTest4() {
        Assert.assertEquals(Calculator.Process("6/2"),"3.0");
    }

    @Test
    public void CalculatorTest5() {
        Assert.assertEquals(Calculator.Process("(2+2)*3"),"12.0");
    }

    @Test
    public void CalculatorTest6() {
        Assert.assertEquals(Calculator.Process("(2+7)/3"),"3.0");
    }

    @Test
    public void CalculatorTest7() {

        try {
            Calculator.Process("10/0");
            Assert.fail("Divide by zero");
        }
        catch (ArithmeticException ignored) {

        }
    }

}