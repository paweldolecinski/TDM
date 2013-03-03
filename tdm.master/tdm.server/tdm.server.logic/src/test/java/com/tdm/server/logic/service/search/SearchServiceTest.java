package com.tdm.server.logic.service.search;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pl.dolecinski.subdicium.server.datastore.dao.ProblemDao;
import pl.dolecinski.subdicium.server.datastore.dto.ProblemDTO;

import com.tdm.server.logic.model.ExpertId;
import com.tdm.server.logic.model.GdmProblem;
import com.tdm.server.logic.problem.service.SearchService;

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

		List<ProblemDTO> list = new ArrayList<ProblemDTO>();
		list.add(problem0);
		list.add(problem1);
		when(problemDaoMock.findAllAssignedTo(expertId.getId())).thenReturn(
				list);
		// When

		// Then
		Collection<GdmProblem> problemsIds = searchService
				.retrieveProblemsForExpert(expertId);

		Assert.assertEquals(2, problemsIds.size());
		Iterator<GdmProblem> iterator = problemsIds.iterator();
		Assert.assertEquals(problem0.getId(), iterator.next().getId().getId());
		Assert.assertEquals(problem1.getId(), iterator.next().getId().getId());

	}
}
