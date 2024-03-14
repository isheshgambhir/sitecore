package com.rest.sitecore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.sitecore.model.Issue;
import com.rest.sitecore.repository.IssueRepository;

@RestController
public class IssueController {

    @Autowired
    private IssueRepository issueRepository;

    @GetMapping("/issues/{id}")
    public ResponseEntity<Issue> getIssue(@PathVariable(value = "id") Long id) {
        Optional<Issue> issue = issueRepository.findById(id);
        if (issue.isPresent()) {
            return new ResponseEntity<>(issue.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/issues")
    public ResponseEntity<List<Issue>> getIssues() {
        List<Issue> issues = issueRepository.findAll();
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }

    @PostMapping("/issues")
    public ResponseEntity<Issue> addIssue(@RequestBody Issue issue) {
        if(issue.getId() != null) {
            Optional<Issue> existingIssue = issueRepository.findById(issue.getId());
            if (existingIssue.isPresent()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(issueRepository.save(issue), HttpStatus.CREATED);
    }

    @PutMapping("/issues/{id}")
    public ResponseEntity<Issue> updateIssue(@PathVariable(value = "id") Long id, @RequestBody Issue issue) {
        Optional<Issue> existingIssue = issueRepository.findById(id);
        if (existingIssue.isPresent()) {
            Issue updatedIssue = existingIssue.get();
            updatedIssue.setDescription(issue.getDescription());
            updatedIssue.setTitle(issue.getTitle());
            return new ResponseEntity<>(issueRepository.save(issue), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(issueRepository.save(issue), HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/issues/{id}")
    public ResponseEntity<Issue> deleteIssue(@PathVariable(value = "id") Long id) {
        Optional<Issue> existingIssue = issueRepository.findById(id);
        if (existingIssue.isPresent()) {
            issueRepository.delete(existingIssue.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}