package com.github.mixin;

import com.mojang.serialization.Codec;
import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;


@Mixin(GameOptions.class)
public class GameOptionsMixin {
	private final static int MAX_FPS = 520;

//	// The constant MaxFps, not sure if this is used due to inlining in the compiler, but it doesn't hurt to be sure
//	@ModifyConstant(method = "<init>", constant = @Constant(intValue = 260))
//	private static int modifyMaxFramerateConstant(int originalMax) {
//		return MAX_FPS;
//	}

	// The point at which the options slider shows "Unlimited"
	@ModifyConstant(method = "method_42559(Lnet/minecraft/text/Text;Ljava/lang/Integer;)Lnet/minecraft/text/Text;", constant = @Constant(intValue = 260))
	private static int sliderTextMaxThreshold(int originalBound) {
		return MAX_FPS;
	}

	// The upper limit of the options slider
	@ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "net/minecraft/client/option/SimpleOption$ValidatingIntSliderCallbacks.<init> (II)V"), index = 1)
	private int modifySliderTextLimit(int originalBound) {
		return MAX_FPS / 10;
	}

	// Allows game to save and load settings above 260 successfully
	@ModifyArg(
			method = "<init>",
			index = 1,
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
	private int modifyCodec(int originalCodec) {
		return MAX_FPS;
	}
}