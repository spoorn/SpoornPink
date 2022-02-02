package org.spoorn.spoornpink.mixin;

import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spoorn.spoornpink.world.biome.core.SpoornPinkBiomeRegistry;

import java.util.function.BiConsumer;

@Mixin(ConfiguredStructureFeatures.class)
public class ConfiguredStructureFeaturesMixin {

    @Shadow @Final private static ConfiguredStructureFeature<StructurePoolFeatureConfig, ? extends StructureFeature<StructurePoolFeatureConfig>> VILLAGE_PLAINS;

    @Shadow @Final private static ConfiguredStructureFeature<MineshaftFeatureConfig, ? extends StructureFeature<MineshaftFeatureConfig>> MINESHAFT;

    @Shadow @Final private static ConfiguredStructureFeature<StructurePoolFeatureConfig, ? extends StructureFeature<StructurePoolFeatureConfig>> PILLAGER_OUTPOST;

    /**
     * Allow villages to spawn in my biomes.
     */
    @Inject(method = "registerAll", at = @At(value = "TAIL"))
    private static void addConfiguredStructureFeatures(BiConsumer<ConfiguredStructureFeature<?, ?>, RegistryKey<Biome>> registrar, CallbackInfo ci) {
        // TODO: Add different villages based on the biome type (i.e. forest village)
        for (RegistryKey<Biome> biomeKey : SpoornPinkBiomeRegistry.BIOMES.keySet()) {
            registrar.accept(VILLAGE_PLAINS, biomeKey);
            registrar.accept(MINESHAFT, biomeKey);
            registrar.accept(PILLAGER_OUTPOST, biomeKey);
        }
    }
}
