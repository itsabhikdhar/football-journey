package com.footballjourney.backend.journal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/journal")
@RequiredArgsConstructor
public class JournalController {

    private final JournalService journalService;

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalRequest request, Principal principal) {
        return ResponseEntity.ok(journalService.createEntry(request, principal.getName()));
    }

    @GetMapping
    public ResponseEntity<List<JournalEntry>> getMyJournal(Principal principal) {
        return ResponseEntity.ok(journalService.getMyJournal(principal.getName()));
    }
}
