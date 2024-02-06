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

package org.auioc.mcmod.arnicalib.game.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.model.data.ModelData;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class RenderUtils {

    public static void scale(PoseStack poseStack, Vector3f scale) {
        poseStack.scale(scale.x(), scale.y(), scale.z());
    }

    public static void translate(PoseStack poseStack, Vec3 translation) {
        poseStack.translate(translation.x, translation.y, translation.z);
    }

    public static void translate(PoseStack poseStack, float x, float y, float z) {
        poseStack.last().pose().translate(x, y, z);
    }

    public static void translate(PoseStack poseStack, Vector3f translation) {
        translate(poseStack, translation.x(), translation.y(), translation.z());
    }

    public static void rotate(PoseStack poseStack, Quaternionf rotation) {
        poseStack.mulPose(rotation);
    }

    public static void rotate(PoseStack poseStack, float x, float y, float z) {
        // new Quaternionf().rotateX
        // poseStack.mulPose(new Quaternionf(x, y, z, true)); // TODO
    }

    public static void rotate(PoseStack poseStack, Vector3f rotation) {
        rotate(poseStack, rotation.x(), rotation.y(), rotation.z());
    }

    // ============================================================================================================== //

    public static void renderSingleBlock(
        @Nullable BlockState blockState, BakedModel model, ModelData modelData,
        BlockRenderDispatcher blockRenderer, RenderType renderType,
        PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay
    ) {
        blockRenderer.getModelRenderer().renderModel(poseStack.last(), bufferSource.getBuffer(renderType), blockState, model, 1.0F, 1.0F, 1.0F, combinedLight, combinedOverlay, modelData, renderType);
    }

    public static void renderSingleBlock(
        BlockState blockState, BakedModel model, ModelData modelData,
        BlockRenderDispatcher blockRenderer,
        PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay
    ) {
        renderSingleBlock(blockState, model, modelData, blockRenderer, ItemBlockRenderTypes.getRenderType(blockState, false), poseStack, bufferSource, combinedLight, combinedOverlay);
    }

}
