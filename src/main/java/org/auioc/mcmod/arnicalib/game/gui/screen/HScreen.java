package org.auioc.mcmod.arnicalib.game.gui.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HScreen extends Screen {

    public static final int DEFAULT_TEXTURE_SIZE = 256;

    public HScreen(Component title) {
        super(title);
    }

    public static int center(int a) {
        return a / 2;
    }

    public static int center(int a, int b) {
        return (a - b) / 2;
    }

    public static void blitSquare(PoseStack poseStack, int x, int y, int size) {
        //              X, Y, U, V, W,    H,    TW,   TH
        blit(poseStack, x, y, 0, 0, size, size, DEFAULT_TEXTURE_SIZE, DEFAULT_TEXTURE_SIZE);
    }

    public static void blitSquare(PoseStack poseStack, int x, int y, int size, int textureSize) {
        blit(poseStack, x, y, 0, 0, size, size, textureSize, textureSize);
    }

    public static void blitSquare(PoseStack poseStack, int x, int y, int u, int v, int size, int textureSize) {
        blit(poseStack, x, y, u, v, size, size, textureSize, textureSize);
    }

    public static void blit(PoseStack poseStack, int x, int y, int u, int v, int w, int h, int textureSize) {
        blit(poseStack, x, y, u, v, w, h, textureSize, textureSize);
    }

    public static int adjustColor(int rgbColor) {
        return (rgbColor & -67108864) == 0 ? rgbColor | -16777216 : rgbColor;
    }

    public static void closeScreen() {
        Minecraft.getInstance().setScreen((Screen) null);
    }

}
