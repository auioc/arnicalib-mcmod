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

    protected static final int BACKGROUND_SIZE = 1;
    protected static final int BACKGROUND_U_OFFSET = 4;
    protected static final int BACKGROUND_V_OFFSET = 4;

    protected static final int CORNER_SIZE = 4;
    protected static final int TOP_LEFT_CORNER_U_OFFSET = 0;
    protected static final int TOP_LEFT_CORNER_V_OFFSET = 0;
    protected static final int TOP_RIGHT_CORNER_U_OFFSET = 5;
    protected static final int TOP_RIGHT_CORNER_V_OFFSET = 0;
    protected static final int BOTTOM_LEFT_CORNER_U_OFFSET = 0;
    protected static final int BOTTOM_LEFT_CORNER_V_OFFSET = 5;
    protected static final int BOTTOM_RIGHT_CORNER_U_OFFSET = 5;
    protected static final int BOTTOM_RIGHT_CORNER_V_OFFSET = 5;

    protected static final int HORIZONTAL_BORDER_WIDTH = 1;
    protected static final int HORIZONTAL_BORDER_HEIGHT = 4;
    protected static final int VERTICAL_BORDER_WIDTH = 4;
    protected static final int VERTICAL_BORDER_HEIGHT = 1;
    protected static final int TOP_BORDER_U_OFFSET = 4;
    protected static final int TOP_BORDER_V_OFFSET = 0;
    protected static final int BOTTOM_BORDER_U_OFFSET = 4;
    protected static final int BOTTOM_BORDER_V_OFFSET = 5;
    protected static final int LEFT_BORDER_U_OFFSET = 0;
    protected static final int LEFT_BORDER_V_OFFSET = 4;
    protected static final int RIGHT_BORDER_U_OFFSET = 5;
    protected static final int RIGHT_BORDER_V_OFFSET = 4;

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

        for (int xOffset = 0; xOffset < this.divWidth; xOffset++) {
            for (int yOffset = 0; yOffset < this.divHeight; yOffset++) {
                blitSquare(
                    poseStack,
                    this.divX + xOffset, this.divY + yOffset,
                    BACKGROUND_U_OFFSET, BACKGROUND_V_OFFSET, BACKGROUND_SIZE, TEXTURE_SIZE
                );
            }
        }
    }

    private void renderBorderEdge(PoseStack poseStack) {
        for (int xOffset = 0; xOffset < this.divWidth; xOffset++) {
            blitBorderEdge(
                poseStack,
                this.divX + xOffset, this.divY - HORIZONTAL_BORDER_HEIGHT,
                TOP_BORDER_U_OFFSET, TOP_BORDER_V_OFFSET,
                false
            );
            blitBorderEdge(
                poseStack,
                this.divX + xOffset, this.divY + this.divHeight,
                BOTTOM_BORDER_U_OFFSET, BOTTOM_BORDER_V_OFFSET,
                false
            );
        }

        for (int yOffset = 0; yOffset < this.divHeight; yOffset++) {
            blitBorderEdge(
                poseStack,
                this.divX - VERTICAL_BORDER_WIDTH, this.divY + yOffset,
                LEFT_BORDER_U_OFFSET, LEFT_BORDER_V_OFFSET,
                true
            );
            blitBorderEdge(
                poseStack,
                this.divX + this.divWidth, this.divY + yOffset,
                RIGHT_BORDER_U_OFFSET, RIGHT_BORDER_V_OFFSET,
                true
            );
        }
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

    private static void blitBorderEdge(PoseStack poseStack, int x, int y, int u, int v, boolean isVertical) {
        blit(
            poseStack, x, y, u, v,
            isVertical ? VERTICAL_BORDER_WIDTH : HORIZONTAL_BORDER_WIDTH,
            isVertical ? VERTICAL_BORDER_HEIGHT : HORIZONTAL_BORDER_HEIGHT,
            TEXTURE_SIZE
        );
    }

    protected void subRender(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {}

}
