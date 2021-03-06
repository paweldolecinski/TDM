package com.tdm.domain.model.expert;

import java.io.Serializable;

/**
 * Uniquely identifies a particular cargo. Automatically generated by the
 * application.
 */
@SuppressWarnings("serial")
public class ExpertId implements Serializable {

    private String id;

    public ExpertId() {
    }

    public ExpertId(String id) {
        this.id = id;
    }

    public String getIdString() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ExpertId other = (ExpertId) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    boolean sameValueAs(ExpertId other) {
        return other != null && this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return id;
    }
}
