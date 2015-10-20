package at.fhooe.mc.shape;

import java.awt.Graphics;

public class Line {

	public int mStartX;
	public int mStartY;
	
	public int mEndX;
	public int mEndY;
	
	private RectangleObject mStartRect;
	private RectangleObject mEndRect;
	
	public Line(RectangleObject _startRect, RectangleObject _endRect) {
		mStartRect = _startRect;
		mEndRect = _endRect;
	}
	
	public void draw(Graphics _graphics) {
		// TODO Auto-generated method stub
		if(mStartRect != null) {
			mStartX = mStartRect.mStartX + (mStartRect.mWidth / 2);
			mStartY = mStartRect.mStartY + (mStartRect.mHeight / 2);
		}
		
		if(mEndRect != null) {
			mEndX = mEndRect.mStartX + (mEndRect.mWidth / 2);
			mEndY = mEndRect.mStartY + (mEndRect.mHeight / 2);
		}

		System.out.println("Draw Line from: " + mStartX + "," + mStartY+" to " + mEndX + "," + mEndY);

		_graphics.drawLine(mStartX, mStartY, mEndX, mEndY);
	}

	public void move(int _dX, int _dY) {
		// TODO Auto-generated method stub
	}
	
	
}
