package ru.pixonic;

/**
 * Created by sah4ez on 04.11.16.
 */
public class CalculatedFactorial{

    private Integer number = 0;
    private Integer result = 1;

    protected CalculatedFactorial() {
    }

    private CalculatedFactorial(Integer number) {
        this.number = number;
    }

    public Integer calc(){
        result = 1;
        for (int i = 1; i<=number; i++){
            result *= i;
        }
        return result;
    }

    public static CalculatedFactorial factorial(Integer number){
        return new CalculatedFactorial(number);
    }
}
