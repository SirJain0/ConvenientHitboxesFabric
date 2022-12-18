package sirjain.convenient_hitboxes.mixin;

import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin extends Screen {
    protected GameMenuScreenMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("HEAD"), method = "initWidgets()V")
    private void init(CallbackInfo info) {
        System.out.println("Mixin initialized");

        this.addDrawableChild(new ButtonWidget(this.width / 2 - 102, (this.height / 4 + 120 + -16 + 36), 204, 20,Text.translatable("gui.entity_hitbox"), (button) -> {
            System.out.println("button clicked");
        }));
    }
}