package com.tdm.domain.model.problem;

import java.io.Serializable;

import javax.jdo.annotations.EmbeddedOnly;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable = "true")
@EmbeddedOnly
public class CurrentConsensus implements Serializable {

	private static final long serialVersionUID = -2172666092337681104L;

	String consensus;
}
