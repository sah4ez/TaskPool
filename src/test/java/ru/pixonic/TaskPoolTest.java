package ru.pixonic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;


/**
 * Created by sah4ez on 04.11.16.
 */
public class TaskPoolTest extends Assert{

    @Before
    public void setUp(){

    }

    @Test
    public void addTaskToTaskPoll(){
        LocalDate date = LocalDate.of(2016, 11, 4);
        LocalTime time = LocalTime.of(14, 6, 30);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime dateTime = ZonedDateTime.of(date, time, zoneId);

        CalculatedFactorial cf = CalculatedFactorial.factorial(5);

        ScheduledTask task = new ScheduledTask(dateTime, cf);

        TaskPool taskPool = new TaskPool();
        taskPool.add(task);
        assertEquals(1, taskPool.size());
    }

}
