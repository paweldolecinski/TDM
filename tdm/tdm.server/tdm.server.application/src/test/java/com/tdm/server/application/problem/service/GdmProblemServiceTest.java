package com.tdm.server.application.problem.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.tdm.domain.model.expert.Expert;
import com.tdm.domain.model.expert.ExpertId;
import com.tdm.domain.model.expert.ExpertRole;
import com.tdm.domain.model.expert.dto.ExpertIdAsEmail;
import com.tdm.domain.model.problem.GdmProblem;
import com.tdm.domain.model.problem.GdmProblemId;
import com.tdm.domain.model.problem.ProblemRepository;
import com.tdm.domain.model.problem.dto.GdmProblemDto;
import com.tdm.domain.model.problem.dto.GdmProblemIdDto;

public class GdmProblemServiceTest {

	private GdmProblemService manager;
	private ProblemRepository problemDaoMock = mock(ProblemRepository.class);
	private GdmProblem problem0;
	private GdmProblem problem1;

	@Before
	public void setUp() {

		manager = new DefaultGdmProblemService(problemDaoMock);

		problem1 = new GdmProblemDto();
		problem1.setName("Car Choosing");
		when(problemDaoMock.read(new GdmProblemIdDto("1"))).thenReturn(problem1);

		problem0 = new GdmProblemDto();
		when(problemDaoMock.read(new GdmProblemIdDto("0"))).thenReturn(problem0);
	}

	@Test
	public void shouldAddBasicGdmProblem() {
		// Given
		String name = "Car Choosing";
		String description = "";

		// When
		manager.addProblem(name, description, new Date());

		// Then
		GdmProblemId id = new GdmProblemIdDto("1");
		GdmProblem result = manager.retrieveProblem(id);

		Assert.assertEquals(name, result.getName());
		Assert.assertEquals(description, result.getDescription());
		Assert.assertEquals(id, result.getId());
	}

	@Test
	public void shouldAssignExpertToProblem() {
		// Given
		ExpertId expertId = new ExpertIdAsEmail("1");

		GdmProblemId id = new GdmProblemIdDto("0");
		HashSet<GdmProblem> hashSet = new HashSet<GdmProblem>();
		hashSet.add(problem0);
		when(problemDaoMock.findAllAssignedTo(expertId)).thenReturn(hashSet);
		// When
		manager.assignExpertToProblem(id, expertId, ExpertRole.MEMBER);

		// Then
		List<Expert> expertsIds = manager.retrieveExpertsAssignedToProblem(id);

		Assert.assertEquals(1, expertsIds.size());
		Assert.assertTrue(expertsIds.contains(expertId));

	}

	@Test(expected = IllegalStateException.class)
	public void shouldThrowExceptionWhenNoOwnerForProblem() {
		// Given
		GdmProblemId id = new GdmProblemIdDto("0");
		// When
		manager.getOwnerOfProblem(id);

		// Then
		// exception

	}

	@Test
	public void shouldAssignOwningExpertToProblem() {
		// Given
		ExpertId expertId = new ExpertIdAsEmail("1");

		GdmProblemId id = new GdmProblemIdDto("0");

		HashSet<GdmProblem> hashSet = new HashSet<GdmProblem>();
		hashSet.add(problem0);
		when(problemDaoMock.findAllAssignedTo(expertId)).thenReturn(hashSet);
		when(problemDaoMock.findAllAssignedTo(expertId, ExpertRole.OWNER))
				.thenReturn(hashSet);
		// When
		manager.setOwnerOfProblem(id, expertId);

		// Then
		List<Expert> assignedExperts = manager
				.retrieveExpertsAssignedToProblem(id);

		Assert.assertEquals(1, assignedExperts.size());
		Assert.assertTrue(assignedExperts.contains(expertId));
		Assert.assertEquals(expertId, assignedExperts.iterator().next().getId());

		ExpertId problemOwner = manager.getOwnerOfProblem(id);
		Assert.assertEquals(expertId, problemOwner);

	}

	@Test
	public void shouldAssignModeratorToProblem() {
		// Given
		ExpertId expertId = new ExpertIdAsEmail("1");

		GdmProblemId id = new GdmProblemIdDto("0");

		HashSet<GdmProblem> hashSet = new HashSet<GdmProblem>();
		hashSet.add(problem0);
		when(problemDaoMock.findAllAssignedTo(expertId)).thenReturn(hashSet);
		when(problemDaoMock.findAllAssignedTo(expertId, ExpertRole.MODERATOR))
				.thenReturn(hashSet);
		// When
		manager.assignExpertToProblem(id, expertId, ExpertRole.MODERATOR);

		// Then
		List<Expert> assignedExperts = manager
				.retrieveExpertsAssignedToProblem(id);

		Assert.assertEquals(1, assignedExperts.size());
		Assert.assertTrue(assignedExperts.contains(expertId));
		Assert.assertEquals(expertId, assignedExperts.iterator().next().getId());

		List<Expert> moderatorsOfProblem = manager
				.retrieveModeratorsOfProblem(id);
		Assert.assertEquals(1, moderatorsOfProblem.size());
		Assert.assertTrue(moderatorsOfProblem.contains(expertId));
		Assert.assertEquals(expertId, moderatorsOfProblem.iterator().next()
				.getId());

	}
}
