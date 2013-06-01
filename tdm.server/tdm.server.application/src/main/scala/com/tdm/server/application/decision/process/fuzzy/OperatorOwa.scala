package com.tdm.server.application.decision.process.fuzzy

class OperatorOwa(weightsArg: Double*) {
  
  private val weights: Seq[Double] = weightsArg;
  checkWeights

  def this() = this(0.13, 0.17, 0.2, 0.5)

  private def checkWeights() = {
    var sum = 0.0;
    weights.foreach(w => sum += w)

    val diff = Math.abs(1.0 - sum);
    if (diff > 0.0001)
      throw new IllegalArgumentException(
        "sum(weights) must be 1.0 (was: %f, %.5f)".format(sum, diff));
  }

  //	public OperatorOwa(double... ) {
  //		this.weights = weights;
  //		
  //	}

  def calculate(values: List[Double]): Double = {
    val min = Math.min(values.size, weights.length);
    // sort
    values.sorted
    //
    var s = 0.0;
    for (i <- 0 to min)
      s += values(i) * weights(i);

    return s;
  }
}