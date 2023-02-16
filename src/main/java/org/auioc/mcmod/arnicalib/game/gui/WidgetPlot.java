package org.auioc.mcmod.arnicalib.game.gui;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @deprecated Use {@link net.minecraft.client.renderer.Rect2i} instead
 *             (by LainIO24)
 */
@Deprecated(since = "5.7.3", forRemoval = true)
@OnlyIn(Dist.CLIENT)
public class WidgetPlot {

    public int x;
    public int y;
    public int w;
    public int h;

    public WidgetPlot(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public static WidgetPlot zero() {
        return new WidgetPlot(0, 0, 0, 0);
    }

}
