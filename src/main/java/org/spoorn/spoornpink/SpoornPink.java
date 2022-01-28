package org.spoorn.spoornpink;

import lombok.extern.log4j.Log4j2;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spoorn.spoornpink.block.SpoornPinkBlocks;
import org.spoorn.spoornpink.config.ModConfig;
import org.spoorn.spoornpink.item.SpoornPinkItems;
import org.spoorn.spoornpink.world.biome.SpoornPinkBiomeProvider;
import org.spoorn.spoornpink.world.biome.SpoornPinkBiomeRegistry;
import terrablender.api.BiomeProviders;
import terrablender.api.TerraBlenderApi;

@Log4j2
public class SpoornPink implements ModInitializer, TerraBlenderApi {

    public static final String MODID = "spoornpink";
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier(MODID, "general"),
            () -> new ItemStack(SpoornPinkBlocks.CHERRY_LOG)
    );

    @Override
    public void onInitialize() {
        log.info("Hello from SpoornPink!");

        // Config
        ModConfig.init();

        // Blocks
        SpoornPinkBlocks.init();

        // Items
        SpoornPinkItems.init();

        // Register biome keys
        SpoornPinkBiomeRegistry.init();
    }

    @Override
    public void onTerraBlenderInitialized()
    {
        BiomeProviders.register(new SpoornPinkBiomeProvider(new Identifier(MODID, "biome_provider"), 1));
    }
}
