package org.example;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class FibCalc {
    Logger log = LogManager.getLogger();


    public static void main(String[] args) throws IOException {
        FibCalc fibCalc = new FibCalc();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Для выхода введите любую букву");
        while (true) {
            System.out.print("Введите число:  ");
            try {
                System.out.println(fibCalc.calc(reader.readLine()));
            } catch (NumberFormatException s) {
                System.out.println("Выход\n" + s);
                break;
            }
        }
        reader.close();
    }

    public final static BigInteger[][] ZERO = {{BigInteger.ONE, BigInteger.ZERO}, {BigInteger.ZERO, BigInteger.ONE}};
    public final static BigInteger[][] ONE = {{BigInteger.ZERO, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ONE}};

    public static BigInteger[][] mul(BigInteger[][] a, BigInteger[][] b) {
        return new BigInteger[][]{
                {a[0][0].multiply(b[0][0]).add(a[0][1].multiply(b[1][0])), a[0][0].multiply(b[0][1]).add(a[0][1].multiply(b[1][1]))},
                {a[1][0].multiply(b[0][0]).add(a[1][1].multiply(b[1][0])), a[1][0].multiply(b[0][1]).add(a[1][1].multiply(b[1][1]))}
        };
    }

    public BigInteger[][] pow(BigInteger[][] a, long l) {
        if (l == 0) return ZERO;
        if (l == 1) return a;
        if (l == 2) return mul(a, a);
        if (l % 2 == 1) return mul(ONE, pow(a, l - 1));
        return pow(pow(a, l / 2), 2);
    }

    public String calc(String s) {
        BigInteger bigOne;
        BigInteger bigTwo;

        long l = Long.parseLong(s);
        if (l < 0) {
            l = -(l);
            if (l % 2 == 0) {
                bigOne = pow(ONE, l)[0][1];
                bigTwo = bigOne.multiply(BigInteger.valueOf(-1));
                return bigTwo.toString();
            }
        }
        bigOne = pow(ONE, l)[0][1];
        return bigOne.toString();
    }
}
