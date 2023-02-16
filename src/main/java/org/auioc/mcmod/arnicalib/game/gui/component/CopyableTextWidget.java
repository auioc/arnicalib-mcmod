package org.auioc.mcmod.arnicalib.game.gui.component;

import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CopyableTextWidget extends PlainTextWidget {

    private String value;

    public CopyableTextWidget(int x, int y, int width, int height, Component message, String value) {
        super(x, y, width, height, message);
        this.value = value;
    }

    public CopyableTextWidget(int x, int y, int w, Component message, String value) {
        super(x, y, w, message);
        this.value = value;
    }

    public CopyableTextWidget(int x, int y, Component message, String value) {
        super(x, y, message);
        this.value = value;
    }

    public CopyableTextWidget(int x, int y, int width, int height, Component message) {
        this(x, y, width, height, message, message.getString());
    }

    public CopyableTextWidget(int x, int y, int w, Component message) {
        this(x, y, w, message, message.getString());
    }

    public CopyableTextWidget(int x, int y, Component message) {
        this(x, y, message, message.getString());
    }

    // ====================================================================== //

    public String getValue() { return this.value; }

    public void setValue(String value) { this.value = value; }

    // ====================================================================== //

    public void update(int x, int y, int width, int height, Component message, String value) {
        super.update(x, y, width, height, message);
        this.setValue(value);
    }

    public void update(int x, int y, int width, Component message, String value) {
        super.update(x, y, width, message);
        this.setValue(value);
    }

    public void update(int width, Component message, String value) {
        super.update(width, message);
        this.setValue(value);
    }

    public void update(Component message, String value) {
        super.update(message);
        this.setValue(value);
    }

    @Override
    public void update(int x, int y, int width, int height, Component message) {
        this.update(x, y, width, height, message, message.getString());
    }

    @Override
    public void update(int x, int y, int width, Component message) {
        this.update(x, y, width, message, message.getString());
    }

    @Override
    public void update(int width, Component message) {
        this.update(width, message, message.getString());
    }

    @Override
    public void update(Component message) {
        this.update(message, message.getString());
    }

    // ====================================================================== //

    @Override
    public void onClick(double mouseX, double mouseY) {
        this.minecraft.keyboardHandler.setClipboard(this.getValue());
    }

    @Override
    public List<Component> getTooltips() {
        return List.of(new TranslatableComponent("chat.copy.click"));
    }

}
