package org.spoorn.spoornpink.mixin;

import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.model.Model;
import net.minecraft.data.client.model.Models;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spoorn.spoornpink.item.SPItems;

@Mixin(ItemModelGenerator.class)
public abstract class ItemModelGeneratorMixin {

    @Shadow public abstract void register(Item item, Model model);

    @Inject(method = "register()V", at = @At(value = "TAIL"))
    private void registerCustomItems(CallbackInfo ci) {
        this.register(SPItems.PINK_BLOSSOM_BOAT, Models.GENERATED);
    }
}
