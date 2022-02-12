package org.auioc.mods.arnicalib.server.event.impl;

import java.util.Random;
import java.util.function.Function;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.projectile.EyeOfEnder;
import net.minecraftforge.eventbus.api.Event;

public class SetEyeOfEnderSurvivableEvent extends Event {

    private final EyeOfEnder eye;
    private final ServerPlayer player;
    private Function<Random, Boolean> survivable;

    public SetEyeOfEnderSurvivableEvent(EyeOfEnder eye, ServerPlayer player) {
        this.eye = eye;
        this.player = player;
        this.survivable = (random) -> (random.nextInt(5) > 0);
    }

    public EyeOfEnder getEye() {
        return this.eye;
    }

    public ServerPlayer getPlayer() {
        return this.player;
    }

    public Function<Random, Boolean> getSurvivable() {
        return this.survivable;
    }

    public void setSurvivable(Function<Random, Boolean> survivable) {
        this.survivable = survivable;
    }

    public void setSurvivable(boolean survivable) {
        this.survivable = (random) -> survivable;
    }

}
