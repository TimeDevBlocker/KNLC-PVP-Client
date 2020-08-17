package knlc.mods.impl;

import knlc.gui.hud.ScreenPosition;
import knlc.mods.ModDraggable;

public class ModHelloWorld extends ModDraggable{

	private ScreenPosition pos;
	
	@Override
	public int getWidth() {
		return font.getStringWidth("Test (Dummy");
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		font.drawString("Test", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, -1);
		
	}
	@Override
	public void renderDummy(ScreenPosition pos) {
		font.drawString("Test Dummy", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, 0xFF00FF00);
	}
	
	@Override
	public void save(ScreenPosition pos) {
		this.pos = pos;
	}

	@Override
	public ScreenPosition load() {
		return pos;
	}

}
