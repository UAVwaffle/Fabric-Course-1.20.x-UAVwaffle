package net.uavwaffle.mccourse.util;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.uavwaffle.mccourse.item.ModItems;

public class ModRegistries {
    public static void registerModStuffs(){
        registerFuels();
    }

    private static void registerFuels() {
        FuelRegistry registry = FuelRegistry.INSTANCE;

        registry.add(ModItems.PEAT_BRICK, 200);//this will burn for 200 ticks, smelting one iron
    }
}
