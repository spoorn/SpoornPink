//package org.spoorn.spoornpink.mixin;
//
//import net.minecraft.client.render.entity.EntityRendererFactory;
//import net.minecraft.client.render.entity.EntityRenderers;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityType;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//import org.spoorn.spoornpink.entity.SPEntities;
//import org.spoorn.spoornpink.entity.boat.SPBoatEntityRenderer;
//
//@Mixin(EntityRenderers.class)
//public class EntityRenderersMixin {
//
//    @Shadow
//    private static <T extends Entity> void register(EntityType<? extends T> entityType, EntityRendererFactory<T> entityRendererProvider) {
//        throw new Error("Mixin did not apply!");
//    }
//
//    @Inject(method = "<clinit>", at = @At("RETURN"))
//    private static void registerSPEntities(CallbackInfo ci) {
//        register(SPEntities.BOAT, SPBoatEntityRenderer::new);
//    }
//}
