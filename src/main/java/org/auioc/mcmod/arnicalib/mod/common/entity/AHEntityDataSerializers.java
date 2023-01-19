package org.auioc.mcmod.arnicalib.mod.common.entity;

import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.game.entity.HEntityDataSerializers;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraftforge.registries.DataSerializerEntry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class AHEntityDataSerializers {

    public static final DeferredRegister<DataSerializerEntry> DATA_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.DATA_SERIALIZERS, ArnicaLib.MOD_ID);

    private static RegistryObject<DataSerializerEntry> register(String id, EntityDataSerializer<?> serializer) {
        return DATA_SERIALIZERS.register(id, () -> new DataSerializerEntry(serializer));
    }

    // ====================================================================== //

    public static final RegistryObject<DataSerializerEntry> VEC3 = register("vec3", HEntityDataSerializers.VEC3);
    public static final RegistryObject<DataSerializerEntry> VECTOR3F = register("vector3f", HEntityDataSerializers.VECTOR3F);
    public static final RegistryObject<DataSerializerEntry> QUATERNION = register("quaternion", HEntityDataSerializers.QUATERNION);

}
