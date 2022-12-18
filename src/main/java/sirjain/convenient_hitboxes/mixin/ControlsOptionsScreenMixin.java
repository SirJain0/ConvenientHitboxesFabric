package sirjain.convenient_hitboxes.mixin;

import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.ControlsOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ControlsOptionsScreen.class)
public class ControlsOptionsScreenMixin extends Screen {
    protected ControlsOptionsScreenMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {
        assert this.client != null;
        boolean bl = !this.client.getEntityRenderDispatcher().shouldRenderHitboxes();
        System.out.println("Mixin initialized");

        // Adds the button
        this.addDrawableChild(
            new ButtonWidget(
                (this.width / 2 - 102) + 107, // x location
                (this.height / 6 - 12) + 48, // y location
                150, 20, // button dimensions
                bl ? Text.translatable("gui.entity_hitbox.disabled") : Text.translatable("gui.entity_hitbox.enabled"), (button) -> {
                    this.client.getEntityRenderDispatcher().setRenderHitboxes(bl);
                    System.out.println("button clicked");
                }
            )
        );
    }
}