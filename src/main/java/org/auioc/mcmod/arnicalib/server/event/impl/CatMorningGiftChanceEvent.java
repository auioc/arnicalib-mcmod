package org.auioc.mcmod.arnicalib.server.event.impl;

import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;

public class CatMorningGiftChanceEvent extends LivingEvent {

    private final Cat cat;
    private final Player ownerPlayer;
    private double chance = 0.7D;

    public CatMorningGiftChanceEvent(Cat cat, Player ownerPlayer) {
        super(cat);
        this.cat = cat;
        this.ownerPlayer = ownerPlayer;
    }

    public Cat getCat() {
        return this.cat;
    }

    public Player getOwnerPlayer() {
        return this.ownerPlayer;
    }

    public double getChance() {
        return this.chance;
    }

    public void setChance(double chance) {
        this.chance = chance;
    }

}
