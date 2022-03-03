package io.github.marinersfan824.tpsrint.mixin;

import io.github.marinersfan824.tpsrint.ToggleSprint;
import net.minecraft.client.input.KeyboardInput;
import net.minecraft.client.options.GameOptions;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardInput.class)
public class KeyboardInputMixin {
    @Shadow @Final private GameOptions options;

    @Inject(method = "<init>", at =@At("TAIL"))
    public void resetSprint(GameOptions options, CallbackInfo ci) {
        ToggleSprint.isSprinting = false;
    }
    @Inject(method = "method_1302", at = @At("TAIL"))
    public void setSprintClient(CallbackInfo ci) {
        if (this.options.keySprint.isPressed()) {
            if (ToggleSprint.toggleSprintCooldown >= 2) {
                ToggleSprint.toggleSprintCooldown = 0;
                ToggleSprint.isSprinting = !ToggleSprint.isSprinting;
            }
            ToggleSprint.toggleSprintCooldown++;
        }
    }
}
