package com.tdm.server.application.decision.process

import com.tdm.domain.model.problem.ProblemId
import com.tdm.domain.model.idea.SolutionIdeaId
import com.tdm.domain.model.expert.ExpertId

object ConsensusModule {
  
  def calculateConsensus(problemId: ProblemId,
    experts: List[ExpertId], ideas: List[SolutionIdeaId],
    globalRanking: List[SolutionIdeaId]) = {

    //p_i(x_j) = ( |V^c - V^i| / n-1 )^b

    // C(x_j) = 1 - sum (p_i(x_j)/m) m to liczba ekspertow

    //C_X = (1-beta)* sum

  }
}