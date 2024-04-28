package com.github.anyfps.sodium.mixin;

import me.jellysquid.mods.sodium.client.gui.SodiumGameOptionPages;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(SodiumGameOptionPages.class)
public class SodiumGameOptionPagesMixin {
    private final static int MAX_FPS = 520;

    @ModifyConstant(method = "lambda$general$14(Lme/jellysquid/mods/sodium/client/gui/options/OptionImpl;)Lme/jellysquid/mods/sodium/client/gui/options/control/Control;", constant = @Constant(intValue = 260), remap = false)
    private static int modifySliderMaxThreshold(int originalMax) {
        return MAX_FPS;
    }
}
