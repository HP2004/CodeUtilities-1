package io.github.codeutilities.screen.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.codeutilities.CodeUtilities;
import io.github.codeutilities.util.RenderUtil;
import java.awt.Rectangle;
import java.util.Optional;
import java.util.function.Consumer;
import net.minecraft.client.item.TooltipContext.Default;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import org.lwjgl.opengl.GL11;

public class CItem implements CWidget {

    private final int x;
    private final int y;
    private final ItemStack item;
    private Consumer<Integer> onClick;

    public CItem(int x, int y, ItemStack item) {
        this.x = x;
        this.y = y;
        this.item = item;
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        stack.push();
        stack.translate(x, y, 0);
        RenderUtil.renderGuiItem(stack, item);
        stack.pop();
    }

    @Override
    public void renderOverlay(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        Rectangle rect = new Rectangle(x, y,8, 8);

        if (rect.contains(mouseX, mouseY)) {
            stack.push();
            stack.translate(mouseX, mouseY, 0);
            stack.scale(0.5f, 0.5f, 1f);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            CodeUtilities.MC.currentScreen.renderTooltip(stack, item.getTooltip(
                CodeUtilities.MC.player, Default.NORMAL
            ), Optional.empty(), 0, 0);
            stack.pop();
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 8, 8);
    }

    @Override
    public void mouseClicked(double x, double y, int button) {
        Rectangle rect = new Rectangle(this.x, this.y, 8, 8);

        if (rect.contains(x, y)) {
            if (onClick != null) {
                CodeUtilities.MC.getSoundManager().play(PositionedSoundInstance.ambient(SoundEvents.UI_BUTTON_CLICK, 1f, 1f));
                onClick.accept(button);
            }
        }

        CWidget.super.mouseClicked(x, y, button);
    }

    public void setClickListener(Consumer<Integer> onClick) {
        this.onClick = onClick;
    }

}
