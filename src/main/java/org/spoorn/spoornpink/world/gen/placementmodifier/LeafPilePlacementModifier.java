package org.spoorn.spoornpink.world.gen.placementmodifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Places leaf piles if they are under leaves and within a chance between 0.0 and 1.0, else under a random chance.
 */
public class LeafPilePlacementModifier extends PlacementModifier {

    public static final Codec<LeafPilePlacementModifier> MODIFIER_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    (Codecs.POSITIVE_INT.fieldOf("max_height")).forGetter(leafPilePlacementModifier -> leafPilePlacementModifier.maxHeight),
                    (Codec.FLOAT.fieldOf("under_chance")).forGetter(leafPilePlacementModifier -> leafPilePlacementModifier.underChance),
                    (Codec.FLOAT.fieldOf("outside_chance")).forGetter(leafPilePlacementModifier -> leafPilePlacementModifier.outsideChance))
            .apply(instance, LeafPilePlacementModifier::new));
    private static final PlacementModifierType<LeafPilePlacementModifier> LEAF_PILE_PLACEMENT_MODIFIER_TYPE = register("leaf_pile_placement_modifier", LeafPilePlacementModifier.MODIFIER_CODEC);


    // Max height to check for leaves blocks
    public final int maxHeight;
    // Chance to place leaf pile if under leaves block
    public final float underChance;
    // Chance to place leaf pile if not under leaves block i.e. outside tree radius
    public final float outsideChance;
    
    private LeafPilePlacementModifier(int maxHeight, float underChance, float outsideChance) {
        this.maxHeight = maxHeight;
        this.underChance = underChance;
        this.outsideChance = outsideChance;
    }

    /**
     * If randomChanceIfNotUnderBlock <= 0, will default to no chance
     */
    public static LeafPilePlacementModifier of(int maxHeight, float underChance, float outsideChance) {
        return new LeafPilePlacementModifier(maxHeight, underChance, outsideChance);
    }

    @Override
    public final Stream<BlockPos> getPositions(FeaturePlacementContext context, Random random, BlockPos pos) {
        List<BlockPos> positivePos = new ArrayList<>();
        int posX = pos.getX();
        int posY = pos.getY();
        int posZ = pos.getZ();
        BlockPos.Mutable mutable = new BlockPos.Mutable(posX, posY, posZ);
        for (int x = posX; x < posX + 16; x++) {
            for (int z = posZ; z < posZ + 16; z++) {
                // Get y at the surface level
                posY = context.getTopY(Heightmap.Type.WORLD_SURFACE_WG, x, z);
                mutable.set(x, posY, z);
                
                // Only consider placing leaf block if nothing else is placed here
                if (!context.getWorld().isAir(mutable)) {
                    continue;
                }
                
                if (this.shouldPlace(context, random, mutable)) {
                    positivePos.add(new BlockPos(x, posY, z));
                }
            }
        }
        
        if (!positivePos.isEmpty()) {
            //System.out.println("positive pos: " + positivePos);
            return positivePos.stream();
        }
        return Stream.of(new BlockPos[0]);
    }
    
    private boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos pos) {
        //System.out.println(pos);
        BlockPos.Mutable mutablePos = pos.mutableCopy().move(Direction.UP);
        for (int i = 0; i < maxHeight; i++) {
            //System.out.println(mutablePos + " : " + context.getBlockState(mutablePos).getBlock());
            if (context.getBlockState(mutablePos).getBlock() instanceof LeavesBlock) {
                //System.out.println(context.getBlockState(mutablePos).getBlock());
                return random.nextFloat() < this.underChance;
            }
            mutablePos.move(Direction.UP);
        }
        
        return random.nextFloat() < this.outsideChance;
    }

    @Override
    public PlacementModifierType<?> getType() {
        return LEAF_PILE_PLACEMENT_MODIFIER_TYPE;
    }
    
    // Register custom PlacementModifiers
    private static <P extends PlacementModifier> PlacementModifierType<P> register(String id, Codec<P> codec) {
        return Registry.register(Registry.PLACEMENT_MODIFIER_TYPE, id, () -> codec);
    }
}
