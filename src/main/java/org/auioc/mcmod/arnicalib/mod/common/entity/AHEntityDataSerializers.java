package org.auioc.mcmod.arnicalib.mod.common.entity;

import org.auioc.mcmod.arnicalib.ArnicaLib;
import org.auioc.mcmod.arnicalib.game.entity.HEntityDataSerializers;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class AHEntityDataSerializers {

    public static final DeferredRegister<EntityDataSerializer<?>> ENTITY_DATA_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.ENTITY_DATA_SERIALIZERS, ArnicaLib.MOD_ID);

    private static <T> RegistryObject<EntityDataSerializer<T>> register(String id, EntityDataSerializer<T> serializer) {
        return ENTITY_DATA_SERIALIZERS.register(id, () -> serializer);
    }

    // ====================================================================== //

    public static final RegistryObject<EntityDataSerializer<Vec3>> VEC3 = register("vec3", HEntityDataSerializers.VEC3);
    public static final RegistryObject<EntityDataSerializer<Vector3f>> VECTOR3F = register("vector3f", HEntityDataSerializers.VECTOR3F);
    public static final RegistryObject<EntityDataSerializer<Quaternionf>> QUATERNION_F = register("quaternion_f", HEntityDataSerializers.QUATERNION_F);

}
