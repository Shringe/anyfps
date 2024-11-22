package com.github.anyfps.mixin;

import com.github.anyfps.Main;
import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;

import static com.github.anyfps.Main.getMaxFps;

@Mixin(GameOptions.class)
public class GameOptionsMixin {
    // The point at which the options slider shows "Unlimited", default 260
    @ModifyConstant(method = "method_42559(Lnet/minecraft/text/Text;Ljava/lang/Integer;)Lnet/minecraft/text/Text;", constant = @Constant(intValue = 260))
    private static int sliderTextMaxThreshold(int originalBound) {
        return getMaxFps() / Main.config.main.fpsLimitInterval;
    }

    // The upper limit of the options slider, default 260
    @ModifyArg(
            method = "<init>",
            index = 1,
            at = @At(
                value = "INVOKE",
                target = "net/minecraft/client/option/SimpleOption$ValidatingIntSliderCallbacks.<init> (II)V",
                ordinal = 1
            ),
            slice = @Slice(
                from = @At(value = "INVOKE", target = "net/minecraft/client/option/SimpleOption.emptyTooltip ()Lnet/minecraft/client/option/SimpleOption$TooltipFactory;"),
                to = @At(value = "INVOKE", target = "java/lang/Integer.valueOf (I)Ljava/lang/Integer;")
            )
        )
    private int modifySliderTextLimit(int originalBound) {
        return getMaxFps() / Main.config.main.fpsLimitInterval;
    }

    // Allows game to save and load fps settings from 1 to the 32bit integer limit, default 10-260
    @ModifyArgs(
            method = "<init>",
            at = @At(
                value = "INVOKE",
                target = "com/mojang/serialization/Codec.intRange (II)Lcom/mojang/serialization/Codec;",
                remap = false,
                ordinal = 0
            ), slice = @Slice(
                from = @At(value = "INVOKE", target = "net/minecraft/client/option/SimpleOption.emptyTooltip ()Lnet/minecraft/client/option/SimpleOption$TooltipFactory;"),
                to = @At(value = "INVOKE", target = "java/lang/Integer.valueOf (I)Ljava/lang/Integer;")
            )
        )
    private void modifyCodec(Args args) {
        args.setAll(1, 2147483647);
    }


    // changes the intervals that the fps slider snaps to, default 10
    @ModifyArgs(
            method = "<init>",
            at = @At(
                value = "INVOKE",
                target = "net/minecraft/client/option/SimpleOption$ValidatingIntSliderCallbacks.withModifier (Ljava/util/function/IntFunction;Ljava/util/function/ToIntFunction;)Lnet/minecraft/client/option/SimpleOption$SliderCallbacks;",
                ordinal = 1
            ), slice = @Slice(
                from = @At(value = "INVOKE", target = "net/minecraft/client/option/SimpleOption.emptyTooltip ()Lnet/minecraft/client/option/SimpleOption$TooltipFactory;"),
                to = @At(value = "INVOKE", target = "java/lang/Integer.valueOf (I)Ljava/lang/Integer;")
            )
        )
    private void modifySliderSnapInterval(Args args) {
        IntFunction<Integer> intFunction = value -> value * Main.config.main.fpsLimitInterval;
        ToIntFunction<Integer> toIntFunction = value -> value / Main.config.main.fpsLimitInterval;
        args.setAll(intFunction, toIntFunction);
    }
}