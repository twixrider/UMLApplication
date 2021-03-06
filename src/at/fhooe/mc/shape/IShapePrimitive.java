package at.fhooe.mc.shape;

import java.awt.Graphics;

public interface IShapePrimitive {
	
	public void setText(String _str);
	
	public String getText();
	
	public void draw(Graphics _graphics);
	
	public void move(int _dX, int _dY);	
	
	public boolean clickInside(int _clickX, int _clickY);	
}
