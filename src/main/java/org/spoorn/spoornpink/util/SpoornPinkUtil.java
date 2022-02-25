package org.spoorn.spoornpink.util;

import lombok.AllArgsConstructor;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import org.spoorn.spoornpacks.api.Resource;
import org.spoorn.spoornpacks.type.BlockType;
import org.spoorn.spoornpacks.type.ItemType;

import java.util.Optional;

public final class SpoornPinkUtil {

    /**
     * Contains all data necessary to construct a {@link terrablender.worldgen.TBClimate.ParameterPoint} aside from
     * the uniqueness parameter.
     */
    @AllArgsConstructor
    public static class ParameterPointData {
        public float temperatureMin;
        public float temperatureMax;
        public float humidityMin;
        public float humidityMax;
        public float continentalnessMin;
        public float continentalnessMax;
        public float erosionMin;
        public float erosionMax;
        public float weirdnessMin;
        public float weirdnessMax;
        public float depth;
        public float offset;
    }

    public static ParameterPointData constructParameterPoint(float temperatureMin, float temperatureMax, float humidityMin, float humidityMax, float continentalnessMin,
            float continentalnessMax, float erosionMin, float erosionMax, float weirdnessMin, float weirdnessMax, float depth, float offset) {
        return new ParameterPointData(temperatureMin, temperatureMax, humidityMin, humidityMax, continentalnessMin, continentalnessMax, erosionMin,
                erosionMax, weirdnessMin, weirdnessMax, depth, offset);
    }
    
    public static Block getBlockFromResource(Resource resource, BlockType type, String name) {
        Optional<Block> block = resource.getBlock(type, name);
        if (block.isEmpty()) {
            throw new RuntimeException("Could not find block of type=" + type + ", name=" + name + " in generated resources");
        }
        return block.get();
    }

    public static Item getItemFromResource(Resource resource, ItemType type, String name) {
        Optional<Item> block = resource.getItem(type, name);
        if (block.isEmpty()) {
            throw new RuntimeException("Could not find item of type=" + type + ", name=" + name + " in generated resources");
        }
        return block.get();
    }
}
