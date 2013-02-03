package com.tdm.server.logic.search;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import pl.dolecinski.subdicium.server.datastore.dao.ProblemDao;
import pl.dolecinski.subdicium.server.datastore.dto.ProblemDTO;

import com.tdm.server.logic.model.ExpertId;
import com.tdm.server.logic.model.GdmProblemId;

public class SearchServiceTest {

	private SearchService searchService;
	private ProblemDao problemDaoMock = mock(ProblemDao.class);
	private ProblemDTO problem0;
	private ProblemDTO problem1;

	@Before
	public void setUp() {
		searchService = new SearchService(problemDaoMock);

		problem1 = new ProblemDTO(1, "Car Choosing", "");
		when(problemDaoMock.create(eq("Car Choosing"), eq(""))).thenReturn(
				problem1);
		when(problemDaoMock.read(1)).thenReturn(problem1);

		problem0 = new ProblemDTO(0, "", "");
		when(problemDaoMock.create(eq(""), eq(""))).thenReturn(problem0);
		when(problemDaoMock.read(0)).thenReturn(problem0);
	}

	@Test
	public void shouldReturnAllProblemsAvailableForExpert() {
		// Given
		ExpertId expertId = ExpertId.create(1);

		HashSet<ProblemDTO> hashSet = new HashSet<ProblemDTO>();
		hashSet.add(problem0);
		hashSet.add(problem1);
		when(problemDaoMock.findAllAssignedTo(expertId.getId())).thenReturn(
				hashSet);
		// When

		// Then
		Collection<GdmProblemId> problemsIds = searchService
				.retrieveProblemsIdsForExpert(expertId);

		Assert.assertEquals(2, problemsIds.size());
		Assert.assertTrue(problemsIds.contains(GdmProblemId.create(problem0
				.getId())));
		Assert.assertTrue(problemsIds.contains(GdmProblemId.create(problem1
				.getId())));

	}
}
