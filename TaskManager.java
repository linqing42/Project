package linqing.tojava;

import static linqing.tojava.Parser.*;

/**
 * The TaskManager program implements an application that can store a list of task,save to a txt file.
 * and print the task on the screen.
 * it link to other classes which is an member for TaskManager such as Storage, Task and Ui Class
 *
 * @author LIN QING
 * @version 1.0
 * @since 10/11/2018
 */
public class TaskManager {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * @param path program will store the task in this path
     * @throws FileNotFoundException if the file is not found
     * @throws TaskManagerException  if the specific txt file is empty.
     * @see TaskManagerException
     * @see FileNotFoundException
     */
    public TaskManager(String path) throws FileNotFoundException {
        ui = new Ui();
        storage = new Storage(path);
        try {
            tasks = new TaskList(storage.load());
        } catch ( TaskManagerException e ) {
            ui.showToUser("Problem reading file. Starting with an empty task list");
            tasks = new TaskList();
        }
    }

    /**
     * run program implements an application that simply shows the tasks and print on the screen
     *
     * @return task and the number of task in the list
     * @throws TaskManagerException if the command word not in the case
     * @see TaskManagerException
     */
    public void run() {
        ui.printWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readUserCommand();
                String commandWord = getCommandWord(fullCommand);
                switch (commandWord) {
                    case "exit":
                    case ""://exit if it is empty
                        isExit = true;
                        break;
                    case "todo":
                        tasks.addTask(createToDo(fullCommand));
                        break;
                    case "deadline":
                        tasks.addTask(createDeadline(fullCommand));
                        break;
                    case "remove":
                        tasks.removeTask(getIndex(fullCommand));
                        break;
                    case "print":
                        tasks.printTasks();
                        break;
                    case "done":
                        tasks.markAsDone(fullCommand);
                        break;
                    case "save":
                        tasks.save();
                        break;
                    default:
                        ui.printError();
                }
            } catch ( TaskManagerException e ) {
                ui.printError(e.getMessage());
            }
        }
        ui.showExit();
    }


    /**
     * This is Main method which made use of TaskManager and run methods
     *
     * @param args unused
     * @throws FileNotFoundException if no file is create for this path
     * @see FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        assert (args.length) > 0;
        new TaskManager("C:/data/tasks.txt").run();
    }

}
