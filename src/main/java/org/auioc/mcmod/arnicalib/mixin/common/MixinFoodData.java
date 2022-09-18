package org.auioc.mcmod.arnicalib.mixin.common;

import javax.annotation.Nullable;
import org.auioc.mcmod.arnicalib.common.event.AHCommonEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

@Mixin(value = FoodData.class)
public abstract class MixinFoodData {


    /**
     * @author WakelessSloth56
     * @reason {@link org.auioc.mcmod.arnicalib.common.event.impl.LivingEatEvent}
     */
    @Overwrite(remap = false)
    public void eat(Item p_38713_, ItemStack p_38714_, @Nullable LivingEntity entity) {
        var event = AHCommonEventFactory.onLivingEat(entity, ((FoodData) (Object) this), p_38714_);
        if (!event.isCanceled()) {
            int nutrition = event.getNutrition();
            float saturationModifier = event.getSaturationModifier();
            if (nutrition != 0 || saturationModifier != 0.0F) {
                this.eat(nutrition, saturationModifier);
            }
        }
    }

    @Shadow
    protected abstract void eat(int p_38708_, float p_38709_);

}
