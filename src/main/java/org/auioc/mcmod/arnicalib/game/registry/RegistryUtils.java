package org.auioc.mcmod.arnicalib.game.registry;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryObject;

public class RegistryUtils {

    private RegistryUtils() {}

    public static <T extends IForgeRegistryEntry<T>> List<T> getAllRegistryObjects(DeferredRegister<T> deferredRegister) {
        return deferredRegister.getEntries().stream().map(RegistryObject::get).toList();
    }

    // ============================================================================================================== //

    public static <K extends ForgeRegistryEntry<?>> Comparator<K> comparator() {
        return new Comparator<K>() {
            @Override
            public int compare(K o1, K o2) { return o1.getRegistryName().compareTo(o2.getRegistryName()); }
        };
    }

    public static <K extends ForgeRegistryEntry<?>, V> Comparator<Map.Entry<K, V>> mapComparator() {
        return Map.Entry.comparingByKey(comparator());
    }

    // ====================================================================== //

    public static <K extends ForgeRegistryEntry<?>, V> LinkedHashMap<K, V> sortMap(Map<K, V> map) {
        var linkedMap = new LinkedHashMap<K, V>(map.size(), 1.0F);
        map.entrySet().stream().sorted(mapComparator()).forEach((e) -> linkedMap.put(e.getKey(), e.getValue()));
        return linkedMap;
    }

    public static <T extends ForgeRegistryEntry<?>> List<T> sortList(List<T> list) {
        return list.stream().sorted(comparator()).toList();
    }

    // ============================================================================================================== //

    /**
     * For special use only,
     * please use {@link #getAllRegistryObjects(DeferredRegister)} instead generally.
     */
    @Deprecated
    @SuppressWarnings("unchecked")
    public static <T extends IForgeRegistryEntry<? super T>> List<T> getAllRegistryObjects(Class<?> clazz, Class<T> type) {
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
