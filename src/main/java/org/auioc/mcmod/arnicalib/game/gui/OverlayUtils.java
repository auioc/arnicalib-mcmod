package org.auioc.mcmod.arnicalib.game.gui;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.auioc.mcmod.arnicalib.base.word.WordUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.client.gui.OverlayRegistry.OverlayEntry;

@OnlyIn(Dist.CLIENT)
public class OverlayUtils {

    public static Map<Boolean, List<OverlayEntry>> partitionByEnabled(List<OverlayEntry> entries) {
        return entries.stream().collect(Collectors.partitioningBy(OverlayEntry::isEnabled));
    }

    public static void toggle(OverlayEntry entry) {
        OverlayRegistry.enableOverlay(entry.getOverlay(), !entry.isEnabled());
    }

    public static void toggle(List<OverlayEntry> entries) {
        entries.stream().forEach(OverlayUtils::toggle);
    }

    public static void enableOverlays(List<OverlayEntry> entries, boolean enable) {
        entries.stream().map(OverlayEntry::getOverlay).forEach((e) -> OverlayRegistry.enableOverlay(e, enable));
    }

    public static String formatName(String name) {
        return WordUtils.toCamelCase(name.toLowerCase().replace(" ", "_"));
    }

    public static String getName(OverlayEntry entry) {
        return formatName(entry.getDisplayName());
    }

}
