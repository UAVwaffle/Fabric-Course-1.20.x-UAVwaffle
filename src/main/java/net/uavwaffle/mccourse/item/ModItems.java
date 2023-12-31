package net.uavwaffle.mccourse.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.uavwaffle.mccourse.MCCourseMod;
import net.uavwaffle.mccourse.block.ModBlocks;
import net.uavwaffle.mccourse.item.custom.MetalDetectorItem;

public class ModItems {
                /* All of your items are made here *normal items* */
    public static final Item PINK_GARNET = registerItem("pink_garnet",
            new Item(new FabricItemSettings()));
    public static final Item RAW_PINK_GARNET = registerItem("raw_pink_garnet",
            new Item(new FabricItemSettings()));

                                /*Custom Items*/
    public static final Item METAL_DETECTOR = registerItem("metal_detector",
            new MetalDetectorItem(new FabricItemSettings().maxDamage(256).food(ModFoodComponents.NEURON_FLY)));

                                /*FOOD ITEMS*/
    public static final Item CAULIFLOWER = registerItem("cauliflower",
            new Item(new FabricItemSettings().food(ModFoodComponents.CAULIFLOWER)));
    public static final Item NEURON_FLY = registerItem("neuron_fly",
            new Item(new FabricItemSettings().food(ModFoodComponents.NEURON_FLY)));

                                /*FUEL ITEMS*/
    public static final Item PEAT_BRICK = registerItem("peat_brick",
            new Item(new FabricItemSettings()));

    public static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(MCCourseMod.MOD_ID, name), item);
    }
    private static void itemGroupIngredients(FabricItemGroupEntries entries){//All items in here get put in the ingredients tab, make a new one and a new ItemGroupEvent to put them in other creative tabs
        entries.add(PINK_GARNET);
        entries.add(RAW_PINK_GARNET);

        entries.add(ModBlocks.PINK_GARNET_BLOCK);
        entries.add(ModBlocks.RAW_PINK_GARNET_BLOCK);
    }


    public static void registerModItems(){
        MCCourseMod.LOGGER.info("Registering Mod Items for: " + MCCourseMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::itemGroupIngredients);
    }
}
