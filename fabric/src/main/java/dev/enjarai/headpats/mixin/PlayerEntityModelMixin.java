package dev.enjarai.headpats.mixin;

import dev.enjarai.headpats.PetRendering;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityModel.class)
public class PlayerEntityModelMixin<T extends LivingEntityRenderState> extends BipedEntityModelMixin<T> {
    @Override
    protected void positionModelParts(BipedEntityRenderState bipedEntityRenderState, CallbackInfo ci) {
        PetRendering.setPetAngles(bipedEntityRenderState, rightArm, leftArm, head);
    }
}
