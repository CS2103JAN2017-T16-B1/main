//package seedu.address.logic;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//import static seedu.taskManager.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
//import static seedu.taskManager.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
//import static seedu.taskManager.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.TemporaryFolder;
//
//import com.google.common.eventbus.Subscribe;
//
//import seedu.taskManager.commons.core.EventsCenter;
//import seedu.taskManager.commons.core.Messages;
//import seedu.taskManager.commons.events.model.TaskManagerChangedEvent;
//import seedu.taskManager.commons.events.ui.JumpToListRequestEvent;
//import seedu.taskManager.commons.events.ui.ShowHelpRequestEvent;
//import seedu.taskManager.logic.Logic;
//import seedu.taskManager.logic.LogicManager;
//import seedu.taskManager.logic.commands.AddCommand;
//import seedu.taskManager.logic.commands.ClearCommand;
//import seedu.taskManager.logic.commands.Command;
//import seedu.taskManager.logic.commands.CommandResult;
//import seedu.taskManager.logic.commands.DeleteCommand;
//import seedu.taskManager.logic.commands.ExitCommand;
//import seedu.taskManager.logic.commands.FindCommand;
//import seedu.taskManager.logic.commands.HelpCommand;
//import seedu.taskManager.logic.commands.ListCommand;
//import seedu.taskManager.logic.commands.SelectCommand;
//import seedu.taskManager.logic.commands.SortCommand;
//import seedu.taskManager.logic.commands.exceptions.CommandException;
//import seedu.taskManager.model.Model;
//import seedu.taskManager.model.ModelManager;
//import seedu.taskManager.model.ReadOnlyTaskManager;
//import seedu.taskManager.model.TaskManager;
//import seedu.taskManager.model.Task.Description;
//import seedu.taskManager.model.Task.EndTime;
//import seedu.taskManager.model.Task.ID;
//import seedu.taskManager.model.Task.Name;
//import seedu.taskManager.model.Task.Priority;
//import seedu.taskManager.model.Task.ReadOnlyTask;
//import seedu.taskManager.model.Task.RecurEndDate;
//import seedu.taskManager.model.Task.RecurPeriod;
//import seedu.taskManager.model.Task.StartTime;
//import seedu.taskManager.model.Task.Status;
//import seedu.taskManager.model.Task.Task;
//import seedu.taskManager.model.tag.Tag;
//import seedu.taskManager.model.tag.UniqueTagList;
//import seedu.taskManager.storage.StorageManager;
//
//
//public class LogicManagerTest {
//
//    /**
//     * See https://github.com/junit-team/junit4/wiki/rules#temporaryfolder-rule
//     */
//    @Rule
//    public TemporaryFolder saveFolder = new TemporaryFolder();
//
//    private Model model;
//    private Logic logic;
//
//    //These are for checking the correctness of the events raised
//    private ReadOnlyTaskManager latestSavedTaskManager;
//    private boolean helpShown;
//    private int targetedJumpIndex;
//
//    @Subscribe
//    private void handleLocalModelChangedEvent(TaskManagerChangedEvent abce) {
//        latestSavedTaskManager = new TaskManager(abce.data);
//    }
//
//    @Subscribe
//    private void handleShowHelpRequestEvent(ShowHelpRequestEvent she) {
//        helpShown = true;
//    }
//
//    @Subscribe
//    private void handleJumpToListRequestEvent(JumpToListRequestEvent je) {
//        targetedJumpIndex = je.targetIndex;
//    }
//
//    @Before
//    public void setUp() {
//        model = new ModelManager();
//        String tempTaskManagerFile = saveFolder.getRoot().getPath() + "TempTaskManager.xml";
//        String tempPreferencesFile = saveFolder.getRoot().getPath() + "TempPreferences.json";
//        logic = new LogicManager(model, new StorageManager(tempTaskManagerFile, tempPreferencesFile));
//        EventsCenter.getInstance().registerHandler(this);
//
//        latestSavedTaskManager = new TaskManager(model.getTaskManager()); // last saved assumed to be up to date
//        helpShown = false;
//        targetedJumpIndex = -1; // non yet
//    }
//
//    @After
//    public void tearDown() {
//        EventsCenter.clearSubscribers();
//    }
//
//    @Test
//    public void execute_invalid() {
//        String invalidCommand = "       ";
//        assertCommandFailure(invalidCommand, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
//                  HelpCommand.MESSAGE_USAGE));
//    }
//
//    /**
//     * Executes the command, confirms that a CommandException is not thrown and that the result message is correct.
//     * Also confirms that both the 'task manager' and the 'last shown list' are as specified.
//     * @see #assertCommandBehavior(boolean, String, String, ReadOnlyTaskManager, List)
//     */
//    private void assertCommandSuccess(String inputCommand, String expectedMessage,
//            ReadOnlyTaskManager expectedTaskManager,
//            List<? extends ReadOnlyTask> expectedShownList) {
//        assertCommandBehavior(false, inputCommand, expectedMessage, expectedTaskManager, expectedShownList);
//    }
//
//    /**
//     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
//     * Both the 'task manager' and the 'last shown list' are verified to be unchanged.
//     * @see #assertCommandBehavior(boolean, String, String, ReadOnlyTaskManager, List)
//     */
//    private void assertCommandFailure(String inputCommand, String expectedMessage) {
//        TaskManager expectedTaskManager = new TaskManager(model.getTaskManager());
//        List<ReadOnlyTask> expectedShownList = new ArrayList<>(model.getFilteredTaskList());
//        assertCommandBehavior(true, inputCommand, expectedMessage, expectedTaskManager, expectedShownList);
//    }
//
//    /**
//     * Executes the command, confirms that the result message is correct
//     * and that a CommandException is thrown if expected
//     * and also confirms that the following three parts of the LogicManager object's state are as expected:<br>
//     *      - the internal task manager data are same as those in the {@code expectedTaskManager} <br>
//     *      - the backing list shown by UI matches the {@code shownList} <br>
//     *      - {@code expectedTaskManager} was saved to the storage file. <br>
//     */
//    private void assertCommandBehavior(boolean isCommandExceptionExpected,
//                    String inputCommand, String expectedMessage,
//            ReadOnlyTaskManager expectedTaskManager,
//            List<? extends ReadOnlyTask> expectedShownList) {
//
//        try {
//            CommandResult result = logic.execute(inputCommand);
//            assertFalse("CommandException expected but was not thrown.", isCommandExceptionExpected);
//            assertEquals(expectedMessage, result.feedbackToUser);
//        } catch (CommandException e) {
//            assertTrue("CommandException not expected but was thrown.", isCommandExceptionExpected);
//            assertEquals(expectedMessage, e.getMessage());
//        }
//
//        //Confirm the ui display elements should contain the right data
//        assertEquals(expectedShownList, model.getFilteredTaskList());
//
//        //Confirm the state of data (saved and in-memory) is as expected
//        assertEquals(expectedTaskManager, model.getTaskManager());
//        assertEquals(expectedTaskManager, latestSavedTaskManager);
//    }
//
//    @Test
//    public void execute_unknownCommandWord() {
//        String unknownCommand = "uicfhmowqewca";
//        assertCommandFailure(unknownCommand, MESSAGE_UNKNOWN_COMMAND);
//    }
//
//    @Test
//    public void execute_help() {
//        assertCommandSuccess("help", HelpCommand.SHOWING_HELP_MESSAGE, new TaskManager(), Collections.emptyList());
//        assertTrue(helpShown);
//    }
//
//    @Test
//    public void execute_exit() {
//        assertCommandSuccess("exit", ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT,
//                new TaskManager(), Collections.emptyList());
//    }
//
//    @Test
//    public void execute_clear() throws Exception {
//        TestDataHelper helper = new TestDataHelper();
//        model.addTask(helper.generateTask(1));
//        model.addTask(helper.generateTask(2));
//        model.addTask(helper.generateTask(3));
//
//        assertCommandSuccess("clear", ClearCommand.MESSAGE_SUCCESS, new TaskManager(), Collections.emptyList());
//    }
//
//
//    @Test
//    public void execute_add_invalidArgsFormat() {
//        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);
//        assertCommandFailure("add wrong args wrong args", expectedMessage);
//        assertCommandFailure("add Valid Name 12345 e/valid@email.butNoPhonePrefix a/valid,address", expectedMessage);
//        assertCommandFailure("add Valid Name p/12345 valid@email.butNoPrefix a/valid, address", expectedMessage);
//        assertCommandFailure("add Valid Name p/12345 e/valid@email.butNoAddressPrefix valid,
//              address", expectedMessage);
//    }
//
//    @Test
//    public void execute_add_invalidTaskData() {
//        assertCommandFailure("add []\\[;] p/12345 e/valid@e.mail a/valid, address",
//                Name.MESSAGE_NAME_CONSTRAINTS);
//        assertCommandFailure("add Valid Name p/not_numbers e/valid@e.mail a/valid, address",
//                Description.MESSAGE_DESCRIPTION_CONSTRAINTS);
//        assertCommandFailure("add Valid Name p/12345 e/notAnEmail a/valid, address",
//                StartTime.MESSAGE_TIME_CONSTRAINTS);
//        assertCommandFailure("add Valid Name p/12345 e/valid@e.mail a/valid, address t/invalid_-[.tag",
//                Tag.MESSAGE_TAG_CONSTRAINTS);
//
//    }
//
//    @Test
//    public void execute_add_successful() throws Exception {
//        // setup expectations
//        TestDataHelper helper = new TestDataHelper();
//        Task toBeAdded = helper.adam();
//        TaskManager expectedAB = new TaskManager();
//        expectedAB.addTask(toBeAdded);
//
//        // execute command and verify result
//        assertCommandSuccess(helper.generateAddCommand(toBeAdded),
//                String.format(AddCommand.MESSAGE_SUCCESS, toBeAdded),
//                expectedAB,
//                expectedAB.getTaskList());
//
//    }
//
//    @Test
//    public void execute_addDuplicate_notAllowed() throws Exception {
//        // setup expectations
//        TestDataHelper helper = new TestDataHelper();
//        Task toBeAdded = helper.adam();
//
//        // setup starting state
//        model.addTask(toBeAdded); // task already in internal task manager
//
//        // execute command and verify result
//        assertCommandFailure(helper.generateAddCommand(toBeAdded),  AddCommand.MESSAGE_DUPLICATE_TASK);
//
//    }
//
//
//    @Test
//    public void execute_list_showsAllTasks() throws Exception {
//        // prepare expectations
//        TestDataHelper helper = new TestDataHelper();
//        TaskManager expectedAB = helper.generateTaskManager(2);
//        List<? extends ReadOnlyTask> expectedList = expectedAB.getTaskList();
//
//        // prepare task manager state
//        helper.addToModel(model, 2);
//
//        assertCommandSuccess("list",
//                ListCommand.MESSAGE_SUCCESS,
//                expectedAB,
//                expectedList);
//    }
//
//
//    /**
//     * Confirms the 'invalid argument index number behaviour' for the given command
//     * targeting a single task in the shown list, using visible index.
//     * @param commandWord to test assuming it targets a single task in the last shown list
//     *                    based on visible index.
//     */
//    private void assertIncorrectIndexFormatBehaviorForCommand(String commandWord, String expectedMessage)
//            throws Exception {
//        assertCommandFailure(commandWord , expectedMessage); //index missing
//        assertCommandFailure(commandWord + " +1", expectedMessage); //index should be unsigned
//        assertCommandFailure(commandWord + " -1", expectedMessage); //index should be unsigned
//        assertCommandFailure(commandWord + " 0", expectedMessage); //index cannot be 0
//        assertCommandFailure(commandWord + " not_a_number", expectedMessage);
//    }
//    /**
//     * Confirms the 'invalid argument input parameter behaviour' for the given command
//     *
//     */
//    @Test
//    public void assertIncorrectInputFormatBehaviorForSortCommand()
//            throws Exception {
//        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);
//        assertCommandFailure("sort" , expectedMessage); //parameter missing
//        assertCommandFailure("sort" + " endTime", expectedMessage); //parameter must be duedate
//        assertCommandFailure("sort" + " startTime", expectedMessage); //parameter cannot be startTime
//        assertCommandFailure("sort" + " NAME", expectedMessage); //parameter must be lowercase
//        assertCommandFailure("sort" + " not_a_number", expectedMessage);
//    }
//    /**
//     * Confirms the 'invalid argument index number behaviour' for the given command
//     * targeting a single task in the shown list, using visible index.
//     * @param commandWord to test assuming it targets a single task in the last shown list
//     *                    based on visible index.
//     */
//    private void assertIndexNotFoundBehaviorForCommand(String commandWord) throws Exception {
//        String expectedMessage = MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
//        TestDataHelper helper = new TestDataHelper();
//        List<Task> taskList = helper.generateTaskList(2);
//
//        // set AB state to 2 tasks
//        model.resetData(new TaskManager());
//        for (Task p : taskList) {
//            model.addTask(p);
//        }
//
//        assertCommandFailure(commandWord + " 3", expectedMessage);
//    }
//
//    @Test
//    public void execute_selectInvalidArgsFormat_errorMessageShown() throws Exception {
//        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectCommand.MESSAGE_USAGE);
//        assertIncorrectIndexFormatBehaviorForCommand("select", expectedMessage);
//    }
//
//    @Test
//    public void execute_selectIndexNotFound_errorMessageShown() throws Exception {
//        assertIndexNotFoundBehaviorForCommand("select");
//    }
//
//    @Test
//    public void execute_select_jumpsToCorrectTask() throws Exception {
//        TestDataHelper helper = new TestDataHelper();
//        List<Task> threeTasks = helper.generateTaskList(3);
//
//        TaskManager expectedAB = helper.generateTaskManager(threeTasks);
//        helper.addToModel(model, threeTasks);
//
//        assertCommandSuccess("select 2",
//                String.format(SelectCommand.MESSAGE_SELECT_TASK_SUCCESS, 2),
//                expectedAB,
//                expectedAB.getTaskList());
//        assertEquals(1, targetedJumpIndex);
//        assertEquals(model.getFilteredTaskList().get(1), threeTasks.get(1));
//    }
//
//
//    @Test
//    public void execute_deleteInvalidArgsFormat_errorMessageShown() throws Exception {
//        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE);
//        assertIncorrectIndexFormatBehaviorForCommand("delete", expectedMessage);
//    }
//
//    @Test
//    public void execute_SortInvalidArgsFormat_errorMessageShown() throws Exception {
//        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);
//        assertIncorrectIndexFormatBehaviorForCommand("sort", expectedMessage);
//    }
//
//    @Test
//    public void execute_deleteIndexNotFound_errorMessageShown() throws Exception {
//        assertIndexNotFoundBehaviorForCommand("delete");
//    }
//
//    @Test
//    public void execute_delete_removesCorrectTask() throws Exception {
//        TestDataHelper helper = new TestDataHelper();
//        List<Task> threeTasks = helper.generateTaskList(3);
//
//        TaskManager expectedAB = helper.generateTaskManager(threeTasks);
//        expectedAB.removeTask(threeTasks.get(1));
//        helper.addToModel(model, threeTasks);
//
//        assertCommandSuccess("delete 2",
//                String.format(DeleteCommand.MESSAGE_DELETE_TASK_SUCCESS, threeTasks.get(1)),
//                expectedAB,
//                expectedAB.getTaskList());
//    }
//
//
//    @Test
//    public void execute_find_invalidArgsFormat() {
//        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);
//        assertCommandFailure("find ", expectedMessage);
//    }
//
//    @Test
//    public void execute_find_onlyMatchesFullWordsInNames() throws Exception {
//        TestDataHelper helper = new TestDataHelper();
//        Task pTarget1 = helper.generateTaskWithName("bla bla KEY bla");
//        Task pTarget2 = helper.generateTaskWithName("bla KEY bla bceofeia");
//        Task p1 = helper.generateTaskWithName("KE Y");
//        Task p2 = helper.generateTaskWithName("KEYKEYKEY sduauo");
//
//        List<Task> fourTasks = helper.generateTaskList(p1, pTarget1, p2, pTarget2);
//        TaskManager expectedAB = helper.generateTaskManager(fourTasks);
//        List<Task> expectedList = helper.generateTaskList(pTarget1, pTarget2);
//        helper.addToModel(model, fourTasks);
//
//        assertCommandSuccess("find KEY",
//                Command.getMessageForTaskListShownSummary(expectedList.size()),
//                expectedAB,
//                expectedList);
//    }
//
//    @Test
//    public void execute_find_isNotCaseSensitive() throws Exception {
//        TestDataHelper helper = new TestDataHelper();
//        Task p1 = helper.generateTaskWithName("bla bla KEY bla");
//        Task p2 = helper.generateTaskWithName("bla KEY bla bceofeia");
//        Task p3 = helper.generateTaskWithName("key key");
//        Task p4 = helper.generateTaskWithName("KEy sduauo");
//
//        List<Task> fourTasks = helper.generateTaskList(p3, p1, p4, p2);
//        TaskManager expectedAB = helper.generateTaskManager(fourTasks);
//        List<Task> expectedList = fourTasks;
//        helper.addToModel(model, fourTasks);
//
//        assertCommandSuccess("find KEY",
//                Command.getMessageForTaskListShownSummary(expectedList.size()),
//                expectedAB,
//                expectedList);
//    }
//
//    @Test
//    public void execute_find_matchesIfAnyKeywordPresent() throws Exception {
//        TestDataHelper helper = new TestDataHelper();
//        Task pTarget1 = helper.generateTaskWithName("bla bla KEY bla");
//        Task pTarget2 = helper.generateTaskWithName("bla rAnDoM bla bceofeia");
//        Task pTarget3 = helper.generateTaskWithName("key key");
//        Task p1 = helper.generateTaskWithName("sduauo");
//
//        List<Task> fourTasks = helper.generateTaskList(pTarget1, p1, pTarget2, pTarget3);
//        TaskManager expectedAB = helper.generateTaskManager(fourTasks);
//        List<Task> expectedList = helper.generateTaskList(pTarget1, pTarget2, pTarget3);
//        helper.addToModel(model, fourTasks);
//
//        assertCommandSuccess("find key rAnDoM",
//                Command.getMessageForTaskListShownSummary(expectedList.size()),
//                expectedAB,
//                expectedList);
//    }
//
//
//    /**
//     * A utility class to generate test data.
//     */
//    class TestDataHelper {
//
//        Task adam() throws Exception {
//            Name name = new Name("buy milk");
//            Description privatePhone = new Description("111111");
//            StartTime startTime = new StartTime("2017-04-04-1000");
//            EndTime endTime = new EndTime("2017-04-04-1200");
//            ID id = new ID("1");
//            Priority priority = new Priority("m");
//            Status status = new Status("undone");
//            RecurPeriod recurPeriod = new RecurPeriod("");
//            RecurEndDate recurEndDate = new RecurEndDate("");
//            Tag tag1 = new Tag("tag1");
//            Tag tag2 = new Tag("longertag2");
//            UniqueTagList tags = new UniqueTagList(tag1, tag2);
//            return new
//                    Task(name, privatePhone, startTime, endTime, id, priority,
//                              status, recurPeriod, recurEndDate, tags);
//        }
//
//        /**
//         * Generates a valid task using the given seed.
//         * Running this function with the same parameter values guarantees the returned task will have the same state.
//         * Each unique seed will generate a unique Task object.
//         *
//         * @param seed used to generate the task data field values
//         */
//        Task generateTask(int seed) throws Exception {
//            return new Task(
//                    new Name("Task " + seed),
//                    new Description("" + Math.abs(seed)),
//                    new StartTime("2017-10-10-1600"),
//                    new EndTime("2017-12-12-2000"),
//                    null, null, null,
//                    new UniqueTagList(new Tag("tag" + Math.abs(seed)), new Tag("tag" + Math.abs(seed + 1)))
//                    );
//        }
//
//        /** Generates the correct add command based on the task given */
//        String generateAddCommand(Task p) {
//            StringBuffer cmd = new StringBuffer();
//
//            cmd.append("add ");
//
//            cmd.append(p.getName().fullName);
//            cmd.append(" s/").append(p.getStartTime().startTime);
//            cmd.append(" d/").append(p.getDescription().description);
//            cmd.append(" e/").append(p.getEndTime().endTime);
//
//            UniqueTagList tags = p.getTags();
//            for (Tag t: tags) {
//                cmd.append(" t/").append(t.tagName);
//            }
//
//            return cmd.toString();
//        }
//
//        /**
//         * Generates an TaskManager with auto-generated tasks.
//         */
//        TaskManager generateTaskManager(int numGenerated) throws Exception {
//            TaskManager taskManager = new TaskManager();
//            addToTaskManager(taskManager, numGenerated);
//            return taskManager;
//        }
//
//        /**
//         * Generates an TaskManager based on the list of Tasks given.
//         */
//        TaskManager generateTaskManager(List<Task> tasks) throws Exception {
//            TaskManager taskManager = new TaskManager();
//            addToTaskManager(taskManager, tasks);
//            return taskManager;
//        }
//
//        /**
//         * Adds auto-generated Task objects to the given TaskManager
//         * @param taskManager The TaskManager to which the Tasks will be added
//         */
//        void addToTaskManager(TaskManager taskManager, int numGenerated) throws Exception {
//            addToTaskManager(taskManager, generateTaskList(numGenerated));
//        }
//
//        /**
//         * Adds the given list of Tasks to the given TaskManager
//         */
//        void addToTaskManager(TaskManager taskManager, List<Task> tasksToAdd) throws Exception {
//            for (Task p: tasksToAdd) {
//                taskManager.addTask(p);
//            }
//        }
//
//        /**
//         * Adds auto-generated Task objects to the given model
//         * @param model The model to which the Tasks will be added
//         */
//        void addToModel(Model model, int numGenerated) throws Exception {
//            addToModel(model, generateTaskList(numGenerated));
//        }
//
//        /**
//         * Adds the given list of Tasks to the given model
//         */
//        void addToModel(Model model, List<Task> tasksToAdd) throws Exception {
//            for (Task p: tasksToAdd) {
//                model.addTask(p);
//            }
//        }
//
//        /**
//         * Generates a list of Tasks based on the flags.
//         */
//        List<Task> generateTaskList(int numGenerated) throws Exception {
//            List<Task> tasks = new ArrayList<>();
//            for (int i = 1; i <= numGenerated; i++) {
//                tasks.add(generateTask(i));
//            }
//            return tasks;
//        }
//
//        List<Task> generateTaskList(Task... tasks) {
//            return Arrays.asList(tasks);
//        }
//
//        /**
//         * Generates a Task object with given name. Other fields will have some dummy values.
//         */
//        Task generateTaskWithName(String name) throws Exception {
//            return new Task(
//                    new Name(name),
//                    new Description("1"),
//                    new StartTime("monday 1000"),
//                    new EndTime("monday 1200"),
//                    new ID("20000"), new Priority("m"), new Status("done"), new UniqueTagList(new Tag("tag"))
//                    );
//        }
//    }
//}
