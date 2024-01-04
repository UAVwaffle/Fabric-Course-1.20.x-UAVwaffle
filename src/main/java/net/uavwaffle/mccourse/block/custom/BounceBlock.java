package net.uavwaffle.mccourse.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.uavwaffle.mccourse.MCCourseMod;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BounceBlock extends Block {
    public BounceBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        entity.handleFallDamage(fallDistance, 0.0f, world.getDamageSources().fall());
//        super.onLandedUpon(world, state, pos, entity, fallDistance);
    }

    @Override
    public void onEntityLand(BlockView world, Entity entity) {
        if (entity.bypassesLandingEffects()) {
            super.onEntityLand(world, entity);
        } else {
            this.bounce(entity);
        }
    }

    private void bounce(Entity entity) {
        Vec3d vec3d = entity.getVelocity();
        double maxJump = 1;
        double minJump = 0.4;
        double jumpPower = 1.3;
        if (vec3d.y < 0.0) {
            double d = entity instanceof LivingEntity ? jumpPower : 1.0;
            if(-vec3d.y > minJump && -vec3d.y < maxJump) {
                entity.setVelocity(vec3d.x, -vec3d.y * d, vec3d.z);
            }else if (-vec3d.y >= maxJump){
                entity.setVelocity(vec3d.x, -vec3d.y, vec3d.z);
            }else{
                entity.setVelocity(entity.getVelocity().multiply( 1.0, 0.0, 1.0));
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.translatable("tooltip.mccourse.bounceblock"));
    }
}
