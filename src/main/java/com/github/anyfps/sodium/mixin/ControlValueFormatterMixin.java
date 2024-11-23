package com.github.anyfps.sodium.mixin;

import net.caffeinemc.mods.sodium.client.gui.options.control.ControlValueFormatter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import static com.github.anyfps.Main.getMaxFps;

@Mixin(ControlValueFormatter.class)
public interface ControlValueFormatterMixin {
    // Sets the point where the fps will say "Unlimited", default 260
    @ModifyConstant(method = "lambda$fpsLimit$2(I)Lnet/minecraft/text/Text;", constant = @Constant(intValue = 260), remap = true)
    private static int modifySliderTextMaxThreshold(int originalPoint) {
        return getMaxFps();
    }
}
