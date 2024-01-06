package net.uavwaffle.mccourse.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import net.uavwaffle.mccourse.block.ModBlocks;
import net.uavwaffle.mccourse.item.ModItems;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeGenerator extends FabricRecipeProvider {
    public ModRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModItems.METAL_DETECTOR)
                .pattern("  i")
                .pattern(" b ")
                .pattern("idi")
                .input('i', Items.IRON_INGOT)
                .input('b', Items.REDSTONE_BLOCK)
                .input('d', ModItems.PINK_GARNET)
                .criterion(hasItem(Items.IRON_INGOT),conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.REDSTONE_BLOCK),conditionsFromItem(Items.REDSTONE_BLOCK))
                .criterion(hasItem(ModItems.PINK_GARNET),conditionsFromItem(ModItems.PINK_GARNET))
                .criterion(hasItem(ModItems.METAL_DETECTOR),conditionsFromItem(ModItems.METAL_DETECTOR))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.METAL_DETECTOR) + "_"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.BOUNCE_BLOCK)
                .pattern("ccc")
                .pattern("csc")
                .pattern("ccc")
                .input('s', Items.SLIME_BLOCK)
                .input('c', Items.CLAY_BALL)
                .criterion(hasItem(Items.SLIME_BALL),conditionsFromItem(Items.SLIME_BALL))
                .criterion(hasItem(Items.SLIME_BLOCK),conditionsFromItem(Items.SLIME_BLOCK))
                .criterion(hasItem(Items.CLAY_BALL),conditionsFromItem(Items.CLAY_BALL))
                .criterion(hasItem(ModBlocks.BOUNCE_BLOCK),conditionsFromItem(ModBlocks.BOUNCE_BLOCK))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.BOUNCE_BLOCK)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_PINK_GARNET)
                .pattern("SSS")
                .pattern("SPS")
                .pattern("SSS")
                .input('S', Items.STONE)
                .input('P', ModItems.PINK_GARNET)
                .criterion(hasItem(Items.STONE), conditionsFromItem(Items.STONE))
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.RAW_PINK_GARNET) + "_"));


        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.PINK_GARNET, RecipeCategory.MISC, ModBlocks.PINK_GARNET_BLOCK);


        offerSmelting(exporter, List.of(ModItems.RAW_PINK_GARNET, ModBlocks.PINK_GARNET_ORE, ModBlocks.DEEPSLATE_PINK_GARNET_ORE,
                ModBlocks.END_STONE_PINK_GARNET_ORE, ModBlocks.NETHER_PINK_GARNET_ORE), RecipeCategory.MISC, ModItems.PINK_GARNET,
                10.00f, 200, "pink_garnet");
        offerBlasting(exporter, List.of(ModItems.RAW_PINK_GARNET, ModBlocks.PINK_GARNET_ORE, ModBlocks.DEEPSLATE_PINK_GARNET_ORE,
                ModBlocks.END_STONE_PINK_GARNET_ORE, ModBlocks.NETHER_PINK_GARNET_ORE), RecipeCategory.MISC, ModItems.PINK_GARNET,
                10.0f, 100, "pink_garnet");

    }
}
