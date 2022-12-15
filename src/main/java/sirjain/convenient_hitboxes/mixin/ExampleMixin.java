package sirjain.convenient_hitboxes.mixin;

import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public class ExampleMixin extends Screen {
    protected ExampleMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("HEAD"), method = "initWidgets()V")
    private void init(CallbackInfo info) {
        System.out.println("Mixin initialized");
    }
}