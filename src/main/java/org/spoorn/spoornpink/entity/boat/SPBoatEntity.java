//package org.spoorn.spoornpink.entity.boat;
//
//import net.minecraft.block.Block;
//import net.minecraft.entity.EntityType;
//import net.minecraft.entity.data.DataTracker;
//import net.minecraft.entity.data.TrackedData;
//import net.minecraft.entity.data.TrackedDataHandlerRegistry;
//import net.minecraft.entity.vehicle.BoatEntity;
//import net.minecraft.item.Item;
//import net.minecraft.item.Items;
//import net.minecraft.nbt.NbtCompound;
//import net.minecraft.network.Packet;
//import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
//import net.minecraft.world.World;
//import org.spoorn.spoornpink.block.SPBlocks;
//import org.spoorn.spoornpink.entity.SPEntities;
//import org.spoorn.spoornpink.item.SPItems;
//
//public class SPBoatEntity extends BoatEntity {
//
//    private static final TrackedData<Integer> SP_BOAT_TYPE = DataTracker.registerData(SPBoatEntity.class, TrackedDataHandlerRegistry.INTEGER);
//    private static final String TYPE_NBT_KEY = "SPBoatType";
//
//    public SPBoatEntity(World world, double x, double y, double z) {
//        this(SPEntities.BOAT, world);
//        this.setPosition(x, y, z);
//        this.prevX = x;
//        this.prevY = y;
//        this.prevZ = z;
//    }
//
//    public SPBoatEntity(EntityType<? extends BoatEntity> entityType, World world) {
//        super(entityType, world);
//    }
//
//    @Override
//    public Item asItem() {
//        switch (this.getSPBoatType()) {
//            case PINK_BLOSSOM :
//                return SPItems.PINK_BLOSSOM_BOAT;
//            default :
//                return Items.OAK_BOAT;
//        }
//    }
//
//    public Type getSPBoatType() {
//        return Type.getType(this.dataTracker.get(SP_BOAT_TYPE));
//    }
//
//    public void setSPBoatType(Type boatType) {
//        this.dataTracker.set(SP_BOAT_TYPE, boatType.ordinal());
//    }
//
//    @Override
//    protected void initDataTracker() {
//        super.initDataTracker();
//        this.dataTracker.startTracking(SP_BOAT_TYPE, Type.PINK_BLOSSOM.ordinal());
//    }
//
//    @Override
//    protected void writeCustomDataToNbt(NbtCompound nbt) {
//        nbt.putString(TYPE_NBT_KEY, this.getSPBoatType().getName());
//    }
//
//    @Override
//    protected void readCustomDataFromNbt(NbtCompound nbt) {
//        if (nbt.contains(TYPE_NBT_KEY, 8)) {
//            this.setSPBoatType(Type.getType(nbt.getString(TYPE_NBT_KEY)));
//        }
//    }
//
//    @Override
//    public Packet<?> createSpawnPacket() {
//        return new EntitySpawnS2CPacket(this);
//    }
//
//
//    public static enum Type {
//        PINK_BLOSSOM(SPBlocks.PINK_BLOSSOM_PLANKS, "pink_blossom");
//
//        private final String name;
//        private final Block baseBlock;
//
//        private Type(Block baseBlock, String name) {
//            this.name = name;
//            this.baseBlock = baseBlock;
//        }
//
//        public String getName() {
//            return this.name;
//        }
//
//        public Block getBaseBlock() {
//            return this.baseBlock;
//        }
//
//        public String toString() {
//            return this.name;
//        }
//
//        public static Type getType(int type) {
//            Type[] types = Type.values();
//            if (type < 0 || type >= types.length) {
//                type = 0;
//            }
//            return types[type];
//        }
//
//        public static Type getType(String name) {
//            Type[] types = Type.values();
//            for (int i = 0; i < types.length; ++i) {
//                if (!types[i].getName().equals(name)) continue;
//                return types[i];
//            }
//            return types[0];
//        }
//    }
//}
