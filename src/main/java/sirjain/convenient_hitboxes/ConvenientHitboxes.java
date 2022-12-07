package sirjain.convenient_hitboxes;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.player.PlayerEntity;

public class ConvenientHitboxes implements ModInitializer {
	public static final String MOD_NAME = "Convenient Hitboxes";
	public static final String MOD_ID = "convenient_hitboxes";
	public static final String MOD_VERSION = "1.0.0";

	@Override
	public void onInitialize() {
		System.out.println("Initializing " + MOD_NAME);
	}
}