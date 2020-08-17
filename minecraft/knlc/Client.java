package knlc;

import knlc.event.EventManager;
import knlc.event.EventTarget;
import knlc.event.impl.ClientTickEvent;
import knlc.gui.SplashProgress;
import knlc.gui.hud.HUDManager;
import knlc.mods.ModInstances;
import net.minecraft.client.Minecraft;

public class Client {

	private static final Client INSTANCE = new Client();
	public static final Client getInstance() {
		return INSTANCE;
	}
	
	private DiscordRP discordRP = new DiscordRP();
	
	private HUDManager hudManager;
	
	public void init() {
		SplashProgress.setProgress(1, "Client - Initalising Discord RP");
		discordRP.start();
		EventManager.register(this);
	}
	
	public void start() {
		hudManager = HUDManager.getInstance();
		ModInstances.register(hudManager);
	}
	
	public void shutdown() {
		discordRP.shutdown();
	}
	
	public DiscordRP getDiscordRP() {
		return discordRP;
	}
	
	@EventTarget
	public void onTick(ClientTickEvent e) {
		if(Minecraft.getMinecraft().gameSettings.CLIENT_GUI_MOD_POS.isPressed()) {
		 hudManager.openConfigScreen();
		}
	}
}
