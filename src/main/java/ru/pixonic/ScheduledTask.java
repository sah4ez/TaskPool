package ru.pixonic;

import java.time.ZonedDateTime;
import java.util.concurrent.Callable;

/**
 * Created by sah4ez on 04.11.16.
 */
public class ScheduledTask implements Callable<Integer> {

    private ZonedDateTime dateTime = ZonedDateTime.now();
    private CalculatedFactorial calculatedFactorial = new CalculatedFactorial();
    private Integer serialNum = 0;

    public ScheduledTask(ZonedDateTime dateTime, CalculatedFactorial calculatedFactorial) {
        this.dateTime = dateTime;
        this.calculatedFactorial = calculatedFactorial;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public CalculatedFactorial getCalculatedFactorial() {
        return calculatedFactorial;
    }

    public void setSerialNum(Integer serialNum) {
        this.serialNum = serialNum;
    }

    public Integer getSerialNum() {
        return serialNum;
    }

    @Override
    public Integer call(){
        return getCalculatedFactorial().calc();
    }
}
