package org.spoorn.spoornpink.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.spoorn.spoornpink.SpoornPink;
import org.spoorn.spoornpink.block.sapling.SPSaplingGenerator;
import org.spoorn.spoornpink.world.gen.feature.SPConfiguredFeatures;
import org.spoorn.spoornpink.world.gen.feature.config.SPTreeConfig;

import java.util.ArrayList;
import java.util.List;

public class SpoornPinkBlocks {

    public static final List<Block> POTTED_BLOCKS = new ArrayList<>();

    // Logs
    public static final Block PINK_BLOSSOM_LOG = registerLog("pink_blossom_log");

    // Leaves
    public static final Block PINK_BLOSSOM_LEAVES = registerLeaves(MapColor.PINK,"pink_blossom_leaves");
    public static final Block DARK_PINK_BLOSSOM_LEAVES = registerLeaves(MapColor.PINK,"dark_pink_blossom_leaves");

    // Saplings
    public static final Block PINK_BLOSSOM_SAPLING = registerSapling("pink_blossom_sapling", SPConfiguredFeatures.PINK_BLOSSOM_TREE);

    public static void init() {

    }

    private static Block registerLog(String id) {
        Block block = new PillarBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0f).sounds(BlockSoundGroup.WOOD));
        return Registry.register(Registry.BLOCK, new Identifier(SpoornPink.MODID, id), block);
    }

    private static Block registerLeaves(MapColor color, String id) {
        Block block = new LeavesBlock(AbstractBlock.Settings.of(Material.LEAVES, color).strength(0.2f).ticksRandomly()
                .sounds(BlockSoundGroup.GRASS).nonOpaque().allowsSpawning(SpoornPinkBlocks::canSpawnOnLeaves)
                .suffocates((state, world, pos) -> false).blockVision((state, world, pos) -> false));
        return Registry.register(Registry.BLOCK, new Identifier(SpoornPink.MODID, id), block);
    }

    private static Block registerSapling(String id, ConfiguredFeature<SPTreeConfig, ?> configuredFeature) {
        Block saplingBlock = new SPSaplingBlock(new SPSaplingGenerator(configuredFeature), FabricBlockSettings.copyOf(Blocks.OAK_SAPLING));
        registerFlowerPot("potted_" + id, saplingBlock);
        return Registry.register(Registry.BLOCK, new Identifier(SpoornPink.MODID, id), saplingBlock);
    }

    private static void registerFlowerPot(String id, Block saplingBlock) {
        Block block = new FlowerPotBlock(saplingBlock, FabricBlockSettings.copyOf(Blocks.POTTED_OAK_SAPLING));
        POTTED_BLOCKS.add(Registry.register(Registry.BLOCK, new Identifier(SpoornPink.MODID, id), block));
    }

    private static Boolean canSpawnOnLeaves(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }
}
