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
import org.spoorn.spoornpink.mixin.DoorBlockAccessor;
import org.spoorn.spoornpink.mixin.PressurePlateBlockAccess;
import org.spoorn.spoornpink.mixin.StairsBlockAccess;
import org.spoorn.spoornpink.mixin.WoodenButtonBlockAccessor;
import org.spoorn.spoornpink.world.gen.feature.SPConfiguredFeatures;
import org.spoorn.spoornpink.world.gen.feature.config.SPTreeConfig;

import java.util.ArrayList;
import java.util.List;

public class SPBlocks {

    public static final List<Block> SAPLINGS = new ArrayList<>();
    public static final List<Block> POTTED_BLOCKS = new ArrayList<>();
    public static final List<Block> DOOR_BLOCKS = new ArrayList<>();
    public static final List<Block> FENCES = new ArrayList<>();
    public static final List<Block> FENCE_GATES = new ArrayList<>();

    // Logs
    public static final Block PINK_BLOSSOM_LOG = registerLog("pink_blossom_log");
    public static final Block STRIPPED_PINK_BLOSSOM_LOG = registerLog("stripped_pink_blossom_log");

    // Wood
    public static final Block PINK_BLOSSOM_WOOD = registerWood("pink_blossom_wood");
    public static final Block STRIPPED_PINK_BLOSSOM_WOOD = registerWood("stripped_pink_blossom_wood");

    // Leaves
    public static final Block PINK_BLOSSOM_LEAVES = registerLeaves(MapColor.PINK,"pink_blossom_leaves");
    public static final Block DARK_PINK_BLOSSOM_LEAVES = registerLeaves(MapColor.PINK,"dark_pink_blossom_leaves");

    // Saplings
    public static final Block PINK_BLOSSOM_SAPLING = registerSapling("pink_blossom_sapling", SPConfiguredFeatures.PINK_BLOSSOM_TREE);

    // Planks
    public static final Block PINK_BLOSSOM_PLANKS = registerPlanks("pink_blossom_planks");

    // Slabs
    public static final Block PINK_BLOSSOM_SLAB = registerSlab("pink_blossom_slab");

    // Stairs
    public static final Block PINK_BLOSSOM_STAIRS = registerStairs("pink_blossom_stairs", PINK_BLOSSOM_PLANKS);

    // Pressure Plates
    public static final Block PINK_BLOSSOM_PRESSURE_PLATE = registerPressurePlate("pink_blossom_pressure_plate");

    // Buttons
    public static final Block PINK_BLOSSOM_BUTTON = registerButton("pink_blossom_button");

    // Doors
    public static final Block PINK_BLOSSOM_DOOR = registerDoor("pink_blossom_door");

    // Fences
    public static final Block PINK_BLOSSOM_FENCE = registerFence("pink_blossom_fence");
    public static final Block PINK_BLOSSOM_FENCE_GATE = registerFenceGate("pink_blossom_fence_gate");

    public static void init() {

    }

    private static Block registerLog(String id) {
        Block block = new PillarBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0f).sounds(BlockSoundGroup.WOOD));
        return Registry.register(Registry.BLOCK, new Identifier(SpoornPink.MODID, id), block);
    }

    private static Block registerWood(String id) {
        Block block = new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD));
        return Registry.register(Registry.BLOCK, new Identifier(SpoornPink.MODID, id), block);
    }

    private static Block registerLeaves(MapColor color, String id) {
        Block block = new LeavesBlock(AbstractBlock.Settings.of(Material.LEAVES, color).strength(0.2f).ticksRandomly()
                .sounds(BlockSoundGroup.GRASS).nonOpaque().allowsSpawning(SPBlocks::canSpawnOnLeaves)
                .suffocates((state, world, pos) -> false).blockVision((state, world, pos) -> false));
        return Registry.register(Registry.BLOCK, new Identifier(SpoornPink.MODID, id), block);
    }

    private static Block registerSapling(String id, ConfiguredFeature<SPTreeConfig, ?> configuredFeature) {
        Block saplingBlock = new SPSaplingBlock(new SPSaplingGenerator(configuredFeature), FabricBlockSettings.copyOf(Blocks.OAK_SAPLING));
        SAPLINGS.add(saplingBlock);
        registerFlowerPot("potted_" + id, saplingBlock);
        return Registry.register(Registry.BLOCK, new Identifier(SpoornPink.MODID, id), saplingBlock);
    }

    private static void registerFlowerPot(String id, Block saplingBlock) {
        Block block = new FlowerPotBlock(saplingBlock, FabricBlockSettings.copyOf(Blocks.POTTED_OAK_SAPLING));
        POTTED_BLOCKS.add(Registry.register(Registry.BLOCK, new Identifier(SpoornPink.MODID, id), block));
    }

    private static Block registerPlanks(String id) {
        Block block = new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS));
        return Registry.register(Registry.BLOCK, new Identifier(SpoornPink.MODID, id), block);
    }

    private static Block registerSlab(String id) {
        Block block = new SlabBlock(FabricBlockSettings.copyOf(Blocks.OAK_SLAB));
        return Registry.register(Registry.BLOCK, new Identifier(SpoornPink.MODID, id), block);
    }

    private static Block registerStairs(String id, Block defaultBlock) {
        Block block = StairsBlockAccess.create(defaultBlock.getDefaultState(), FabricBlockSettings.copyOf(defaultBlock));
        return Registry.register(Registry.BLOCK, new Identifier(SpoornPink.MODID, id), block);
    }

    private static Block registerPressurePlate(String id) {
        Block block = PressurePlateBlockAccess.create(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copyOf(Blocks.OAK_PRESSURE_PLATE));
        return Registry.register(Registry.BLOCK, new Identifier(SpoornPink.MODID, id), block);
    }

    private static Block registerButton(String id) {
        Block block = WoodenButtonBlockAccessor.create(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON));
        return Registry.register(Registry.BLOCK, new Identifier(SpoornPink.MODID, id), block);
    }

    private static Block registerDoor(String id) {
        Block block = DoorBlockAccessor.create(FabricBlockSettings.copyOf(Blocks.OAK_DOOR));
        DOOR_BLOCKS.add(block);
        return Registry.register(Registry.BLOCK, new Identifier(SpoornPink.MODID, id), block);
    }

    private static Block registerFence(String id) {
        Block block = new FenceBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE));
        FENCES.add(block);
        return Registry.register(Registry.BLOCK, new Identifier(SpoornPink.MODID, id), block);
    }

    private static Block registerFenceGate(String id) {
        Block block = new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE_GATE));
        FENCE_GATES.add(block);
        return Registry.register(Registry.BLOCK, new Identifier(SpoornPink.MODID, id), block);
    }

    private static Boolean canSpawnOnLeaves(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }
}
