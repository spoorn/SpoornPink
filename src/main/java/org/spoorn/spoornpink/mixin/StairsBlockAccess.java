package org.spoorn.spoornpink.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(StairsBlock.class)
public interface StairsBlockAccess {

    @Invoker("<init>")
    static StairsBlock create(BlockState defaultState, AbstractBlock.Settings settings) {
        throw new Error("Mixin did not apply!");
    }
}
