package org.auioc.mcmod.arnicalib.game.gui.screen;

import java.awt.Rectangle;
import java.util.function.IntUnaryOperator;
import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.game.gui.component.CloseButton;
import org.auioc.mcmod.arnicalib.game.resource.ResourceUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SimpleScreen extends HScreen {

    private static final ResourceLocation TEXTURE = ArnicaLib.id("textures/gui/simple_screen.png");
    private static final int TEXTURE_SIZE = 16;

    protected static final int DEFAULT_BOX_WIDTH = 248;
    protected static final int DEFAULT_BOX_HEIGHT = 166;

    private static final int CLOSE_BUTTON_PADDING = 3;
    private static final int BORDER_SIZE = 4;
    private static final int BORDER_1_SIZE = 1;
    private static final int BORDER_2_SIZE = 3;
    private static final int LEFT_CORNER_U = 0;
    private static final int TOP_CORNER_V = 0;
    private static final int RIGHT_CORNER_U = 4;
    private static final int BOTTOM_CORNER_V = 4;

    private static final IntUnaryOperator COLOR_MAP_U = (w) -> 0;
    private static final IntUnaryOperator COLOR_MAP_V = (h) -> h - 1;
    private static final int COLOR_MAP_WIDTH = 4;
    private static final int COLOR_MAP_HEIGHT = 1;
    protected static int colorFF = 0xFFFFFFFF;
    protected static int colorC6 = 0xFFC6C6C6;
    protected static int color55 = 0xFF555555;
    protected static int color00 = 0xFF000000;

    protected final int boxWidth;
    protected final int boxHeight;
    protected final boolean hasCloseButton;

    protected int boxX1;
    protected int boxX2;
    protected int boxY1;
    protected int boxY2;

    public SimpleScreen(Component title, int boxWidth, int boxHeight, boolean hasCloseButton) {
        super(title);
        this.boxWidth = boxWidth;
        this.boxHeight = boxHeight;
        this.hasCloseButton = hasCloseButton;
    }

    public SimpleScreen(Component title, int boxWidth, int boxHeight) {
        this(title, boxWidth, boxHeight, false);
    }

    public SimpleScreen(Component title, boolean hasCloseButton) {
        this(title, DEFAULT_BOX_WIDTH, DEFAULT_BOX_HEIGHT, hasCloseButton);
    }

    public SimpleScreen(Component title) {
        this(title, false);
    }

    public SimpleScreen() {
        this(Component.literal(SimpleScreen.class.getSimpleName()));
    }

    @Override
    public boolean isPauseScreen() { return false; }

    @Override
    protected final void init() {
        calcBoxVertices();
        if (this.hasCloseButton) {
            renderableWidget(
                new CloseButton<Screen>(
                    boxX2 - CloseButton.CROSS_SIZE - CLOSE_BUTTON_PADDING,
                    boxY1 + CLOSE_BUTTON_PADDING,
                    this
                )
            );
        }
        subInit();
    }

    protected void subInit() {}

    @Override
    public final void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        calcBoxVertices();
        renderBackground(guiGraphics);
        subRender(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    protected void subRender(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {}

    protected <T extends GuiEventListener & Renderable & NarratableEntry> T renderableWidget(T widget) {
        addRenderableWidget(widget);
        return widget;
    }

    private void calcBoxVertices() {
        boxX1 = center(width, boxWidth);
        boxX2 = boxX1 + boxWidth;
        boxY1 = center(height, boxHeight);
        boxY2 = boxY1 + boxHeight;
    }

    public final void renderBackground(GuiGraphics guiGraphics) {
        super.renderBackground(guiGraphics);
        renderBorderEdge(guiGraphics);
        renderBorderCorner(guiGraphics);
        guiGraphics.fill(boxX1, boxY1, boxX2, boxY2, colorC6);
    }

    private void renderBorderEdge(GuiGraphics guiGraphics) {
        //@formatter:off
         guiGraphics.fill(boxX1 - BORDER_SIZE,   boxY1,                 boxX2 + BORDER_SIZE,   boxY2,                 color00);
         guiGraphics.fill(boxX1,                 boxY1 - BORDER_SIZE,   boxX2,                 boxY2 + BORDER_SIZE,   color00);
         guiGraphics.fill(boxX1,                 boxY1,                 boxX2 + BORDER_2_SIZE, boxY2 + BORDER_2_SIZE, color55);
         guiGraphics.fill(boxX1 - BORDER_2_SIZE, boxY1 - BORDER_2_SIZE, boxX2,                 boxY2,                 colorFF);
         guiGraphics.fill(boxX1 - BORDER_1_SIZE, boxY1 - BORDER_1_SIZE, boxX2 + BORDER_1_SIZE, boxY2 + BORDER_1_SIZE, colorC6);
        //@formatter:on
    }

    private void renderBorderCorner(GuiGraphics guiGraphics) {
        //@formatter:off
        blitBorderCorner(guiGraphics, boxX1 - BORDER_SIZE, boxY1 - BORDER_SIZE, LEFT_CORNER_U,  TOP_CORNER_V);
        blitBorderCorner(guiGraphics, boxX2,               boxY1 - BORDER_SIZE, RIGHT_CORNER_U, TOP_CORNER_V);
        blitBorderCorner(guiGraphics, boxX1 - BORDER_SIZE, boxY2,               LEFT_CORNER_U,  BOTTOM_CORNER_V);
        blitBorderCorner(guiGraphics, boxX2,               boxY2,               RIGHT_CORNER_U, BOTTOM_CORNER_V);
        //@formatter:on
    }

    // ====================================================================== //

    private static void blitBorderCorner(GuiGraphics guiGraphics, int x, int y, int u, int v) {
        blitSquare(guiGraphics, TEXTURE, x, y, u, v, BORDER_SIZE, TEXTURE_SIZE);
    }

    // ====================================================================== //

    public static void reloadColors() {
        var colors = ResourceUtils.getARGBColorsFromTexture(
            TEXTURE, (w, h) -> new Rectangle(
                COLOR_MAP_U.applyAsInt(w), COLOR_MAP_V.applyAsInt(h),
                COLOR_MAP_WIDTH, COLOR_MAP_HEIGHT
            )
        );
        boolean flag = (colors != null) && (colors.length == 4);
        colorFF = flag ? colors[0] : 0xFFFFFFFF;
        colorC6 = flag ? colors[1] : 0xFFC6C6C6;
        color55 = flag ? colors[2] : 0xFF555555;
        color00 = flag ? colors[3] : 0xFF000000;
    }

}
