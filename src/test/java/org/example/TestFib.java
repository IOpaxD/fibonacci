package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigInteger;

public class TestFib {
    private Logger log = LogManager.getLogger();
    private FibCalc fibCalc;

    @DataProvider(parallel = false)
    public static Object[][] data() {
        return new Object[][]{
                {"0", "0"},
                {"1", "1"},
                {"10", "55"},
                {"50", "12586269025"},
                {"-3", "2"},
                {"-10", "-55"}
        };
    }

    @Test(dataProvider = "data")
    public void testFib(String n, String exp) {
        String matrix = fibCalc.matrix(n);
        log.info("Получено число из матрицы {}", matrix);
        String cycle = fibCalc.cycle(n);
        log.info("Получено число из цикла {}", cycle);
        long recursion = fibCalc.recursion(Long.parseLong(n));
        log.info("Получено число из рекурсии {}", recursion);

        Assert.assertEquals(cycle, exp, "Неправильный ответ из цикла");
        Assert.assertEquals(matrix,exp, "Неправильный ответ из матрицы");
        Assert.assertEquals(recursion,Long.parseLong(exp), "Неправильный ответ из рекурсии");

    }

    @BeforeClass
    public void setUp() {
        fibCalc = new FibCalc();
    }
}
