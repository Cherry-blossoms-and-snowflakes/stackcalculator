# Stack Calculator Mod

A Minecraft Forge mod for calculating item stacks.

## 语言选择 / Language Selection

- [English](#english)
- [中文](#中文)

---

## English

### Features

- **Command System**: `/stackcalc calculate <totalItems> [stackSize]`
- **GUI Interface**: Press `C` key to open calculator
- **Customizable**: Configurable default stack size
- **Multi-language**: English and Chinese support
- **Combination Keys**: Supports Ctrl+C, Shift+C, Alt+C

### Installation

1. Download the latest release
2. Place the jar file in your `.minecraft/mods` folder
3. Launch Minecraft with Forge

### Usage

#### Command Usage
- `/stackcalc calculate 928` - Calculate 928 items with default stack size
- `/stackcalc calculate 928 16` - Calculate 928 items with stack size 16
- `/stackcalc help` - Show help information

#### GUI Usage
1. Press `C` key in-game
2. Enter total items and stack size
3. Click "Calculate" or press Enter
4. View the result

### Configuration

Edit `.minecraft/config/examplemod-common.toml`:
- `enableCalculator` - Enable/disable the calculator
- `defaultStackSize` - Set default stack size (default: 64)

### Compatibility

- Minecraft 1.20.1
- Forge 47.4.10+

---

## 中文

### 功能特性

- **命令系统**：`/stackcalc calculate <总物品数> [堆叠大小]`
- **GUI界面**：按 `C` 键打开计算器
- **可自定义**：可配置默认堆叠大小
- **多语言支持**：英文和中文
- **组合键支持**：支持 Ctrl+C、Shift+C、Alt+C

### 安装方法

1. 下载最新版本
2. 将jar文件放入 `.minecraft/mods` 文件夹
3. 使用Forge启动Minecraft

### 使用方法

#### 命令使用
- `/stackcalc calculate 928` - 计算928个物品（使用默认堆叠大小）
- `/stackcalc calculate 928 16` - 计算928个物品（使用堆叠大小16）
- `/stackcalc help` - 显示帮助信息

#### GUI使用
1. 在游戏中按 `C` 键
2. 输入总物品数和堆叠大小
3. 点击"计算"或按Enter键
4. 查看结果

### 配置选项

编辑 `.minecraft/config/examplemod-common.toml`：
- `enableCalculator` - 启用/禁用计算器
- `defaultStackSize` - 设置默认堆叠大小（默认：64）

### 兼容性

- Minecraft 1.20.1
- Forge 47.4.10+
