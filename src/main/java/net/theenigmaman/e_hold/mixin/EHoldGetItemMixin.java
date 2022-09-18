package net.theenigmaman.e_hold.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.item.Item;
import net.theenigmaman.e_hold.EHold;
import net.theenigmaman.e_hold.util.BlockData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public class EHoldGetItemMixin {

    @Inject(at = @At("HEAD"), method = "updateHeldItems()V")
    private void updateHeldItems(CallbackInfo info) {
        BlockData.updateName(BlockData.item);
        BlockData.item = MinecraftClient.getInstance().player.getHandItems().toString();
    }
}
