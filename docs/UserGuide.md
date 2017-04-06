# Task Manager - User Guide

By : `Team SE-EDU`  &nbsp;&nbsp;&nbsp;&nbsp; Since: `Jun 2016`  &nbsp;&nbsp;&nbsp;&nbsp; Licence: `MIT`

---

1. [Quick Start](#quick-start)
2. [Features](#features)
3. [FAQ](#faq)
4. [Command Summary](#command-summary)

## 1. Quick Start

0. Ensure you have Java version `1.8.0_60` or later installed in your Computer.<br>

   > Having any Java 8 version is not enough. <br>
   > This app will not work with earlier versions of Java 8.

1. Download the latest `TaskManager.jar` from the [releases](../../../releases) tab.
2. Copy the file to the folder you want to use as the home folder for your Address Book.
3. Double-click the file to start the app. The GUI should appear in a few seconds.

   > <img src="images/GUI.PNG" width="600">


4. Type the command in the command box and press <kbd>Enter</kbd> to execute it. <br>
   e.g. typing **`help`** and pressing <kbd>Enter</kbd> will open the help window.
5. Some example commands you can try:
   * **`list`** : Returns a numbered list of all tasks/events yet undone in the tasks manager
   * **`add`**` Study for midterms e/2017-3-2-2359 d/CS2103 at LT7` :
        Add a Task named “Study for midterms” with endtime “2017 2nd of March 23:59pm” with 
        task description of “CS2103 at LT7”

   * **`delete`**` 3` : deletes the 3rd task shown in the current list
   * **`exit`** : exits the app

6. Refer to the [Features](#features) section below for details of each command.<br>


## 2. Features

> **Command Format**
>
> * Words in `UPPER_CASE` are the parameters.
> * Items in `SQUARE_BRACKETS` are optional.
> * Items with `...` after them can have multiple instances.
> * Parameters can be in any order.

### 2.1. Viewing help : `help`

Format: `help`

> Help is also shown if you enter an incorrect command e.g. `abcd`

### 2.2. Adding a task/event : `add`

Adds a task/event to the address book<br>

Task/event: 


Format: `add TASK_NAME [s/STARTTIME] [e/ENDTIME] [d/DESCRIPTION] [r/REPEATPERIOD] [l/RECURENDDATE] [t/TAG] ...`

> Tasks can have a description

> Tasks can have any number of tags (including 0)
> Tasks can be flagged as recurring by adding the period to repeat
> Tasks flagged as recurring can have an end date to stop the recurring task
> User may use FROM and TO instead of s/ and e/, also may use BY instead of e/


Examples:
* `add Do laundry e/2017-3-1-2359 `
* `add Study for midterms e/Monday 1000 d/CS2103 at lt7 `
* `add Buy milk for baby BY tuesday 0800 d/yaas milk t/family`
* `add Create user story e/2017-4-1-1300 t/work t/computing` 
* `add Meeting FROM monday 0800 TO monday 1100 t/work r/weekly`





### 2.3.1 Finding all tasks/events containing any keyword in their names, description or tag : `find`



Finds tasks/events whose names contain any of the given keywords.<br>

Format: `find KEYWORD [MORE_KEYWORDS]`

> * The search is case sensitive. e.g homework will not match Homework
> * The order of the keywords does not matter. e.g. homework due will match due homework
> * Only names, description and tags are searched.
> * Tasks matching at least one keyword will be returned (i.e. OR search). e.g. CS2103 final exams will match finals bo rawr

Examples:
* find Milk
* Returns a numbered list of undone tasks/events whose names/description/tags contain buy Milk but not milk
* find work family friends
* Returns a numbered list of undone tasks/events whose names/description/tags contain work, family or friends

### 2.3.2 Finding all tasks/events that matches the given priority level : `find #`


Finds tasks/events that matches a priority level.<br>

Format: `find #KEYWORD`

> * The search is case sensitive. e.g. h will not match H
> * Only priority is searched
> * Tasks matching the given priority will be returned.

Examples:
* find #h
* Returns a numbered list of tasks/events that are labelled as high priority

### 2.3.3 Finding all archived tasks/events containing the keyword in their names, description or tag : `find @`


Finds tasks/events whose name/description/tags match a given keyword.<br>

Format: `find @KEYWORD`

> * The search is case sensitive. e.g homework will not match Homework
> * Only names/descriptions/tags will be searched
> * Task matching the keyword given will be returned.

Examples:
* find @Milk
* Returns a numbered list of tasks/events that are archived and whose name/description/tags contain buy Milk but not milk


### 2.4. Listing all tasks/events : `list`


Shows a list of all tasks/events in the tasks manager.<br>
Format: `list`

Examples:

* `list`
* `Returns a numbered list of all tasks/events yet undone in the tasks manager`


### 2.5 Show tasks/events that are done : `archived`

Shows a list of all task that are marked as done and events that has passed in the task manager.<br>
Format: `archived`

> * Tasks/events is arranged by deadline, with the most recent dateline first
> * Only the past one month worth of event/task can be done

Examples:

* `archived`<br>
  Returns a list of tasks that have been marked as done and or events that have passed


### 2.6. Editing a Task/event : `edit`

Edits an existing task/event in the address book.<br>
Format: `edit INDEX [n/NAME] [s/START_TIME] [e/END_TIME] [d/DESCRIPTION] [t/TAG]...`

>Edits the task/event at the specified INDEX. The index refers to the index number shown in the last task listing printed out when the search or list function was called.<br>

> * The index must be a positive integer 1, 2, 3, ...
> * At least one of the optional fields must be provided.
> * If the selected index is a task, there should not be entries that edit end time or start time.
> * If the selected index is an event, there should not be entries that edit deadlines
> * Existing values will be updated to the input values.
> * When editing tags, the existing tags of the task/event will be removed i.e adding of tags is not cumulative.
> * You can remove all the task's tags by typing t/ without specifying any tags after it.

Examples:


* `edit 1 buy milk e/2017-3-2-2359`<br>
  Edits the name and deadline of the task ( numbered 1 on the list) to be buy milk and 2017-3-2-2359 respectively.
* `edit 2 midterm exam s/2017-3-3-1000`<br>
  Edits the name and start time of the event(numbered 2 on the list) to be midterm exam and s/2017-3-3-1000 respectively

### 2.7.  Sorting Tasks/Events : `sort`

Sorts the list of tasks/events in the address book by a given parameter <br>
Format: `sort PARAMETER`

> * The parameter must be strictly in the form of name/duedate/priority
> * Sorting by name will sort all tasks in the Task Manager in alphabetical order
> * Sorting by duedate will sort all tasks in the Task Manager by EndTimes of Tasks or Events. Floating Tasks will be sorted to the back
> * Sorting by priority will sort all tasks with those with high priority at the top and low priority at the bottom

* `list`<br>
  `sort duedate`<br>
  Sorts all tasks in the list by duedate
* `find homework` <br>
  `sort priority`<br>
  Sorts all tasks with names/descriptions/tags containing homework by priority.

### 2.7  Deleting task(s): `delete`


Deletes the specified task(s) from the task manager.<br>
Format: `delete INDEX,[INDEX]...`

> * Deletes the task(s) at the specified INDEX. <br>
> * The index refers to the index number shown in the most recent listing.<br>
> * The index **must be a positive integer** 1, 2, 3, ...<br>

Examples:

* `list`<br>
  `delete 2,3`<br>
  Deletes the 2nd and 3rd task in the list of task.
* `find meeting` <br>
  `delete 1`<br>
  Deletes the 1st task in the results of the `find` command.

### 2.8 Mark task(s) as done : `archive`

Mark the specified task(s) as done.<br>
Format: `done INDEX,[INDEX]...`

> * Mark the task(s) as done at the specified INDEX. 
> * The index refers to the index number shown in the most recent listing.
> * The index must be a positive integer 1, 2, 3, ...

Examples:

* `list`<br>
   `archive 2,3`<br>
   Mark the 2nd and 3rd task in the list as done.
   
### 2.9.1 Save Task Manager Manually: 

Save the current instance of Task Manager to a File.<br>
HotKey: `ALT + S Key`

> * Choose a File & Location to save the Task List. 
> * Any further changes in this session will be saved to the new File.
> * Task Manager will use the most recent File that was used upon startup
> * Alternatively User can navigate to File > Save File from the GUI.

### 2.9.2 Load Task Manager Manually: 

Load a instance of Task Manager from a File.<br>
HotKey: `ALT + L Key`

> * Choose a File to Load. 
> * Any further changes in this session will be saved to the new File.
> * Task Manager will use the most recent File that was used upon startup
> * Alternatively User can navigate to File > Load File from the GUI.

### 2.10 Quick Launch HotKey: 

Show/Hide the Task Manager to facilitate multi-tasking.<br>
HotKey: `CTRL + SPACE Key`

> * Press the HotKey at anytime to Show or Hide the Task Manager. 
> * Window/Mac/Linux Supported

### 2.11 Clearing all entries : `clear`

Clears all entries from the task manager.<br>
Format: `clear`

### 2.12 Undo the last command : `undo`

Undo the last add/edit/archive/undo command input by the user.<br>
Format: `undo`

### 2.13 Exiting the program : `exit`

Exits the program.<br>
Format: `exit`

### 2.14 Saving the data

Address book data are saved in the hard disk automatically after any command that changes the data.<br>
There is no need to save manually.

## 3. FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with
       the file that contains the data of your previous Address Book folder.

## 4. Command Summary

* **Add :** <br>
* **Task :**<br>
* `add TASK_NAME [s/STARTTIME] [e/ENDTIME] [d/DESCRIPTION] [r/REPEATPERIOD] [l/RECURENDDATE] [t/TAG] ...` <br>
   e.g. `add Study for midterms s/Saturday e/Sunday d/CS2103 Chapter 1 to 10`

* **Clear** : `clear`<br>

* **Delete** : `delete INDEX [INDEX]...` <br>
   e.g. `delete 3`

* **Find** : `find KEYWORD [MORE_KEYWORDS]` <br>
  e.g. `find milk`

* **List** : `list` <br>
  e.g. `list`

* **List** : `undo` <br>
  e.g. `undo`
 
* **Archive** : `archive` <br>
  e.g. `archive 2`

* **Archived** : `archived` <br>
  e.g. `archived`
  
* **Save/Load** : `ALT + S / ALT + L` <br>
  
* **Quick Launch** : `CTRL + SPACE` <br>

* **edit** : `edit TASK_ID[n/NAME] [s/START_TIME] [e/END_TIME] [d/DESCRIPTION] [t/TAG]...` <br>

* **Help** : `help` <br>
 



