package com.tdm.server.application.service.search;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.tdm.domain.model.expert.ExpertId;
import com.tdm.domain.model.expert.dto.ExpertIdAsEmail;
import com.tdm.domain.model.problem.GdmProblem;
import com.tdm.domain.model.problem.GdmProblemId;
import com.tdm.domain.model.problem.ProblemRepository;
import com.tdm.domain.model.problem.dto.GdmProblemDto;
import com.tdm.server.application.problem.service.SearchService;

public class SearchServiceTest {

	private SearchService searchService;
	private ProblemRepository problemDaoMock = mock(ProblemRepository.class);
	private GdmProblem problem0;
	private GdmProblem problem1;

	@Before
	public void setUp() {
		searchService = new SearchService(problemDaoMock);

		problem1 = new GdmProblemDto();
		problem1.setName("Car Choosing");
		problem1.setId(new GdmProblemId("1"));
		when(problemDaoMock.read(new GdmProblemId("1"))).thenReturn(problem1);

		problem0 = new GdmProblemDto();
		when(problemDaoMock.read(any(GdmProblemId.class))).thenReturn(problem0);
	}

	@Test
	public void shouldReturnAllProblemsAvailableForExpert() {
		// Given
		ExpertId expertId = new ExpertIdAsEmail("1");

		List<GdmProblem> list = new ArrayList<GdmProblem>();
		list.add(problem0);
		list.add(problem1);
		when(problemDaoMock.findAllAssignedTo(expertId)).thenReturn(list);
		// When

		// Then
		Collection<GdmProblem> problemsIds = searchService
				.retrieveProblemsForExpert(expertId);

		Assert.assertEquals(2, problemsIds.size());
		Iterator<GdmProblem> iterator = problemsIds.iterator();
		Assert.assertEquals(problem0.getId(), iterator.next().getId());
		Assert.assertEquals(problem1.getId(), iterator.next().getId());

	}
}
