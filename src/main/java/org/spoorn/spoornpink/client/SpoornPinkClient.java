package org.spoorn.spoornpink.client;

import lombok.extern.log4j.Log4j2;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import org.spoorn.spoornpink.SpoornPink;
import org.spoorn.spoornpink.client.particle.PetalParticle;
import org.spoorn.spoornpink.particle.SpoornPinkParticles;

@Log4j2
@Environment(EnvType.CLIENT)
public class SpoornPinkClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        log.info("Hello client from SpoornPink!");

        // Particles
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((atlasTexture, registry) -> {
            registry.register(new Identifier(SpoornPink.MODID, "particle/pink_blossom_petal"));
        }));

        ParticleFactoryRegistry.getInstance().register(SpoornPinkParticles.PINK_BLOSSOM_PETALS, PetalParticle.Factory::new);
    }
}
