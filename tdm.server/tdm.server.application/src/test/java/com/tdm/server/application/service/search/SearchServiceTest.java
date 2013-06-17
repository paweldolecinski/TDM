package com.tdm.server.application.service.search;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.tdm.domain.model.expert.ExpertId;
import com.tdm.domain.model.problem.Problem;
import com.tdm.domain.model.problem.ProblemId;
import com.tdm.domain.model.problem.ProblemRepository;
import com.tdm.server.application.problem.service.DefaultSearchService;
import com.tdm.server.application.problem.service.SearchService;

public class SearchServiceTest {

	private SearchService searchService;
	private ProblemRepository problemDaoMock = mock(ProblemRepository.class);
	private Problem problem0;
	private Problem problem1;

	@Before
	public void setUp() {
		searchService = new DefaultSearchService(problemDaoMock);

		problem1 = new Problem();
		problem1.setName("Car Choosing");
		// problem1.setKey(new ProblemId("1"));
		when(problemDaoMock.read(new ProblemId("1"))).thenReturn(problem1);

		problem0 = new Problem();
		when(problemDaoMock.read(any(ProblemId.class))).thenReturn(problem0);
	}

	@Test
	public void shouldReturnAllProblemsAvailableForExpert() {
		// Given
		ExpertId expertId = new ExpertId("1");

		List<Problem> list = new ArrayList<Problem>();
		list.add(problem0);
		list.add(problem1);
		when(problemDaoMock.findAllAssignedTo(expertId)).thenReturn(list);
		// When

		// Then
		Collection<Problem> problemsIds = searchService
				.retrieveProblemsForExpert(expertId);

		Assert.assertEquals(2, problemsIds.size());

	}
}
