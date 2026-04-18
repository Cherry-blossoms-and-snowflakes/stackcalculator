package com.Cherryblossomsandsnowflakes.stackcalculator;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

/**
 * 快捷键绑定管理器
 */
@Mod.EventBusSubscriber(modid = ExampleMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeyBindings {
    
    public static final String KEY_CATEGORY_STACK_CALC = "key.category.examplemod.stackcalc";
    public static final String KEY_OPEN_CALCULATOR = "key.examplemod.open_calculator";
    
    public static final KeyMapping OPEN_CALCULATOR_KEY = new KeyMapping(
        KEY_OPEN_CALCULATOR,
        KeyConflictContext.IN_GAME,
        InputConstants.Type.KEYSYM,
        GLFW.GLFW_KEY_C,
        KEY_CATEGORY_STACK_CALC
    );
    
    /**
     * 检查组合键是否被按下
     * 支持单独按C键，或组合键（如Ctrl+C、Shift+C等）
     */
    public static boolean isCalculatorKeyPressed() {
        return OPEN_CALCULATOR_KEY.consumeClick();
    }
    
    /**
     * 检查是否按下了特定的组合键
     * @param keyCode 按键代码
     * @param modifiers 修饰键（如Ctrl、Shift等）
     * @return 是否按下了组合键
     */
    public static boolean isCombinationPressed(int keyCode, int modifiers) {
        // 检查是否按下了C键
        if (keyCode != GLFW.GLFW_KEY_C) {
            return false;
        }
        
        // 允许以下组合：
        // 1. 单独按C键（无修饰键）
        // 2. Ctrl+C
        // 3. Shift+C
        // 4. Alt+C
        return modifiers == 0 || 
               (modifiers & GLFW.GLFW_MOD_CONTROL) != 0 ||
               (modifiers & GLFW.GLFW_MOD_SHIFT) != 0 ||
               (modifiers & GLFW.GLFW_MOD_ALT) != 0;
    }
    
    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(OPEN_CALCULATOR_KEY);
    }
}