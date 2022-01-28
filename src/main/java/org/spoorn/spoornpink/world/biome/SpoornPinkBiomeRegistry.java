package org.spoorn.spoornpink.world.biome;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import org.spoorn.spoornpink.SpoornPink;

public class SpoornPinkBiomeRegistry {

    public static final RegistryKey<Biome> KIKO_FOREST = registerBiomeKey("kiko_forest");

    public static void init() {
        register(KIKO_FOREST, SpoornPinkOverworldBiomeCreator.kikoForest());
    }

    private static Biome register(RegistryKey<Biome> key, Biome biome) {
        return BuiltinRegistries.set(BuiltinRegistries.BIOME, key, biome);
    }

    private static RegistryKey<Biome> registerBiomeKey(String name) {
        return RegistryKey.of(Registry.BIOME_KEY, new Identifier(SpoornPink.MODID, name));
    }
}
