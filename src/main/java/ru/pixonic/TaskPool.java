package ru.pixonic;

import java.util.Comparator;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by sah4ez on 04.11.16.
 */
public class TaskPool extends PriorityBlockingQueue<ScheduledTask> {

    ForkJoinPool es = ForkJoinPool.commonPool();

    public TaskPool() {
        super(1000,
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

//    private synchronized ScheduledTask getFirst() {
//        peek()
//    }

    private Runnable task() {
        return () -> {
            while (!isEmpty()) {
//                if (ZonedDateTime.now().compareTo(peek().getDateTime()) >= 0) {
//                System.out.print(peek().getDateTime().toLocalTime() + " number " + peek().getSerialNum());
                ScheduledTask task = poll();
                es.invoke(task.fork());
                Long result = task.join();
//                System.out.println(" result " + result + " time Execute " + ZonedDateTime.now().toLocalTime());
//                }
            }
        };
    }
}
