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

    public static Item CHERRY_LOG_ITEM = registerBlockItem("cherry_log", SpoornPinkBlocks.CHERRY_LOG);

    public static Item PINK_CHERRY_LEAVES_ITEM = registerBlockItem("pink_cherry_leaves", SpoornPinkBlocks.PINK_CHERRY_LEAVES);

    public static Item PINK_CHERRY_SAPLING = registerSaplingItem("pink_cherry_sapling", SpoornPinkBlocks.PINK_CHERRY_SAPLING);


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
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put((ItemConvertible)item.asItem(), 0.3f);
    }
}
