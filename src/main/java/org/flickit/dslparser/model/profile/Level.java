package org.flickit.dslparser.model.profile;

public enum Level {

    WEAK(1), MODERATE(2), GOOD(3), GREAT(4), EXCEPTIONAL(5);

    private Integer value;

    private Level(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
