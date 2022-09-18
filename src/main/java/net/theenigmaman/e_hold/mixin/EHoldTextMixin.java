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
    private static final Identifier HUDELEMENT3 = new Identifier(EHold.MOD_ID,
            "textures/hudelements/hudelement3.png");
    private static final Identifier HUDELEMENT2BODY2 = new Identifier(EHold.MOD_ID,
            "textures/hudelements/hudelement2body2.png");
    private static final Identifier HUDELEMENT2BODY3 = new Identifier(EHold.MOD_ID,
            "textures/hudelements/hudelement2body3.png");
    private static final Identifier HUDELEMENT2BODY4 = new Identifier(EHold.MOD_ID,
            "textures/hudelements/hudelement2body4.png");
    private static final Identifier HUDELEMENT2HEAD = new Identifier(EHold.MOD_ID,
            "textures/hudelements/hudelement2head.png");
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

            RenderSystem.setShaderTexture(0, HUDELEMENT2HEAD);
            DrawableHelper.drawTexture(matrices,0 ,1,0,0,5,15,
                    5,15);

            RenderSystem.setShaderTexture(0, HUDELEMENT2TAIL);
            DrawableHelper.drawTexture(matrices, 7 + BlockData.length,1,0,0,5,15,
                    5,15);
            if(BlockData.length < 89) {
                RenderSystem.setShaderTexture(0, HUDELEMENT);
                DrawableHelper.drawTexture(matrices, 0,1,0,0,99,121,
                        99,121);
            } else {
                for (int i = 0;i < BlockData.length - 2 ; i++){
                    if(i < 91) {
                        RenderSystem.setShaderTexture(0, HUDELEMENT2BODY2);
                        DrawableHelper.drawTexture(matrices, 5 + i,1,0,0,5,15,
                                5,15);
                    } else if (i > 90 && i < 92) {
                        RenderSystem.setShaderTexture(0, HUDELEMENT2BODY3);
                        DrawableHelper.drawTexture(matrices, 5 + i,1,0,0,5,15,
                                5,15);
                    } else if (i > 91 && i < 94) {
                        RenderSystem.setShaderTexture(0, HUDELEMENT2BODY4);
                        DrawableHelper.drawTexture(matrices, 5 + i,1,0,0,5,15,
                                5,15);
                    } else {
                        RenderSystem.setShaderTexture(0, HUDELEMENT2BODY);
                        DrawableHelper.drawTexture(matrices, 5 + i,1,0,0,5,15,
                                5,15);
                    }
                }
                RenderSystem.setShaderTexture(0, HUDELEMENT3);
                DrawableHelper.drawTexture(matrices, 0,16,0,0,99,106,
                        99,106);
            }
            if (BlockData.length < 92) {
                int l = 45 - (BlockData.length / 2);
                MinecraftClient.getInstance().textRenderer.draw(matrices, BlockData.itemM, 6 + l, 5,-1);
            } else {
                MinecraftClient.getInstance().textRenderer.draw(matrices, BlockData.itemM, 6, 5,-1);
            }
            BlockData.length = 0;
        }
        else {
            MinecraftClient.getInstance().textRenderer.draw(matrices, BlockData.itemM, 6, 5,-1);
        }
    }
}