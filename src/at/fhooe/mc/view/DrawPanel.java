package at.fhooe.mc.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import at.fhooe.mc.shape.Line;
import at.fhooe.mc.shape.RectangleObject;

/**
 * Here is actually the Drawing.
 * @author christian
 *
 */
@SuppressWarnings("serial")
public class DrawPanel extends Panel implements MouseListener{

	public static final String BUTTON_TYPE_RECT = "rect";
	public static final String BUTTON_TYPE_LINE = "line";
	
	ArrayList<RectangleObject> mRectList = new ArrayList<RectangleObject>();
	ArrayList<Line> mLineList = new ArrayList<Line>();

	String mButtonType;
	private int mStartX;
	private int mStartY;
	
	//for lines
	private RectangleObject mStartRect = null;
	private RectangleObject mEndRect = null;
	
	public void setButtonType(String mButtonType) {
		this.mButtonType = mButtonType;
	}

	public DrawPanel() {
		this.setBackground(Color.LIGHT_GRAY);
		this.addMouseListener(this);
	}
	
	public void clearWindow() {
		mRectList.clear();
		mLineList.clear();
		repaint();
	}
	
	@Override
	public void paint(Graphics _graphics) {
		_graphics.setColor(Color.BLACK);
		for(RectangleObject rect : mRectList) {
			rect.draw(_graphics);
		}
		for(Line line : mLineList) {
			line.draw(_graphics);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent _event) {
		// TODO Auto-generated method stub
		if(mButtonType == DrawPanel.BUTTON_TYPE_RECT) {
			mStartX = _event.getX();
			mStartY = _event.getY();

			System.out.println("Pressed at: " + mStartX + "," + mStartY);
			mRectList.add(new RectangleObject(mStartX, mStartY));
		} else {
			System.out.println("Error in Paint, Wrong Button");
		}
		
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent _event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent _event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent _event) {
		// TODO Auto-generated method stub
		if(mButtonType == DrawPanel.BUTTON_TYPE_LINE) {	
			for(RectangleObject rect : mRectList) {
				if(rect.clickInside(_event.getX(), _event.getY())) {
					mStartRect = rect;
					rect.highlightRect();
				}	
			}
		}
	}
	

	@Override
	public void mouseReleased(MouseEvent _event) {
		
		if(mButtonType == DrawPanel.BUTTON_TYPE_LINE) {
			if(mButtonType == DrawPanel.BUTTON_TYPE_LINE) {	
				for(RectangleObject rect : mRectList) {
					if(rect.clickInside(_event.getX(), _event.getY())) {
						mEndRect = rect;
					}	
				}
			}
			mLineList.add(new Line(mStartRect, mEndRect));
		}
		repaint();
	}
}
