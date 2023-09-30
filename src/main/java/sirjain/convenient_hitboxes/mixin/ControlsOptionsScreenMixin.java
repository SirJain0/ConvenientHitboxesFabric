package sirjain.convenient_hitboxes.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.ControlsOptionsScreen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ControlsOptionsScreen.class)
public class ControlsOptionsScreenMixin extends GameOptionsScreen {
	public ControlsOptionsScreenMixin(Screen parent, GameOptions gameOptions) {
		super(parent, gameOptions, Text.translatable("hi"));
	}

	@Inject(at = @At("HEAD"), method = "init()V")
	protected void init(CallbackInfo info) {
		if (this.client == null) return;
		System.out.println("Ctrls options screen");

		this.addDrawableChild(ButtonWidget.builder(
				!this.client.getEntityRenderDispatcher().shouldRenderHitboxes() ? Text.translatable("gui.entity_hitbox.disabled") : Text.translatable("gui.entity_hitbox.enabled"),
				(button) -> {
					this.client.getEntityRenderDispatcher().setRenderHitboxes(!this.client.getEntityRenderDispatcher().shouldRenderHitboxes());
					button.setMessage(!this.client.getEntityRenderDispatcher().shouldRenderHitboxes() ? Text.translatable("gui.entity_hitbox.disabled") : Text.translatable("gui.entity_hitbox.enabled"));
					this.debugLog(!this.client.getEntityRenderDispatcher().shouldRenderHitboxes() ? "debug.show_hitboxes.off" : "debug.show_hitboxes.on");
				}
			)
			.dimensions(this.width / 2 - 75, this.height / 2 + 20, 150, 20)
			.build());
	}

	private void addDebugMessage(Text text) {
		if (client == null) return;
		this.client.inGameHud.getChatHud().addMessage(Text.empty().append(Text.translatable("debug.prefix").formatted(Formatting.YELLOW, Formatting.BOLD)).append(" ").append(text));
	}

	private void debugLog(Text text) {
		this.addDebugMessage(text);
	}

	private void debugLog(String key, Object... args) {
		this.debugLog(Text.translatable(key, args));
	}
}