package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    @Autowired
    private TimeEntryRepository repository;

    public TimeEntryController (TimeEntryRepository timeEntryRepository) {
        this.repository = timeEntryRepository;
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry updateEntry = repository.update( id,  timeEntry);
        if ( updateEntry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(updateEntry, HttpStatus.OK);
        }
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if ( id != null  ) {
            repository.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry tEntryCreated = repository.create(timeEntryToCreate);
        return new ResponseEntity<>(tEntryCreated, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<>( repository.list(), HttpStatus.OK);

    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long id) {
        TimeEntry findEntry = repository.find(id);
        if ( findEntry == null ) {
            return new ResponseEntity<>(repository.find(id), HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(findEntry, HttpStatus.OK);
        }

    }
}
