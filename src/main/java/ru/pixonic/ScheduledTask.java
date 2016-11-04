package ru.pixonic;

import java.time.ZonedDateTime;

/**
 * Created by sah4ez on 04.11.16.
 */
public class ScheduledTask {

    private ZonedDateTime dateTime = ZonedDateTime.now();
    private CalculatedFactorial calculatedFactorial = new CalculatedFactorial();

    public ScheduledTask(ZonedDateTime dateTime, CalculatedFactorial calculatedFactorial) {
        this.dateTime = dateTime;
        this.calculatedFactorial = calculatedFactorial;
    }
}
