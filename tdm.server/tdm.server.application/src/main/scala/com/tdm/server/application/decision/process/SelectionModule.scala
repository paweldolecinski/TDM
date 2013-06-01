package com.tdm.server.application.decision.process

import com.tdm.domain.model.problem.ProblemId
import com.tdm.server.application.decision.preference.FuzzyPreferenceRelation
import com.tdm.server.application.decision.preference.SolutionIdeaTupleWithValue
import com.tdm.server.application.decision.process.fuzzy.OperatorOwa
import scala.collection.convert.Wrappers.JCollectionWrapper
import scala.collection.JavaConverters._
import com.tdm.domain.model.idea.SolutionIdeaId
import com.tdm.domain.model.expert.ExpertId

object SelectionModule {

  def getCollectivePreference(
    preferences: java.util.List[FuzzyPreferenceRelation]): FuzzyPreferenceRelation = {
    getCollectivePreference(preferences.asScala.toList)
  }

  def getCollectivePreference(
    preferences: List[FuzzyPreferenceRelation]): FuzzyPreferenceRelation = {

    val collectivePref = new FuzzyPreferenceRelation();

    if (preferences.isEmpty) {
      return collectivePref;
    }

    val owa = new OperatorOwa();

    var ideaTuples = preferences(0);

    for (i <- 0 until ideaTuples.size()) {
      val solutionIdeaTuple = ideaTuples.get(i);

      var values: List[Double] = List();

      for (j <- 0 until preferences.size) {
        val fuzzyPreferenceRelation = preferences(j);

        val tuple = fuzzyPreferenceRelation
          .get(solutionIdeaTuple);
        if (tuple != null) {
          values ::= tuple.getValue();
        }
      }
      collectivePref.add(new SolutionIdeaTupleWithValue(
        solutionIdeaTuple, owa.calculate(values)));
    }
    return collectivePref;
  }

  def getGlobalRanking(ideas: java.util.List[SolutionIdeaId],
    collectivePreference: FuzzyPreferenceRelation): java.util.List[SolutionIdeaId] = {
    getGlobalRanking(ideas.asScala.toList, collectivePreference).asJava
  }

  def getGlobalRanking(ideas: List[SolutionIdeaId],
    collectivePreference: FuzzyPreferenceRelation): List[SolutionIdeaId] = {
    val qgdd: Map[Double, SolutionIdeaId] = calculateQGDD(ideas,
      collectivePreference);

    val keys: List[Double] = qgdd.keys.toList;
    keys.sorted
    keys.reverse

    var ranking: List[SolutionIdeaId] = List();
    for (key <- keys) {
      ranking ::= qgdd.get(key).get;
    }

    return ranking;
  }

  private def calculateQGDD(
    ideas: List[SolutionIdeaId],
    collectivePreference: FuzzyPreferenceRelation): Map[Double, SolutionIdeaId] = {
    var qgdd: Map[Double, SolutionIdeaId] = Map();
    val owa = new OperatorOwa(0.07, 0.67, 0.26);
    for (idea1 <- ideas) {
      var values: List[Double] = List();
      for (idea2 <- ideas) {
        if (!(idea1.equals(idea2))) {
          values ::= collectivePreference.getValue(idea1, idea2);
        }

      }
      val owaV = owa.calculate(values);
      qgdd += owaV -> idea1;
    }
    return qgdd;
  }
}