package org.spoorn.spoornpink.block.entity;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.ItemConvertible;
import org.spoorn.spoornpink.item.SPItems;

public class SPFurnaceBlockFuelTimes {

    public static void init() {
        registerFurnaceBlockFuelTimes();
    }

    private static void registerFurnaceBlockFuelTimes() {

        // Fences
        registerFence(SPItems.PINK_BLOSSOM_FENCE);
    }

    private static void registerFence(ItemConvertible item) {
        FuelRegistry.INSTANCE.add(item, 300);
    }
}
