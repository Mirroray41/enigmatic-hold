package net.theenigmaman.e_hold.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.theenigmaman.e_hold.util.BlockData;

public class TestCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        dispatcher.register(CommandManager.literal("tester")
                .executes(TestCommand::run));
    }

    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        BlockData.length++;
        return 1;
    }
}

