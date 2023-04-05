package org.flickit.dslparser.model.profile;

public enum Level {

    WEAK(1), RISKY(2), NORMAL(3), GOOD(4), OPTIMIZED(5);

    private Integer value;

    private Level(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
