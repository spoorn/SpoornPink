package org.spoorn.spoornpink.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.spoorn.spoornpink.SpoornPink;
import org.spoorn.spoornpink.block.SPBlocks;

public class SPItems {

    // Logs
    public static Item PINK_BLOSSOM_LOG_ITEM = registerBlockItem("pink_blossom_log", SPBlocks.PINK_BLOSSOM_LOG);
    public static Item STRIPPED_PINK_BLOSSOM_LOG_ITEM = registerBlockItem("stripped_pink_blossom_log", SPBlocks.STRIPPED_PINK_BLOSSOM_LOG);

    // Wood
    public static Item PINK_BLOSSOM_WOOD = registerBlockItem("pink_blossom_wood", SPBlocks.PINK_BLOSSOM_WOOD);
    public static Item STRIPPED_PINK_BLOSSOM_WOOD = registerBlockItem("stripped_pink_blossom_wood", SPBlocks.STRIPPED_PINK_BLOSSOM_WOOD);

    // Leaves
    public static Item PINK_BLOSSOM_LEAVES_ITEM = registerBlockItem("pink_blossom_leaves", SPBlocks.PINK_BLOSSOM_LEAVES);
    public static Item DARK_PINK_BLOSSOM_LEAVES_ITEM = registerBlockItem("dark_pink_blossom_leaves", SPBlocks.DARK_PINK_BLOSSOM_LEAVES);

    // Saplings
    public static Item PINK_BLOSSOM_SAPLING = registerSaplingItem("pink_blossom_sapling", SPBlocks.PINK_BLOSSOM_SAPLING);

    // Planks
    public static Item PINK_BLOSSOM_PLANKS = registerBlockItem("pink_blossom_planks", SPBlocks.PINK_BLOSSOM_PLANKS);

    // Slabs
    public static Item PINK_BLOSSOM_SLAB = registerBlockItem("pink_blossom_slab", SPBlocks.PINK_BLOSSOM_SLAB);

    // Stairs
    public static Item PINK_BLOSSOM_STAIRS = registerBlockItem("pink_blossom_stairs", SPBlocks.PINK_BLOSSOM_STAIRS);

    // Pressure Plates
    public static Item PINK_BLOSSOM_PRESSURE_PLATE = registerBlockItem("pink_blossom_pressure_plate", SPBlocks.PINK_BLOSSOM_PRESSURE_PLATE);

    // Buttons
    public static Item PINK_BLOSSOM_BUTTON = registerBlockItem("pink_blossom_button", SPBlocks.PINK_BLOSSOM_BUTTON);

    // Doors
    public static Item PINK_BLOSSOM_DOOR = registerBlockItem("pink_blossom_door", SPBlocks.PINK_BLOSSOM_DOOR);

    // Fences
    public static Item PINK_BLOSSOM_FENCE = registerBlockItem("pink_blossom_fence", SPBlocks.PINK_BLOSSOM_FENCE);
    public static Item PINK_BLOSSOM_FENCE_GATE = registerBlockItem("pink_blossom_fence_gate", SPBlocks.PINK_BLOSSOM_FENCE_GATE);

    // Trapdoors
    public static Item PINK_BLOSSOM_TRAPDOOR = registerBlockItem("pink_blossom_trapdoor", SPBlocks.PINK_BLOSSOM_TRAPDOOR);


    public static void init() {

    }

    private static Item registerBlockItem(String id, Block block) {
        return Registry.register(Registry.ITEM, new Identifier(SpoornPink.MODID, id), new BlockItem(block, new FabricItemSettings().group(SpoornPink.ITEM_GROUP)));
    }

    private static Item registerSaplingItem(String id, Block block) {
        Item item = Registry.register(Registry.ITEM, new Identifier(SpoornPink.MODID, id), new BlockItem(block, new FabricItemSettings().group(SpoornPink.ITEM_GROUP)));
        registerCompostable(item);
        return item;
    }

    // Update vanilla's composter block
    private static void registerCompostable(Item item) {
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(item.asItem(), 0.3f);
    }
}
