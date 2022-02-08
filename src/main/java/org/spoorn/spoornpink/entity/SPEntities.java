//package org.spoorn.spoornpink.entity;
//
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EntityType;
//import net.minecraft.entity.SpawnGroup;
//import net.minecraft.util.Identifier;
//import net.minecraft.util.registry.Registry;
//import org.spoorn.spoornpink.SpoornPink;
//import org.spoorn.spoornpink.entity.boat.SPBoatEntity;
//
//public class SPEntities {
//
//    public static final EntityType<SPBoatEntity> BOAT = registerEntity("boat",
//            EntityType.Builder.<SPBoatEntity>create(SPBoatEntity::new, SpawnGroup.MISC).setDimensions(1.375F, 0.5625F).build(SpoornPink.MODID + ":boat"));
//
//    public static void init() {
//
//    }
//
//    private static <E extends Entity, ET extends EntityType<E>> ET registerEntity(String id, ET entityType) {
//        return Registry.register(Registry.ENTITY_TYPE, new Identifier(SpoornPink.MODID, id), entityType);
//    }
//}
