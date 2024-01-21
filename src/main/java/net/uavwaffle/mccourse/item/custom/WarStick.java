package net.uavwaffle.mccourse.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.uavwaffle.mccourse.MCCourseMod;

public class WarStick extends Item {

    private boolean used = false;
    private LivingEntity entity1;
    private LivingEntity entity2;

    public WarStick(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if(!user.getWorld().isClient){
            if(!this.used){
                this.entity1 = entity;
                this.used = true;
            }else if(this.used && !entity.equals(entity1)){
                this.entity2 = entity;
                this.used = false;
                entity1.setAttacker(entity2);
                entity2.setAttacker(entity1);
            }
        }
        return ActionResult.SUCCESS;
    }



}
