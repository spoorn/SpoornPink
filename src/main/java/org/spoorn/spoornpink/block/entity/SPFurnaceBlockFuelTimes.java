package org.spoorn.spoornpink.block.entity;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.ItemConvertible;
import org.spoorn.spoornpink.item.SPItems;

/**
 * Furnace fuel times in ticks for various items.
 */
public class SPFurnaceBlockFuelTimes {

    public static void init() {
        registerFurnaceBlockFuelTimes();
    }

    private static void registerFurnaceBlockFuelTimes() {

        // Fences
        registerFence(SPItems.PINK_BLOSSOM_FENCE);
        registerFence(SPItems.PINK_BLOSSOM_FENCE_GATE);

        // Crafting tables
        registerCraftingTable(SPItems.PINK_BLOSSOM_CRAFTING_TABLE);
    }

    private static void registerFence(ItemConvertible item) {
        FuelRegistry.INSTANCE.add(item, 300);
    }

    private static void registerCraftingTable(ItemConvertible item) {
        FuelRegistry.INSTANCE.add(item, 300);
    }
}
