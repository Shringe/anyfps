package com.github.anyfps.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import static com.github.anyfps.Main.getMaxFps;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    // This allows framerate limits above 260 to be honored
    @ModifyConstant(method = "render(Z)V", constant = @Constant(intValue = 260))
    private int modifyMaxFramerateCap(int originalMax) {
        return getMaxFps();
    }
}
