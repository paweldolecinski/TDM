package com.tdm.server.logic.problem;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.tdm.server.logic.model.GdmProblemId;
import com.tdm.server.logic.model.Idea;

public class IdeasServiceTest {

	IdeasService ideasService;

	@Before
	public void setUp() {
		ideasService = new IdeasService();
	}

	@Test
	public void shouldRetrieveAllIdeasForProblem() {
		GdmProblemId problemId = GdmProblemId.create(0);
		Collection<Idea> ideas = ideasService
				.retrieveIdeasForProblem(problemId);

		assertEquals(0, ideas.size());
	}

}
