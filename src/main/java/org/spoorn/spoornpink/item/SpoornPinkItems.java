package org.spoorn.spoornpink.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.spoorn.spoornpink.SpoornPink;
import org.spoorn.spoornpink.block.SpoornPinkBlocks;

public class SpoornPinkItems {

    // Logs
    public static Item PINK_BLOSSOM_LOG_ITEM = registerBlockItem("pink_blossom_log", SpoornPinkBlocks.PINK_BLOSSOM_LOG);

    // Leaves
    public static Item PINK_BLOSSOM_LEAVES_ITEM = registerBlockItem("pink_blossom_leaves", SpoornPinkBlocks.PINK_BLOSSOM_LEAVES);
    public static Item DARK_PINK_BLOSSOM_LEAVES_ITEM = registerBlockItem("dark_pink_blossom_leaves", SpoornPinkBlocks.DARK_PINK_BLOSSOM_LEAVES);

    // Saplings
    public static Item PINK_BLOSSOM_SAPLING = registerSaplingItem("pink_blossom_sapling", SpoornPinkBlocks.PINK_BLOSSOM_SAPLING);


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
