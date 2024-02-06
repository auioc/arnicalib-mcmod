package org.auioc.mcmod.arnicalib.game.event.common;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodData;
import net.neoforged.neoforge.event.entity.living.LivingEvent;

public abstract class FoodDataEvent extends LivingEvent {

    private final FoodData foodData;

    public FoodDataEvent(LivingEntity living, FoodData foodData) {
        super(living);
        this.foodData = foodData;
    }

    public FoodData getFoodData() {
        return this.foodData;
    }

}
