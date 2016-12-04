package ru.pixonic;

import java.time.ZonedDateTime;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by sah4ez on 04.11.16.
 */
public class ScheduledTask extends RecursiveTask<Long> {

    private ZonedDateTime dateTime = ZonedDateTime.now();
    private CalculatedFactorial calculatedFactorial = new CalculatedFactorial();
    private AtomicInteger serialNum = new AtomicInteger(0);
    private AtomicLong result = new AtomicLong(0);

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
        this.serialNum.set(serialNum);
    }

    public int getSerialNum() {
        return serialNum.get();
    }

    @Override
    protected Long compute() {
        while (dateTime.compareTo(ZonedDateTime.now()) > 0) {

        }
        System.out.print(getDateTime().toLocalTime() + " number " + getSerialNum());
        result.set(getCalculatedFactorial().calc());
        System.out.println(" result " + result + " time Execute " + ZonedDateTime.now().toLocalTime());
        return result.get();
    }

//    @Override
//    public Integer call(){
//        return getCalculatedFactorial().calc();
//    }

}
