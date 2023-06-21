package org.auioc.mcmod.arnicalib.game.entity;

import org.joml.Quaternionf;
import org.joml.Vector3f;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.world.phys.Vec3;

public class HEntityDataSerializers {

    public static final EntityDataSerializer<Vec3> VEC3 = new EntityDataSerializer<Vec3>() {
        public void write(FriendlyByteBuf buffer, Vec3 vec3) {
            buffer.writeDouble(vec3.x);
            buffer.writeDouble(vec3.y);
            buffer.writeDouble(vec3.z);
        }

        public Vec3 read(FriendlyByteBuf buffer) {
            return new Vec3(buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
        }

        public Vec3 copy(Vec3 vec3) {
            return new Vec3(vec3.x, vec3.y, vec3.z);
        }
    };

    public static final EntityDataSerializer<Vector3f> VECTOR3F = new EntityDataSerializer<Vector3f>() {
        public void write(FriendlyByteBuf buffer, Vector3f vector3f) {
            buffer.writeFloat(vector3f.x());
            buffer.writeFloat(vector3f.y());
            buffer.writeFloat(vector3f.z());
        }

        public Vector3f read(FriendlyByteBuf buffer) {
            return new Vector3f(buffer.readFloat(), buffer.readFloat(), buffer.readFloat());
        }

        public Vector3f copy(Vector3f vector3f) {
            return new Vector3f(vector3f.x(), vector3f.y(), vector3f.z());
        }
    };

    public static final EntityDataSerializer<Quaternionf> QUATERNION_F = new EntityDataSerializer<Quaternionf>() {
        public void write(FriendlyByteBuf buffer, Quaternionf quaternion) {
            buffer.writeFloat(quaternion.x());
            buffer.writeFloat(quaternion.y());
            buffer.writeFloat(quaternion.z());
            buffer.writeFloat(quaternion.w());
        }

        public Quaternionf read(FriendlyByteBuf buffer) {
            return new Quaternionf(buffer.readFloat(), buffer.readFloat(), buffer.readFloat(), buffer.readFloat());
        }

        public Quaternionf copy(Quaternionf quaternion) {
            return new Quaternionf(quaternion);
        }
    };

}
