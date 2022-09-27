package org.auioc.mcmod.arnicalib.server.event.impl;

import java.util.Random;
import javax.annotation.Nullable;
import org.auioc.mcmod.arnicalib.game.event.ServerPlayerEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public class ItemHurtEvent extends ServerPlayerEvent {

    private final ItemStack itemStack;
    private final Random random;
    private int damage;

    public ItemHurtEvent(ItemStack itemStack, int damage, Random random, @Nullable ServerPlayer player) {
        super(player);
        this.itemStack = itemStack;
        this.damage = damage;
        this.random = random;
    }

    public ItemStack getItemStack() {
        return this.itemStack;
    }

    public Random getRandom() {
        return this.random;
    }

    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

}

