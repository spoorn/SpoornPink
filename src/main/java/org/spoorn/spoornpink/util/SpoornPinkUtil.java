package org.spoorn.spoornpink.util;

import lombok.AllArgsConstructor;

public final class SpoornPinkUtil {

    /**
     * Contains all data necessary to construct a {@link terrablender.worldgen.TBClimate.ParameterPoint} aside from
     * the uniqueness parameter.
     */
    @AllArgsConstructor
    public static class ParameterPointData {
        public float temperature;
        public float humidity;
        public float continentalnessMin;
        public float continentalnessMax;
        public float erosionMin;
        public float erosionMax;
        public float weirdnessMin;
        public float weirdnessMax;
        public float depth;
        public float offset;
    }

    public static ParameterPointData constructParameterPoint(float temperature, float humidity, float continentalnessMin,
            float continentalnessMax, float erosionMin, float erosionMax, float weirdnessMin, float weirdnessMax, float depth, float offset) {
        return new ParameterPointData(temperature, humidity, continentalnessMin, continentalnessMax, erosionMin,
                erosionMax, weirdnessMin, weirdnessMax, depth, offset);
    }
}
