package seedu.taskManager.logic;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.taskManager.commons.core.ComponentManager;
import seedu.taskManager.commons.core.LogsCenter;
import seedu.taskManager.logic.commands.Command;
import seedu.taskManager.logic.commands.CommandResult;
import seedu.taskManager.logic.commands.exceptions.CommandException;
import seedu.taskManager.logic.parser.Parser;
import seedu.taskManager.model.Model;
import seedu.taskManager.model.Task.ReadOnlyTask;
import seedu.taskManager.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager extends ComponentManager implements Logic {
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Parser parser;

    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.parser = new Parser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        commandText = parser.parseArguments(commandText);
        Command command = parser.parseCommand(commandText);
        command.setData(model);
        return command.execute();
    }

    @Override
    public ObservableList<ReadOnlyTask> getFilteredTaskList() {
        return model.getFilteredTaskList();
    }
}
