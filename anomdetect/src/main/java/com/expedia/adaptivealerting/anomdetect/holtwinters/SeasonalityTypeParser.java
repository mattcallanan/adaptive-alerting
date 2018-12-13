package com.expedia.adaptivealerting.anomdetect.holtwinters;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SeasonalityTypeParser {

    /**
     * Parse a string to its matching SeasonalityType
     *
     * @param text SeasonalityType in string format (e.g. "add")
     * @return SeasonalityType enum
     */
    public SeasonalityType parse(String text) {
        if (text == null) {
            return null;
        }
        Optional<SeasonalityType> match = findMatchingSeasonalityType(text);
        if (match.isEmpty()) {
            throw new RuntimeException(String.format("Failed to parse seasonality type \"%s\". Accepted values are %s", text, getValidNames()));
        }
        return match.get();
    }

    private Optional<SeasonalityType> findMatchingSeasonalityType(String text) {
        return Arrays.stream(SeasonalityType.values())
                .filter(
                        seasonalityType -> seasonalityType.getName().matches(text.toLowerCase())
                )
                .findFirst();
    }

    private List<String> getValidNames() {
        return Arrays.stream(SeasonalityType.values())
                .map(SeasonalityType::getName)
                .collect(Collectors.toList());
    }

}
