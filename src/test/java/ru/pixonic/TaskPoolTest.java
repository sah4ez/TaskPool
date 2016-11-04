package ru.pixonic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


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
    public void addTaskToTaskPool() throws Exception {

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
        initMultiThreadTaskPool();

        assertEquals(1, taskPool.get(0).getCalculatedFactorial().calc().intValue());
        assertEquals(6, taskPool.get(3).getCalculatedFactorial().calc().intValue());
    }

    @Test
    public void testExecutionTaskFromTaskPool() throws Exception {
        ArrayList<Integer> results = new ArrayList<>();

        ZonedDateTime now = ZonedDateTime.now();

        CalculatedFactorial cf1 = CalculatedFactorial.factorial(1);
        CalculatedFactorial cf2 = CalculatedFactorial.factorial(2);
        CalculatedFactorial cf3 = CalculatedFactorial.factorial(3);
        CalculatedFactorial cf10 = CalculatedFactorial.factorial(10);

        taskPool.addTask(new ScheduledTask(now, cf1));
        taskPool.addTask(new ScheduledTask(now, cf2));
        taskPool.addTask(new ScheduledTask(now, cf3));

        initMultiThreadTaskPool();

        Boolean addTestDataSet = false;

        ExecutorService es = Executors.newFixedThreadPool(10);
        while (taskPool.size() > 0) {
            ScheduledTask task = taskPool.popFirst();
            Future<Integer> f = es.submit(task);
            while (!f.isDone()) {

            }
            if (taskPool.size() == 2 && !addTestDataSet){
                taskPool.addTask(new ScheduledTask(now, cf10));
                taskPool.addTask(new ScheduledTask(now, cf3));
                taskPool.addTask(new ScheduledTask(now, cf10));
                taskPool.addTask(new ScheduledTask(now, cf10));

                addTestDataSet = true;
            }
            results.add(f.get());
        }

        es.shutdown();

        assertEquals(1,results.get(0).intValue());
        assertEquals(2,results.get(1).intValue());
        assertEquals(6,results.get(2).intValue());
        assertEquals(1,results.get(3).intValue());
        assertEquals(1,results.get(4).intValue());
        assertEquals(2,results.get(5).intValue());
        assertEquals(6,results.get(6).intValue());
        assertEquals(24,results.get(7).intValue());
        assertEquals(120,results.get(8).intValue());
        assertEquals(720,results.get(9).intValue());
        assertEquals(5040,results.get(10).intValue());
        assertEquals(3628800,results.get(11).intValue());
        assertEquals(6,results.get(12).intValue());
        assertEquals(3628800,results.get(13).intValue());
        assertEquals(3628800,results.get(14).intValue());
        assertEquals(40320,results.get(15).intValue());
        assertEquals(362880,results.get(16).intValue());
    }

    public void initMultiThreadTaskPool() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread threadTask = new Thread(() -> {
                LocalDate date = LocalDate.now();
                LocalTime time = LocalTime.now();
                time = LocalTime.of(time.getHour(), time.getMinute(), time.getSecond(), time.getNano());
                ZonedDateTime dateTime = ZonedDateTime.of(date, time, zoneId);
                CalculatedFactorial cf = CalculatedFactorial.factorial(finalI);
                ScheduledTask task = new ScheduledTask(dateTime, cf);
                try {
                    taskPool.addTask(task);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            threadTask.start();
        }
    }

    @Test
    public void orderAddAtTheTimeTask() throws Exception {
        ZonedDateTime now = ZonedDateTime.now();
        CalculatedFactorial cf = CalculatedFactorial.factorial(3);
        taskPool.addTask(new ScheduledTask(now, cf));
        taskPool.addTask(new ScheduledTask(now, cf));
        taskPool.addTask(new ScheduledTask(now, cf));

        assertEquals(1, taskPool.get(0).getSerialNum());
        assertEquals(2, taskPool.get(1).getSerialNum());
        assertEquals(3, taskPool.get(2).getSerialNum());
    }

    @Test
    public void zeroIndexNotExistTask() throws InterruptedException {
        initMultiThreadTaskPool();
        ZonedDateTime now = ZonedDateTime.now();
        CalculatedFactorial cf = CalculatedFactorial.factorial(3);
        ScheduledTask task = new ScheduledTask(now, cf);
        assertEquals(0, taskPool.getTaskIndex(task));

    }

}
