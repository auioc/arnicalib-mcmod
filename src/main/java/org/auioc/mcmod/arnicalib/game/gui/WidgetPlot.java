package org.auioc.mcmod.arnicalib.game.gui;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

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
