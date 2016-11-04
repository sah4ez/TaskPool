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
public class TaskPoolTest extends Assert {

    ZoneId zoneId;
    LocalDate date;
    LocalTime time;
    ZonedDateTime dateTime;
    CalculatedFactorial cf;
    TaskPool taskPool;


    @Before
    public void setUp() {
        zoneId = ZoneId.systemDefault();
        date = LocalDate.of(2016, 11, 4);
        time = LocalTime.of(14, 6, 30);
        dateTime = ZonedDateTime.of(date, time, zoneId);
        cf = CalculatedFactorial.factorial(5);
        taskPool = new TaskPool();
    }

    @Test
    public void addTaskToTaskPoll() throws Exception {

        assertEquals(0, taskPool.size());

        ScheduledTask task = new ScheduledTask(dateTime, cf);

        taskPool.addTask(task);
        assertEquals(1, taskPool.size());

        ScheduledTask task2 = new ScheduledTask(dateTime, cf);

        taskPool.addTask(task2);
        assertEquals(2, taskPool.size());
    }

    @Test
    public void addTaskWithSortDateTime() throws Exception {

        LocalDate date1 = LocalDate.of(2016, 11, 4);
        LocalTime time1 = LocalTime.of(13, 6, 30);
        ZonedDateTime dateTime1 = ZonedDateTime.of(date1, time1, zoneId);
        CalculatedFactorial cf1 = CalculatedFactorial.factorial(10);


        ScheduledTask task = new ScheduledTask(dateTime, cf);
        ScheduledTask task2 = new ScheduledTask(dateTime1, cf1);

        LocalDate date2 = LocalDate.of(2016, 10, 4);
        LocalTime time2 = LocalTime.of(11, 6, 30);
        ZonedDateTime dateTime2 = ZonedDateTime.of(date2, time2, zoneId);
        CalculatedFactorial cf2 = CalculatedFactorial.factorial(15);
        ScheduledTask task3 = new ScheduledTask(dateTime2, cf2);

        taskPool.addTask(task);
        taskPool.addTask(task2);
        taskPool.addTask(task3);

        assertEquals(3, taskPool.size());

        assertEquals(1, taskPool.getTaskIndex(task2));
        assertEquals(2, taskPool.getTaskIndex(task));
        assertEquals(0, taskPool.getTaskIndex(task3));
    }


    @Test
    public void addMultiThreadTask() throws Exception {
        for (int i = 0; i < 10; i++){
            int finalI = i;
            Thread threadTask = new Thread(() -> {
                ZonedDateTime dateTime = ZonedDateTime.now();
                CalculatedFactorial cf = CalculatedFactorial.factorial(finalI);
                ScheduledTask task = new ScheduledTask(dateTime, cf);
                try {
                    taskPool.addTask(task);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            threadTask.start();
            Thread.sleep(100);
        }

        System.out.println(taskPool.size());
        taskPool.forEach(t -> System.out.println(t.getDateTime()));
    }

    @Test
    public void testExecutionTaskFromTaskPool(){

    }

}
