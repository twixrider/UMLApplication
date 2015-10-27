package at.fhooe.mc.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import at.fhooe.mc.shape.CommentObject;
import at.fhooe.mc.shape.Line;
import at.fhooe.mc.shape.RectangleObject;
import at.fhooe.mc.shape.ShapePrimitive;

/**
 * Here is actually the Drawing.
 * @author christian
 *
 */
@SuppressWarnings("serial")
public class DrawPanel extends Panel implements MouseListener{

	public static final String BUTTON_TYPE_RECT = "rect";
	public static final String BUTTON_TYPE_LINE = "line";
	public static final String BUTTON_TYPE_COMM = "comment";
	public static final String BUTTON_TYPE_MOVE = "move";
	public static final String BUTTON_TYPE_TEXT = "text";
	
	ArrayList<ShapePrimitive> mShapeList = new ArrayList<ShapePrimitive>();
	
	String mButtonType;
	private int mStartX;
	private int mStartY;
	
	//for lines
	private RectangleObject mStartRect = null;
	private RectangleObject mEndRect = null;
	
	private boolean mMouseInsideOnPress = false;
	private RectangleObject mMovedRect;
	
	public void setButtonType(String mButtonType) {
		this.mButtonType = mButtonType;
	}

	public DrawPanel() {
		this.setBackground(Color.LIGHT_GRAY);
		this.addMouseListener(this);
	}
	
	public void clearWindow() {	
		mShapeList.clear();
		mStartRect = null;
		mEndRect = null;
		repaint();
	}
	
	@Override
	public void paint(Graphics _graphics) {
		_graphics.setColor(Color.BLACK);
		
		for(ShapePrimitive shape : mShapeList) {
			shape.draw(_graphics);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent _event) {
		mStartX = _event.getX();
		mStartY = _event.getY();
		
		if(mButtonType == DrawPanel.BUTTON_TYPE_RECT) {
			System.out.println("Pressed at: " + mStartX + "," + mStartY);
			mShapeList.add(new RectangleObject(mStartX, mStartY));
			
		} else if(mButtonType == DrawPanel.BUTTON_TYPE_TEXT) {
			for(ShapePrimitive shape : mShapeList) {
					if(shape.clickInside(_event.getX(), _event.getY())) {
						shape.setText(DrawWindow.mTextField.getText());
					}
			}
		} else if(mButtonType == DrawPanel.BUTTON_TYPE_COMM) {
			RectangleObject tempRect;
			for(ShapePrimitive shape : mShapeList) {
				if(shape.getClass().equals(RectangleObject.class)) {
					tempRect = (RectangleObject)shape;
					if(tempRect.clickInside(_event.getX(), _event.getY())) {
						mStartRect = tempRect;
					}
				}
			}
			mShapeList.add(new CommentObject(mStartRect));
		} else {
			System.out.println("Error in Paint, Wrong Button");
		}		
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent _event) {}

	@Override
	public void mouseExited(MouseEvent _event) {}

	@Override
	public void mousePressed(MouseEvent _event) {
		if(mButtonType == DrawPanel.BUTTON_TYPE_LINE) {	
			RectangleObject tempRect;
			for(ShapePrimitive shape : mShapeList) {
				if(shape.getClass().equals(RectangleObject.class)) {
					tempRect = (RectangleObject)shape;
					if(tempRect.clickInside(_event.getX(), _event.getY())) {
							mStartRect = tempRect;
					}	
				}
			}
		
		} 
//		else if(mButtonType == DrawPanel.BUTTON_TYPE_MOVE) {
//			for(ShapePrimitive shape : mShapeList) {
//				if(shape.getClass().equals(RectangleObject.class)) {
//					if(((RectangleObject)shape).clickInside(_event.getX(), _event.getY())) {
//						((RectangleObject)shape).mStartX = _event.getX()/((RectangleObject)shape).mWidth/2;
//						((RectangleObject)shape).mStartY = _event.getY()/((RectangleObject)shape).mHeight/2;
//					}	
//				}
//			}
//		}
	}
	

	@Override
	public void mouseReleased(MouseEvent _event) {
		if(mButtonType == DrawPanel.BUTTON_TYPE_LINE) {	
			RectangleObject tempRect;
			for(ShapePrimitive shape : mShapeList) {
				if(shape.getClass().equals(RectangleObject.class)) {
					tempRect = (RectangleObject)shape;
					if(tempRect.clickInside(_event.getX(), _event.getY())) {
							mEndRect = tempRect;
					}	
				}
			}
			mShapeList.add(new Line(mStartRect, mEndRect));
		} 
		

		
		
		
//      else if(mButtonType == DrawPanel.BUTTON_TYPE_MOVE) {
//			RectangleObject tempRect;
//			for(ShapePrimitive shape : mShapeList) {
//			if(shape.getClass().equals(RectangleObject.class)) {
//				tempRect = (RectangleObject)shape;
//				if(tempRect.clickInside(_event.getX(), _event.getY())) {
//					mMovedRect = tempRect;	
//				}	
//			}
//		}
//	}
		repaint();
	}
}
