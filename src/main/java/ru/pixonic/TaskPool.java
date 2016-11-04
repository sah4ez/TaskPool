package ru.pixonic;

import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by sah4ez on 04.11.16.
 */
public class TaskPool extends CopyOnWriteArrayList<ScheduledTask>{

    public int getTaskIndex(ScheduledTask task) {
        if (stream().filter(st -> st.equals(task)).findFirst().isPresent()){
            return this.indexOf(task);
        }
        return 0;
    }


    public class ExceptionExistTask extends RuntimeException{

    }

    public void addTask(ScheduledTask task) throws Exception {
        if (this.contains(task)) throw new ExceptionExistTask();
        this.add(task);
        Collections.sort(this);
    }

}
