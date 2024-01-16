package net.uavwaffle.mccourse.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.uavwaffle.mccourse.block.ModBlocks;
import net.uavwaffle.mccourse.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ModTags.Blocks.METAL_DETECTOR_DETECTABLE_BLOCKS)
                .add(ModBlocks.PINK_GARNET_ORE)
                .forceAddTag(BlockTags.GOLD_ORES)
                .forceAddTag(BlockTags.COAL_ORES)
                .forceAddTag(BlockTags.COPPER_ORES)
                .forceAddTag(BlockTags.DIAMOND_ORES)
                .forceAddTag(BlockTags.IRON_ORES)
                .forceAddTag(BlockTags.LAPIS_ORES)
                .forceAddTag(BlockTags.REDSTONE_ORES)
                .forceAddTag(BlockTags.EMERALD_ORES);


        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.NETHER_PINK_GARNET_ORE)
                .add(ModBlocks.END_STONE_PINK_GARNET_ORE)
                .add(ModBlocks.DEEPSLATE_PINK_GARNET_ORE)
                .add(ModBlocks.PINK_GARNET_ORE)
                .add(ModBlocks.SOUND_BLOCK)
                .add(ModBlocks.RAW_PINK_GARNET_BLOCK)
                .add(ModBlocks.PINK_GARNET_BLOCK)
                .add(ModBlocks.PINK_GARNET_STAIRS)
                .add(ModBlocks.PINK_GARNET_SLAB)
                .add(ModBlocks.PINK_GARNET_PRESSURE_PLATE)
                .add(ModBlocks.PINK_GARNET_WALL)
                .add(ModBlocks.PINK_GARNET_FENCE)
                .add(ModBlocks.PINK_GARNET_FENCE_GATE)
                .add(ModBlocks.PINK_GARNET_DOOR)
                .add(ModBlocks.PINK_GARNET_TRAPDOOR);


        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL);


        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.PINK_GARNET_BLOCK)
                .add(ModBlocks.PINK_GARNET_STAIRS)
                .add(ModBlocks.PINK_GARNET_SLAB)
                .add(ModBlocks.RAW_PINK_GARNET_BLOCK)
                .add(ModBlocks.PINK_GARNET_ORE);

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.NETHER_PINK_GARNET_ORE)
                .add(ModBlocks.SOUND_BLOCK);

        //Netherite mining lvl (4)
        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("fabric", "needs_tool_level_4")))
                .add(ModBlocks.DEEPSLATE_PINK_GARNET_ORE);
        //Pink Garnet mining lvl (5)
        getOrCreateTagBuilder(TagKey.of(RegistryKeys.BLOCK, new Identifier("fabric", "needs_tool_level_5")))
                .add(ModBlocks.END_STONE_PINK_GARNET_ORE);

        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.PINK_GARNET_WALL);
        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(ModBlocks.PINK_GARNET_FENCE);
        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.PINK_GARNET_FENCE_GATE);

        getOrCreateTagBuilder(ModTags.Blocks.PAXEL_MINEABLE)
                .forceAddTag(BlockTags.PICKAXE_MINEABLE)
                .forceAddTag(BlockTags.AXE_MINEABLE)
                .forceAddTag(BlockTags.SHOVEL_MINEABLE);

    }
}
