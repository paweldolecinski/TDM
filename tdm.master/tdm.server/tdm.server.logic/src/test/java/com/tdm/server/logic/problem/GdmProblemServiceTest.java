package com.tdm.server.logic.problem;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.tdm.server.logic.model.Expert;
import com.tdm.server.logic.model.ExpertRole;
import com.tdm.server.logic.model.GdmProblem;
import com.tdm.server.logic.model.GdmProblemId;
import com.tdm.server.logic.requests.GdmProblemService;

public class GdmProblemServiceTest {

	private GdmProblemService manager;

	@Before
	public void setUp() {
		manager = new DefaultGdmProblemService();
	}

	@Test
	public void shouldAddBasicGdmProblem() {
		// Given
		String name = "Car Choosing";
		String description = "";

		// When
		GdmProblem problem = manager.createEmptyProblem();
		problem.setName(name);
		problem.setDescription(description);

		GdmProblemId id = manager.addProblem(problem);

		// Then
		GdmProblem result = manager.retrieveProblem(id);

		Assert.assertEquals(name, result.getName());
		Assert.assertEquals(description, result.getDescription());
		Assert.assertEquals(id, result.getId());
	}

	@Test
	public void shouldAssignExpertToProblem() {
		// Given
		Expert expert = new Expert("Pawel");

		GdmProblem emptyProblem = manager.createEmptyProblem();

		GdmProblemId problemId = manager.addProblem(emptyProblem);

		// When
		manager.assignExpertToProblem(problemId, expert.getId(),
				ExpertRole.MEMBER);

		// Then
		Collection<GdmProblemId> problemsIds = manager
				.retrieveProblemsIdsForExpert(expert.getId());

		Assert.assertEquals(1, problemsIds.size());
		Assert.assertTrue(problemsIds.contains(problemId));

	}

	@Test
	public void shouldAssignOwningExpertToProblem() {
		// Given
		Expert expert = new Expert("Pawel");

		GdmProblem emptyProblem = manager.createEmptyProblem();

		GdmProblemId problemId = manager.addProblem(emptyProblem);

		// When
		manager.setOwnerOfProblem(problemId, expert.getId());

		// Then
		Collection<GdmProblemId> problemsIds = manager
				.retrieveProblemsIdsForExpert(expert.getId());

		Assert.assertEquals(1, problemsIds.size());
		Assert.assertTrue(problemsIds.contains(problemId));

		Collection<GdmProblemId> ownedProblemsIds = manager
				.retrieveProblemsOwnedByExpert(expert.getId());
		Assert.assertEquals(1, ownedProblemsIds.size());
		Assert.assertTrue(ownedProblemsIds.contains(problemId));

	}

	@Test
	public void shouldAssignModeratorToProblem() {
		// Given
		Expert expert = new Expert("Pawel");

		GdmProblem emptyProblem = manager.createEmptyProblem();

		GdmProblemId problemId = manager.addProblem(emptyProblem);

		// When
		manager.assignExpertToProblem(problemId, expert.getId(),
				ExpertRole.MODERATOR);

		// Then
		Collection<GdmProblemId> problemsIds = manager
				.retrieveProblemsIdsForExpert(expert.getId());

		Assert.assertEquals(1, problemsIds.size());
		Assert.assertTrue(problemsIds.contains(problemId));

		Collection<GdmProblemId> editableProblemsIds = manager
				.retrieveProblemsModeratedByExpert(expert.getId());
		Assert.assertEquals(1, editableProblemsIds.size());
		Assert.assertTrue(editableProblemsIds.contains(problemId));

	}
}
