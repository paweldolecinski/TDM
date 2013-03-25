package com.tdm.server.application.problem.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.tdm.domain.model.idea.SolutionIdea;
import com.tdm.domain.model.idea.SolutionIdeaId;
import com.tdm.domain.model.idea.SolutionIdeaRepository;
import com.tdm.domain.model.idea.dto.SolutionIdeaDto;
import com.tdm.domain.model.problem.GdmProblemId;
import com.tdm.domain.model.problem.ProblemRepository;
import com.tdm.server.application.problem.service.DefaultSolutionIdeaService;
import com.tdm.server.application.problem.service.SolutionIdeaService;

public class SolutionIdeaServiceTest {

	SolutionIdeaService ideasService;
	private ProblemRepository problemDaoMock = mock(ProblemRepository.class);
	private SolutionIdeaRepository solutionIdeaDao = mock(SolutionIdeaRepository.class);

	@Before
	public void setUp() {
		ideasService = new DefaultSolutionIdeaService(problemDaoMock,
				solutionIdeaDao);
		SolutionIdeaDto solutionIdea = new SolutionIdeaDto();
		solutionIdea.setId(new SolutionIdeaId("1"));
		solutionIdea.setProblemId(new GdmProblemId("1"));
		solutionIdea.setName("New idea");
		when(
				solutionIdeaDao.read(
						org.mockito.Matchers.any(GdmProblemId.class),
						org.mockito.Matchers.any(SolutionIdeaId.class)))
				.thenReturn(solutionIdea);
	}

	@Test
	public void shouldAddNewIdeaToProblem() {
		// Given
		GdmProblemId problemId = new GdmProblemId("1");
		SolutionIdeaId solutionIdeaId = new SolutionIdeaId("1");
		String ideaName = "New idea";

		// When
		ideasService.createAndAddSolutionIdea(problemId, ideaName);
		SolutionIdea solutionIdea = ideasService.getSolutionIdea(problemId,
				solutionIdeaId);
		// Then
		Assert.assertEquals(problemId, solutionIdea.getProblemId());
		Assert.assertEquals(ideaName, solutionIdea.getName());
		Assert.assertNotNull(solutionIdea.getId());
	}

	@Test
	public void shouldRetrieveAllIdeasForProblem() {
		GdmProblemId problemId = new GdmProblemId("0");
		Collection<SolutionIdea> ideas = ideasService
				.retrieveSolutionIdeasForProblem(problemId);

		assertEquals(0, ideas.size());
	}

}
