package org.auioc.mcmod.arnicalib.api.game.item;

import java.util.function.Supplier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@SuppressWarnings("deprecation")
public class HArmorMaterial implements ArmorMaterial {

    private final String name;
    private int durability = 1;
    private int defense = 1;
    private int enchantmentValue = 1;
    private float toughness = 0.0F;
    private float knockbackResistance = 0.0F;
    private SoundEvent sound = null;
    private LazyLoadedValue<Ingredient> repairIngredient = new LazyLoadedValue<Ingredient>(() -> Ingredient.of(Items.AIR));

    public HArmorMaterial(String name) {
        this.name = name;
    }


    public HArmorMaterial setDurability(int durability) {
        this.durability = durability;
        return this;
    }

    public HArmorMaterial setDefense(int defense) {
        this.defense = defense;
        return this;
    }

    public HArmorMaterial setEnchantmentValue(int enchantmentValue) {
        this.enchantmentValue = enchantmentValue;
        return this;
    }

    public HArmorMaterial setToughness(float toughness) {
        this.toughness = toughness;
        return this;
    }

    public HArmorMaterial setKnockbackResistance(float knockbackResistance) {
        this.knockbackResistance = knockbackResistance;
        return this;
    }

    public HArmorMaterial setEquipSound(SoundEvent sound) {
        this.sound = sound;
        return this;
    }

    public HArmorMaterial setRepairIngredient(Supplier<Ingredient> repairIngredient) {
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
        return this;
    }



    @Override
    public int getDurabilityForSlot(EquipmentSlot slotType) {
        return this.durability;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot slotType) {
        return this.defense;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.sound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
