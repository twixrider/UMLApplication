package at.fhooe.mc.shape;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class Line implements ShapePrimitive{

	public int mStartX;
	public int mStartY;
	
	public int mEndX;
	public int mEndY;
	
	public int mWidth;
	public int mHeight;
	
	private RectangleObject mStartRect;
	private RectangleObject mEndRect;
	
	private String mText;
	
	public Line(RectangleObject _startRect, RectangleObject _endRect) {
		mStartRect = _startRect;
		mEndRect = _endRect;
		
		if(mStartRect != null) {
			mStartX = mStartRect.mStartX + (mStartRect.mWidth / 2);
			mStartY = mStartRect.mStartY + (mStartRect.mHeight / 2);
			if(mEndRect != null) {
				mEndX = mEndRect.mStartX + (mEndRect.mWidth / 2);
				mEndY = mEndRect.mStartY + (mEndRect.mHeight / 2);
				
				mWidth = mEndX - mStartX;
				mHeight = mEndY - mStartY;
			}
		}	
	}
	
	@Override
	public void draw(Graphics _graphics) {
		System.out.println("Draw Line from: " + mStartX + "," + mStartY+" to " + mEndX + "," + mEndY);
		_graphics.drawLine(mStartX, mStartY, mEndX, mEndY);
		if(mText != null) {
			_graphics.drawString(mText, mStartX+(mWidth/2), mStartY+(mHeight/2));
		}
	}
	
	@Override
	public boolean clickInside(int _clickX, int _clickY) {
		Rectangle2D boundingBox = new Rectangle2D.Double();
		
		boundingBox.setFrame(mStartX, mStartY, mWidth, mHeight);
			
		if(boundingBox.contains(_clickX, _clickY)) {
			System.out.println("CLick inside");	
			return true;
		} else {
			System.out.println("Click outside");
		}
	}

	@Override
	public void move(int _dX, int _dY) {}

	@Override
	public void setText(String _str) {
		mText = _str;		
	}
	
	
}
