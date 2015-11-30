package at.fhooe.mc.shape;

import java.awt.Color;
import java.awt.Graphics;

import at.fhooe.mc.view.DrawPanel;

public class RectangleObject extends ShapePrimitive{

	public int mStartX;
	public int mStartY;
	
	public int mWidth;
	public int mHeight;
	
	private String mText;
	
	public RectangleObject(DrawPanel _panel, int _startX, int _startY) {
		super(_panel);
		mStartX = _startX;
		mStartY = _startY;
		
		mWidth = 150;
		mHeight = 150;
		
		setChanged();
        notifyObservers();
	}

	@Override
	public void draw(Graphics _graphics) {
		_graphics.setColor(Color.BLACK);

		_graphics.drawRect(mStartX, mStartY, mWidth, mHeight);
		if(mText != null) {
			_graphics.drawString(mText, mStartX+10, mStartY+10);
		}
	}

	@Override
	public void move(int _dX, int _dY) {
		mStartX += _dX;
		mStartY += _dY;
		setChanged();
        notifyObservers();
	}
	
	@Override
	public boolean clickInside(int _clickX, int _clickY) {
		if(_clickX > mStartX && _clickX < (mStartX + mWidth) 
				&& _clickY > mStartY && _clickY < (mStartY + mHeight)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void highlightRect() { }

	@Override
	public void setText(String _str) {
		mText = _str;	
		setChanged();
        notifyObservers();
	}

	@Override
	public String getText() {
		return mText;
	}
}
