package org.spoorn.spoornpink.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.spoorn.spoornpink.SpoornPink;
import org.spoorn.spoornpink.block.SpoornPinkBlocks;

public class SpoornPinkItems {

    public static Item CHERRY_LOG_ITEM = registerBlockItem("cherry_log", SpoornPinkBlocks.CHERRY_LOG);

    public static void init() {

    }

    private static Item registerBlockItem(String id, Block block) {
        return Registry.register(Registry.ITEM, new Identifier(SpoornPink.MODID, id), new BlockItem(block, new FabricItemSettings().group(SpoornPink.ITEM_GROUP)));
    }
}
