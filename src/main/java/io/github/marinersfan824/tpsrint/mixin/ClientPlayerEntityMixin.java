package io.github.marinersfan824.tpsrint.mixin;

import io.github.marinersfan824.tpsrint.ToggleSprint;
import net.minecraft.entity.player.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin {
    @Shadow public abstract void setSprinting(boolean sprinting);

    @Inject(method = "tickMovement", at = @At("HEAD"))
    public void sendSprintServer(CallbackInfo ci) {
        setSprinting(ToggleSprint.isSprinting);
    }
}
