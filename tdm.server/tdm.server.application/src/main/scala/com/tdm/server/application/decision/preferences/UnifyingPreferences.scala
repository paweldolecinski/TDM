/**
 *
 */
package com.tdm.server.application.decision.preferences

import scala.collection.convert.Wrappers.JCollectionWrapper
import scala.collection.convert.Wrappers.SeqWrapper
import scala.collection.mutable.ArrayBuffer
import com.tdm.server.application.decision.preference.SolutionIdeaOrderList
import com.tdm.server.application.decision.preference.SolutionIdeaTupleWithValue
import com.tdm.server.application.decision.preference.FuzzyPreferenceRelation
import com.tdm.server.application.decision.preference.SolutionIdeaUtilityList
import com.tdm.server.application.decision.preference.MultiplicativePreferenceRelation

/**
 * @author dolek
 *
 */
object UnifyingPreferences {

  val logBase = Math.log(9);

  /**
   * @param preferences
   * @return
   */
  def transform(preferences: SolutionIdeaOrderList): FuzzyPreferenceRelation = {

    var fuzzyList = ArrayBuffer[SolutionIdeaTupleWithValue]();

    val n: Double = preferences.size() - 1;

    for (i <- 0 until preferences.size()) {
      val o_i = preferences.get(i);
      for (j <- i until preferences.size()) {
        val o_j = preferences.get(j);
        var l = o_j.getNote() - o_i.getNote();
        var pref = (1 + (l / n)) / 2;
        fuzzyList += new SolutionIdeaTupleWithValue(o_i.getSolutionId(), o_j
          .getSolutionId(), pref);
      }
    }
    return new FuzzyPreferenceRelation(SeqWrapper(fuzzyList));
  }

  def transform(preferences: SolutionIdeaUtilityList): FuzzyPreferenceRelation = {

    var fuzzyList = ArrayBuffer[SolutionIdeaTupleWithValue]();

    for (i <- 0 until preferences.size()) {
      val u_i = preferences.get(i);
      for (j <- i until preferences.size()) {
        val u_j = preferences.get(j);
        var pref = (Math.pow(u_i.getNote(), 2)) / (Math.pow(u_i.getNote(), 2) + Math.pow(u_j.getNote(), 2));
        fuzzyList += new SolutionIdeaTupleWithValue(u_i.getSolutionId(), u_j
          .getSolutionId(), pref);
      }
    }
    return new FuzzyPreferenceRelation(SeqWrapper(fuzzyList));
  }

  def transform(
    preferences: MultiplicativePreferenceRelation): FuzzyPreferenceRelation = {
    var fuzzyList = ArrayBuffer[SolutionIdeaTupleWithValue]();

    for (tuple <- JCollectionWrapper(preferences)) {
      val pref = (1 + log(tuple.getValue())) / 2;
      fuzzyList += new SolutionIdeaTupleWithValue(tuple, pref);
    }
    return new FuzzyPreferenceRelation(SeqWrapper(fuzzyList));
  }

  def log(num: Double): Double = {
    Math.log(num) / logBase
  }
}