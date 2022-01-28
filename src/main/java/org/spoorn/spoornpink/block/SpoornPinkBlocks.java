package org.spoorn.spoornpink.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.spoorn.spoornpink.SpoornPink;

public class SpoornPinkBlocks {

    public static final Block CHERRY_LOG = registerLog("cherry_log");

    public static void init() {

    }

    public static Block registerLog(String id) {
        Block block = new PillarBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0f));
        return Registry.register(Registry.BLOCK, new Identifier(SpoornPink.MODID, id), block);
    }
}
