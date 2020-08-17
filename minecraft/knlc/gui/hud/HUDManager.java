package knlc.gui.hud;

import java.util.Collection;
import java.util.Set;
import java.util.PrimitiveIterator.OfDouble;

import com.google.common.collect.Sets;

import knlc.event.EventManager;
import knlc.event.EventTarget;
import knlc.event.impl.RendererEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.GuiScreen;

public class HUDManager{

	public HUDManager() {}
	
	private static HUDManager instance = null;
	
	public static HUDManager getInstance() {
		
		if(instance != null) {
			return instance;
		}
		
		instance = new HUDManager();
		EventManager.register(instance);
		
		return instance;
		
	}
	
	private Set<IRenderer> registeredRenders = Sets.newHashSet();
	private Minecraft mc = Minecraft.getMinecraft();
	
	public void register(IRenderer... renderers) {
		for(IRenderer render : renderers) {
			this.registeredRenders.add(render);
		}
	}
	
	public void unregister(IRenderer... renderers) {
		for(IRenderer render : renderers) {
			this.registeredRenders.remove(render);
		}
	}
	
	public Collection<IRenderer> getRegisteredRenderers(){
		return Sets.newHashSet(registeredRenders);
	}
	public void openConfigScreen() {
		mc.displayGuiScreen(new HUDConfigScreen(this));
	}
	
	@EventTarget
	public void onRender(RendererEvent e) {
		if(mc.currentScreen == null || mc.currentScreen instanceof GuiContainer || mc.currentScreen instanceof GuiChat) {
			for(IRenderer renderer : registeredRenders) {
				callRenderer(renderer);
			}
		}
	}
	
	private void callRenderer(IRenderer renderer) {
		if(!renderer.isEnabled()) {
			return;
		}
		
		ScreenPosition pos = renderer.load();
		
		if(pos == null) {
			pos = ScreenPosition.fromRelativePosition(0.5, 0.5);
					
			renderer.render(pos);
		}
	}
}
