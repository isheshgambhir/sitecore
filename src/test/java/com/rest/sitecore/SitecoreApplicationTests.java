package com.rest.sitecore;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rest.sitecore.controller.IssueController;

@SpringBootTest
class SitecoreApplicationTests {

	@Autowired
	private IssueController issueController;

	@Test
	void contextLoads() {
		assertNotNull(issueController);
	}

}
