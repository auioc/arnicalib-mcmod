package org.auioc.mcmod.arnicalib.utils.game;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.auioc.mcmod.arnicalib.api.game.registry.IHRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryObject;

public class RegistryUtils {

    private RegistryUtils() {}

    public static <T extends IForgeRegistryEntry<T>> List<T> getAllRegistryObjects(DeferredRegister<T> deferredRegister) {
        return deferredRegister.getEntries().stream().map(RegistryObject::get).toList();
    }

    /**
     * For special use only,
     * please use {@link org.auioc.mcmod.arnicalib.utils.game.RegistryUtils#getAllRegistryObjects(DeferredRegister)} instead generally.
     */
    @SuppressWarnings("unchecked")
    public static <T extends IForgeRegistryEntry<? super T>> List<T> getAllRegistryObjects(Class<? extends IHRegistry> clazz, Class<T> type) {
        List<T> list = new ArrayList<T>();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!(Modifier.isFinal(field.getModifiers()))) {
                continue;
            }

            Type gType = field.getGenericType();
            if (!(gType instanceof ParameterizedType)) {
                continue;
            }

            ParameterizedType pType = (ParameterizedType) gType;
            if (!pType.getRawType().equals(RegistryObject.class)) {
                continue;
            }

            if (!pType.getActualTypeArguments()[0].equals(type)) {
                continue;
            }

            try {
                Object object = field.get(null);
                list.add(((RegistryObject<T>) object).get());
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

}
