package com.github.anyfps.sodium.mixin;

import com.github.anyfps.Main;
import net.caffeinemc.mods.sodium.client.gui.SodiumGameOptionPages;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import java.lang.Math;

@Mixin(SodiumGameOptionPages.class)
public class SodiumGameOptionPagesMixin {
    // changes the max fps slider threshold, default 260
    @ModifyConstant(method = "lambda$general$19", constant = @Constant(intValue = 260), remap = false)
    private static int modifySliderMaxThreshold(int originalBound) {
        return (int) Math.ceil((double) Main.config.main.fpsLimitMax / Main.config.main.fpsLimitInterval) * Main.config.main.fpsLimitInterval;
    }

    // changes slider interval and minimum threshold, default 10
    @ModifyConstant(method = "lambda$general$19", constant = @Constant(intValue = 10), remap = false)
    private static int modifySliderInterval(int originalBound) {
        return Main.config.main.fpsLimitInterval;
    }
}
