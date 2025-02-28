# Ekud User Guide

> “I have studied many philosophers and many cats. The wisdom of cats is infinitely superior.” – Hippolyte Taine

The new and improved version of Duke, __Ekud__ is:
- Text-based
- Easy to learn
- ~~Fast~~ **_ULTRA_** FAST to use <br/>

  Steps to download:<br/>
1. Download **ekud.jar** from [here](https://github.com/Frozennfishh/ip/releases/tag/v0.1
2. Move it to a folder (e.g. Ekud)<br/>
3. Open the terminal and navigate to the folder created<br/>
4. Run the command `java -jar "ekud.jar"`<br/>
5. Add your tasks.<br/>
   6. Let it manage your tasks for you 🐈 </br>
      And it is **_FREE_**!!!


> [!NOTE]
> If you are a Java programmer, you can use it to practice Java too. Here's the `main` method: <br/>
>```ruby
>public class Main {
>    public static void main(String[] args) throws FileNotFoundException {
>        new Ekud("data/list.txt").run();
>    }
>}
>```

## Adding Todos

Command: `todo (TASK_NAME)`

Adds a task to the task list.

Example: `deadline homework`

The bot will respond with:

```
Gotcha, Todo task added!
[T][ ] homework
You're left with 1 tasks left to do!
```

## Adding Deadlines

Command: `deadline (TASK_NAME) /by (DEADLINE)`

Adds a task with a set deadline.

Deadline can be given and noted down as a date and time! (In the form `DD/MM/YYYY` or `DD/MM/YY HHMM`)

Example: `deadline homework /by 25/03/2025 2359`

The bot will respond with:

```
Gotcha, Deadline task added!
[D][ ] homework (by: Mar 25 2025, 11:59 pm)
You're left with 1 tasks left to do!
```

## Adding Events

Command: `event (TASK_NAME) /from (START_DATE_TIME) /to (END_DATE_TIME)`

Adds a task with a given start and end time.

Start and end times can be given and noted down as a date and time! (In the form `DD/MM/YYYY` or `DD/MM/YY HHMM`)

Example: `event party /from 26/03/2025 0800 /to 26/03/2025 1600`

The bot will respond with:

```
Gotcha, Event task added!
[E][ ] party (from: Mar 26 2025, 8:00 am to: Mar 26 2025, 4:00 pm)
You're left with 1 tasks left to do!
```

## Displaying List

Command: `list`

Displays full list of tasks added

With the given above examples and running `list`, the bot will respond with:

```
Here are the tasks in your list:
1.[D][ ] homework (by: Mar 25 2025, 11:59 pm)
2.[T][ ] homework
3.[E][ ] party (from: Mar 26 2025, 8:00 am to: Mar 26 2025, 4:00 pm)
```

## Deleting tasks

Command: `delete (INDEX_NUMBER)`

Deletes task from the list with the given index number.

With the given above examples and running `delete 2`, the bot will respond with:

```
Omgie, removing this task from the list!
[T][ ] homework
You're left with 2 tasks left to do!
```

## Marking and Unmarking tasks

Command: `mark (INDEX_NUMBER)` / `unmark (INDEX_NUMBER)`

Marks and unmarks task as being done with the given index number.

With the given above examples and running `mark 1`, the bot will respond with:

```
Yippee marking this task as done!
[D][X] homework (by: Mar 25 2025, 11:59 pm)
You're left with 1 tasks left to do!
```

Running `unmark 1`, the bot will respond with:

```
Awww marking this task undone :(
[D][ ] homework (by: Mar 25 2025, 11:59 pm)
You're left with 2 tasks left to do!
```

## Finding tasks by keywords

Command: `find (STRING)`

Finds task in the list that contains the given `STRING`.

With the given above examples and running `find home`, the bot will respond with:

```
Here are the matching tasks in your list!
1. [D][ ] homework (by: Mar 25 2025, 11:59 pm)
```

## Finding next available free time

Command: `freeTime /for HH:MM`

Finds the next available free time that allows for `HH:MM` amount of free time.

## Finding next available free time on a given date

Command: `freeTimeOn (INPUT_DATE) /for HH:MM`

Finds all available free time that allows for `HH:MM` amount of free time on a given date.

With the given above examples and running `freeTimeOn 26/03/2025 /for 2:0`, the bot will respond with:

```
Here are the time slots that are available on 2025-03-26:
00:00 to 08:00
1600: to 00:00
```

## Features!
- [x] Managing Tasks
- [x] Managing Deadlines, To-dos and Events
- [x] Searches Tasks in the list by date and keywords
- [x] Searches Free Time slots on any date
- [x] Automatically saved and easily exportable
- [x] Colour coded messages that change depending on input

## Coming Soon!
- [ ] Sorting by deadline and/or name
- [ ] Tagging tasks by types