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

    var ideaTuples = preferences(0);
    var maxSize = ideaTuples.size()
    for (fuzzyPreferenceRelation <- preferences) {
      if (fuzzyPreferenceRelation.size() > maxSize) {
        ideaTuples = fuzzyPreferenceRelation
        maxSize = fuzzyPreferenceRelation.size()
      }
    }

    for (solutionIdeaTuple <- ideaTuples.asScala) {

      var values: List[Double] = List();

      for (fuzzyPreferenceRelation <- preferences) {

        if (fuzzyPreferenceRelation.contains(solutionIdeaTuple)) {
          val tuple = fuzzyPreferenceRelation
            .get(solutionIdeaTuple);
          if (tuple != null) {
            values ::= tuple.getValue();
          }
        }
      }
      val owa = new OperatorOwa(values.length, true);
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
    var qgdd: Map[Double, SolutionIdeaId] = calculateQGDD(ideas,
      collectivePreference);

    var keys: List[Double] = qgdd.keys.toList;
    keys = keys.sorted

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
    for (idea1 <- ideas) {
      var values: List[Double] = List();
      for (idea2 <- ideas) {
        if (!(idea1.equals(idea2))) {
          values ::= collectivePreference.getValue(idea1, idea2);
        }

      }
      if (values.isEmpty) {
        qgdd += 0.5 -> idea1;
      } else {
        val owa = new OperatorOwa(values.length, false);
        val owaV = owa.calculate(values);
        qgdd += owaV -> idea1;
      }
    }
    return qgdd;
  }
}