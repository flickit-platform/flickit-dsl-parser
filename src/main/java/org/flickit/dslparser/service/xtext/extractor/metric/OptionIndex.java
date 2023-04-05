package org.flickit.dslparser.service.xtext.extractor.metric;

import static org.flickit.dslparser.service.xtext.extractor.metric.MetricOptionExtractor.MIN_OPTION_NUMBER;
import static org.flickit.dslparser.service.xtext.extractor.metric.MetricOptionExtractor.MAX_OPTION_NUMBER;
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
        if (optionNumber < MIN_OPTION_NUMBER || optionNumber > MAX_OPTION_NUMBER) {
            throw new IllegalArgumentException("Invalid option number.");
        }

        if (index < 0 || index >= optionNumber) {
            throw new IllegalArgumentException("Invalid index.");
        }

        switch (optionNumber) {
            case 2:
                return index == 0 ? INDEX1 : INDEX5;
            case 3:
                return index == 0 ? INDEX1 : (index == 1 ? INDEX3 : INDEX5);
            case 4:
                return index == 0 ? INDEX1 : (index == 1 ? INDEX2 : (index == 2 ? INDEX4 : INDEX5));
            case 5:
                return values()[index];
            default:
                throw new IllegalArgumentException("Invalid option number.");
        }
    }
}
