package com.rest.sitecore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.rest.sitecore.model.Issue;

public interface IssueRepository extends CrudRepository<Issue, Long> {
    Optional<Issue> findById(Long id);
    List<Issue> findAll();
}