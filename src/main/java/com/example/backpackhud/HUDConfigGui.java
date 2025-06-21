package com.example.backpackhud;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

import java.io.IOException;

public class HUDConfigGui extends GuiScreen {
    private GuiTextField xField, yField, sizeField;
    private GuiButton doneButton;

    @Override
    public void initGui() {
        int centerX = width / 2;
        int centerY = height / 2;

        xField = new GuiTextField(0, fontRendererObj, centerX - 50, centerY - 40, 100, 20);
        xField.setText(String.valueOf(BackpackHUD.settings.hudX));

        yField = new GuiTextField(1, fontRendererObj, centerX - 50, centerY - 10, 100, 20);
        yField.setText(String.valueOf(BackpackHUD.settings.hudY));

        sizeField = new GuiTextField(2, fontRendererObj, centerX - 50, centerY + 20, 100, 20);
        sizeField.setText(String.valueOf(BackpackHUD.settings.slotSize));

        doneButton = new GuiButton(3, centerX - 40, centerY + 60, 80, 20, "Done");
        buttonList.add(doneButton);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 3) {
            try {
                BackpackHUD.settings.hudX = Integer.parseInt(xField.getText());
                BackpackHUD.settings.hudY = Integer.parseInt(yField.getText());
                BackpackHUD.settings.slotSize = Integer.parseInt(sizeField.getText());
            } catch (NumberFormatException ignored) {}
            BackpackHUD.settings.save(); // ← 儲存設定
            mc.displayGuiScreen(null);
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        xField.textboxKeyTyped(typedChar, keyCode);
        yField.textboxKeyTyped(typedChar, keyCode);
        sizeField.textboxKeyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        xField.mouseClicked(mouseX, mouseY, mouseButton);
        yField.mouseClicked(mouseX, mouseY, mouseButton);
        sizeField.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        drawCenteredString(fontRendererObj, "HUD Configuration", width / 2, height / 2 - 70, 0xFFFFFF);
        drawString(fontRendererObj, "X:", width / 2 - 70, height / 2 - 34, 0xAAAAAA);
        drawString(fontRendererObj, "Y:", width / 2 - 70, height / 2 - 4, 0xAAAAAA);
        drawString(fontRendererObj, "Slot Size:", width / 2 - 70, height / 2 + 26, 0xAAAAAA);
        xField.drawTextBox();
        yField.drawTextBox();
        sizeField.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
