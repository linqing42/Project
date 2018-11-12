package linqing.tojava;

/**
 * deadline task description /by deadline description: add to the task list a deadline task with the given task
 * description and with the deadline description
 *
 * @inheritDoc
 * @see linqing.tojava.ToDo
 */
public class Deadline extends ToDo {
    String by;

    Deadline(String description) {
        super(description.split("/by")[ 0 ]);
        setDeadline(description.split("/by")[ 1 ]);
        saveTask(2, "do by:" + by);
    }

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }


    public void setDeadline(String deadline) {
        this.by = deadline;
    }

    public String getDeadline() {
        return by;
    }

    public void saveTask(int level, String info) {
        super.saveTask(level, info);
    }

    public String toString() {
        return super.toString(2);
    }
}