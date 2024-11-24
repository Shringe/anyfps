package com.github.anyfps;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;

public class Main implements ModInitializer {
    public static ModConfig config;

    @Override
    public void onInitialize() {
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }

    // Round the maximum fps up to the nearest interval
    public static int getMaxFps() {
        if (Main.config.main.fpsLimitMax % Main.config.main.fpsLimitInterval > 0) {
            return ((Main.config.main.fpsLimitMax / Main.config.main.fpsLimitInterval) + 1) * Main.config.main.fpsLimitInterval;
        } else {
            return Main.config.main.fpsLimitMax;
        }
    }
}
