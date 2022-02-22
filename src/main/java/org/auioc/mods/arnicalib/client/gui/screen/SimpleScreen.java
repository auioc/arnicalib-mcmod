package org.auioc.mods.arnicalib.client.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import org.auioc.mods.arnicalib.api.game.screen.HScreen;
import org.auioc.mods.arnicalib.utils.game.TextUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class SimpleScreen extends HScreen {

    protected static final ResourceLocation TEXTURE = new ResourceLocation("minecraft:textures/gui/demo_background.png");
    protected static final int TEXTURE_SIZE = 256;

    protected static final int DIV_WIDTH = 248;
    protected static final int DIV_HEIGHT = 166;

    protected static final int BG_WIDTH = 248;
    protected static final int BG_HEIGHT = 166;
    protected static final int BG_U_OFFSET = 0;
    protected static final int BG_V_OFFSET = 0;

    protected int divX;
    protected int divY;

    protected boolean isPauseScreen = false;

    public SimpleScreen() {
        super(TextUtils.StringText("ArnicaLib Demo Screen"));
    }

    public SimpleScreen(Component title) {
        super(title);
    }

    @Override
    public boolean isPauseScreen() {
        return isPauseScreen;
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        this.divX = center(this.width, DIV_WIDTH);
        this.divY = center(this.height, DIV_HEIGHT);

        this.renderBackground(poseStack);

        this.subRender(poseStack, mouseX, mouseY, partialTick);

        super.render(poseStack, mouseX, mouseY, partialTick);
    }

    @Override
    public void renderBackground(PoseStack poseStack) {
        super.renderBackground(poseStack);

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        blit(
            poseStack,
            this.divX, this.divY,
            BG_U_OFFSET, BG_V_OFFSET,
            BG_WIDTH, BG_HEIGHT,
            TEXTURE_SIZE, TEXTURE_SIZE
        );
    }

    protected void subRender(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {}


    /*
        0─────────────────────────────────────────────▶ X (U)
        │ ╔═════════════════════════════════════════╗
        │ ║           ┊                             ║ <d>
        │ ║          <b>                            ║
        │ ║           ┊                             ║
        │ ║┄┄┄┄<a>┄┄┄┄┏━━━━━━━━━━━━━━━━━┓           ║
        │ ║           ┃                 ┃<f>        ║
        │ ║           ┃                 ┃           ║
        │ ║           ┃                 ┃           ║
        │ ║           ┃                 ┃           ║
        │ ║           ┗━━━━━━━━━━━━━━━━━┛           ║
        │ ║            <e>                          ║
        │ ║                                         ║
        │ ║                                         ║
        │ ╚═════════════════════════════════════════╝
        ▼  <c>
        Y (V)

        <a>. DIV X (this.divX)
             Background Texture U Offset

        <b>. DIV Y (this.divY)
             Background Texture V Offset

        <c>. Window Width (this.width)
             Texture Width

        <d>. Window Height (this.height)
             Texture Height

        <e>. DIV Width
             Background Texture Width

        <f>. DIV Height
             Background Texture Height
    */

}
