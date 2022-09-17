package org.auioc.mcmod.arnicalib.server.event.impl;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;

public class PiglinStanceEvent extends LivingEvent {

    public enum Stance {
        NEUTRAL, HOSTILE, DEFAULT
    }

    private Stance stance = Stance.DEFAULT;

    public PiglinStanceEvent(LivingEntity target) {
        super(target);
    }

    public LivingEntity getTarget() {
        return super.getEntityLiving();
    }

    public Stance getStance() {
        return this.stance;
    }

    public void setStance(Stance stance) {
        this.stance = stance;
    }

}
