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

package org.auioc.mcmod.arnicalib.game.registry;

import org.auioc.mcmod.arnicalib.base.function.ThrowableSupplier;

public class RegistryEntryException extends RuntimeException {

    public static final ThrowableSupplier<RegistryEntryException> UNKNOWN_ITEM = (id) -> () -> new Unknown("item", id);
    public static final ThrowableSupplier<RegistryEntryException> UNKNOWN_BLOCK = (id) -> () -> new Unknown("block", id);
    public static final ThrowableSupplier<RegistryEntryException> UNKNOWN_ENTITY_TYPE = (id) -> () -> new Unknown("entity type", id);
    public static final ThrowableSupplier<RegistryEntryException> UNKNOWN_MOB_EFFECT = (id) -> () -> new Unknown("mob effect", id);
    public static final ThrowableSupplier<RegistryEntryException> UNKNOWN_POTION = (id) -> () -> new Unknown("potion", id);
    public static final ThrowableSupplier<RegistryEntryException> UNKNOWN_SOUND_EVENT = (id) -> () -> new Unknown("sound event", id);
    public static final ThrowableSupplier<RegistryEntryException> UNKNOWN_ENCHANTMENT = (id) -> () -> new Unknown("enchantment", id);

    public RegistryEntryException(String message) {
        super(message);
    }

    public RegistryEntryException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegistryEntryException(Throwable cause) {
        super(cause);
    }

    public static class Unknown extends RegistryEntryException {

        public Unknown(String type, String id) {
            super("Unknown " + type + " '" + id + "'");
        }

    }

}
