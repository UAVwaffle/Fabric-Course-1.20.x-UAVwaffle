package net.uavwaffle.mccourse.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.uavwaffle.mccourse.MCCourseMod;

public class ModItems {

    public static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(MCCourseMod.MOD_ID, name), item);
    }
    private static void itemGroupIngredients(FabricItemGroupEntries entries){

    }
}
