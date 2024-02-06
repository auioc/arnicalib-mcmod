/*
 * Copyright (C) 2022-2024 AUIOC.ORG
 *
 * This file is part of ArnicaLib, a mod made for Minecraft.
 *
 * ArnicaLib is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <https://www.gnu.org/licenses/>.
 */

package org.auioc.mcmod.arnicalib.game.tag;

import org.auioc.mcmod.arnicalib.ArnicaLib;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class HBlockTags {

    public static final TagKey<Block> INSTABREAKABLE = create("instabreakable");
    public static final TagKey<Block> RANDOMLY_TICKABLE = create("randomly_tickable");
    public static final TagKey<Block> LIGHT = create("light");

    public static void init() { }

    public static TagKey<Block> create(String _path) {
        return TagCreator.block(ArnicaLib.id(_path));
    }

}
