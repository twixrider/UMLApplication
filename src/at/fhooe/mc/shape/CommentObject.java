package at.fhooe.mc.shape;

import java.awt.Color;
import java.awt.Graphics;

import at.fhooe.mc.view.DrawPanel;

public class CommentObject extends ShapePrimitive {
	
	private int mStartX;
	private int mStartY;
	
	private int mWidth = 120;
	private int mHeight = 100;
	
	private String mText;
	
	private RectangleObject mRect;
		
	public CommentObject(RectangleObject _rect, DrawPanel _panel) {
		
		super(_panel);
		
		
		mRect = _rect;
		
		mStartX = (mRect.mStartX + mRect.mWidth + mHeight);
		mStartY = (mRect.mStartY + mRect.mHeight/2) - (mHeight/2);
		
		setChanged();
        notifyObservers();
	}	
	
	@Override
	public void draw(Graphics _graphics) {
		_graphics.setColor(Color.BLUE);
		if(mRect != null) {
						
			_graphics.drawRect(this.mStartX, this.mStartY, this.mWidth, this.mHeight);
			_graphics.setColor(Color.BLACK);
			
			_graphics.drawLine(
					this.mStartX+(this.mWidth/2), 
					this.mStartY+(this.mHeight/2), 
					(this.mRect.mStartX + (this.mRect.mHeight/2)), 
					(this.mRect.mStartY + (this.mRect.mWidth/2)));
			
			if(mText != null) {
				_graphics.drawString(mText, mStartX+10, mStartY+10);
			}
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
