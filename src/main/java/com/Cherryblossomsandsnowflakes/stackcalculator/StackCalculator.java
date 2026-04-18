package com.Cherryblossomsandsnowflakes.stackcalculator;

/**
 * 堆叠计算工具类
 * 用于计算Minecraft物品的堆叠数量
 */
public class StackCalculator {
    
    /**
     * 默认物品堆叠上限（从配置中读取）
     */
    public static int getDefaultMaxStackSize() {
        return Config.defaultStackSize;
    }
    
    /**
     * 计算物品堆叠结果
     * @param totalItems 总物品数量
     * @param stackSize 每个堆叠的最大数量（默认为64）
     * @return 堆叠计算结果
     */
    public static StackResult calculate(int totalItems, int stackSize) {
        if (stackSize <= 0) {
            stackSize = getDefaultMaxStackSize();
        }
        
        if (totalItems < 0) {
            throw new IllegalArgumentException("物品数量不能为负数");
        }
        
        int stacks = totalItems / stackSize;
        int remainder = totalItems % stackSize;
        
        return new StackResult(totalItems, stackSize, stacks, remainder);
    }
    
    /**
     * 计算物品堆叠结果（使用默认堆叠大小）
     * @param totalItems 总物品数量
     * @return 堆叠计算结果
     */
    public static StackResult calculate(int totalItems) {
        return calculate(totalItems, getDefaultMaxStackSize());
    }
    
    /**
     * 堆叠计算结果类
     */
    public static class StackResult {
        private final int totalItems;
        private final int stackSize;
        private final int stacks;
        private final int remainder;
        
        public StackResult(int totalItems, int stackSize, int stacks, int remainder) {
            this.totalItems = totalItems;
            this.stackSize = stackSize;
            this.stacks = stacks;
            this.remainder = remainder;
        }
        
        public int getTotalItems() {
            return totalItems;
        }
        
        public int getStackSize() {
            return stackSize;
        }
        
        public int getStacks() {
            return stacks;
        }
        
        public int getRemainder() {
            return remainder;
        }
        
        @Override
        public String toString() {
            if (stacks == 0) {
                return String.format("%d个物品（堆叠大小：%d）", totalItems, stackSize);
            } else if (remainder == 0) {
                return String.format("%d组（每组%d个）", stacks, stackSize);
            } else {
                return String.format("%d组%d个（堆叠大小：%d）", stacks, remainder, stackSize);
            }
        }
        
        /**
         * 获取详细的计算信息
         * @return 详细的计算结果字符串
         */
        public String getDetailedInfo() {
            return String.format("总物品数：%d\n堆叠大小：%d\n完整组数：%d\n剩余物品：%d\n计算结果：%s", 
                totalItems, stackSize, stacks, remainder, toString());
        }
    }
}