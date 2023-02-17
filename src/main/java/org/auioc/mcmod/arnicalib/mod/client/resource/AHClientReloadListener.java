package org.auioc.mcmod.arnicalib.mod.client.resource;

import org.auioc.mcmod.arnicalib.game.gui.screen.SimpleScreen;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

public class AHClientReloadListener extends SimplePreparableReloadListener<Void> {

    @Override
    protected Void prepare(ResourceManager resourceManager, ProfilerFiller profiler) { return null; }

    @Override
    protected void apply(Void v, ResourceManager resourceManager, ProfilerFiller profiler) {
        SimpleScreen.reloadColors();
    }

}
