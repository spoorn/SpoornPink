package org.spoorn.spoornpink.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.CraftingTableBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(CraftingTableBlock.class)
public interface CraftingTableBlockAccessor {

    @Invoker("<init>")
    static CraftingTableBlock create(AbstractBlock.Settings settings) {
        throw new Error("Mixin did not apply!");
    }
}
