package org.auioc.mcmod.arnicalib.game.advancement;

import javax.annotation.Nullable;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class DisplayInfoBuilder {

    private Component title = new TextComponent("advancement");
    private Component description = new TextComponent("advancement.description");
    private ItemStack icon = ItemStack.EMPTY;
    @Nullable
    private ResourceLocation background = null;
    private FrameType frame = FrameType.TASK;
    private boolean showToast = false;
    private boolean announceChat = false;
    private boolean hidden = false;

    public DisplayInfoBuilder() {}

    public DisplayInfo build() {
        return new DisplayInfo(icon, title, description, background, frame, showToast, hidden, announceChat);
    }

    public DisplayInfoBuilder title(Component title) {
        this.title = title;
        return this;
    }

    public DisplayInfoBuilder title(String key) {
        title(new TranslatableComponent(key));
        return this;
    }

    public DisplayInfoBuilder description(Component description) {
        this.description = description;
        return this;
    }

    public DisplayInfoBuilder description(String key) {
        description(new TranslatableComponent(key));
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

    public DisplayInfoBuilder frame(FrameType frame) {
        this.frame = frame;
        return this;
    }

    public DisplayInfoBuilder taskFrame() {
        frame(FrameType.TASK);
        return this;
    }

    public DisplayInfoBuilder challengeFrame() {
        frame(FrameType.CHALLENGE);
        return this;
    }

    public DisplayInfoBuilder goalFrame() {
        frame(FrameType.GOAL);
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
