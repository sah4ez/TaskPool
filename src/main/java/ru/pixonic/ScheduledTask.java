package ru.pixonic;

import java.time.ZonedDateTime;
import java.util.concurrent.Callable;

/**
 * Created by sah4ez on 04.11.16.
 */
public class ScheduledTask implements Comparable, Callable<Integer> {

    private ZonedDateTime dateTime = ZonedDateTime.now();
    private CalculatedFactorial calculatedFactorial = new CalculatedFactorial();
    private int serialNum = 0;

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

    public void setSerialNum(int serialNum) {
        this.serialNum = serialNum;
    }

    public int getSerialNum() {
        return serialNum;
    }

    @Override
    public int compareTo(Object o) {
        ScheduledTask task = ((ScheduledTask) o);
        int result = getDateTime().compareTo(task.getDateTime());
        if (result != 0) return result;
        result = serialNum - task.serialNum;
        if (result != 0)
            return (int) result / Math.abs(result);
        return 0;
    }

    @Override
    public Integer call() throws Exception {
        while (ZonedDateTime.now().compareTo(getDateTime()) <= 0) {
        }
        Integer result = getCalculatedFactorial().calc();
        return result;
    }
}
