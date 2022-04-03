package org.spoorn.spoornpink.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(EnvType.CLIENT)
public class PetalParticle extends SpriteBillboardParticle {
    
    private static final float FADE_IN = 10;
    private static final float FADE_AWAY = 100;

    PetalParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        super(world, x, y - 0.125, z, velocityX, velocityY, velocityZ);
        this.setBoundingBoxSpacing(0.01f, 0.01f);
        this.scale *= this.random.nextFloat() * 0.2f + 0.4f;
        this.maxAge = (int) (Math.random() * 1000) + 500;
        this.collidesWithWorld = true;
        this.velocityMultiplier = 1.0f;
        this.gravityStrength = 0.01f;
        this.alpha = 0.0f;
    }

    @Override
    public void tick() {
        super.tick();
        this.prevAngle = this.angle;
        // rotate
        if(!this.onGround) {
            this.angle = (float) Math.cos(this.age * 0.1f);
        }
        // fade in
        if (this.age < FADE_IN) {
            this.alpha = this.age / FADE_IN;
        }
        // fade away
        if (this.onGround || (this.age + FADE_AWAY >= this.maxAge)) {
            this.alpha = (this.maxAge - this.age) / FADE_AWAY;
        }
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(value=EnvType.CLIENT)
    public static class Factory
            implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            PetalParticle petalParticle = new PetalParticle(clientWorld, d, e, f, g, -0.8f, i);
            petalParticle.setSprite(this.spriteProvider);
            return petalParticle;
        }
    }
}
