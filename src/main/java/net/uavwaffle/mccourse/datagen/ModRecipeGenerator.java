package net.uavwaffle.mccourse.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
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
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.BOUNCE_BLOCK)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.PINK_GARNET_STAIRS, 4)
                .pattern("g  ")
                .pattern("gg ")
                .pattern("ggg")
                .input('g', ModBlocks.PINK_GARNET_BLOCK)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.PINK_GARNET_STAIRS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.PINK_GARNET_SLAB, 6)
                .pattern("   ")
                .pattern("   ")
                .pattern("ggg")
                .input('g', ModBlocks.PINK_GARNET_BLOCK)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.PINK_GARNET_SLAB)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.PINK_GARNET_DOOR, 3)
                .pattern(" gg")
                .pattern(" gg")
                .pattern(" gg")
                .input('g', ModItems.PINK_GARNET)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.PINK_GARNET_DOOR)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.PINK_GARNET_PRESSURE_PLATE, 3)
                .pattern("   ")
                .pattern("   ")
                .pattern(" gg")
                .input('g', ModItems.PINK_GARNET)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.PINK_GARNET_PRESSURE_PLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.PINK_GARNET_TRAPDOOR, 2)
                .pattern("   ")
                .pattern("ggg")
                .pattern("ggg")
                .input('g', ModItems.PINK_GARNET)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.PINK_GARNET_TRAPDOOR)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.PINK_GARNET_WALL, 6)
                .pattern("   ")
                .pattern("ggg")
                .pattern("ggg")
                .input('g', ModBlocks.PINK_GARNET_BLOCK)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.PINK_GARNET_WALL)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.PINK_GARNET_FENCE, 3)
                .pattern("   ")
                .pattern("gbg")
                .pattern("gbg")
                .input('g', ModItems.PINK_GARNET)
                .input('b', ModBlocks.PINK_GARNET_BLOCK)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.PINK_GARNET_FENCE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.PINK_GARNET_FENCE_GATE, 1)
                .pattern("   ")
                .pattern("bgb")
                .pattern("bgb")
                .input('g', ModItems.PINK_GARNET)
                .input('b', ModBlocks.PINK_GARNET_BLOCK)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.PINK_GARNET_FENCE_GATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.PINK_GARNET_ORE)
                .pattern("SSS")
                .pattern("SPS")
                .pattern("SSS")
                .input('S', Items.STONE)
                .input('P', ModItems.PINK_GARNET)
                .criterion(hasItem(Items.STONE), conditionsFromItem(Items.STONE))
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.PINK_GARNET_ORE) + "_"));



        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.PINK_GARNET, RecipeCategory.MISC, ModBlocks.PINK_GARNET_BLOCK);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.PINK_GARNET_BUTTON)
                .input(ModItems.PINK_GARNET)
                .criterion(hasItem(ModItems.PINK_GARNET), conditionsFromItem(ModItems.PINK_GARNET))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.PINK_GARNET_BUTTON)));


        offerSmelting(exporter, List.of(ModItems.RAW_PINK_GARNET, ModBlocks.PINK_GARNET_ORE, ModBlocks.DEEPSLATE_PINK_GARNET_ORE,
                ModBlocks.END_STONE_PINK_GARNET_ORE, ModBlocks.NETHER_PINK_GARNET_ORE), RecipeCategory.MISC, ModItems.PINK_GARNET,
                10.00f, 200, "pink_garnet");
        offerBlasting(exporter, List.of(ModItems.RAW_PINK_GARNET, ModBlocks.PINK_GARNET_ORE, ModBlocks.DEEPSLATE_PINK_GARNET_ORE,
                ModBlocks.END_STONE_PINK_GARNET_ORE, ModBlocks.NETHER_PINK_GARNET_ORE), RecipeCategory.MISC, ModItems.PINK_GARNET,
                10.0f, 100, "pink_garnet");

    }
}
