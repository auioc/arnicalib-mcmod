package org.auioc.mcmod.arnicalib.server.event.impl;

import org.auioc.mcmod.arnicalib.api.game.event.ServerPlayerEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public class FishingRodCastEvent extends ServerPlayerEvent {

    private final ServerLevel level;
    private final ItemStack fishingRod;

    public FishingRodCastEvent(ServerPlayer player, ServerLevel level, ItemStack fishingRod) {
        super(player);
        this.level = level;
        this.fishingRod = fishingRod;
    }

    public ServerLevel getLevel() {
        return this.level;
    }

    public ItemStack getFishingRod() {
        return this.fishingRod;
    }

    public static class Pre extends FishingRodCastEvent {

        private int speedBonus;
        private int luckBonus;

        public Pre(ServerPlayer player, ServerLevel level, ItemStack fishingRod, int speedBonus, int luckBonus) {
            super(player, level, fishingRod);
            this.speedBonus = speedBonus;
            this.luckBonus = luckBonus;
        }

        public int getSpeedBonus() {
            return this.speedBonus;
        }

        public void setSpeedBonus(int speedBonus) {
            this.speedBonus = speedBonus;
        }

        public int getLuckBonus() {
            return this.luckBonus;
        }

        public void setLuckBonus(int luckBonus) {
            this.luckBonus = luckBonus;
        }

    }

}
