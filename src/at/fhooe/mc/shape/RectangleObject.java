package at.fhooe.mc.shape;

import java.awt.Graphics;

public class RectangleObject {

	public int mStartX;
	public int mStartY;
	
	public int mWidth;
	public int mHeight;
	
	public RectangleObject(int _startX, int _startY) {
		mStartX = _startX;
		mStartY = _startY;
		
		mWidth = 150;
		mHeight = 150;
	}

	
	public void draw(Graphics _graphics) {
		// TODO Auto-generated method stub
		System.out.println("Draw Rect: " + mStartX + "," + mStartY);
		_graphics.drawRect(mStartX, mStartY, mWidth, mHeight);
	}

	public void move(int _dX, int _dY) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean clickInside(int _clickX, int _clickY) {
		if(_clickX > mStartX && _clickX < (mStartX + mWidth) 
				&& _clickY > mStartY && _clickY < (mStartY + mHeight)) {
			System.out.println("CLick inside");	
			return true;
		} else {
			System.out.println("Click outside");
			return false;
		}
	}
	
	public void highlightRect() {
		
	}
}
