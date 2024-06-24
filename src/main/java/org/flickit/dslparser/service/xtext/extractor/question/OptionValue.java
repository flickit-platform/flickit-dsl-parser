package org.flickit.dslparser.service.xtext.extractor.question;

public enum OptionValue {
    VALUE1(1), VALUE2(2), VALUE3(3), VALUE4(4), VALUE5(5);

    private final int value;

    OptionValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static OptionValue getOptionValue(OptionIndex optionIndex) {
        switch (optionIndex) {
            case INDEX1:
                return VALUE1;
            case INDEX2:
                return VALUE2;
            case INDEX3:
                return VALUE3;
            case INDEX4:
                return VALUE4;
            case INDEX5:
                return VALUE5;
            default:
                throw new IllegalArgumentException("Invalid option index.");
        }
    }
}