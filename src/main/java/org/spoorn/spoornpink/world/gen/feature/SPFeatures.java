package org.spoorn.spoornpink.world.gen.feature;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import org.spoorn.spoornpink.SpoornPink;
import org.spoorn.spoornpink.world.gen.feature.config.SPTreeConfig;
import org.spoorn.spoornpink.world.gen.feature.overworld.trees.AbstractSPTree;
import org.spoorn.spoornpink.world.gen.feature.overworld.trees.CherryTree;

public class SPFeatures {

    public static AbstractSPTree<SPTreeConfig> CHERRY_TREE = register("cherry_tree", new CherryTree(SPTreeConfig.CODEC.stable()));

    public static void init() {

    }

    public static <C extends FeatureConfig, F extends Feature<C>> F register(String id, F feature) {
        Identifier identifier = new Identifier(SpoornPink.MODID, id);
        return Registry.register(Registry.FEATURE, identifier, feature);
    }
}
