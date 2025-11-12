package dev.enjarai.headpats.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.enjarai.headpats.PetRendering;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {
    @Inject(
            method = "renderFirstPersonItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;isEmpty()Z"
            )
    )
    private void pettingHand(CallbackInfo ci, 
                             @Local AbstractClientPlayerEntity player, 
                             @Local(ordinal = 0) float tickDelta, 
                             @Local Hand hand, 
                             @Local MatrixStack matrices) {
        if (hand == Hand.MAIN_HAND && player != null) {
            PetRendering.modifyHandMatrix(player, tickDelta, matrices);
        }
    }
}
