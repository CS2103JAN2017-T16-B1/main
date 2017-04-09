package seedu.address.testutil;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.loadui.testfx.GuiTest;
import org.testfx.api.FxToolkit;

import com.google.common.io.Files;

import guitests.guihandles.TaskCardHandle;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import junit.framework.AssertionFailedError;
import seedu.address.TestApp;
import seedu.taskManager.commons.core.LogsCenter;
import seedu.taskManager.commons.exceptions.IllegalValueException;
import seedu.taskManager.commons.util.FileUtil;
import seedu.taskManager.commons.util.XmlUtil;
import seedu.taskManager.model.Task.Description;
import seedu.taskManager.model.Task.EndTime;
import seedu.taskManager.model.Task.ID;
import seedu.taskManager.model.Task.Name;
import seedu.taskManager.model.Task.Priority;
import seedu.taskManager.model.Task.ReadOnlyTask;
import seedu.taskManager.model.Task.RecurEndDate;
import seedu.taskManager.model.Task.RecurPeriod;
import seedu.taskManager.model.Task.StartTime;
import seedu.taskManager.model.Task.Status;
import seedu.taskManager.model.Task.Task;
import seedu.taskManager.model.TaskManager;
import seedu.taskManager.model.tag.Tag;
import seedu.taskManager.model.tag.UniqueTagList;
import seedu.taskManager.storage.XmlSerializableTaskManager;

/**
 * A utility class for test cases.
 */
public class TestUtil {

    public static final String LS = System.lineSeparator();

    /**
     * Folder used for temp files created during testing. Ignored by Git.
     */
    public static final String SANDBOX_FOLDER = FileUtil.getPath("./src/test/data/sandbox/");

    public static final Task[] SAMPLE_TASK_DATA = getSampleTaskData();

    public static final Tag[] SAMPLE_TAG_DATA = getSampleTagData();


    public static void assertThrows(Class<? extends Throwable> expected, Runnable executable) {
        try {
            executable.run();
        } catch (Throwable actualException) {
            if (actualException.getClass().isAssignableFrom(expected)) {
                return;
            }
            String message = String.format("Expected thrown: %s, actual: %s", expected.getName(),
                    actualException.getClass().getName());
            throw new AssertionFailedError(message);
        }
        throw new AssertionFailedError(
                String.format("Expected %s to be thrown, but nothing was thrown.", expected.getName()));
    }

    private static Task[] getSampleTaskData() {
        try {
            //CHECKSTYLE.OFF: LineLength
            return new Task[]{
                new Task(new Name("School"), new Description("go to school"), new StartTime("2017-03-03-2100"), new EndTime("2017-05-04-2000"),
                        new ID("20000"), new Priority("m"), new Status("undone"), new RecurPeriod(""), new RecurEndDate(""), new UniqueTagList()),
                new Task(new Name("Homework"), new Description("do homework"), new StartTime("2017-03-03-2100"), new EndTime("2017-06-02-1000"),
                        new ID("20001"), new Priority("h"), new Status("done"), new RecurPeriod(""), new RecurEndDate(""), new UniqueTagList()),
                new Task(new Name("Midterm"), new Description("study for midterm"), new StartTime("2017-02-01-2100"), new EndTime("2017-08-08-2100"),
                        new ID("20002"), new Priority("m"), new Status("done"), new RecurPeriod(""), new RecurEndDate(""), new UniqueTagList()),
                new Task(new Name("Exam"), new Description("study for exam"), new StartTime("2017-01-01-1200"), new EndTime("2017-03-03-2100"),
                        new ID("20003"), new Priority("l"), new Status("done"), new RecurPeriod(""), new RecurEndDate(""), new UniqueTagList()),
                new Task(new Name("buy milk"), new Description("buy milk at ntuc"), new StartTime("2017-01-01-1400"), new EndTime("2017-03-03-2100"),
                        new ID("20004"), new Priority("m"), new Status("done"), new RecurPeriod(""), new RecurEndDate(""), new UniqueTagList()),
                new Task(new Name("buy dinner"), new Description("buy dinner at coffeeshop"), new StartTime("2017-08-09-2100"), new EndTime("2017-12-11-2100"),
                        new ID("20005"), new Priority("l"), new Status("done"), new RecurPeriod(""), new RecurEndDate(""), new UniqueTagList()),
                new Task(new Name("cook dinner"), new Description("cook steak for dinner"), new StartTime("2017-01-03-2100"), new EndTime("2017-05-05-2100"),
                        new ID("20006"), new Priority("h"), new Status("undone"), new RecurPeriod(""), new RecurEndDate(""), new UniqueTagList()),
                new Task(new Name("go to bed"), new Description("time to sleep"), new StartTime("2017-03-01-2200"), new EndTime("2017-07-07-2100"),
                        new ID("20007"), new Priority("l"), new Status("undone"), new RecurPeriod(""), new RecurEndDate(""), new UniqueTagList()),
                new Task(new Name("Sleeping"), new Description("zzzzz...."), new StartTime("2017-03-03-1500"), new EndTime("2017-05-05-2100"),
                        new ID("20008"), new Priority("m"), new Status("undone"), new RecurPeriod(""), new RecurEndDate(""), new UniqueTagList())

            };
            //CHECKSTYLE.ON: LineLength
        } catch (IllegalValueException e) {

            // not possible
            return null;
        }
    }


    private static Tag[] getSampleTagData() {
        try {
            return new Tag[]{
                new Tag("relatives"),
                new Tag("friends")
            };
        } catch (IllegalValueException e) {
            assert false;
            return null;
            //not possible
        }
    }

    public static List<Task> generateSampleTaskData() {
        return Arrays.asList(SAMPLE_TASK_DATA);
    }

    /**
     * Appends the file name to the sandbox folder path.
     * Creates the sandbox folder if it doesn't exist.
     * @param fileName
     * @return
     */
    public static String getFilePathInSandboxFolder(String fileName) {
        try {
            FileUtil.createDirs(new File(SANDBOX_FOLDER));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return SANDBOX_FOLDER + fileName;
    }

    public static void createDataFileWithSampleData(String filePath) {
        createDataFileWithData(generateSampleStorageTaskManager(), filePath);
    }

    public static <T> void createDataFileWithData(T data, String filePath) {
        try {
            File saveFileForTesting = new File(filePath);
            FileUtil.createIfMissing(saveFileForTesting);
            XmlUtil.saveDataToFile(saveFileForTesting, data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String... s) {
        createDataFileWithSampleData(TestApp.SAVE_LOCATION_FOR_TESTING);
    }

    public static XmlSerializableTaskManager generateSampleStorageTaskManager() {
        return new XmlSerializableTaskManager(new TaskManager());
    }

    /**
     * Tweaks the {@code keyCodeCombination} to resolve the {@code KeyCode.SHORTCUT} to their
     * respective platform-specific keycodes
     */
    public static KeyCode[] scrub(KeyCodeCombination keyCodeCombination) {
        List<KeyCode> keys = new ArrayList<>();
        if (keyCodeCombination.getAlt() == KeyCombination.ModifierValue.DOWN) {
            keys.add(KeyCode.ALT);
        }
        if (keyCodeCombination.getShift() == KeyCombination.ModifierValue.DOWN) {
            keys.add(KeyCode.SHIFT);
        }
        if (keyCodeCombination.getMeta() == KeyCombination.ModifierValue.DOWN) {
            keys.add(KeyCode.META);
        }
        if (keyCodeCombination.getControl() == KeyCombination.ModifierValue.DOWN) {
            keys.add(KeyCode.CONTROL);
        }
        keys.add(keyCodeCombination.getCode());
        return keys.toArray(new KeyCode[]{});
    }

    public static boolean isHeadlessEnvironment() {
        String headlessProperty = System.getProperty("testfx.headless");
        return headlessProperty != null && headlessProperty.equals("true");
    }

    public static void captureScreenShot(String fileName) {
        File file = GuiTest.captureScreenshot();
        try {
            Files.copy(file, new File(fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String descOnFail(Object... comparedObjects) {
        return "Comparison failed \n"
                + Arrays.asList(comparedObjects).stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    public static void setFinalStatic(Field field, Object newValue) throws NoSuchFieldException,
        IllegalAccessException {
        field.setAccessible(true);
        // remove final modifier from field
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        // ~Modifier.FINAL is used to remove the final modifier from field so that its value is no longer
        // final and can be changed
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, newValue);
    }

    public static void initRuntime() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.hideStage();
    }

    public static void tearDownRuntime() throws Exception {
        FxToolkit.cleanupStages();
    }

    /**
     * Gets private method of a class
     * Invoke the method using method.invoke(objectInstance, params...)
     *
     * Caveat: only find method declared in the current Class, not inherited from supertypes
     */
    public static Method getPrivateMethod(Class<?> objectClass, String methodName) throws NoSuchMethodException {
        Method method = objectClass.getDeclaredMethod(methodName);
        method.setAccessible(true);
        return method;
    }

    public static void renameFile(File file, String newFileName) {
        try {
            Files.copy(file, new File(newFileName));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Gets mid point of a node relative to the screen.
     * @param node
     * @return
     */
    public static Point2D getScreenMidPoint(Node node) {
        double x = getScreenPos(node).getMinX() + node.getLayoutBounds().getWidth() / 2;
        double y = getScreenPos(node).getMinY() + node.getLayoutBounds().getHeight() / 2;
        return new Point2D(x, y);
    }

    /**
     * Gets mid point of a node relative to its scene.
     * @param node
     * @return
     */
    public static Point2D getSceneMidPoint(Node node) {
        double x = getScenePos(node).getMinX() + node.getLayoutBounds().getWidth() / 2;
        double y = getScenePos(node).getMinY() + node.getLayoutBounds().getHeight() / 2;
        return new Point2D(x, y);
    }

    /**
     * Gets the bound of the node relative to the parent scene.
     * @param node
     * @return
     */
    public static Bounds getScenePos(Node node) {
        return node.localToScene(node.getBoundsInLocal());
    }

    public static Bounds getScreenPos(Node node) {
        return node.localToScreen(node.getBoundsInLocal());
    }

    public static double getSceneMaxX(Scene scene) {
        return scene.getX() + scene.getWidth();
    }

    public static double getSceneMaxY(Scene scene) {
        return scene.getX() + scene.getHeight();
    }

    public static Object getLastElement(List<?> list) {
        return list.get(list.size() - 1);
    }

    /**
     * Removes a subset from the list of tasks.
     * @param tasks The list of tasks
     * @param tasksToRemove The subset of tasks.
     * @return The modified tasks after removal of the subset from tasks.
     */
    public static TestTask[] removeTasksFromList(final TestTask[] tasks, TestTask... tasksToRemove) {
        List<TestTask> listOfTasks = asList(tasks);
        listOfTasks.removeAll(asList(tasksToRemove));
        return listOfTasks.toArray(new TestTask[listOfTasks.size()]);
    }


    /**
     * Returns a copy of the list with the task at specified index removed.
     * @param list original list to copy from
     * @param targetIndexInOneIndexedFormat e.g. index 1 if the first element is to be removed
     */
    public static TestTask[] removeTaskFromList(final TestTask[] list, int targetIndexInOneIndexedFormat) {
        return removeTasksFromList(list, list[targetIndexInOneIndexedFormat - 1]);
    }

    /**
     * Returns a copy of the list with the task at specified index archived.
     * @param list
     *            original list to copy from
     * @param targetIndexInOneIndexedFormat
     *            e.g. index 1 if the first element is to be archived
     */
    // @@author A0140072X
    public static TestTask[] archiveTaskFromList(final TestTask[] list, int targetIndexInOneIndexedFormat) {
        List<TestTask> listOfTasks = asList(list);
        try {
            listOfTasks.get(targetIndexInOneIndexedFormat - 1).setStatus(new Status("done"));
        } catch (IllegalValueException e) {
            Logger logger = LogsCenter.getLogger(TestUtil.class);
            logger.info("Illegal value at archiveTaskFromList");
        }
        sortByEndTime(listOfTasks);
        return listOfTasks.toArray(new TestTask[listOfTasks.size()]);
    }

    // @@author A0138998B
    public static TestTask[] sortByEndTime(final TestTask[] list) {
        List<TestTask> listOfTasks = asList(list);
        sortByEndTime(listOfTasks);
        return listOfTasks.toArray(new TestTask[listOfTasks.size()]);
    }

    public static void sortByEndTime(List<TestTask> list) {
        Collections.sort(list, new Comparator<TestTask>() {
            public int compare(TestTask task1, TestTask task2) {

                if (task1.getDueDate() != null && task2.getDueDate() != null) {
                    return task1.getDueDate().compareTo(task2.getDueDate());
                }
                else if (task1.getDueDate() == null && task2.getDueDate() != null) {
                    return 1;
                }
                else if (task1.getDueDate() != null && task2.getDueDate() == null) {
                    return -1;
                }
                else if (task1.getDueDate() == null && task2.getDueDate() == null) {
                    return 0;
                }
                return 0;
            }
            });
    }
    //@@author
    /**
     * Replaces tasks[i] with a task.
     * @param tasks The array of tasks.
     * @param task The replacement task
     * @param index The index of the task to be replaced.
     * @return
     */
    public static TestTask[] replaceTaskFromList(TestTask[] tasks, TestTask task, int index) {
        tasks[index] = task;
        return tasks;
    }

    /**
     * Appends tasks to the array of tasks.
     * @param tasks A array of tasks.
     * @param tasksToAdd The tasks that are to be appended behind the original array.
     * @return The modified array of tasks.
     */
    public static TestTask[] addTasksToList(final TestTask[] tasks, TestTask... tasksToAdd) {
        List<TestTask> listOfTasks = asList(tasks);
        listOfTasks.addAll(asList(tasksToAdd));
        return listOfTasks.toArray(new TestTask[listOfTasks.size()]);
    }

    // @@author A0140072X
    public static TestTask[] addTasksToListandSort(final TestTask[] tasks, TestTask... tasksToAdd) {
        List<TestTask> listOfTasks = asList(tasks);
        listOfTasks.addAll(asList(tasksToAdd));

        sortByEndTime(listOfTasks);

        return listOfTasks.toArray(new TestTask[listOfTasks.size()]);
    }
    private static <T> List<T> asList(T[] objs) {
        List<T> list = new ArrayList<>();
        for (T obj : objs) {
            list.add(obj);
        }
        return list;
    }

    public static boolean compareCardAndTask(TaskCardHandle card, ReadOnlyTask task) {
        return card.isSameTask(task);
    }

    public static Tag[] getTagList(String tags) {
        if ("".equals(tags)) {
            return new Tag[]{};
        }

        final String[] split = tags.split(", ");

        final List<Tag> collect = Arrays.asList(split).stream().map(e -> {
            try {
                return new Tag(e.replaceFirst("Tag: ", ""));
            } catch (IllegalValueException e1) {
                //not possible
                assert false;
                return null;
            }
        }).collect(Collectors.toList());

        return collect.toArray(new Tag[split.length]);
    }

}
