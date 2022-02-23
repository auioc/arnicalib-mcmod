package org.auioc.mods.arnicalib.client.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import org.auioc.mods.arnicalib.ArnicaLib;
import org.auioc.mods.arnicalib.api.game.screen.HScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class SimpleScreen extends HScreen {

    protected static final ResourceLocation TEXTURE = ArnicaLib.id("textures/gui/simple_screen.png");
    protected static final int TEXTURE_SIZE = 16;

    protected static final int CORNER_SIZE = 4;
    protected static final int TOP_LEFT_CORNER_U_OFFSET = 0;
    protected static final int TOP_LEFT_CORNER_V_OFFSET = 0;
    protected static final int TOP_RIGHT_CORNER_U_OFFSET = 5;
    protected static final int TOP_RIGHT_CORNER_V_OFFSET = 0;
    protected static final int BOTTOM_LEFT_CORNER_U_OFFSET = 0;
    protected static final int BOTTOM_LEFT_CORNER_V_OFFSET = 5;
    protected static final int BOTTOM_RIGHT_CORNER_U_OFFSET = 5;
    protected static final int BOTTOM_RIGHT_CORNER_V_OFFSET = 5;

    protected static final int COLOR_BLACK = adjustColor(0x000000);
    protected static final int COLOR_WHITE = adjustColor(0xFFFFFF);
    protected static final int COLOR_GRAY = adjustColor(0x555555);
    protected static final int COLOR_BACKGROUND = adjustColor(0xC6C6C6);

    protected final int divWidth;
    protected final int divHeight;

    protected int divX;
    protected int divY;

    protected boolean isPauseScreen = false;

    public SimpleScreen(Component title, int divWidth, int divHeight) {
        super(title);
        this.divWidth = divWidth;
        this.divHeight = divHeight;
    }

    @Override
    public boolean isPauseScreen() {
        return isPauseScreen;
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        this.divX = center(this.width, this.divWidth);
        this.divY = center(this.height, this.divHeight);

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        this.renderBackground(poseStack);

        this.renderBorderEdge(poseStack);
        this.renderBorderCorner(poseStack);

        this.subRender(poseStack, mouseX, mouseY, partialTick);

        super.render(poseStack, mouseX, mouseY, partialTick);
    }

    @Override
    public void renderBackground(PoseStack poseStack) {
        super.renderBackground(poseStack);

        fill(
            poseStack,
            this.divX, this.divY,
            this.divX + this.divWidth, this.divY + this.divHeight,
            COLOR_BACKGROUND
        );
    }

    private void renderBorderEdge(PoseStack poseStack) {
        drawBorderEdge(
            poseStack,
            this.divX, this.divY,
            this.divX + this.divWidth, this.divY,
            false, false
        );
        drawBorderEdge(
            poseStack,
            this.divX, this.divY + this.divHeight,
            this.divX + this.divWidth, this.divY + this.divHeight,
            false, true
        );
        drawBorderEdge(
            poseStack,
            this.divX, this.divY,
            this.divX, this.divY + this.divHeight,
            true, false
        );
        drawBorderEdge(
            poseStack,
            this.divX + this.divWidth, this.divY,
            this.divX + this.divWidth, this.divY + this.divHeight,
            true, true
        );
    }

    private static void drawBorderEdge(PoseStack poseStack, int x1, int y1, int x2, int y2, boolean isVertical, boolean isPositive) {
        int p = isPositive ? 1 : -1;
        fill(
            poseStack,
            x1 + (isVertical ? 4 * p : 0), y1 + (isVertical ? 0 : 4 * p),
            x2 + (isVertical ? 3 * p : 0), y2 + (isVertical ? 0 : 3 * p),
            COLOR_BLACK
        );
        fill(
            poseStack,
            x1 + (isVertical ? 3 * p : 0), y1 + (isVertical ? 0 : 3 * p),
            x2 + (isVertical ? 1 * p : 0), y2 + (isVertical ? 0 : 1 * p),
            isPositive ? COLOR_GRAY : COLOR_WHITE
        );
        fill(
            poseStack,
            x1 + (isVertical ? p : 0), y1 + (isVertical ? 0 : p),
            x2, y2,
            COLOR_BACKGROUND
        );
    }

    private void renderBorderCorner(PoseStack poseStack) {
        blitBorderCorner(
            poseStack,
            this.divX - CORNER_SIZE, this.divY - CORNER_SIZE,
            TOP_LEFT_CORNER_U_OFFSET, TOP_LEFT_CORNER_V_OFFSET

        );
        blitBorderCorner(
            poseStack,
            this.divX + this.divWidth, this.divY - CORNER_SIZE,
            TOP_RIGHT_CORNER_U_OFFSET, TOP_RIGHT_CORNER_V_OFFSET

        );
        blitBorderCorner(
            poseStack,
            this.divX - CORNER_SIZE, this.divY + this.divHeight,
            BOTTOM_LEFT_CORNER_U_OFFSET, BOTTOM_LEFT_CORNER_V_OFFSET

        );
        blitBorderCorner(
            poseStack,
            this.divX + this.divWidth, this.divY + this.divHeight,
            BOTTOM_RIGHT_CORNER_U_OFFSET, BOTTOM_RIGHT_CORNER_V_OFFSET
        );

    }

    private static void blitBorderCorner(PoseStack poseStack, int x, int y, int u, int v) {
        blitSquare(poseStack, x, y, u, v, CORNER_SIZE, TEXTURE_SIZE);
    }

    protected void subRender(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {}

}
