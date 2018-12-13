package com.expedia.adaptivealerting.anomdetect.holtwinters;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public enum SeasonalityType {
    ADDITIVE((byte) 0, "additive"), MULTIPLICATIVE((byte) 1, "multiplicative");

    private final byte id;
    private final String name;

    SeasonalityType(byte id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Return the english-formatted name of the SeasonalityType
     *
     * @return English representation of SeasonalityType
     */
    public String getName() {
        return this.name;
    }

    /**
     * Serialize the SeasonalityType to the output stream
     */
    public void writeTo(OutputStream out) throws IOException {
        out.write(id);
    }

    /**
     * Deserialize the SeasonalityType from the input stream
     *
     * @param in the input stream
     * @return SeasonalityType Enum
     */
    public static SeasonalityType readFrom(InputStream in) throws IOException {
        byte id = (byte) in.read();
        for (SeasonalityType seasonalityType : values()) {
            if (id == seasonalityType.id) {
                return seasonalityType;
            }
        }
        throw new IllegalStateException("Unknown Seasonality Type with id " + id + "");
    }

}
