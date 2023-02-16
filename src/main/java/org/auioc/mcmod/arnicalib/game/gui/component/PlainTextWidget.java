package org.auioc.mcmod.arnicalib.game.gui.component;

import java.util.List;
import javax.annotation.Nullable;
import org.auioc.mcmod.arnicalib.game.gui.GuiUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PlainTextWidget extends AbstractWidget {

    protected final Minecraft minecraft;
    protected final Font font;

    public PlainTextWidget(int x, int y, int width, int height, Component message) {
        super(x, y, width, height, message);
        this.minecraft = Minecraft.getInstance();
        this.font = this.minecraft.font;
    }

    public PlainTextWidget(int x, int y, int w, Component message) {
        this(x, y, w, 0, message);
        this.update(x, y, w, message);
    }

    public PlainTextWidget(int x, int y, Component message) {
        this(x, y, 0, 0, message);
        this.update(x, y, font.width(message), font.lineHeight, message);
    }

    // ====================================================================== //

    @Override
    public void renderButton(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        GuiUtils.drawTextWarp(poseStack, font, getMessage(), x, y, width, (0 | Mth.ceil(this.alpha * 255.0F) << 24));
        if (this.isHoveredOrFocused()) {
            this.renderToolTip(poseStack, mouseX, mouseY);
        }
    }

    @Override
    public void renderToolTip(PoseStack poseStack, int mouseX, int mouseY) {
        var tooltips = getTooltips();
        var screen = minecraft.screen;
        if (tooltips != null && screen != null) {
            screen.renderComponentTooltip(poseStack, tooltips, mouseX, mouseY);
        }
    }

    // ====================================================================== //

    public void update(int x, int y, int width, int height, Component message) {
        this.x = x;
        this.y = y;
        this.setWidth(width);
        this.setHeight(height);
        this.setMessage(message);
    }

    public void update(int x, int y, int width, Component message) {
        update(x, y, width, (font.split(message, width).size() * font.lineHeight), message);
    }

    public void update(int width, Component message) {
        update(this.x, this.y, width, message);
    }

    public void update(Component message) {
        update(this.x, this.y, font.width(message), font.lineHeight, message);
    }

    // ====================================================================== //

    @Override
    public void onClick(double mouseX, double mouseY) {}

    @Nullable
    public List<Component> getTooltips() { return null; }

    // ====================================================================== //

    @Override
    public void updateNarration(NarrationElementOutput narrationElementOutput) {
        this.defaultButtonNarrationText(narrationElementOutput);
    }

}
