package dev.enjarai.headpats.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.enjarai.headpats.PetRendering;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {
    @Inject(
            method = "renderArm",
            at = @At("HEAD")
    )
    private void fixFunnyAnimation(CallbackInfo ci, @Local ModelPart arm) {
        PetRendering.fixFirstPersonAngles(MinecraftClient.getInstance().player, MinecraftClient.getInstance().getRenderTickCounter().getDynamicDeltaTicks(), arm);
    }
}
