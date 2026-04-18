package com.Cherryblossomsandsnowflakes.stackcalculator;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * 客户端事件处理器
 * 处理快捷键输入和GUI打开
 */
@Mod.EventBusSubscriber(modid = ExampleMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEventHandler {
    
    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        // 增加安全检查，防止空指针错误
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft == null || minecraft.player == null || minecraft.screen != null) {
            return;
        }
        
        try {
            // 检查快捷键是否被按下（支持组合键）
            if (KeyBindings.isCalculatorKeyPressed() || KeyBindings.isCombinationPressed(event.getKey(), event.getModifiers())) {
                // 安全创建并打开计算器界面
                minecraft.setScreen(new StackCalculatorScreen());
            }
        } catch (Exception e) {
            // 捕获所有异常，防止模组崩溃
            ExampleMod.LOGGER.warn("堆叠计算器打开失败: {}", e.getMessage());
        }
    }
    
    // 也监听鼠标事件，确保鼠标按键不会导致问题
    @SubscribeEvent
    public static void onMouseInput(InputEvent.MouseButton event) {
        // 增加安全检查，防止空指针错误
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft == null || minecraft.player == null || minecraft.screen != null) {
            return;
        }
        
        try {
            // 检查快捷键是否被按下（鼠标按键也可能触发）
            if (KeyBindings.isCalculatorKeyPressed()) {
                // 安全创建并打开计算器界面
                minecraft.setScreen(new StackCalculatorScreen());
            }
        } catch (Exception e) {
            // 捕获所有异常，防止模组崩溃
            ExampleMod.LOGGER.warn("堆叠计算器打开失败: {}", e.getMessage());
        }
    }
}