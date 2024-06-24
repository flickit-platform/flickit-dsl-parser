package org.flickit.dslparser.service.xtext.extractor.question;

public enum OptionIndex {
    INDEX1(0), INDEX2(1), INDEX3(2), INDEX4(3), INDEX5(4);

    private final int index;

    OptionIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static OptionIndex getOptionIndex(int optionNumber, int index) {
        if (index < 0 || index >= optionNumber) {
            throw new IllegalArgumentException("Invalid index.");
        }

        switch (optionNumber) {
            case 2:
                return index == 0 ? INDEX1 : INDEX5;
            case 3:
                switch (index) {
                    case 0:
                        return INDEX1;
                    case 1:
                        return INDEX3;
                    default:
                        return INDEX5;
                }
            case 4:
                switch (index) {
                    case 0:
                        return INDEX1;
                    case 1:
                        return INDEX2;
                    case 2:
                        return INDEX4;
                    default:
                        return INDEX5;
                }
            case 5:
                return values()[index];
            default:
                throw new IllegalArgumentException("Invalid option number.");
        }
    }
}
