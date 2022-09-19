package net.theenigmaman.e_hold.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.theenigmaman.e_hold.EHold;
import net.theenigmaman.e_hold.util.BlockData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class EHoldTextMixin {
    private static final Identifier HUDELEMENT = new Identifier(EHold.MOD_ID,
            "textures/hudelements/hudelement.png");
    private static final Identifier HUDELEMENT2 = new Identifier(EHold.MOD_ID,
            "textures/hudelements/hudelement2.png");
    private static final Identifier HUDELEMENT2TAIL = new Identifier(EHold.MOD_ID,
            "textures/hudelements/hudelement2tail.png");
    private static final Identifier HUDELEMENT2BODY = new Identifier(EHold.MOD_ID,
            "textures/hudelements/hudelement2body.png");

    @Inject(method = "render", at = @At("RETURN"), cancellable = true)
    public void onRender (MatrixStack matrices, float tickDelta, CallbackInfo info) {

        for (int i = 0;i < BlockData.itemM.length(); i++){


            switch (BlockData.itemM.charAt(i)) {
                case 'f':
                case 'k':
                    BlockData.length =BlockData.length+5;
                    break;
                case ' ':
                case 't':
                case 'I':
                    BlockData.length =BlockData.length+4;
                    break;
                case 'l':
                    BlockData.length =BlockData.length+3;
                    break;
                case 'i':
                    BlockData.length =BlockData.length+2;
                    break;
                default:
                    BlockData.length =BlockData.length+6;
            }

        }
        if(BlockData.length != 0) {
            if(BlockData.length > 89 && 92 > BlockData.length) {
                RenderSystem.setShaderTexture(0, HUDELEMENT2TAIL);
                DrawableHelper.drawTexture(matrices, 99,1,0,0,5,15,
                        5,15);
            } else {
                RenderSystem.setShaderTexture(0, HUDELEMENT2TAIL);
                DrawableHelper.drawTexture(matrices, 8 + BlockData.length,1,0,0,5,15,
                        5,15);
            }

            if(BlockData.length < 91) {
                RenderSystem.setShaderTexture(0, HUDELEMENT);
                DrawableHelper.drawTexture(matrices, 0,1,0,0,99,121,
                        99,121);
            } else {
                for (int i = 0;i < BlockData.length -95; i++){
                    RenderSystem.setShaderTexture(0, HUDELEMENT2BODY);
                    DrawableHelper.drawTexture(matrices, 99 + i,1,0,0,5,15,
                            5,15);
                    }
                    RenderSystem.setShaderTexture(0, HUDELEMENT2);
                    DrawableHelper.drawTexture(matrices, 0,1,0,0,99,121,
                            99,121);
                }
            }
            if (BlockData.length < 91) {
                int l = 45 - (BlockData.length / 2);
                MinecraftClient.getInstance().textRenderer.draw(matrices, BlockData.itemM, 7 + l, 5,-1);
            } else {
                MinecraftClient.getInstance().textRenderer.draw(matrices, BlockData.itemM, 7, 5,-1);
            }
            BlockData.length = 0;
    }
}