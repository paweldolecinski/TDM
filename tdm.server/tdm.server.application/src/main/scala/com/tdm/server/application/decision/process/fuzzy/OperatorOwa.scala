package com.tdm.server.application.decision.process.fuzzy

//class OperatorOwa(weightsArg: Double*) {
class OperatorOwa(size: Int, specialMosfOf: Boolean) {

  private val weights: Seq[Double] = calculateWeights(size, specialMosfOf);
  checkWeights
  //  def this() = this(0.5, 0.2, 0.17, 0.13)

  def calculate(values: List[Double]): Double = {
    val min = Math.min(values.size, weights.length);
    // sort
    var values2 = values.sorted
    values2 = values.reverse
    //
    var s = 0.0;
    for (i <- 0 until min)
      s += values2(i) * weights(i);

    return s;
  }

  private def calculateWeights(size: Int, specialMosfOf: Boolean): Seq[Double] = {
    var w: Array[Double] = new Array(size)
    if (specialMosfOf) {
      for (k <- 1 to size) {
        w(k - 1) = round2(math.pow((k: Float) / size, 0.5) - Math.pow((k - 1: Float) / size, 0.5))
      }
    } else {
      for (k <- 1 to size) {
        var i = (k: Float) / size
        var j = (k - 1: Float) / size
        w(k - 1) = ((Q(i) - Q(j)))
      }
    }

    w
  }
  private def round2(value: Double): Double = {
    var result = value * 1000;
    result = Math.round(result);
    result = result / 1000;
    return result;
  }
  private def Q(r: Float): Float = {
    val a = 0.3f
    val b = 0.8f
    if (r < a) {
      return 0f
    }
    if (r > b) {
      return 1f
    }
    return (r - a) / (b - a)
  }
  private def checkWeights() = {
    var sum = 0.0;
    weights.foreach(w => sum += w)

    val diff = Math.abs(1.0 - sum);
    if (diff > 0.0001)
      throw new IllegalArgumentException(
        "sum(weights) must be 1.0 (was: %f, %.5f)".format(sum, diff));
  }
}