package ru.pixonic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sah4ez on 04.11.16.
 */
public class TaskPool extends LinkedList {



    public void add(ScheduledTask task) {
        this.addFirst(task);
    }

}
