package com.ninni.species.server.packet;

import com.ninni.species.mixin_util.LivingEntityAccess;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;

import java.util.Optional;
import java.util.function.Supplier;

public class TankedPacket {
    private final int entityId;
    private final boolean flag;

    public TankedPacket(int entityId, boolean flag) {
        this.entityId = entityId;
        this.flag = flag;
    }

    public static TankedPacket read(FriendlyByteBuf buf) {
        int entityId = buf.readInt();
        boolean flag = buf.readBoolean();
        return new TankedPacket(entityId, flag);
    }

    public static void write(TankedPacket packet, FriendlyByteBuf buf) {
        buf.writeInt(packet.entityId);
        buf.writeBoolean(packet.flag);
    }

    public static void handle(TankedPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Minecraft minecraft = Minecraft.getInstance();
            Optional.ofNullable(minecraft.level).ifPresent(world -> {
                int id = packet.getEntityId();
                Optional.ofNullable(minecraft.level.getEntity(id))
                        .filter(LivingEntity.class::isInstance)
                        .map(LivingEntityAccess.class::cast)
                        .ifPresent(entity -> {
                            boolean tanked = packet.getFlag();
                            entity.setTanked(tanked);
                        });
            });
        });
        ctx.get().setPacketHandled(true);
    }

    @OnlyIn(Dist.CLIENT)
    public int getEntityId() {
        return this.entityId;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean getFlag() {
        return this.flag;
    }

}
