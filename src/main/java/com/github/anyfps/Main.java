package com.github.anyfps;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;

public class Main implements ModInitializer {
    public static ModConfig config;

    @Override
    public void onInitialize() {
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        this.config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }
    public static int getMaxFps() {
        return (int) Math.ceil((double) Main.config.main.fpsLimitMax / Main.config.main.fpsLimitInterval) * Main.config.main.fpsLimitInterval;
    }
}
