package com.Cherryblossomsandsnowflakes.stackcalculator;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

/**
 * 堆叠计算命令
 * 允许玩家在游戏中执行物品堆叠计算
 */
public class StackCalculatorCommand {
    
    /**
     * 注册命令
     */
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("stackcalc")
            .then(Commands.literal("calculate")
                .then(Commands.argument("totalItems", IntegerArgumentType.integer(0))
                    .executes(context -> calculate(context, StackCalculator.getDefaultMaxStackSize()))
                    .then(Commands.argument("stackSize", IntegerArgumentType.integer(1))
                        .executes(context -> calculate(context, IntegerArgumentType.getInteger(context, "stackSize"))))))
            .then(Commands.literal("help")
                .executes(StackCalculatorCommand::showHelp)));
    }
    
    /**
     * 执行堆叠计算
     */
    private static int calculate(CommandContext<CommandSourceStack> context, int stackSize) {
        int totalItems = IntegerArgumentType.getInteger(context, "totalItems");
        
        try {
            StackCalculator.StackResult result = StackCalculator.calculate(totalItems, stackSize);
            
            CommandSourceStack source = context.getSource();
            source.sendSuccess(() -> Component.literal("§6堆叠计算结果：§r\n" + result.getDetailedInfo()), false);
            
            return 1;
        } catch (IllegalArgumentException e) {
            context.getSource().sendFailure(Component.literal("§c错误：" + e.getMessage()));
            return 0;
        }
    }
    
    /**
     * 显示帮助信息
     */
    private static int showHelp(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        
        source.sendSuccess(() -> Component.literal("§6=== 堆叠计算器帮助 ===\n" +
            "§a/stackcalc calculate <总物品数> [堆叠大小]§r - 计算物品堆叠\n" +
            "§a/stackcalc help§r - 显示此帮助信息\n\n" +
            "§7示例：\n" +
            "§7• /stackcalc calculate 928§r - 计算928个物品（默认堆叠64）\n" +
            "§7• /stackcalc calculate 928 16§r - 计算928个物品（堆叠16）\n" +
            "§7• /stackcalc help§r - 显示帮助信息"), false);
        
        return 1;
    }
}