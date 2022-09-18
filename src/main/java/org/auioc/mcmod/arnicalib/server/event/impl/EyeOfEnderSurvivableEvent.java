package org.auioc.mcmod.arnicalib.server.event.impl;

import java.util.Random;
import java.util.function.Function;
import org.auioc.mcmod.arnicalib.api.game.event.ServerPlayerEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.projectile.EyeOfEnder;

public class EyeOfEnderSurvivableEvent extends ServerPlayerEvent {

    private final EyeOfEnder eye;
    private Function<Random, Boolean> survivable;

    public EyeOfEnderSurvivableEvent(ServerPlayer player, EyeOfEnder eye) {
        super(player);
        this.eye = eye;
        this.survivable = (random) -> (random.nextInt(5) > 0);
    }

    public EyeOfEnder getEye() {
        return this.eye;
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
