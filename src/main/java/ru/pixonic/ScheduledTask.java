package ru.pixonic;

import java.time.ZonedDateTime;

/**
 * Created by sah4ez on 04.11.16.
 */
public class ScheduledTask implements Comparable {

    private ZonedDateTime dateTime = ZonedDateTime.now();
    private CalculatedFactorial calculatedFactorial = new CalculatedFactorial();

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

    @Override
    public int compareTo(Object o) {
        return getDateTime().compareTo(((ScheduledTask) o).getDateTime());
    }
}
