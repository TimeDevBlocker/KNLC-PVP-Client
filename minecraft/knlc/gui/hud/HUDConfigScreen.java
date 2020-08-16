package knlc.gui.hud;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import org.lwjgl.input.Keyboard;
import net.minecraft.client.gui.GuiScreen;

public class HUDConfigScreen extends GuiScreen{

private final HashMap<IRenderer, ScreenPosition> renderers = new HashMap<IRenderer, ScreenPosition>();
	
	private Optional<IRenderer> selectedRender = Optional.empty();
	
	private int prevX, prevY;

	
	public HUDConfigScreen(HUDManager api) {
		
		Collection<IRenderer> registeredIRenderers = api.getRegisteredRenderers();
		
		for(IRenderer ren : registeredIRenderers) {
			if(!ren.isEnabled()) {
				continue;
			}
			
			ScreenPosition pos= ren.load();
			
			if(pos == null) {
				pos = ScreenPosition.fromRelativePosition(0.5, 0.5);
			}
			
			adjustBounds(ren, pos);
			this.renderers.put(ren, pos);
		}	
	}
	@Override
		public void drawScreen(int mouseX, int mouseY, float partialTicks) {
			super.drawDefaultBackground();
			
			final float zBackup = this.zLevel;
			this.zLevel = 200;
			
			this.drawHollowRect(0, 0, this.width - 1, this.height - 1, 0xFFFF0000);
			
			for(IRenderer renderer : renderers.keySet()) {
				
				ScreenPosition pos = renderers.get(renderer);
				
				renderer.renderDummy(pos);
				
				this.drawHollowRect(pos.getAbsoluteX(), pos.getAbsoluteY(), renderer.getWidth(), renderer.getHeight(), 0xFF00FFFF);
				
			}
		}
	private void drawHollowRect(int x, int y, int w, int h, int color) {
		this.drawHorizontalLine(x, x + w, y, color);
		this.drawHorizontalLine(x, x + w, y + h, color);
		
		this.drawVerticalLine(x, y + h, y, color);
		this.drawVerticalLine(x + w, y + h, y, color);
	}
	
	@Override
		protected void keyTyped(char typedChar, int keyCode) throws IOException {
			if(keyCode == Keyboard.KEY_ESCAPE) {
				
			}
		}
}
