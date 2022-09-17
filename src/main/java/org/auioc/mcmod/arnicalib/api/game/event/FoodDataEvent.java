package org.auioc.mcmod.arnicalib.api.game.event;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodData;
import net.minecraftforge.event.entity.living.LivingEvent;

public class FoodDataEvent extends LivingEvent {

    private final FoodData foodData;

    public FoodDataEvent(LivingEntity living, FoodData foodData) {
        super(living);
        this.foodData = foodData;
    }

    public FoodData getFoodData() {
        return this.foodData;
    }

}
