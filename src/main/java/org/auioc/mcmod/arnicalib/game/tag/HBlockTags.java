package org.auioc.mcmod.arnicalib.game.tag;

import org.auioc.mcmod.arnicalib.ArnicaLib;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class HBlockTags {

    public static final TagKey<Block> INSTABREAKABLE = create("instabreakable");
    public static final TagKey<Block> RANDOMLY_TICKABLE = create("randomly_tickable");
    public static final TagKey<Block> LIGHT = create("light");

    public static void init() {}

    public static TagKey<Block> create(String _path) {
        return TagCreator.block(ArnicaLib.id(_path));
    }

}
