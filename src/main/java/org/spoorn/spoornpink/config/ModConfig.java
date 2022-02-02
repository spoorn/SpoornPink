package org.spoorn.spoornpink.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import org.spoorn.spoornpink.SpoornPink;

@Config(name = SpoornPink.MODID)
public class ModConfig implements ConfigData {

    @Comment("Weight for this mod's biome region in the Overworld.  Higher number means more biomes from this mod will\n" +
            "generate compared to other biomes.  [default = 4]")
    public int overworldWeight = 4;

    public static void init() {
        AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
    }

    public static ModConfig get() {
        return AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }
}
