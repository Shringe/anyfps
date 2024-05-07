package com.github.anyfps.sodium.mixin;

import com.github.anyfps.Main;
import me.jellysquid.mods.sodium.client.gui.SodiumGameOptionPages;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(SodiumGameOptionPages.class)
public class SodiumGameOptionPagesMixin {
    // changes the max fps slider threshold, default 260
    @ModifyConstant(method = "lambda$general$14(Lme/jellysquid/mods/sodium/client/gui/options/OptionImpl;)Lme/jellysquid/mods/sodium/client/gui/options/control/Control;", constant = @Constant(intValue = 260), remap = false)
    private static int modifySliderMaxThreshold(int originalBound) {
        return Main.config.main.fpsLimitMax;
    }

    // changes slider interval and minimum threshold, default 10
    @ModifyConstant(method = "lambda$general$14(Lme/jellysquid/mods/sodium/client/gui/options/OptionImpl;)Lme/jellysquid/mods/sodium/client/gui/options/control/Control;", constant = @Constant(intValue = 10), remap = false)
    private static int modifySliderInterval(int originalBound) {
        return Main.config.main.fpsLimitInterval;
    }
}
