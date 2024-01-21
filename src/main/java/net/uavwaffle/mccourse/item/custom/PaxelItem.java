package net.uavwaffle.mccourse.item.custom;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import net.uavwaffle.mccourse.MCCourseMod;
import net.uavwaffle.mccourse.util.ModTags;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class PaxelItem extends MiningToolItem {
    protected static final Map<Block, BlockState> PATH_STATES = Maps.newHashMap(new ImmutableMap.Builder<Block, BlockState>()
            .put(Blocks.GRASS_BLOCK, Blocks.DIRT_PATH.getDefaultState())
            .put(Blocks.DIRT, Blocks.DIRT_PATH.getDefaultState())
            .put(Blocks.PODZOL, Blocks.DIRT_PATH.getDefaultState())
            .put(Blocks.COARSE_DIRT, Blocks.DIRT_PATH.getDefaultState())
            .put(Blocks.MYCELIUM, Blocks.DIRT_PATH.getDefaultState())
            .put(Blocks.ROOTED_DIRT, Blocks.DIRT_PATH.getDefaultState()).build());
    protected static final Map<Block, Pair<Predicate<ItemUsageContext>, Consumer<ItemUsageContext>>> TILLING_ACTIONS =
            Maps.newHashMap(ImmutableMap.of(Blocks.GRASS_BLOCK, Pair.of(HoeItem::canTillFarmland, HoeItem.createTillAction(Blocks.FARMLAND.getDefaultState())), Blocks.DIRT_PATH,
                    Pair.of(HoeItem::canTillFarmland, HoeItem.createTillAction(Blocks.FARMLAND.getDefaultState())), Blocks.DIRT,
                    Pair.of(HoeItem::canTillFarmland, HoeItem.createTillAction(Blocks.FARMLAND.getDefaultState())), Blocks.COARSE_DIRT,
                    Pair.of(HoeItem::canTillFarmland, HoeItem.createTillAction(Blocks.DIRT.getDefaultState())), Blocks.ROOTED_DIRT,
                    Pair.of(itemUsageContext -> true, HoeItem.createTillAndDropAction(Blocks.DIRT.getDefaultState(), Items.HANGING_ROOTS))));



    public PaxelItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(attackDamage, attackSpeed, material, ModTags.Blocks.PAXEL_MINEABLE, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        Pair<Predicate<ItemUsageContext>, Consumer<ItemUsageContext>> pair = TILLING_ACTIONS.get(world.getBlockState(blockPos = context.getBlockPos()).getBlock());

        if(context.getPlayer() != null && context.getPlayer().isSneaking()){
            if (pair == null) {
                return ActionResult.PASS;
            }
            Predicate<ItemUsageContext> predicate = pair.getFirst();
            Consumer<ItemUsageContext> consumer = pair.getSecond();
            if (predicate.test(context)) {
                PlayerEntity playerEntity = context.getPlayer();
                world.playSound(playerEntity, blockPos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0f, 1.0f);
                if (!world.isClient) {
                    consumer.accept(context);
                    if (playerEntity != null) {
                        context.getStack().damage(1, playerEntity, p -> p.sendToolBreakStatus(context.getHand()));
                    }
                }
                return ActionResult.success(world.isClient);
            }
            return ActionResult.PASS;
        } else if (context.getPlayer() != null) {
            if (context.getSide() != Direction.DOWN) {
                PlayerEntity playerEntity = context.getPlayer();
                BlockState blockState2 = PATH_STATES.get(blockState.getBlock());
                BlockState blockState3 = null;
                if (blockState2 != null && world.getBlockState(blockPos.up()).isAir()) {
                    world.playSound(playerEntity, blockPos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0f, 1.0f);
                    blockState3 = blockState2;
                } else if (blockState.getBlock() instanceof CampfireBlock && blockState.get(CampfireBlock.LIT).booleanValue()) {
                    if (!world.isClient()) {
                        world.syncWorldEvent(null, WorldEvents.FIRE_EXTINGUISHED, blockPos, 0);
                    }
                    CampfireBlock.extinguish(context.getPlayer(), world, blockPos, blockState);
                    blockState3 = (BlockState) blockState.with(CampfireBlock.LIT, false);
                }
                if (blockState3 != null) {
                    if (!world.isClient) {
                        world.setBlockState(blockPos, blockState3, Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
                        world.emitGameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Emitter.of(playerEntity, blockState3));
                        if (playerEntity != null) {
                            context.getStack().damage(1, playerEntity, p -> p.sendToolBreakStatus(context.getHand()));
                        }
                    }
                    return ActionResult.success(world.isClient);
                }
                return ActionResult.PASS;
            }
            return ActionResult.PASS;
        }
        return ActionResult.PASS;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.setOnFireFor(10);
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 200, 0), attacker);
        return super.postHit(stack, target, attacker);
    }

    /**
     * {@return a tilling action that sets a block state}
     *
     * @param result the tilled block state
     */
    public static Consumer<ItemUsageContext> createTillAction(BlockState result) {
        return context -> {
            context.getWorld().setBlockState(context.getBlockPos(), result, Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
            context.getWorld().emitGameEvent(GameEvent.BLOCK_CHANGE, context.getBlockPos(), GameEvent.Emitter.of(context.getPlayer(), result));
        };
    }

    /**
     * {@return a tilling action that sets a block state and drops an item}
     *
     * @param droppedItem the item to drop
     * @param result the tilled block state
     */
    public static Consumer<ItemUsageContext> createTillAndDropAction(BlockState result, ItemConvertible droppedItem) {
        return context -> {
            context.getWorld().setBlockState(context.getBlockPos(), result, Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
            context.getWorld().emitGameEvent(GameEvent.BLOCK_CHANGE, context.getBlockPos(), GameEvent.Emitter.of(context.getPlayer(), result));
            Block.dropStack(context.getWorld(), context.getBlockPos(), context.getSide(), new ItemStack(droppedItem));
        };
    }

    /**
     * {@return whether the used block can be tilled into farmland}
     * This method is used as the tilling predicate for most vanilla blocks except rooted dirt.
     */
    public static boolean canTillFarmland(ItemUsageContext context) {
        return context.getSide() != Direction.DOWN && context.getWorld().getBlockState(context.getBlockPos().up()).isAir();
    }
}
