package ru.pixonic;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by aleksandr on 04.11.16.
 */
public class CalculatedFactorialTest extends Assert {

    @Test
    public void factorial() throws Exception {
        CalculatedFactorial cf = CalculatedFactorial.factorial(2);
        assertEquals(2, cf.calc().intValue());

        CalculatedFactorial cf2 = CalculatedFactorial.factorial(3);
        assertEquals(6, cf2.calc().intValue());
    }

    @Test
    public void factorialZero() throws Exception{
        CalculatedFactorial cf = CalculatedFactorial.factorial(0);
        assertEquals(1, cf.calc().intValue());
    }

    @Test
    public void factorialMinusNumber() throws Exception{
        CalculatedFactorial cf = CalculatedFactorial.factorial(-1);
        assertEquals(1, cf.calc().intValue());
    }


}