package com.tdm.server.application.problem.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.tdm.domain.model.expert.Expert;
import com.tdm.domain.model.expert.ExpertId;
import com.tdm.domain.model.expert.ExpertRole;
import com.tdm.domain.model.problem.Problem;
import com.tdm.domain.model.problem.ProblemId;
import com.tdm.domain.model.problem.ProblemRepository;

public class GdmProblemServiceTest {

	private GdmProblemService manager;
	private ProblemRepository problemDaoMock = mock(ProblemRepository.class);
	private Problem problem0;
	private Problem problem1;

	@Before
	public void setUp() {

		manager = new DefaultGdmProblemService(problemDaoMock);

		problem1 = new Problem();
		problem1.setName("Car Choosing");
		when(problemDaoMock.read(new ProblemId("1"))).thenReturn(problem1);

		problem0 = new Problem();
		when(problemDaoMock.read(new ProblemId("0"))).thenReturn(problem0);
	}

	@Test
	public void shouldAddBasicGdmProblem() {
		// Given
		String name = "Car Choosing";
		String description = "";

		// When
		manager.createProblem(problem1);

		// Then
		ProblemId id = new ProblemId("1");
		Problem result = manager.retrieveProblem(id);

		Assert.assertEquals(name, result.getName());
		Assert.assertEquals(description, result.getDescription());
	}

	@Test
	public void shouldAssignExpertToProblem() {
		// Given
		ExpertId expertId = new ExpertId("1");

		ProblemId id = new ProblemId("0");
		ArrayList<Problem> hashSet = new ArrayList<Problem>();
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
		ProblemId id = new ProblemId("0");
		// When
		manager.getOwnerOfProblem(id);

		// Then
		// exception

	}

	@Test
	public void shouldAssignOwningExpertToProblem() {
		// Given
		ExpertId expertId = new ExpertId("1");

		ProblemId id = new ProblemId("0");

		ArrayList<Problem> hashSet = new ArrayList<Problem>();
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
		ExpertId expertId = new ExpertId("1");

		ProblemId id = new ProblemId("0");

		ArrayList<Problem> hashSet = new ArrayList<Problem>();
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

		Set<Expert> moderatorsOfProblem = manager
				.retrieveModeratorsOfProblem(id);
		Assert.assertEquals(1, moderatorsOfProblem.size());
		Assert.assertTrue(moderatorsOfProblem.contains(expertId));
		Assert.assertEquals(expertId, moderatorsOfProblem.iterator().next()
				.getId());

	}
}
