package net.theenigmaman.e_hold.util;


import com.mojang.authlib.GameProfile;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.ClientPlayerTickable;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.encryption.PlayerPublicKey;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.theenigmaman.e_hold.EHold;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.MixinEnvironment;

import java.util.Arrays;

public class BlockData {
    public static String item = " ";
    public static String itemN = " ";
    public static String itemM = " ";
    public static int length = 4;

    public static void updateName(String name) {
        String[] name2 = name.split(",");
        itemN = name2[0];
        String[] itemN2 = itemN.split(" ", 2);
        for (String str : itemN2) {
            if(!str.contains("[")) {
                itemM = str;
            }
        }
        itemM = itemM.replace("[", "");
        itemM = itemM.replace("]", "");
        itemM = itemM.replace("_", " ");
        itemM = capitalizeString(itemM);
        if(itemM.contains("Air")) {itemM = "";}
    }
    public static String capitalizeString(String string) {
        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i]=='.' || chars[i]=='\'') { // You can add other chars here
                found = false;
            }
        }
        return String.valueOf(chars);
    }
}

