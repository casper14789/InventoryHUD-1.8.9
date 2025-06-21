# InventoryHUD - Minecraft Forge Mod (1.8.9)

🎒 A customizable HUD mod for Minecraft 1.8.9 that displays your main inventory directly on screen — perfect for PvP, Skyblock, and any situation where you want to see your backpack at all times.

---

## ✨ Features

- 📦 **Shows your main inventory** (36 slots) as an on-screen HUD
- 🖱️ **Draggable interface** — reposition the HUD by dragging it
- 🎛️ **Settings GUI** — adjust position and scale with sliders
- 🗄️ **Saves position and size** between sessions
- ⌨️ **Customizable keybinds**:
  - `H`: Toggle HUD visibility
- 🎨 Minimal transparent design with borders
- ☁️ Lightweight and optimized for 1.8.9 Forge environments

---

## 🔧 Installation

1. Install [Minecraft Forge 1.8.9](https://files.minecraftforge.net/)
2. Download the latest `InventoryHUD-1.8.9.jar` from [Releases](https://github.com/casper14789/InventoryHUD-1.8.9/releases)
3. Drop the `.jar` file into your `.minecraft/mods` folder
4. Launch Minecraft with the Forge 1.8.9 profile

---

## 🎮 Keybinds

| Key | Action                     |
|-----|----------------------------|
| H   | Toggle Inventory HUD       |

Keybinds can be changed in `Options > Controls > InventoryHUD`

---

## 🖼️ Screenshots

![image](https://github.com/user-attachments/assets/5ed650dc-6aec-4972-9445-008db7d9f8fa)


---

## 🛡️ Server Use Disclaimer

This mod is **client-side only** and does **not perform automation or send server-side commands**.  
Still, always check if HUD overlays are allowed on your server (e.g. Hypixel).

---

## 📁 File Structure

- `BackpackHUD.java`: HUD renderer and keybind handler
- `HUDConfigGui.java`: In-game GUI to adjust position/scale
- `HUDSettings.java`: Handles saving/loading HUD config

---

## 📜 License

MIT License — free to use, modify, and share.  
Pull requests are welcome!

---

## ❤️ Credits

Developed by Casper14789
Special thanks to Minecraft Forge and the open-source modding community.
