//package guitests;
//
//import static org.junit.Assert.assertTrue;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Test;
//
//import seedu.address.testutil.TestTask;
//import seedu.address.testutil.TestUtil;
//
////@@author A0139375W
//public class RecuringTaskTest extends TaskManagerGuiTest {
//
//    @Test
//    public void recuringTask() {
//
//        TestTask[] currentList = td.getTypicalTasks();
//        TestTask[] expectedArchivedList = {td.task3,td.task11};
//   //     TestTask[] expectedList = TestUtil.replaceTaskFromList(currentList, td.task11, 2);
//     //   TestTask[] expectedList= TestUtil.removeTaskFromList(currentList, 2);
//     //   expectedList = TestUtil.addTasksToList(currentList, td.task11);
//       // expectedList = TestUtil.sortByEndTime(expectedList);
//        TestTask[] //expectedList = {td.task1, td.task2, td.task3, td.task4, td.task5, td.task7};
//        expectedList = TestUtil.removeTaskFromList(currentList, 3);
//        expectedList = TestUtil.addTasksToList(expectedList, td.task11);
//        expectedList = TestUtil.sortByEndTime(expectedList);
//
//        commandBox.runCommand("archive 3");
//        assertTrue(taskListPanel.isListMatching(expectedList));
//
//    //    expectedList = TestUtil.removeTaskFromList(currentList, 7);
//   //     commandBox.runCommand("archive 7");
//    //    assertTrue(taskListPanel.isListMatching(expectedList));
//
//        commandBox.runCommand("archived");
//        assertTrue(taskListPanel.isListMatching(expectedArchivedList));
//    }
//
//    private static <T> List<T> asList(T[] objs) {
//        List<T> list = new ArrayList<>();
//        for (T obj : objs) {
//            list.add(obj);
//        }
//        return list;
//    }
//
//
//}
////@@author