package com.sujay.journalApplication.Controller;

import com.sujay.journalApplication.DTO.JournalEntryCreateDTO;
import com.sujay.journalApplication.DTO.JournalEntryResponseDTO;
import com.sujay.journalApplication.Entity.JournalEntry;
import com.sujay.journalApplication.Entity.User;
import com.sujay.journalApplication.Service.JournalEntryService;
import com.sujay.journalApplication.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

//Controller --> Service --> Repository
@RestController
@RequestMapping("/journal")
@Tag(
        name = "Journal APIs",
        description = "APIs for creating, viewing, updating, and deleting journal entries for authenticated users."
)
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @Operation(
            summary = "Create Journal Entry",
            description = "Creates a new journal entry for the logged-in user. The entry contains title, content, and timestamp. Returns the created entry on success."
    )
    @PostMapping("/post")
    public ResponseEntity<JournalEntryResponseDTO> createEntry(@RequestBody JournalEntryCreateDTO myEntry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();

            //Convert DTO to Entity
            JournalEntry journalEntry = new JournalEntry();
            journalEntry.setTitle(myEntry.getTitle());
            journalEntry.setContent(myEntry.getContent());
            journalEntry.setDate(LocalDateTime.now());

            // Save entry linked to user
            journalEntryService.saveEntry(journalEntry, userName);

            // Convert Entity → Response DTO
            JournalEntryResponseDTO response = new JournalEntryResponseDTO(
                    journalEntry.getId().toHexString(),
                    journalEntry.getTitle(),
                    journalEntry.getContent(),
                    journalEntry.getDate().toString()
            );

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(
            summary = "Get All Journal Entries",
            description = "Retrieves all journal entries created by the currently authenticated user. Returns 404 if no entries are found."
    )
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllJournalEntriesOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> allJournals = user.getJournalEntries();
        if (allJournals != null && !allJournals.isEmpty()) {
            return new ResponseEntity<>(allJournals, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Get Journal Entry by ID",
            description = "Fetches a single journal entry using its unique ID. Returns the journal entry if found, otherwise responds with 404."
    )
    @GetMapping("/get-byId/{myId}")
    public ResponseEntity<JournalEntry> getById(@PathVariable String myId) {
        ObjectId objectId = new ObjectId(myId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(objectId)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<JournalEntry> journalEntry = journalEntryService.findById(objectId);
            if (journalEntry.isPresent()) {
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Delete Journal Entry by ID",
            description = "Deletes a journal entry identified by its ID, but only if it belongs to the logged-in user. Returns 204 on success or 404 if not found."
    )
    @DeleteMapping("/delete/{myId}")
    //The <?> is a wildcard → means “any type”.
    public ResponseEntity<?> deleteById(@PathVariable String myId) {
        ObjectId objectId = new ObjectId(myId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        boolean removed  = journalEntryService.deleteById(objectId, userName);
        if (removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            summary = "Update Journal Entry by ID",
            description = "Updates an existing journal entry for the logged-in user based on its ID. The user can modify the title and content fields. The updated timestamp is saved automatically."
    )
    @PutMapping("/update/{myId}")
    public ResponseEntity<?> updateJournalById(@PathVariable String myId, @RequestBody JournalEntry newEntry) {
        ObjectId objectId = new ObjectId(myId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(objectId)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<JournalEntry> journalEntry = journalEntryService.findById(objectId);
            if (journalEntry.isPresent()) {
                JournalEntry oldEntry = journalEntry.get();
                oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
                oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.getContent());

                //update timestamp on the saved entity itself
                oldEntry.setDate(LocalDateTime.now());
                // Save back the updated entry (replaces old in DB)
                journalEntryService.saveEntry(oldEntry);
                return new ResponseEntity<>(oldEntry, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}