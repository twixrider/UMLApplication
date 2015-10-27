package at.fhooe.mc.shape;

import java.awt.Graphics;

public class CommentObject implements ShapePrimitive {

	public int mStartX;
	public int mStartY;
	
	public int mWidth;
	public int mHeight;
	
	private String mText;
	
	private RectangleObject mRect;
		
	public CommentObject(RectangleObject _rect) {
		
		mRect = _rect;
		
		mWidth = 120;
		mHeight = 100;
		
		mStartX = (_rect.mStartX + _rect.mWidth + 100);
		mStartY = (_rect.mStartY + _rect.mHeight/2) - (mHeight/2);
		
	}	
	
	@Override
	public void draw(Graphics _graphics) {
		// TODO Auto-generated method stub
		if(mRect != null) {
			_graphics.drawRect(mStartX, mStartY, mWidth, mHeight);
			_graphics.drawLine(mStartX+(mWidth/2), mStartY+(mHeight/2), (mRect.mStartX + (mRect.mHeight/2)), (mRect.mStartY + (mRect.mWidth/2)));
			if(mText != null) {
			_graphics.drawString(mText, mStartX+10, mStartY+10);
			}
		}
	}

	@Override
	public void move(int _dX, int _dY) {
		// TODO Auto-generated method stub
	}
	
	@Override
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

	@Override
	public void setText(String _str) {
		mText = _str;		
	}
}
