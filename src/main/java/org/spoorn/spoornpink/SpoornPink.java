package org.spoorn.spoornpink;

import lombok.extern.log4j.Log4j2;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import org.spoorn.spoornpacks.api.Resource;
import org.spoorn.spoornpacks.api.ResourceBuilder;
import org.spoorn.spoornpacks.api.ResourceFactory;
import org.spoorn.spoornpacks.core.generator.ResourceGenerator;
import org.spoorn.spoornpacks.exception.DuplicateNameException;
import org.spoorn.spoornpacks.registry.SpoornPacksRegistry;
import org.spoorn.spoornpacks.type.BlockType;
import org.spoorn.spoornpacks.type.ItemType;
import org.spoorn.spoornpink.config.ModConfig;
import org.spoorn.spoornpink.world.biome.core.SpoornPinkBiomeProvider;
import org.spoorn.spoornpink.world.biome.core.SpoornPinkBiomeRegistry;
import org.spoorn.spoornpink.world.gen.feature.SPConfiguredFeatures;
import org.spoorn.spoornpink.world.gen.feature.SPFeatures;
import org.spoorn.spoornpink.world.gen.feature.SPPlacedFeatures;
import org.spoorn.spoornpink.world.gen.feature.config.SPTreeConfig;
import terrablender.api.BiomeProviders;
import terrablender.api.TerraBlenderApi;

import java.util.Optional;

@Log4j2
public class SpoornPink implements ModInitializer, TerraBlenderApi {

    public static final String MODID = "spoornpink";
    private static ResourceGenerator RESOURCE_GENERATOR = SpoornPacksRegistry.registerResource("spoornpink");
    private static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier(MODID, "general"),
            ResourceFactory.fetchItemGroupSupplierFromBlock(MODID, "pink_blossom_sapling")
    );

    private static boolean configInitialized = false;

    @Override
    public void onInitialize() {
        log.info("Hello from SpoornPink!");

        // Config
        synchronized (this) {
            if (!configInitialized) {
                ModConfig.init();
                configInitialized = true;
            }
        }

        try {
            // Pink Blossom blocks and items
            String pink_blossom_name = "pink_blossom";
            ResourceBuilder rb = ResourceFactory.create(MODID, pink_blossom_name, ITEM_GROUP)
                    .addBlocks(BlockType.LOG, BlockType.WOOD, BlockType.PLANKS, BlockType.FENCE, BlockType.FENCE_GATE,
                            BlockType.LEAVES, BlockType.BUTTON, BlockType.SLAB, BlockType.PRESSURE_PLATE, BlockType.STAIRS,
                            BlockType.TRAPDOOR, BlockType.DOOR, BlockType.CRAFTING_TABLE, BlockType.STRIPPED_LOG, BlockType.STRIPPED_WOOD,
                            BlockType.CHEST)
                    .addItems(ItemType.LOG, ItemType.WOOD, ItemType.PLANKS, ItemType.FENCE, ItemType.FENCE_GATE,
                            ItemType.LEAVES, ItemType.BUTTON, ItemType.SLAB, ItemType.PRESSURE_PLATE, ItemType.STAIRS,
                            ItemType.TRAPDOOR, ItemType.DOOR, ItemType.CRAFTING_TABLE, ItemType.BOAT, ItemType.STRIPPED_LOG, ItemType.STRIPPED_WOOD,
                            ItemType.CHEST)
                    .addLeavesWithSaplingOverride("dark_pink_blossom", pink_blossom_name).addItem(ItemType.LEAVES, "dark_pink_blossom");

            Resource resource = RESOURCE_GENERATOR.generate(rb);
            
            Optional<Block> pinkBlossomLogBlock = resource.getBlock(BlockType.LOG, pink_blossom_name);
            Optional<Block> pinkBlossomLeavesBlock = resource.getBlock(BlockType.LEAVES, pink_blossom_name);
            Optional<Block> darkPinkBlossomLeavesBlock = resource.getBlock(BlockType.LEAVES, "dark_" + pink_blossom_name);
            
            if (pinkBlossomLeavesBlock.isEmpty() || pinkBlossomLogBlock.isEmpty() || darkPinkBlossomLeavesBlock.isEmpty()) {
                throw new RuntimeException("Could not generate pink_blossom_log or pink_blossom_leaves, or the dark variants");
            }

            // Trees
            SPTreeConfig pinkBlossomTreeConfig = SPTreeConfig.builder().trunkProvider(SimpleBlockStateProvider.of(pinkBlossomLogBlock.get()))
                    .leavesProvider(SimpleBlockStateProvider.of(pinkBlossomLeavesBlock.get()))
                    .build();
            ConfiguredFeature<SPTreeConfig, ?> pinkBlossomTree = SPConfiguredFeatures.registerSPTreeCF("pink_blossom_tree",
                    SPFeatures.PINK_BLOSSOM_TREE, pinkBlossomTreeConfig);
            SPTreeConfig darkPinkBlossomTreeConfig = SPTreeConfig.builder().trunkProvider(SimpleBlockStateProvider.of(pinkBlossomLogBlock.get()))
                    .leavesProvider(SimpleBlockStateProvider.of(darkPinkBlossomLeavesBlock.get()))
                    .build();
            ConfiguredFeature<SPTreeConfig, ?> darkPinkBlossomTree = SPConfiguredFeatures.registerSPTreeCF("dark_pink_blossom_tree",
                    SPFeatures.DARK_PINK_BLOSSOM_TREE, darkPinkBlossomTreeConfig);
            ConfiguredFeature<?, ?> pinkBlossomTrees = SPConfiguredFeatures.registerMixOfTrees("pink_blossom_trees", pinkBlossomTree, pinkBlossomTree, darkPinkBlossomTree);
            
            // Saplings
            ResourceBuilder saplingsRB = ResourceFactory.create(MODID, pink_blossom_name, ITEM_GROUP)
                    .addSapling(pinkBlossomTrees).addItem(ItemType.SAPLING);
            Resource saplingResource = RESOURCE_GENERATOR.generate(saplingsRB);
            
            Optional<Block> saplingBlock = saplingResource.getBlock(BlockType.SAPLING, pink_blossom_name);
            
            if (saplingBlock.isEmpty()) {
                throw new RuntimeException("Could not generate " + pink_blossom_name + " sapling");
            }
            
            // Placed Features
            SPPlacedFeatures.registerTree(pink_blossom_name + "_trees", pinkBlossomTrees, saplingBlock.get());
        } catch (DuplicateNameException e) {
            log.error("Duplicate names", e);
            throw new RuntimeException(e);
        }

        // Register biome keys
        SpoornPinkBiomeRegistry.init();
    }

    @Override
    public void onTerraBlenderInitialized() {
        // TerraBlender may be initialized before this mod or in parallel
        synchronized (this) {
            if (!configInitialized) {
                ModConfig.init();
                configInitialized = true;
            }
        }
        BiomeProviders.register(new SpoornPinkBiomeProvider(new Identifier(MODID, "biome_provider"), ModConfig.get().overworldWeight));
    }
}
