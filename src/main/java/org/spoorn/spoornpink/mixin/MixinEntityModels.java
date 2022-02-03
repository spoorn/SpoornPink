package org.spoorn.spoornpink.mixin;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModels;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spoorn.spoornpink.entity.boat.SPBoatEntity;
import org.spoorn.spoornpink.entity.boat.SPBoatEntityRenderer;

import java.util.Map;

@Mixin(EntityModels.class)
public class MixinEntityModels {

    @Inject(method = "getModels", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/model/BoatEntityModel;getTexturedModelData()Lnet/minecraft/client/model/TexturedModelData;"), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void createBYGBoatTypeModelRoots(CallbackInfoReturnable<Map<EntityModelLayer, TexturedModelData>> cir, ImmutableMap.Builder<EntityModelLayer, TexturedModelData> builder) {
        for (SPBoatEntity.Type type : SPBoatEntity.Type.values()) {
            builder.put(SPBoatEntityRenderer.createBoat(type), BoatEntityModel.getTexturedModelData());
        }
    }
}
