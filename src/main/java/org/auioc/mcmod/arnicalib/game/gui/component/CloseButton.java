package org.auioc.mcmod.arnicalib.game.gui.component;

import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.game.gui.screen.HScreen;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CloseButton<T extends Screen> extends AbstractButton {

    private static final ResourceLocation TEXTURE = ArnicaLib.id("textures/gui/close_button.png");
    private static final int TEXTURE_SIZE = 16;

    public static final int CROSS_SIZE = 5;
    private static final int CROSS_U_OFFSET = 0;
    private static final int CROSS_V_OFFSET = 0;
    private static final int CROSS_HOVERED_U_OFFSET = 5;
    private static final int CROSS_HOVERED_V_OFFSET = 0;

    protected final T parentScreen;

    public CloseButton(int x, int y, T parentScreen) {
        super(x, y, CROSS_SIZE, CROSS_SIZE, new TextComponent("Close button"));
        this.parentScreen = parentScreen;
    }

    @Override
    public final void renderButton(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        this.x = getX();
        this.y = getY();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        boolean flag = isHoveredOrFocused();
        HScreen.blitSquare(
            poseStack, this.x, this.y,
            (flag) ? CROSS_HOVERED_U_OFFSET : CROSS_U_OFFSET,
            (flag) ? CROSS_HOVERED_V_OFFSET : CROSS_V_OFFSET,
            CROSS_SIZE, TEXTURE_SIZE
        );
    }

    protected int getX() { return this.x; }

    protected int getY() { return this.y; }

    @Override
    public void onPress() {
        this.parentScreen.onClose();
    }

    @Override
    public void updateNarration(NarrationElementOutput narrationElementOutput) {
        defaultButtonNarrationText(narrationElementOutput);
        narrationElementOutput.add(NarratedElementType.HINT, getMessage());
    }

}
