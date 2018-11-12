package linqing.tojava;

/**
 * Parser program containing methods that deals with parsing the user command to extract meaningful details from it.
 */
public class Parser {
    public Parser() {
    }

    /**
     * @param fullCommand is the command word and description
     * @return a the first word of the command such as todo
     */
    public static String getCommandWord(String fullCommand) {
        String command = fullCommand.split(" ")[ 0 ];
        return command;
    }

    /**
     * @param fullCommand is the command word containing the description
     * @return integer and split it base on “ ”
     */
    public static int getIndex(String fullCommand) {
        return Integer.parseInt(fullCommand.split(" ")[ 1 ]);
    }

    /**
     * @param fullCommand is a command word containing the description
     * @return a todo object to match the fullCommand. it will return the description
     * @throws TaskManagerException if the description is empty
     * @see TaskManagerException
     */
    public static ToDo createToDo(String fullCommand) throws TaskManagerException {
        {
            String description = fullCommand.substring("todo".length()).trim();
            if (description.isEmpty())
                throw new TaskManagerException("Error: Empty description for TODO");
        }
        return new ToDo(fullCommand.substring("todo".length()).trim());
    }

    /**
     * @param fullCommand is a command word containing the description
     * @return a deadline object to match the fullCommand.
     * @throws TaskManagerException if the description is empty
     */
    public static Deadline createDeadline(String fullCommand) throws TaskManagerException {
        {
            String description = fullCommand.substring("deadline".length()).trim();
            if (description.isEmpty())
                throw new TaskManagerException("Error: Empty description for DEADLINE");
        }
        return new Deadline(fullCommand.substring("deadline".length()).trim());
    }


}