package linqing.tojava;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Storage tasks e class that will help to load Tasks from the hard disk and save tasks to the hard disk
 */
public class Storage {
    public static String path;

    public Storage(String path) {
        this.path = path;
    }

    private static List<String> getLines(String path) {// base on:https://www.mkyong
        // .com/java/how-to-read-file-from-java-bufferedreader-example/
        //Declaration
        List<String> lines = new ArrayList<>();
        System.out.println(path);
        String line;
        BufferedReader br;
        FileReader fr;
        File file;
        try {
            file = new File(path);
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch ( IOException e ) {
            System.out.println(e.getMessage());
        }
        return lines;
    }


    private static Task createTask(String line) {
        Task task = null;
        String type = line.split("\\|")[ 0 ];
        boolean isDone = line.split("\\|")[ 1 ].equals("1") ? true : false;
        String description = line.split("\\|")[ 2 ];
        switch (type) {
            case "T":
                task = new ToDo(description);
                task.setDone(isDone);
                break;

            case "D":
                String by = line.split("\\|")[ 3 ];
                task = new Deadline(description, by);
                task.setDone(isDone);
                break;
            default:
                Ui.Error();
                break;
        }
        return task;
    }

    public static List<Task> getTasksFromFile() {
        List<Task> loadedTasks = new ArrayList<>();

        try {
            List<String> lines = getLines("C:/data/tasks.txt");
            for (String line : lines) {
                if (line.trim().isEmpty()) { //ignore empty lines
                    continue;
                }
                loadedTasks.add(createTask(line)); //convert the line to a task and add to the list
            }
        } catch ( Exception e ) {
            System.out.println("problem encountered while loading data: " + e.getMessage());
        }
        return loadedTasks;
    }


    /**
     * @return tasks from @code getTaskFrom File
     * @throws FileNotFoundException if no file is create or found
     */
    public List<Task> load() throws FileNotFoundException {
        List<Task> tasks = getTasksFromFile();
        return tasks;

    }

    public static void save(List<Task> save) throws IOException {
        List<Task> useAdd = new ArrayList<>();
        for (int i = 0; i < save.size(); i++) {
            useAdd.add(save.get(i));
        }
        outTask(useAdd);
    }

    private static String outTask(List<Task> tasks) {

        File out = new File(path);
        try ( BufferedWriter b = new BufferedWriter(new FileWriter(out)) ) {
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                if (t instanceof Deadline) {
                    b.append("D");
                    b.append("|").append(t.isDone() ? "1" : "0").append("|").append(t.getDescription()).append("|").append(((Deadline) t).getDeadline());
                } else if (t instanceof ToDo) {
                    b.append("T");
                    b.append("|").append(t.isDone() ? "1" : "0").append("|").append(t.getDescription());
                }
                b.append(System.lineSeparator());
            }
            return b.toString();
        } catch ( IOException e ) {
            System.out.println(e.getMessage());
        }
        return "Empty Tasks" + System.lineSeparator();
    }
}