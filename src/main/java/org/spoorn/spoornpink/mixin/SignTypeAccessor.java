package org.spoorn.spoornpink.mixin;

import net.minecraft.util.SignType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SignType.class)
public interface SignTypeAccessor {

    @Invoker("<init>")
    static SignType create(String id) {
        throw new Error("Mixin did not apply!");
    }

    @Invoker("register")
    static SignType register(SignType type) {
        throw new Error("Mixin did not apply!");
    }
}
