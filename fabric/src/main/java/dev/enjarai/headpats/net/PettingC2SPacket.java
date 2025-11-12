package dev.enjarai.headpats.net;

import dev.enjarai.headpats.Headpats;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record PettingC2SPacket(int entityId) implements CustomPayload {
    public static final Id<PettingC2SPacket> ID = new Id<>(Headpats.id("petting"));
    public static final PacketCodec<PacketByteBuf, PettingC2SPacket> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, PettingC2SPacket::entityId,
            PettingC2SPacket::new
    );

    public void handle(ServerPlayNetworking.Context context) {
        var serverPlayer = context.player(); // This is always ServerPlayerEntity on server
        if (entityId != -1) {
            var target = serverPlayer.getEntityWorld().getEntityById(entityId);
            // Only allow petting other ServerPlayerEntity instances
            if (target instanceof ServerPlayerEntity targetPlayer) {
                Headpats.PETTING_COMPONENT.get(serverPlayer).startPetting(targetPlayer);
            }
        } else {
            Headpats.PETTING_COMPONENT.get(serverPlayer).stopPetting();
        }
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
