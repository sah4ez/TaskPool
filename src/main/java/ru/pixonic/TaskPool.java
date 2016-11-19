package ru.pixonic;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.concurrent.*;

/**
 * Created by sah4ez on 04.11.16.
 */
public class TaskPool extends PriorityBlockingQueue<ScheduledTask> {

    ScheduledExecutorService es = Executors.newScheduledThreadPool(9);

    public TaskPool() {
        super(10,
                Comparator.comparing(ScheduledTask::getDateTime)
                        .thenComparing(ScheduledTask::getSerialNum));
    }

    public void addTask(ScheduledTask task) {
        task.setSerialNum(size() + 1);
        this.add(task);
    }

    public void execute() {
        Runnable run = task();
        Thread thread = new Thread(run);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Runnable task() {
        return () -> {
            while (!isEmpty()) {
                try {
                    if (ZonedDateTime.now().compareTo(peek().getDateTime()) >= 0) {
                        System.out.print(peek().getDateTime().toLocalTime() + " number " + peek().getSerialNum());
                        Future<Integer> future = es.submit(poll());
                        try {
                            System.out.println(" result " + future.get() + " time Execute " + ZonedDateTime.now().toLocalTime());
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                    } else Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
