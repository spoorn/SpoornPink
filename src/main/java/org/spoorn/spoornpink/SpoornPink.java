package org.spoorn.spoornpink;

import lombok.extern.log4j.Log4j2;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.spoorn.spoornpink.config.ModConfig;
import org.spoorn.spoornpink.world.biome.SpoornPinkBiomeProvider;
import org.spoorn.spoornpink.world.biome.SpoornPinkBiomeRegistry;
import terrablender.api.BiomeProviders;
import terrablender.api.TerraBlenderApi;

@Log4j2
public class SpoornPink implements ModInitializer, TerraBlenderApi {

    public static final String MODID = "spoornpink";

    @Override
    public void onInitialize() {
        log.info("Hello from SpoornPink!");

        // Config
        ModConfig.init();

        // Register biome keys
        SpoornPinkBiomeRegistry.init();
    }

    @Override
    public void onTerraBlenderInitialized()
    {
        BiomeProviders.register(new SpoornPinkBiomeProvider(new Identifier(MODID, "biome_provider"), 1));
    }
}
