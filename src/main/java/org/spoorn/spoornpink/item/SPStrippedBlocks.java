package org.spoorn.spoornpink.item;

import net.minecraft.block.Block;
import org.spoorn.spoornpink.block.SPBlocks;
import org.spoorn.spoornpink.mixin.AxeItemAccessor;

import java.util.IdentityHashMap;
import java.util.Map;

public class SPStrippedBlocks {

    public static void init() {
        registerStrippedLogs();
    }

    private static void registerStrippedLogs() {
        Map<Block, Block> strippedBlocks = new IdentityHashMap<>(AxeItemAccessor.getStrippedBlocks());

        strippedBlocks.put(SPBlocks.PINK_BLOSSOM_LOG, SPBlocks.STRIPPED_PINK_BLOSSOM_LOG);
        strippedBlocks.put(SPBlocks.PINK_BLOSSOM_WOOD, SPBlocks.STRIPPED_PINK_BLOSSOM_WOOD);

        AxeItemAccessor.setStrippedBlocks(strippedBlocks);
    }
}
