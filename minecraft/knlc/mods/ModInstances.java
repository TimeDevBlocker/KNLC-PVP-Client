package knlc.mods;

import knlc.gui.hud.HUDManager;
import knlc.mods.impl.ModHelloWorld;

public class ModInstances {

	private static ModHelloWorld modHelloWorld;
	
	public static void register(HUDManager api) {
		modHelloWorld = new ModHelloWorld();
		api.register(modHelloWorld);
	}
	
	public static ModHelloWorld getModHelloWorld() {
		return modHelloWorld;
	}
}
