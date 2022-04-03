package org.spoorn.spoornpink.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.spoorn.spoornpink.SpoornPink;

public class SpoornPinkParticles {
    
    public static final DefaultParticleType PINK_BLOSSOM_PETALS = FabricParticleTypes.simple();
    
    public static void init() {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(SpoornPink.MODID, "pink_blossom_petal"), PINK_BLOSSOM_PETALS);
    }
}
