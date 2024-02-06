package org.auioc.mcmod.arnicalib.game.mod;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.fml.IExtensionPoint.DisplayTest;
import net.neoforged.fml.ModLoadingContext;

public class ExtensionPointUtils {

    @OnlyIn(Dist.CLIENT)
    public static void clientOnly() {
        ModLoadingContext.get().registerExtensionPoint(DisplayTest.class, () -> new DisplayTest(() -> "ANY", (remote, isServer) -> isServer));
    }

    public static void serverOnly() {
        ModLoadingContext.get().registerExtensionPoint(DisplayTest.class, () -> new DisplayTest(() -> DisplayTest.IGNORESERVERONLY, (s, b) -> true));
    }

}
