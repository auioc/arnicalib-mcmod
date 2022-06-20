package org.auioc.mcmod.arnicalib.api.game.alchemy;

import java.util.ArrayList;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import org.auioc.mcmod.arnicalib.utils.game.EffectUtils;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class HPotion extends Potion {

    public HPotion(@Nullable String name, MobEffectInstance... effects) {
        super(name, effects);
    }

    public HPotion(@Nullable String name, Supplier<MobEffectInstance[]> effects) {
        super(name, effects.get());
    }

    public HPotion(String name, MobEffect effect, int duration, int amplifier) {
        super(name, new MobEffectInstance(effect, duration, amplifier));
    }

    public HPotion(String name, MobEffect effect, int duration, int amplifier, ArrayList<ItemStack> curativeItems) {
        this(name, () -> {
            var instance = new MobEffectInstance(effect, duration, amplifier);
            instance.setCurativeItems(curativeItems);
            return new MobEffectInstance[] {instance};
        });
    }

    public HPotion(String name, MobEffect effect, int duration, int amplifier, boolean incurable) {
        this(name, () -> {
            var instance = new MobEffectInstance(effect, duration, amplifier);
            if (incurable) {
                EffectUtils.makeIncurable(instance);
            }
            return new MobEffectInstance[] {instance};
        });
    }

    public HPotion(String name, MobEffect effect, int duration, int amplifier, boolean ambient, boolean visible, boolean showIcon) {
        super(name, new MobEffectInstance(effect, duration, amplifier, ambient, visible, showIcon));
    }


    public static boolean registerBrewingRecipe(Ingredient input, Ingredient mix, ItemStack output) {
        return BrewingRecipeRegistry.addRecipe(input, mix, output);
    }

    public static boolean registerBrewingRecipe(Potion input, Item mix, Potion output) {
        return registerBrewingRecipe(
            Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), input)),
            Ingredient.of(mix),
            PotionUtils.setPotion(new ItemStack(Items.POTION), output)
        );
    }

    public static boolean registerBrewingRecipe(Potion input, Item mix, RegistryObject<Potion> output) {
        return registerBrewingRecipe(input, mix, output.get());
    }

    public static boolean registerBrewingRecipe(RegistryObject<Potion> input, Item mix, RegistryObject<Potion> output) {
        return registerBrewingRecipe(input.get(), mix, output.get());
    }

}
