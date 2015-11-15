package at.fhooe.mc.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import at.fhooe.mc.shape.CommentObject;
import at.fhooe.mc.shape.Connection;
import at.fhooe.mc.shape.RectangleObject;
import at.fhooe.mc.shape.ShapePrimitive;

/**
 * Here is actually the Drawing.
 * @author christian
 *
 */
@SuppressWarnings("serial")
public class DrawPanel extends Panel implements MouseListener, Observer, MouseMotionListener {

	public static final String BUTTON_TYPE_RECT = "rect";
	public static final String BUTTON_TYPE_LINE = "line";
	public static final String BUTTON_TYPE_COMM = "comment";
	public static final String BUTTON_TYPE_MOVE = "move";
	public static final String BUTTON_TYPE_TEXT = "text";
	
	ArrayList<RectangleObject> mRectangleList = new ArrayList<RectangleObject>();
	ArrayList<Connection> mConnectionList = new ArrayList<Connection>();
	ArrayList<CommentObject> mCommentList = new ArrayList<CommentObject>();
	
	String mButtonType;
	
	//for move operation
	private int mStartX;
	private int mStartY;
	
	//highlight operation
	private ShapePrimitive mCurrentShape;
	
	//for lines
	private RectangleObject mStartRect = null;
	private RectangleObject mEndRect = null;
		
	public void setButtonType(String mButtonType) {
		this.mButtonType = mButtonType;
	}

	public DrawPanel() {
		this.setBackground(Color.LIGHT_GRAY);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	public void clearWindow() {	
		mRectangleList.clear();
		mConnectionList.clear();
		mCommentList.clear();
		mStartRect = null;
		mEndRect = null;
		repaint();
	}
	
	@Override
	public void paint(Graphics _graphics) {
				
		for(RectangleObject rect : mRectangleList) {
			rect.draw(_graphics);
		}
		
		for(Connection conn : mConnectionList) {
			conn.draw(_graphics);
		}
		
		for(CommentObject comm : mCommentList) {
			comm.draw(_graphics);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent _event) {		
		if(mButtonType == DrawPanel.BUTTON_TYPE_RECT) {
			mRectangleList.add(new RectangleObject(this, _event.getX(), _event.getY()));
			
		} else if(mButtonType == DrawPanel.BUTTON_TYPE_TEXT) {
			for(RectangleObject rect : mRectangleList) {
					if(rect.clickInside(_event.getX(), _event.getY())) {
						rect.setText(DrawWindow.mTextField.getText());
					}
			}
			for(Connection conn : mConnectionList) {
				if(conn.clickInside(_event.getX(), _event.getY())) {
					conn.setText(DrawWindow.mTextField.getText());
				}	
			}
			for(CommentObject comm : mCommentList) {
				if(comm.clickInside(_event.getX(), _event.getY())) {
					comm.setText(DrawWindow.mTextField.getText());
				}	
			}
		} else if(mButtonType == DrawPanel.BUTTON_TYPE_COMM) {
			
			for(RectangleObject rect : mRectangleList) {
				if(rect.clickInside(_event.getX(), _event.getY())) {
					mCommentList.add(new CommentObject(rect, this));
				}
			}
		} else {
			System.out.println("Error in Paint, Wrong Button");
		}		
	}

	@Override
	public void mouseEntered(MouseEvent _event) {}

	@Override
	public void mouseExited(MouseEvent _event) {}

	@Override
	public void mousePressed(MouseEvent _event) {
		if(mButtonType == DrawPanel.BUTTON_TYPE_LINE) {	
			for(RectangleObject rect : mRectangleList) {
				if(rect.getClass().equals(RectangleObject.class)) {
					if(rect.clickInside(_event.getX(), _event.getY())) {
							mStartRect = rect;
					}	
				}
			}		
		} else if(mButtonType == DrawPanel.BUTTON_TYPE_MOVE) {
			mStartX = _event.getX();
			mStartY = _event.getY();	
		}
	}
	

	@Override
	public void mouseReleased(MouseEvent _event) {
		if(mButtonType == DrawPanel.BUTTON_TYPE_LINE) {	
			for(RectangleObject rect : mRectangleList) {
				if(rect.getClass().equals(RectangleObject.class)) {
					if(rect.clickInside(_event.getX(), _event.getY())) {
							mEndRect = rect;
							mConnectionList.add(new Connection(mStartRect, mEndRect, this));
					}	
				}
			}			
		} 
	}

	@Override
	public void update(Observable _observalbe, Object _object) {
		repaint();	
	}

	// ##### Mouse Motion Listener ####
	@Override
	public void mouseDragged(MouseEvent _event) {
		if(mButtonType == DrawPanel.BUTTON_TYPE_MOVE) {
			for(RectangleObject rect : mRectangleList) {
				if(rect.clickInside(_event.getX(), _event.getY())) {
					
					int dX = _event.getX() - mStartX;
					int dY = _event.getY() - mStartY;
					
					rect.move(dX, dY);
					
					mStartX = _event.getX();
					mStartY = _event.getY();
				}
			}
			for(Connection conn : mConnectionList) {
				if(conn.clickInside(_event.getX(), _event.getY())) {
					int dX = _event.getX() - mStartX;
					int dY = _event.getY() - mStartY;
					
					conn.move(dX, dY);
					
					mStartX = _event.getX();
					mStartY = _event.getY();
				}	
			}
			for(CommentObject comm : mCommentList) {
				if(comm.clickInside(_event.getX(), _event.getY())) {
					int dX = _event.getX() - mStartX;
					int dY = _event.getY() - mStartY;
					
					comm.move(dX, dY);
					
					mStartX = _event.getX();
					mStartY = _event.getY();
				}	
			}
		}	
	}

	@Override
	public void mouseMoved(MouseEvent _event) {
		// TODO Auto-generated method stub
	}
}
