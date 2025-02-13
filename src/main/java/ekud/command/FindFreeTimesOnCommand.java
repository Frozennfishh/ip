package ekud.command;

import ekud.memory.IndexTaskPair;
import ekud.memory.Storage;
import ekud.memory.TaskList;
import ekud.parser.Parser;
import ekud.tasks.Event;
import ekud.ui.Ui;
import javafx.util.Pair;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;

public class FindFreeTimesOnCommand extends Command {
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private LocalDate date;
    private String hoursString;
    private String minuteString;
    private int minutes = 0;
    private LocalDateTime current;

    public FindFreeTimesOnCommand(String input) {
        super(input);
        //input in format "INPUT_DATE /for HH:MM"
        if (input != null) {
            String[] temp = input.split(" /for ", 2);
            this.date = Parser.getDate(temp[0]);
            String[] temp2 = temp.length > 1 ? temp[1].split(":", 2) : null;
            this.hoursString = temp2 == null || temp2.length <= 1 ? null : temp2[0];
            this.minuteString = temp2 != null && temp2.length > 1 ? temp2[1] : null;
            this.minutes += hoursString != null && Parser.isInteger(hoursString)
                    ? Integer.parseInt(hoursString) * 60 : 0;
            this.minutes += minuteString != null && Parser.isInteger(minuteString)
                    ? Integer.parseInt(minuteString) : 0;
        }
        current = LocalDateTime.now();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        super.execute(tasks, ui, storage);
        if (hoursString == null || !Parser.isInteger(hoursString)) {
            return "Time given is invalid, try again! (Format: HH:MM)";
        }
        if (minuteString == null || !Parser.isInteger(minuteString)) {
            return "Time given is invalid, try again! (Format: HH:MM)";
        }
        ArrayList<Event> eventList = tasks.getAllEvents();
        eventList.sort(Comparator.comparing(Event::getStart));
        LocalDateTime startFreeTime = getStartTime(current, date);
        LocalDateTime endFreeTime = getEndTime(startFreeTime);
        System.out.println(startFreeTime);
        System.out.println(endFreeTime);
        ArrayList<Event> involvedEvents = involvedEvents(startFreeTime, endFreeTime, eventList);
        if (involvedEvents != null && involvedEvents.isEmpty()) {
            return "This day is completely free, yay!";
        }
        ArrayList<Pair<LocalDateTime, LocalDateTime>> markers =
                getTimeMarkers(startFreeTime, endFreeTime, involvedEvents);
        ArrayList<Pair<LocalDateTime, LocalDateTime>> res = filterSlots(markers, minutes);

        if (res == null || res.isEmpty()) {
            return "There is not enough time on this day! :(";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the time slots that are avaialable on ").append(date.toString()).append(":\n");
            for (Pair<LocalDateTime, LocalDateTime> slot : res) {
                sb.append(timeDisplay(slot)).append("\n");
            }
            return sb.toString();
        }
    }

    private LocalDateTime getStartTime(LocalDateTime current, LocalDate date) {
        return current.toLocalDate().equals(date)
                ? LocalDateTime.now() : date.atTime(LocalTime.MIDNIGHT);
    }

    private LocalDateTime getEndTime(LocalDateTime t) {
        return t.plusDays(1).toLocalDate().atTime(LocalTime.MIDNIGHT);
    }

    private ArrayList<Event> involvedEvents(LocalDateTime start, LocalDateTime end, ArrayList<Event> list) {
        assert list != null : "List does not exist!";
        ArrayList<Event> res = new ArrayList<>();
        for (Event event : list) {
            //if event cover the whole free time, return null and break
            if (event.getStart().isBefore(start) && event.getEnd().isAfter(end)) {
                return null;
            }
            //If event ends before startFreeTime, move to next
            if (event.getEnd().isBefore(start)) {
                continue;
            }
            //If event starts after endFreeTime, move to next
            if (event.getStart().isAfter(end)) {
                continue;
            }
            //If event is marked done, move to next
            if (event.getDone() == 1) {
                continue;
            }
            res.add(event);
        }
        return res;
    }

    private ArrayList<Pair<LocalDateTime, LocalDateTime>> getTimeMarkers(LocalDateTime start,
                                                          LocalDateTime end, ArrayList<Event> list) {
        if (list == null) {
            return null;
        }
        ArrayList<Pair<Integer, LocalDateTime>> temp = new ArrayList<>();
        ArrayList<Pair<LocalDateTime, LocalDateTime>> res = new ArrayList<>();
        //1 for starts, 0 for ends
        int count = 0;
        for (Event event : list) {
            if (event.getStart().isBefore(end)) {
                temp.add(new Pair<>(1, event.getStart()));
            }
            if (event.getEnd().isAfter(start)) {
                temp.add(new Pair<>(0, event.getEnd()));
                if (event.getEnd().isBefore(start)) {
                    count++;
                }
            }
        }
        temp.sort(Comparator.comparing(Pair::getValue));
        //Using stack counter method
        LocalDateTime freeStartMarker = null;
        for (Pair<Integer, LocalDateTime> marker : temp) {
            //If it's an end marker
            if (marker.getKey() == 0) {
                count--;
                assert count >= 0: "Something went wrong with stack count";
                if (count == 0) {
                    freeStartMarker = marker.getValue();
                }
            } else if (marker.getKey() == 1) {
                if (count == 0) {
                    LocalDateTime freeEndMarker = marker.getValue();
                    if (freeStartMarker != null) {
                        res.add(new Pair<>(freeStartMarker, freeEndMarker));
                    } else {
                        res.add(new Pair<>(start, freeEndMarker));
                    }
                    freeStartMarker = null;
                }
                count++;
            } else {
                assert false : "Key not valid";
            }
        }
        if (freeStartMarker != null) {
            res.add(new Pair<>(freeStartMarker, end));
        }
        return res;
    }

    private ArrayList<Pair<LocalDateTime, LocalDateTime>> filterSlots(ArrayList<Pair<LocalDateTime,
            LocalDateTime>> list, int minutes) {
        if (list == null) {
            return null;
        }
        ArrayList<Pair<LocalDateTime, LocalDateTime>> res = new ArrayList<>();
        for (Pair<LocalDateTime, LocalDateTime> slot : list) {
            if (slot.getKey().plusMinutes(minutes + 1).isBefore(slot.getValue())) {
                res.add(slot);
            }
        }
        return res;
    }

    private String timeDisplay(Pair<LocalDateTime, LocalDateTime> slot) {
        return slot.getKey().toLocalTime().format(timeFormatter)
                + " to " + slot.getValue().toLocalTime().format(timeFormatter);
    }
}
