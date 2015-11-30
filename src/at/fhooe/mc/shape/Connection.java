package at.fhooe.mc.shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import at.fhooe.mc.view.DrawPanel;

public class Connection extends ShapePrimitive{

	public int mStartX;
	public int mStartY;
	
	public int mEndX;
	public int mEndY;
	
	public int mWidth;
	public int mHeight;
	
	private RectangleObject mStartRect;
	private RectangleObject mEndRect;
	
	private String mText;
	private Shape mShape;
	
	public Connection(RectangleObject _startRect, RectangleObject _endRect, DrawPanel _panel) {
		super(_panel);
		
		mStartRect = _startRect;
		mEndRect = _endRect;
			
		setChanged();
        notifyObservers();
	}
	
	@Override
	public void draw(Graphics _graphics) {
		_graphics.setColor(Color.BLACK);
		if(mStartRect != null) {
			mStartX = mStartRect.mStartX + (mStartRect.mWidth / 2);
			mStartY = mStartRect.mStartY + (mStartRect.mHeight / 2);
			if(mEndRect != null) {
				mEndX = mEndRect.mStartX + (mEndRect.mWidth / 2);
				mEndY = mEndRect.mStartY + (mEndRect.mHeight / 2);				
			}
		}
		
		mWidth = mEndX - mStartX;
		mHeight = mEndY - mStartY;
		
		Point p1 = new Point(this.mStartX, this.mStartY);
		Point p2 = new Point(this.mEndX, this.mEndY);
		
		_graphics.drawLine(this.mStartX, this.mStartY, this.mEndX, this.mEndY);
		mShape = new Line2D.Double(p1, p2);
		if(mText != null) {
			_graphics.drawString(mText, mStartX+(mWidth/2), mStartY+(mHeight/2));
		}
	}
	
	@Override
	public boolean clickInside(int _clickX, int _clickY) {
		
		
		Rectangle2D boundingBox = new Rectangle2D.Double();
		
		boundingBox.setFrame(mStartX, mStartY, mWidth, mHeight);
			
		if(boundingBox.getBounds().contains(_clickX, _clickY)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void move(int _dX, int _dY) {
		// Lines should not be moved
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

	public RectangleObject getStartRect() {
		return mStartRect;
	}

	public RectangleObject getEndRect() {
		return mEndRect;
	}

	public Shape getShape() {
		return mShape;
	}
	
	
	
}
