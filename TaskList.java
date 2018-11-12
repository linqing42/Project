package linqing.tojava;

import java.io.IOException;
import java.util.*;

/**
 * TaskList class that is responsible for keeping the in-memory tasks list
 */
public class TaskList {
    private static List<Task> tasks = new ArrayList<>();

    public TaskList() {
    }

    /**
     * @param description for the task detail
     * @throws TaskManagerException if description is empty
     */
    public TaskList(List<Task> description) throws TaskManagerException {
        if (description.isEmpty()) {
            throw new TaskManagerException("Current task list is empty, please try again");
        }
        tasks = description;
    }

    private static void showTotalTask() {
        System.out.println("Tasks in the list: " + tasks.size());
    }

    /**
     * @param t is to add a new task
     */
    public void addTask(Task t) {
        tasks.add(t);
        showTotalTask();
    }

    /**
     * remove the task base on number of index
     *
     * @param i is the integer number for each task
     */
    public void removeTask(int i) {
        tasks.remove(i - 1);
        showTotalTask();
    }

    /**
     * @param line for the done description
     * @throws TaskManagerException if index<1 or index >tasks.size()
     */
    public void markAsDone(String line) throws TaskManagerException {
        {
            int index = Integer.parseInt(line.substring("done".length()).trim());
            if (index < 1 || index > tasks.size()) // if attempting to set done for non-existing task
                throw new TaskManagerException("Error: Invalid Task number for DONE");
            else {
                tasks.get(index - 1).setDone(true);
                showTotalTask();
            }
        }
    }

    /**
     * return print and the number of task
     */
    public void printTasks() {
        System.out.println("Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + tasks.get(i));
        }
    }


    public void save() {
        try {
            Storage.save(tasks);
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }


}
