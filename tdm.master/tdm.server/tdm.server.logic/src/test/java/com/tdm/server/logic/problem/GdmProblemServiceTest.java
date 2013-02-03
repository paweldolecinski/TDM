package com.tdm.server.logic.problem;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pl.dolecinski.subdicium.server.datastore.dao.ProblemDao;
import pl.dolecinski.subdicium.server.datastore.dto.ProblemDTO;

import com.tdm.server.logic.model.Expert;
import com.tdm.server.logic.model.ExpertId;
import com.tdm.server.logic.model.ExpertRole;
import com.tdm.server.logic.model.GdmProblem;
import com.tdm.server.logic.model.GdmProblemId;
import com.tdm.server.logic.requests.GdmProblemService;

public class GdmProblemServiceTest {

	private GdmProblemService manager;
	private ProblemDao problemDaoMock = mock(ProblemDao.class);
	private ProblemDTO problem0;
	private ProblemDTO problem1;

	@Before
	public void setUp() {

		manager = new DefaultGdmProblemService(problemDaoMock);

		problem1 = new ProblemDTO(1, "Car Choosing", "");
		when(problemDaoMock.create(eq("Car Choosing"), eq(""))).thenReturn(
				problem1);
		when(problemDaoMock.read(1)).thenReturn(problem1);

		problem0 = new ProblemDTO(0, "", "");
		when(problemDaoMock.create(eq(""), eq(""))).thenReturn(problem0);
		when(problemDaoMock.read(0)).thenReturn(problem0);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void shouldThrowExceptionWhenAddingExistingProblem() {
		// Given
		GdmProblem problem = new GdmProblem(GdmProblemId.create(1));

		// When
		manager.addProblem(problem);

		// Then
		// exception
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
		ExpertId expertId = ExpertId.create(1);
		Expert expert = new Expert(expertId, "Pawel");

		GdmProblem emptyProblem = manager.createEmptyProblem();
		GdmProblemId problemId = manager.addProblem(emptyProblem);

		HashSet<ProblemDTO> hashSet = new HashSet<ProblemDTO>();
		hashSet.add(problem0);
		when(problemDaoMock.findAllAssignedTo(expertId.getId())).thenReturn(
				hashSet);
		// When
		manager.assignExpertToProblem(problemId, expert.getId(),
				ExpertRole.MEMBER);

		// Then
		Collection<ExpertId> expertsIds = manager
				.retrieveExpertsAssignedToProblem(problemId);

		Assert.assertEquals(1, expertsIds.size());
		Assert.assertTrue(expertsIds.contains(expertId));

	}

	@Test(expected = IllegalStateException.class)
	public void shouldThrowExceptionWhenNoOwnerForProblem() {
		// Given
		GdmProblem emptyProblem = manager.createEmptyProblem();
		GdmProblemId problemId = manager.addProblem(emptyProblem);

		// When
		manager.getOwnerOfProblem(problemId);

		// Then
		// exception

	}

	@Test
	public void shouldAssignOwningExpertToProblem() {
		// Given
		ExpertId expertId = ExpertId.create(1);
		Expert expert = new Expert(expertId, "Pawel");

		GdmProblem emptyProblem = manager.createEmptyProblem();
		GdmProblemId problemId = manager.addProblem(emptyProblem);

		HashSet<ProblemDTO> hashSet = new HashSet<ProblemDTO>();
		hashSet.add(problem0);
		when(problemDaoMock.findAllAssignedTo(expertId.getId())).thenReturn(
				hashSet);
		when(
				problemDaoMock.findAllAssignedTo(expertId.getId(),
						ExpertRole.OWNER.name())).thenReturn(hashSet);
		// When
		manager.setOwnerOfProblem(problemId, expert.getId());

		// Then
		Collection<ExpertId> assignedExperts = manager
				.retrieveExpertsAssignedToProblem(problemId);

		Assert.assertEquals(1, assignedExperts.size());
		Assert.assertTrue(assignedExperts.contains(expertId));
		Assert.assertEquals(expertId.getId(), assignedExperts.iterator().next()
				.getId());

		ExpertId problemOwner = manager.getOwnerOfProblem(problemId);
		Assert.assertEquals(expertId, problemOwner);

	}

	@Test
	public void shouldAssignModeratorToProblem() {
		// Given
		ExpertId expertId = ExpertId.create(1);
		Expert expert = new Expert(expertId, "Pawel");

		GdmProblem emptyProblem = manager.createEmptyProblem();
		GdmProblemId problemId = manager.addProblem(emptyProblem);

		HashSet<ProblemDTO> hashSet = new HashSet<ProblemDTO>();
		hashSet.add(problem0);
		when(problemDaoMock.findAllAssignedTo(expertId.getId())).thenReturn(
				hashSet);
		when(
				problemDaoMock.findAllAssignedTo(expertId.getId(),
						ExpertRole.MODERATOR.name())).thenReturn(hashSet);
		// When
		manager.assignExpertToProblem(problemId, expert.getId(),
				ExpertRole.MODERATOR);

		// Then
		Collection<ExpertId> assignedExperts = manager
				.retrieveExpertsAssignedToProblem(problemId);

		Assert.assertEquals(1, assignedExperts.size());
		Assert.assertTrue(assignedExperts.contains(expertId));
		Assert.assertEquals(expertId.getId(), assignedExperts.iterator().next()
				.getId());

		Collection<ExpertId> moderatorsOfProblem = manager
				.retrieveModeratorsOfProblem(problemId);
		Assert.assertEquals(1, moderatorsOfProblem.size());
		Assert.assertTrue(moderatorsOfProblem.contains(expertId));
		Assert.assertEquals(expertId.getId(), moderatorsOfProblem.iterator()
				.next().getId());

	}
}
