//package org.spoorn.spoornpink.client.render;
//
//import lombok.extern.log4j.Log4j2;
//import net.minecraft.block.Block;
//import net.minecraft.client.render.RenderLayer;
//import org.spoorn.spoornpink.block.SPBlocks;
//import org.spoorn.spoornpink.mixin.RenderLayersAccessor;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * CLIENT SIDE ONLY!
// */
//@Log4j2
//public class SPRenderLayers {
//
//    public static void init() {
//        addCustomRenders();
//    }
//
//    /**
//     * Adds our blocks to Vanilla's RenderLayers maps.
//     */
//    private static void addCustomRenders() {
//        log.info("Adding SpoornPink custom renders");
//        Map<Block, RenderLayer> map = new HashMap<>();
//
//        // Saplings
//        for (Block block : SPBlocks.SAPLINGS) {
//            map.put(block, RenderLayer.getCutout());
//        }
//
//        // Flower pots
//        for (Block block : SPBlocks.POTTED_BLOCKS) {
//            map.put(block, RenderLayer.getCutout());
//        }
//
//        // Doors
//        for (Block block : SPBlocks.DOOR_BLOCKS) {
//            map.put(block, RenderLayer.getCutout());
//        }
//
//        // Trapdoors
//        for (Block block : SPBlocks.TRAPDOORS) {
//            map.put(block, RenderLayer.getCutout());
//        }
//
//        RenderLayersAccessor.getBlocks().putAll(map);
//    }
//}
