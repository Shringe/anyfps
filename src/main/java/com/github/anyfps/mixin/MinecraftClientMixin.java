package com.github.anyfps.mixin;

import com.github.anyfps.Main;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    // This allows framerate limits above 260 to be honored
    @ModifyConstant(method = "render(Z)V", constant = @Constant(intValue = 260))
    private int modifyMaxFramerateCap(int originalMax) {
        return Main.config.main.fpsLimitMax;
    }
}
