package net.uavwaffle.mccourse.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.uavwaffle.mccourse.MCCourseMod;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SoundBlock extends Block {
    public SoundBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        if (player.isSneaking()) {
            world.playSound(player, pos, SoundEvents.BLOCK_NOTE_BLOCK_BANJO.value(), SoundCategory.BLOCKS, 1, 1);
            return ActionResult.SUCCESS;
        } else {
            world.playSound(player, pos, SoundEvents.BLOCK_NOTE_BLOCK_BANJO.value(), SoundCategory.BLOCKS, 1, 5);
            return ActionResult.CONSUME;
        }

    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        world.playSound(entity, pos, SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, SoundCategory.BLOCKS, 1, 1);
        super.onSteppedOn(world, pos, state, entity);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.translatable("tooltip.mccourse.soundblock"));
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        player.incrementStat(Stats.MINED.getOrCreateStat(this));
        player.addExhaustion(0.005f);
        Block.dropStacks(state, world, pos, blockEntity, player, tool);
        player.addVelocity(0,10,0);
        player.velocityModified = true;
    }
    //        if(world.isClient){
//            if(hand == Hand.MAIN_HAND){
//                MCCourseMod.LOGGER.info("MAIN HAND CLIENT");
//            }else {
//                MCCourseMod.LOGGER.info("OFF HAND CLIENT");
//            }
//        }else{
//            if(hand == Hand.MAIN_HAND){
//                MCCourseMod.LOGGER.info("MAIN HAND SERVER");
//            }else {
//                MCCourseMod.LOGGER.info("OFF HAND SERVER");
//            }
//        }
//        return super.onUse(state, world, pos, player, hand, hit);
////        return ActionResult.SUCCESS;// When ActionResult.SUCCESS is retured the game only checks if you are using your main hand (still need world.isServer)
}
