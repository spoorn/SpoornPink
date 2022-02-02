package org.spoorn.spoornpink;

import lombok.extern.log4j.Log4j2;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spoorn.spoornpink.block.SPBlocks;
import org.spoorn.spoornpink.block.SPFlammables;
import org.spoorn.spoornpink.config.ModConfig;
import org.spoorn.spoornpink.item.SPStrippedBlocks;
import org.spoorn.spoornpink.item.SpoornPinkItems;
import org.spoorn.spoornpink.world.biome.core.SpoornPinkBiomeProvider;
import org.spoorn.spoornpink.world.biome.core.SpoornPinkBiomeRegistry;
import org.spoorn.spoornpink.world.gen.feature.SPConfiguredFeatures;
import org.spoorn.spoornpink.world.gen.feature.SPFeatures;
import org.spoorn.spoornpink.world.gen.feature.SPPlacedFeatures;
import terrablender.api.BiomeProviders;
import terrablender.api.TerraBlenderApi;

@Log4j2
public class SpoornPink implements ModInitializer, TerraBlenderApi {

    public static final String MODID = "spoornpink";
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier(MODID, "general"),
            () -> new ItemStack(SPBlocks.PINK_BLOSSOM_SAPLING)
    );

    @Override
    public void onInitialize() {
        log.info("Hello from SpoornPink!");

        // Config
        ModConfig.init();

        // Blocks
        SPBlocks.init();

        // Items
        SpoornPinkItems.init();

        // Stripped blocks
        SPStrippedBlocks.init();

        // Register features
        SPFeatures.init();
        SPConfiguredFeatures.init();
        SPPlacedFeatures.init();

        // Register biome keys
        SpoornPinkBiomeRegistry.init();

        // Flammables
        SPFlammables.init();
    }

    @Override
    public void onTerraBlenderInitialized()
    {
        BiomeProviders.register(new SpoornPinkBiomeProvider(new Identifier(MODID, "biome_provider"), ModConfig.get().overworldWeight));
    }
}
