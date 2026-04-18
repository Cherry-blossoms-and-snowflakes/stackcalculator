package com.Cherryblossomsandsnowflakes.stackcalculator;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

/**
 * 堆叠计算器GUI界面
 */
public class StackCalculatorScreen extends Screen {

    private EditBox totalItemsInput;
    private EditBox stackSizeInput;
    private Button calculateButton;
    private String resultText = "";
    
    public StackCalculatorScreen() {
        super(Component.literal("堆叠计算器"));
    }

    @Override
    protected void init() {
        super.init();

        int centerX = this.width / 2; // 屏幕水平中心
        int centerY = this.height / 2; // 屏幕垂直中心

        // 1. 总物品数输入框：标签在上，输入框在下，拉开10像素间距
        this.totalItemsInput = new EditBox(this.font, centerX - 100, centerY - 60, 200, 20, Component.literal("总物品数"));
        this.totalItemsInput.setMaxLength(10);
        this.totalItemsInput.setValue("");
        this.totalItemsInput.setHint(Component.literal("输入总物品数量"));
        this.addRenderableWidget(this.totalItemsInput);

        // 2. 堆叠大小输入框：和上一个输入框拉开30像素，避免拥挤
        this.stackSizeInput = new EditBox(this.font, centerX - 100, centerY - 10, 200, 20, Component.literal("堆叠大小"));
        this.stackSizeInput.setMaxLength(3);
        this.stackSizeInput.setValue(String.valueOf(Config.defaultStackSize)); // 使用配置中的默认值
        this.stackSizeInput.setHint(Component.literal("输入堆叠上限"));
        this.addRenderableWidget(this.stackSizeInput);

        // 3. 计算按钮：和输入框拉开30像素
        this.calculateButton = Button.builder(Component.literal("计算"), btn -> {
            calculate(); // 你的计算逻辑写在这里
        }).bounds(centerX - 100, centerY + 30, 200, 20).build();
        this.addRenderableWidget(this.calculateButton);

        // 4. 关闭按钮：和计算按钮拉开25像素
        Button closeButton = Button.builder(Component.literal("关闭"), btn -> {
            this.onClose(); // 关闭窗口
        }).bounds(centerX - 100, centerY + 65, 200, 20).build();
        this.addRenderableWidget(closeButton);
    }
    
    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // 【必须第一行】先渲染半透明背景，不然会盖住输入框和按钮
        this.renderBackground(guiGraphics);

        // 1. 标题：往上挪，和下方内容拉开距离，Y=40（数字越大越靠下）
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 40, 0xFFFFFF);

        // 2. 标签：严格和输入框对齐，标签在输入框上方10像素，绝对不会被挡住
        // 总物品数标签：对应输入框Y=centerY-60，标签就写centerY-70（往上10像素）
        guiGraphics.drawString(this.font, "总物品数:", this.width / 2 - 110, this.height / 2 - 70, 0xFFFFFF, false);
        // 堆叠大小标签：对应输入框Y=centerY-10，标签就写centerY-20（往上10像素）
        guiGraphics.drawString(this.font, "堆叠大小:", this.width / 2 - 110, this.height / 2 - 20, 0xFFFFFF, false);

        // 3. 结果文本：在关闭按钮下方，拉开足够距离，Y=centerY+100
        if (!this.resultText.isEmpty()) {
            guiGraphics.drawCenteredString(this.font, this.resultText, this.width / 2, this.height / 2 + 110, 0x00FF00);
        }

        // 4. 快捷键说明：固定在屏幕最底部，不和上面内容拥挤
        guiGraphics.drawCenteredString(this.font, "§7快捷键: C (可在设置中修改)", this.width / 2, this.height - 30, 0xAAAAAA);

        // 【必须最后一行】最后渲染输入框、按钮，保证它们在最上层，不会被背景盖住
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }
    
    private void calculate() {
        try {
            String totalItemsStr = this.totalItemsInput.getValue().trim();
            String stackSizeStr = this.stackSizeInput.getValue().trim();
            
            if (totalItemsStr.isEmpty()) {
                this.resultText = "§c请输入总物品数量";
                return;
            }
            
            int totalItems = Integer.parseInt(totalItemsStr);
            int stackSize = stackSizeStr.isEmpty() ? Config.defaultStackSize : Integer.parseInt(stackSizeStr);
            
            if (totalItems < 0) {
                this.resultText = "§c物品数量不能为负数";
                return;
            }
            
            if (stackSize <= 0) {
                this.resultText = "§c堆叠大小必须大于0";
                return;
            }
            
            StackCalculator.StackResult result = StackCalculator.calculate(totalItems, stackSize);
            this.resultText = "§a" + result.toString();
            
        } catch (NumberFormatException e) {
            this.resultText = "§c请输入有效的数字";
        } catch (Exception e) {
            this.resultText = "§c计算错误: " + e.getMessage();
        }
    }
    
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        // 按Enter键也可以触发计算
        if (keyCode == InputConstants.KEY_RETURN) {
            this.calculate();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
    
    @Override
    public boolean isPauseScreen() {
        return false; // 游戏不会暂停
    }
}