package com.tdm.server.application.decision.internal;

import java.util.Collections;
import java.util.List;

public class OperatorOwa {

	public final double[] weights;

	public OperatorOwa() {
		double[] w = { 0.13, 0.17, 0.2, 0.5};
		weights = w;
	}

	public OperatorOwa(double... weights) {
		this.weights = weights;
		double sum = 0.0;
		for (double w : weights)
			sum += w;
		double diff = Math.abs(1.0 - sum);
		if (diff > 0.0001)
			throw new IllegalArgumentException(String.format(
					"sum(weights) must be 1.0 (was: %f, %.5f)", sum, diff));
	}

	public double calculate(List<Double> values) {
		int min = Math.min(values.size(), weights.length);
		// sort
		Collections.sort(values);
		//
		double s = 0.0;
		for (int i = 0; i < min; i++)
			s += values.get(i) * weights[i];

		return s;
	}

}
