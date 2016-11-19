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

//    @Override
//    public int compareTo(Object o) {
//        ScheduledTask task = (ScheduledTask) o;
////        int r =getDateTime().compareTo(task.getDateTime());
////        if(r != 0) return r;
//
//        return  Integer.compare(getSerialNum().intValue(), task.getSerialNum().intValue());
////        if(r != 0) return r;
////
////        return 0;
//    }

    @Override
    public Integer call(){
//        while (ZonedDateTime.now().compareTo(getDateTime()) <= 0) {
//        }
        return getCalculatedFactorial().calc();
    }
}
