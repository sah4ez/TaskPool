package ru.pixonic;

import java.util.concurrent.Callable;

/**
 * Created by sah4ez on 04.11.16.
 */
public class CalculatedFactorial implements Callable<Integer> {

    private Integer number = 0;
    private Integer result = 1;

    protected CalculatedFactorial() {
    }

    private CalculatedFactorial(Integer number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        for (int i = 1; i<=number; i++){
            result *= i;
        }
        return result;
    }

    public static CalculatedFactorial factorial(Integer number){
        return new CalculatedFactorial(number);
    }
}
