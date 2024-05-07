package com.github.anyfps;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "anyfps")
public class ModConfig implements ConfigData {
    @ConfigEntry.Gui.TransitiveObject
    public Main main = new Main();

    public class Main {
        @ConfigEntry.Gui.PrefixText
        public int fpsLimitMax = 520;

        public int fpsLimitInterval = 10;
    }
}