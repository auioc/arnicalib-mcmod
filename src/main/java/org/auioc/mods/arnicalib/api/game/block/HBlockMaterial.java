package org.auioc.mods.arnicalib.api.game.block;

import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.material.PushReaction;

public class HBlockMaterial {

    private PushReaction pushReaction = PushReaction.NORMAL;
    private boolean blocksMotion = true;
    private boolean flammable = false;
    private boolean liquid = false;
    private boolean replaceable = false;
    private boolean solid = true;
    private MaterialColor color = MaterialColor.NONE;
    private boolean solidBlocking = true;

    public HBlockMaterial() {}

    public HBlockMaterial color(MaterialColor color) {
        this.color = color;
        return this;
    }

    public HBlockMaterial liquid() {
        this.liquid = true;
        return this;
    }

    public HBlockMaterial nonSolid() {
        this.solid = false;
        return this;
    }

    public HBlockMaterial noCollider() {
        this.blocksMotion = false;
        return this;
    }

    public HBlockMaterial notSolidBlocking() {
        this.solidBlocking = false;
        return this;
    }

    public HBlockMaterial flammable() {
        this.flammable = true;
        return this;
    }

    public HBlockMaterial replaceable() {
        this.replaceable = true;
        return this;
    }

    public HBlockMaterial destroyOnPush() {
        this.pushReaction = PushReaction.DESTROY;
        return this;
    }

    public HBlockMaterial notPushable() {
        this.pushReaction = PushReaction.BLOCK;
        return this;
    }

    public HBlockMaterial pushOnly() {
        this.pushReaction = PushReaction.PUSH_ONLY;
        return this;
    }

    public Material build() {
        return new Material(this.color, this.liquid, this.solid, this.blocksMotion, this.solidBlocking, this.flammable, this.replaceable, this.pushReaction);
    }

}
