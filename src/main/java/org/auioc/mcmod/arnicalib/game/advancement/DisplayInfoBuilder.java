package org.auioc.mcmod.arnicalib.game.advancement;

import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.Optional;

public class DisplayInfoBuilder {

    private Component title = Component.literal("advancement");
    private Component description = Component.literal("advancement.description");
    private ItemStack icon = ItemStack.EMPTY;
    @Nullable
    private ResourceLocation background = null;
    private AdvancementType frame = AdvancementType.TASK;
    private boolean showToast = false;
    private boolean announceChat = false;
    private boolean hidden = false;

    public DisplayInfoBuilder() { }

    public DisplayInfo build() {
        return new DisplayInfo(icon, title, description, Optional.ofNullable(background), frame, showToast, hidden, announceChat);
    }

    public DisplayInfoBuilder title(Component title) {
        this.title = title;
        return this;
    }

    public DisplayInfoBuilder title(String key) {
        title(Component.translatable(key));
        return this;
    }

    public DisplayInfoBuilder description(Component description) {
        this.description = description;
        return this;
    }

    public DisplayInfoBuilder description(String key) {
        description(Component.translatable(key));
        return this;
    }

    public DisplayInfoBuilder titleAndDescription(String key) {
        title(key);
        description(key + ".description");
        return this;
    }

    public DisplayInfoBuilder icon(ItemStack icon) {
        this.icon = icon;
        return this;
    }

    public DisplayInfoBuilder icon(ItemLike icon) {
        icon(new ItemStack(icon.asItem()));
        return this;
    }

    public DisplayInfoBuilder background(ResourceLocation background) {
        this.background = background;
        return this;
    }

    public DisplayInfoBuilder background(String background) {
        background(new ResourceLocation(background));
        return this;
    }

    public DisplayInfoBuilder frame(AdvancementType frame) {
        this.frame = frame;
        return this;
    }

    public DisplayInfoBuilder taskFrame() {
        frame(AdvancementType.TASK);
        return this;
    }

    public DisplayInfoBuilder challengeFrame() {
        frame(AdvancementType.CHALLENGE);
        return this;
    }

    public DisplayInfoBuilder goalFrame() {
        frame(AdvancementType.GOAL);
        return this;
    }

    public DisplayInfoBuilder showToast() {
        this.showToast = true;
        return this;
    }

    public DisplayInfoBuilder announceChat() {
        this.announceChat = true;
        return this;
    }

    public DisplayInfoBuilder hidden() {
        this.hidden = true;
        return this;
    }

}
