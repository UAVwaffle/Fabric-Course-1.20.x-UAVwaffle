package net.uavwaffle.mccourse.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent CAULIFLOWER = new FoodComponent.Builder().hunger(4).saturationModifier(0.5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION,200,1), 0.25f).build();
    public static final FoodComponent NEURON_FLY = new FoodComponent.Builder().hunger(3).saturationModifier(0.5f).snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.GLOWING,24000,0,true,false), 0.25f).build();
}
