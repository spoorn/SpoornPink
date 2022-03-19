package org.spoorn.spoornpink.mixin;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spoorn.spoornpink.world.biome.core.SpoornPinkBiomeRegistry;

import java.util.function.BiConsumer;

@Mixin(ConfiguredStructureFeatures.class)
public abstract class ConfiguredStructureFeaturesMixin {
    
    @Shadow @Final public static RegistryEntry<ConfiguredStructureFeature<?, ?>> VILLAGE_PLAINS;
    @Shadow @Final public static RegistryEntry<ConfiguredStructureFeature<?, ?>> MINESHAFT;
    @Shadow @Final public static RegistryEntry<ConfiguredStructureFeature<?, ?>> PILLAGER_OUTPOST;

   // @Shadow protected abstract <FC extends FeatureConfig, F extends StructureFeature<FC>> RegistryEntry<ConfiguredStructureFeature<?, ?>> register(RegistryKey<ConfiguredStructureFeature<?, ?>> key, ConfiguredStructureFeature<FC, F> configuredStructureFeature);

    private static boolean registered = false;

    /**
     * Allow villages to spawn in my biomes.
     */
    /*@Inject(method = "register", at = @At(value = "TAIL"))
    private <FC extends FeatureConfig, F extends StructureFeature<FC>> void addConfiguredStructureFeatures(RegistryKey<ConfiguredStructureFeature<?, ?>> key, ConfiguredStructureFeature<FC, F> configuredStructureFeature, CallbackInfoReturnable<RegistryEntry<ConfiguredStructureFeature<?, ?>>> cir) {
        // TODO: Add different villages based on the biome type (i.e. forest village)
        if (!registered) {
            registered = true;
            for (RegistryKey<Biome> biomeKey : SpoornPinkBiomeRegistry.BIOMES.keySet()) {
                register(biomeKey, VILLAGE_PLAINS);
                register(biomeKey, MINESHAFT);
                register(biomeKey, PILLAGER_OUTPOST);
            }
        }
    }*/
}
