package org.auioc.mcmod.arnicalib.game.mod;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.IExtensionPoint.DisplayTest;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.network.NetworkConstants;

public class ExtensionPointUtils {

    @OnlyIn(Dist.CLIENT)
    public static void clientOnly() {
        ModLoadingContext.get().registerExtensionPoint(DisplayTest.class, () -> new DisplayTest(() -> "ANY", (remote, isServer) -> isServer));
    }

    public static void serverOnly() {
        ModLoadingContext.get().registerExtensionPoint(DisplayTest.class, () -> new DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (s, b) -> true));
    }

}
