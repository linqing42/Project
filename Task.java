package linqing.tojava;

import java.util.ArrayList;
import java.util.List;

/**
 * Task class represent tasks and helps to store tasks
 */
public class Task {
    protected String description;
    protected boolean isDone;
    List<String> saveTask = new ArrayList<>();
    String printTask;

    public Task(String description) {
        this.description = description; // we only want the string after "add" command, so we use split to achieve
        // this purpose
        this.isDone = false;
        saveTask.add(0, "description: " + getDescription());
    }

    public String getDescription() {
        return description;
    }

    public void saveTask(int level, String info) {
        saveTask.add(level, info);
    }

    public String toString(int i) {
        for (int n = 0; n <= i; ++n) {
            if (n == 0)
                printTask = saveTask.get(n);
            else
                printTask = (printTask + '\n' + saveTask.get(n));
        }
        return printTask;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
        saveTask.set(1, "is done? Yes");
    }

    public String checkDone() {
        if (isDone == false)
            return "No";
        else {
            return "Yes";
        }
    }

    public boolean isDone() {
        return isDone;
    }


}