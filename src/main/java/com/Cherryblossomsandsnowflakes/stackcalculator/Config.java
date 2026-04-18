package com.Cherryblossomsandsnowflakes.stackcalculator;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

/**
 * 堆叠计算器配置类
 */
@Mod.EventBusSubscriber(modid = ExampleMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    // 堆叠计算器配置
    private static final ForgeConfigSpec.BooleanValue ENABLE_CALCULATOR = BUILDER
            .comment("是否启用堆叠计算器")
            .define("enableCalculator", true);

    private static final ForgeConfigSpec.IntValue DEFAULT_STACK_SIZE = BUILDER
            .comment("默认堆叠大小")
            .defineInRange("defaultStackSize", 64, 1, 64);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean enableCalculator;
    public static int defaultStackSize;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        enableCalculator = ENABLE_CALCULATOR.get();
        defaultStackSize = DEFAULT_STACK_SIZE.get();
    }
}