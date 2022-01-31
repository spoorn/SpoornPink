package org.spoorn.spoornpink.world.gen.feature;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import org.spoorn.spoornpink.SpoornPink;
import org.spoorn.spoornpink.world.gen.feature.config.SPTreeConfig;
import org.spoorn.spoornpink.world.gen.feature.overworld.trees.AbstractSPTree;
import org.spoorn.spoornpink.world.gen.feature.overworld.trees.PinkBlossomTree;

public class SPFeatures {

    public static AbstractSPTree<SPTreeConfig> PINK_BLOSSOM_TREE = register("pink_blossom_tree", new PinkBlossomTree(SPTreeConfig.CODEC.stable()));

    public static void init() {

    }

    public static <C extends FeatureConfig, F extends Feature<C>> F register(String id, F feature) {
        Identifier identifier = new Identifier(SpoornPink.MODID, id);
        return Registry.register(Registry.FEATURE, identifier, feature);
    }
}
