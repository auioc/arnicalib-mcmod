package org.auioc.mcmod.arnicalib.game.gui.component;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ContainerCloseButton extends CloseButton<AbstractContainerScreen<?>> {

    private final int paddingX;
    private final int paddingY;

    public ContainerCloseButton(int paddingX, int paddingY, AbstractContainerScreen<?> parentScreen) {
        super(0, 0, parentScreen);
        this.paddingX = paddingX;
        this.paddingY = paddingY;
    }

    @Override
    protected int getX() {
        return this.parentScreen.getGuiLeft() + this.parentScreen.getXSize() - this.paddingX - this.width;
    }

    @Override
    protected int getY() {
        return this.parentScreen.getGuiTop() + this.paddingY;
    }

}
