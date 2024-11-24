package com.github.anyfps.sodium.mixin;

import com.github.anyfps.Main;
import net.caffeinemc.mods.sodium.client.gui.SodiumGameOptionPages;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import static com.github.anyfps.Main.getMaxFps;

@Mixin(SodiumGameOptionPages.class)
public class SodiumGameOptionPagesMixin {
    // changes the max fps slider threshold, default 260
    @ModifyConstant(method = "lambda$general$19", constant = @Constant(intValue = 260), remap = false)
    private static int modifySliderMaxThreshold(int originalBound) {
        return getMaxFps();
    }

    // changes slider interval and minimum threshold, default 10
    @ModifyConstant(method = "lambda$general$19", constant = @Constant(intValue = 10), remap = false)
    private static int modifySliderInterval(int originalBound) {
        return Main.config.main.fpsLimitInterval;
    }
}
