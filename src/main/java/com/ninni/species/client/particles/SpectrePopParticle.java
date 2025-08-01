package com.ninni.species.client.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class SpectrePopParticle extends SimpleAnimatedParticle {
    private final SpriteSet spriteProvider;

    SpectrePopParticle(ClientLevel world, double x, double y, double z, SpriteSet spriteProvider) {
        super(world, x, y, z, spriteProvider, 0);
        this.spriteProvider = spriteProvider;
        this.lifetime = 10;
        this.scale(10f);
        this.setSpriteFromAge(spriteProvider);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.spriteProvider);
    }

    @Override
    public int getLightColor(float tint) {
        return 240;
    }

    @OnlyIn(Dist.CLIENT)
    public record Factory(SpriteSet spriteProvider) implements ParticleProvider<SimpleParticleType> {
        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType particleOptions, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i) {
            return new SpectrePopParticle(clientLevel, d, e, f, this.spriteProvider);
        }
    }
}