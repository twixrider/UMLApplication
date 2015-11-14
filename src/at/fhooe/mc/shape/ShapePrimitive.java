package at.fhooe.mc.shape;

import java.util.Observable;

import at.fhooe.mc.view.DrawPanel;

public abstract class ShapePrimitive extends Observable implements IShapePrimitive{

	DrawPanel mDrawPanel;
	
	public ShapePrimitive(DrawPanel _panel) {
		this.mDrawPanel = _panel;
		this.addObserver(mDrawPanel);
	}	
}
