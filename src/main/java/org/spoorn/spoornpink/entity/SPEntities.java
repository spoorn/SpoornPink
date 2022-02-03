package org.spoorn.spoornpink.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import org.spoorn.spoornpink.SpoornPink;
import org.spoorn.spoornpink.block.SPBlocks;
import org.spoorn.spoornpink.entity.boat.SPBoatEntity;

public class SPEntities {

    public static final EntityType<SPBoatEntity> BOAT = registerEntity("boat",
            EntityType.Builder.<SPBoatEntity>create(SPBoatEntity::new, SpawnGroup.MISC).setDimensions(1.375F, 0.5625F).build(SpoornPink.MODID + ":boat"));

    public static final BlockEntityType<SignBlockEntity> PINK_BLOSSOM_SIGNS = registerBlockEntity("pink_blossom_sign",
            BlockEntityType.Builder.create((BlockPos pos, BlockState state) -> {
                new SPSignBlockEntity(pos, state);
            }, SPBlocks.PINK_BLOSSOM_SIGN).build(null));

    public static void init() {

    }

    private static <E extends Entity, ET extends EntityType<E>> ET registerEntity(String id, ET entityType) {
        return Registry.register(Registry.ENTITY_TYPE, new Identifier(SpoornPink.MODID, id), entityType);
    }

    private static <E extends BlockEntity, ET extends BlockEntityType<E>> ET registerBlockEntity(String id, ET entityType) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(SpoornPink.MODID, id), entityType);
    }
}
