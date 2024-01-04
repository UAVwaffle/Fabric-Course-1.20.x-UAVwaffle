package net.uavwaffle.mccourse;

import net.fabricmc.api.ModInitializer;

import net.uavwaffle.mccourse.block.ModBlocks;
import net.uavwaffle.mccourse.item.ModItemGroup;
import net.uavwaffle.mccourse.item.ModItems;
import net.uavwaffle.mccourse.util.ModRegistries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MCCourseMod implements ModInitializer {
	public static final String MOD_ID = "mccourse";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroup.registerItemGroups();



		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModRegistries.registerModStuffs();
	}
}