package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    HashMap<Long, TimeEntry> timeTable = new HashMap<>();
    Long index = 0L;

    public TimeEntry create(TimeEntry t) {
        TimeEntry tNew = new TimeEntry(t.getId(), t.getProjectId(), t.getUserId(), t.getDate(), t.getHours());
        if ( tNew.getId() == null) {
            index++;
            tNew.setId(index);
        }
        timeTable.put(tNew.getId(), tNew);
        return tNew;
    }

    public TimeEntry find(Long id) {
        return timeTable.get(id);

    }

    public void delete(Long id) {
        TimeEntry tEntry = find((id));
        if ( tEntry != null && (tEntry.getId() == id )) {
            timeTable.remove(id, tEntry);
            if (index > 1)
                index--;
        }
    }

    public List<TimeEntry> list() {
        return timeTable.values().stream().collect(Collectors.toCollection(ArrayList::new));

    }

    public TimeEntry update(Long id, TimeEntry timeEntry) {
        if ( timeTable.containsKey(id) )
        {
            TimeEntry updatedEntry = new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(),
                    timeEntry.getHours());
            timeTable.replace(id, updatedEntry);
            return updatedEntry;
        }
        return null;

    }
}
