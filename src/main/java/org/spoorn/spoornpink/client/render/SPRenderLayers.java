package org.spoorn.spoornpink.client.render;

import lombok.extern.log4j.Log4j2;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import org.spoorn.spoornpink.block.SpoornPinkBlocks;
import org.spoorn.spoornpink.mixin.RenderLayersMixin;

import java.util.HashMap;
import java.util.Map;

/**
 * CLIENT SIDE ONLY!
 */
@Log4j2
public class SPRenderLayers {

    public static void init() {
        addCustomRenders();
    }

    /**
     * Adds our blocks to Vanilla's RenderLayers maps.
     */
    private static void addCustomRenders() {
        log.info("Adding SpoornPink custom renders");
        Map<Block, RenderLayer> map = new HashMap<>();

        // Saplings
        map.put(SpoornPinkBlocks.PINK_BLOSSOM_SAPLING, RenderLayer.getCutout());

        // Flower pots
        for (Block block : SpoornPinkBlocks.POTTED_BLOCKS) {
            map.put(block, RenderLayer.getCutout());
        }

        RenderLayersMixin.getBlocks().putAll(map);
    }
}
