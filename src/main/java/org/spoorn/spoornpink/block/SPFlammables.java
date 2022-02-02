package org.spoorn.spoornpink.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import org.spoorn.spoornpink.mixin.FireBlockAccessor;

public class SPFlammables {

    public static FireBlockAccessor FIRE_BLOCK = (FireBlockAccessor) Blocks.FIRE;

    public static void init() {
        registerFlammables();
    }

    private static void registerFlammables() {
        // Logs
        registerLog(SPBlocks.PINK_BLOSSOM_LOG);
        registerLog(SPBlocks.STRIPPED_PINK_BLOSSOM_LOG);

        // Wood
        registerWood(SPBlocks.PINK_BLOSSOM_WOOD);
        registerWood(SPBlocks.STRIPPED_PINK_BLOSSOM_WOOD);

        // Planks
        registerPlanks(SPBlocks.PINK_BLOSSOM_PLANKS);

        // Slabs
        registerSlab(SPBlocks.PINK_BLOSSOM_SLAB);

        // Stairs
        registerStairs(SPBlocks.PINK_BLOSSOM_STAIRS);

        // Leaves
        registerLeaves(SPBlocks.PINK_BLOSSOM_LEAVES);
        registerLeaves(SPBlocks.DARK_PINK_BLOSSOM_LEAVES);
    }

    private static void registerLog(Block block) {
        registerFlammableBlock(block, 5, 5);
    }

    private static void registerWood(Block block) {
        registerFlammableBlock(block, 5, 5);
    }

    private static void registerPlanks(Block block) {
        registerFlammableBlock(block, 5, 20);
    }

    private static void registerSlab(Block block) {
        registerFlammableBlock(block, 5, 20);
    }

    private static void registerStairs(Block block) {
        registerFlammableBlock(block, 5, 20);
    }

    private static void registerLeaves(Block block) {
        registerFlammableBlock(block, 30, 60);
    }

    private static void registerFlammableBlock(Block block, int burnChance, int spreadChance) {
        FIRE_BLOCK.registerSPFlammable(block, burnChance, spreadChance);
    }
}
