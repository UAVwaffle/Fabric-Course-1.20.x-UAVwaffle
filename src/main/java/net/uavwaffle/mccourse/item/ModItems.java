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

public class ModItems {

    public static final Item PINK_GARNET = registerItem("pink_garnet",
            new Item(new FabricItemSettings()));

    public static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(MCCourseMod.MOD_ID, name), item);
    }
    private static void itemGroupIngredients(FabricItemGroupEntries entries){//All items in here get put in the ingredients tab, make a new one and a new ItemGroupEvent to put them in other creative tabs
        entries.add(PINK_GARNET);
    }


    public static void registerModItems(){
        MCCourseMod.LOGGER.info("Registering Mod Items for: " + MCCourseMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::itemGroupIngredients);
    }
}
