package org.auioc.mcmod.arnicalib.api.game.registry;

import org.auioc.mcmod.arnicalib.api.java.function.ThrowableSupplier;

public class RegistryEntryException extends RuntimeException {

    public static final ThrowableSupplier<RegistryEntryException> UNKNOWN_ITEM = (id) -> () -> new Unknown("item", id);
    public static final ThrowableSupplier<RegistryEntryException> UNKNOWN_BLOCK = (id) -> () -> new Unknown("block", id);
    public static final ThrowableSupplier<RegistryEntryException> UNKNOWN_ENTITY_TYPE = (id) -> () -> new Unknown("entity type", id);
    public static final ThrowableSupplier<RegistryEntryException> UNKNOWN_MOB_EFFECT = (id) -> () -> new Unknown("mob effect", id);
    public static final ThrowableSupplier<RegistryEntryException> UNKNOWN_POTION = (id) -> () -> new Unknown("potion", id);
    public static final ThrowableSupplier<RegistryEntryException> UNKNOWN_SOUND_EVENT = (id) -> () -> new Unknown("sound event", id);

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
