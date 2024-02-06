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

package org.auioc.mcmod.arnicalib.game.chat;

import org.auioc.mcmod.arnicalib.base.function.StringUnaryOperator;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class MessageHelper {

    private final Component prefix;
    private final StringUnaryOperator i18n;

    public MessageHelper(Component prefix, StringUnaryOperator i18n) {
        this.prefix = prefix;
        this.i18n = i18n;
    }

    public MessageHelper(String modName, StringUnaryOperator i18n) {
        this(Component.literal("[" + modName + "] ").withStyle(ChatFormatting.AQUA), i18n);
    }


    public MessageHelper(StringUnaryOperator i18n) {
        this(Component.empty(), i18n);
    }


    public MutableComponent create(Component message, boolean withPrefix) {
        return withPrefix ? (Component.empty()).append(this.prefix).append(message) : (Component.empty()).append(message);
    }

    public MutableComponent create(String key, Object[] args, boolean withPrefix) {
        return create(Component.translatable(this.i18n.apply(key), args), withPrefix);
    }

    public MutableComponent create(String key, boolean withPrefix) {
        return create(key, TextUtils.NO_ARGS, withPrefix);
    }

}
