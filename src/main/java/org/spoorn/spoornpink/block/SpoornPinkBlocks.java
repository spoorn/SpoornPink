package org.spoorn.spoornpink.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import org.spoorn.spoornpink.SpoornPink;

public class SpoornPinkBlocks {

    public static final Block CHERRY_LOG = registerLog("cherry_log");

    public static final Block PINK_CHERRY_LEAVES = registerLeaves(MapColor.PINK,"pink_cherry_leaves");


    public static void init() {

    }

    public static Block registerLog(String id) {
        Block block = new PillarBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0f).sounds(BlockSoundGroup.WOOD));
        return Registry.register(Registry.BLOCK, new Identifier(SpoornPink.MODID, id), block);
    }

    public static Block registerLeaves(MapColor color, String id) {
        Block block = new LeavesBlock(AbstractBlock.Settings.of(Material.LEAVES, color).strength(0.2f).ticksRandomly()
                .sounds(BlockSoundGroup.GRASS).nonOpaque().allowsSpawning(SpoornPinkBlocks::canSpawnOnLeaves)
                .suffocates((state, world, pos) -> false).blockVision((state, world, pos) -> false));
        return Registry.register(Registry.BLOCK, new Identifier(SpoornPink.MODID, id), block);
    }

    private static Boolean canSpawnOnLeaves(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }
}
