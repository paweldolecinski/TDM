package com.tdm.server.logic.service.problem;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.eq;

import java.util.Collection;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.Any;

import pl.dolecinski.subdicium.server.datastore.dao.ProblemDao;
import pl.dolecinski.subdicium.server.datastore.dao.SolutionIdeaDao;
import pl.dolecinski.subdicium.server.datastore.dto.SolutionIdeaDTO;

import com.tdm.server.logic.model.GdmProblemId;
import com.tdm.server.logic.model.SolutionIdea;
import com.tdm.server.logic.problem.service.DefaultSolutionIdeaService;
import com.tdm.server.logic.service.SolutionIdeaService;

public class SolutionIdeaServiceTest {

	SolutionIdeaService ideasService;
	private ProblemDao problemDaoMock = mock(ProblemDao.class);
	private SolutionIdeaDao solutionIdeaDao = mock(SolutionIdeaDao.class);

	@Before
	public void setUp() {
		ideasService = new DefaultSolutionIdeaService(problemDaoMock, solutionIdeaDao);
		when(solutionIdeaDao.create(anyLong(), anyString())).thenReturn(
				new SolutionIdeaDTO(1, 1, "New idea"));
	}

	@Test
	public void shouldAddNewIdeaToProblem() {
		// Given
		GdmProblemId problemId = GdmProblemId.create(1);
		String ideaName = "New idea";

		// When
		SolutionIdea solutionIdea = ideasService.createAndAddSolutionIdea(
				problemId, ideaName);

		// Then
		Assert.assertEquals(problemId, solutionIdea.getProblemId());
		Assert.assertEquals(ideaName, solutionIdea.getName());
		Assert.assertNotNull(solutionIdea.getId());
	}

	@Test
	public void shouldRetrieveAllIdeasForProblem() {
		GdmProblemId problemId = GdmProblemId.create(0);
		Collection<SolutionIdea> ideas = ideasService
				.retrieveSolutionIdeasForProblem(problemId);

		assertEquals(0, ideas.size());
	}

}
