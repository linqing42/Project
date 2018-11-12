package linqing.tojava;

/**
 * Todo class is a task to do someday
 *
 * @inheritDoc
 * @see linqing.tojava.Task
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
        saveTask(1, "is done? " + super.checkDone());
    }

    public void saveTask(int level, String info) {
        super.saveTask(level, info);
    }

    public String toString() {
        return super.toString(1);
    }

    @Override
    public String toString(int i) {
        return super.toString(i);
    }
}