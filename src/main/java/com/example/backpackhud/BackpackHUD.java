package com.example.backpackhud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.RenderHelper;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

@Mod(modid = "backpackhud", name = "Backpack HUD", version = "1.0")
public class BackpackHUD {
    public static HUDSettings settings = new HUDSettings();
    private static KeyBinding toggleKey;

    private boolean dragging = false;
    private int dragOffsetX, dragOffsetY;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        toggleKey = new KeyBinding("Toggle HUD", Keyboard.KEY_H, "Backpack HUD");
        ClientRegistry.registerKeyBinding(toggleKey);
        MinecraftForge.EVENT_BUS.register(this);
        settings = HUDSettings.load();
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (toggleKey.isPressed()) {
            settings.hudVisible = !settings.hudVisible;
            settings.save();
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        if (!settings.hudVisible || event.type != RenderGameOverlayEvent.ElementType.ALL) return;

        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution scaled = new ScaledResolution(mc);
        int mouseX = Mouse.getX() * scaled.getScaledWidth() / mc.displayWidth;
        int mouseY = scaled.getScaledHeight() - Mouse.getY() * scaled.getScaledHeight() / mc.displayHeight - 1;

        if (Mouse.isButtonDown(0)) {
            if (!dragging && isMouseInHud(mouseX, mouseY)) {
                dragging = true;
                dragOffsetX = mouseX - settings.hudX;
                dragOffsetY = mouseY - settings.hudY;
            }
        } else {
            if (dragging) {
                dragging = false;
                settings.save();
            }
        }

        if (dragging) {
            settings.hudX = mouseX - dragOffsetX;
            settings.hudY = mouseY - dragOffsetY;
        }

        for (int i = 0; i < 36; i++) {
            int row = (i < 27) ? (i / 9) : 3;
            int col = i % 9;
            int x = settings.hudX + col * settings.slotSize;
            int y = settings.hudY + row * settings.slotSize;

            // ✅ 邊框（用內建 GUI API，不影響別人）
            mc.ingameGUI.drawRect(x - 1, y - 1, x + settings.slotSize, y, 0xFFAAAAAA); // 上
            mc.ingameGUI.drawRect(x - 1, y + settings.slotSize, x + settings.slotSize, y + settings.slotSize + 1, 0xFFAAAAAA); // 下
            mc.ingameGUI.drawRect(x - 1, y, x, y + settings.slotSize, 0xFFAAAAAA); // 左
            mc.ingameGUI.drawRect(x + settings.slotSize, y, x + settings.slotSize + 1, y + settings.slotSize, 0xFFAAAAAA); // 右

            // ✅ 渲染物品（包住 GL 狀態）
            ItemStack stack = mc.thePlayer.inventory.mainInventory[i];
            if (stack != null) {
                GlStateManager.pushMatrix();
                GlStateManager.enableRescaleNormal();
                RenderHelper.enableGUIStandardItemLighting(); // ✅ 正確啟用光影與 GL 狀態
                mc.getRenderItem().renderItemAndEffectIntoGUI(stack, x, y);
                mc.getRenderItem().renderItemOverlayIntoGUI(mc.fontRendererObj, stack, x, y, null);
                GlStateManager.disableRescaleNormal();
                GlStateManager.popMatrix();
            }
        }
    }

    private boolean isMouseInHud(int x, int y) {
        int width = 9 * settings.slotSize;
        int height = 4 * settings.slotSize;
        return x >= settings.hudX && x <= settings.hudX + width &&
               y >= settings.hudY && y <= settings.hudY + height;
    }
}
