package at.fhooe.mc.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The Window Where draw area and the Buttons are in.
 * @author christian
 *
 */
@SuppressWarnings("serial")
public class DrawWindow extends java.awt.Frame implements ActionListener{
	
	Panel mButtPanel = new Panel();
	DrawPanel mDrawingPanel = new DrawPanel();
	
	Button mRectButton;
	Button mLineButton;
	Button mCommentButton;
	Button mMoveButton;
	Button mTextButton;
	Button mClearButton;
	
	public static TextField mTextField;
	
	public DrawWindow(String _name) {
		this.setTitle(_name);
		
		this.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent _e) {
				_e.getWindow().setVisible(false);
				System.exit(0);
			}		
		});
		
		createButtons();
		
		this.setSize(1000, 700);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		
		this.add(mButtPanel, BorderLayout.NORTH);
		this.add(mDrawingPanel, BorderLayout.CENTER);
		
		mTextField = new TextField();
		this.add(mTextField, BorderLayout.SOUTH);
	}
	
	private void createButtons() {	
		mRectButton = new Button("Draw Rect");
		mRectButton.addActionListener(this);
		mButtPanel.add(mRectButton);
		
		mLineButton = new Button("Draw Line");
		mLineButton.addActionListener(this);
		mButtPanel.add(mLineButton);
		
		mCommentButton = new Button("Comment");
		mCommentButton.addActionListener(this);
		mButtPanel.add(mCommentButton);
		
		mMoveButton = new Button("Move");
		mMoveButton.addActionListener(this);
		mButtPanel.add(mMoveButton);
		
		mTextButton = new Button("Text");
		mTextButton.addActionListener(this);
		mButtPanel.add(mTextButton);
		
		mClearButton = new Button("Clear");
		mClearButton.addActionListener(this);
		mButtPanel.add(mClearButton);
		
	}

	@Override
	public void actionPerformed(ActionEvent _event) {	
			if(_event.getSource() == mRectButton) {
				mDrawingPanel.setButtonType(DrawPanel.BUTTON_TYPE_RECT);
			} else if(_event.getSource() == mLineButton) {
				mDrawingPanel.setButtonType(DrawPanel.BUTTON_TYPE_LINE);
			} else if(_event.getSource() == mCommentButton) {
				mDrawingPanel.setButtonType(DrawPanel.BUTTON_TYPE_COMM);
			} else if(_event.getSource() == mMoveButton) {
				mDrawingPanel.setButtonType(DrawPanel.BUTTON_TYPE_MOVE);
			} else if(_event.getSource() == mTextButton) {
				mDrawingPanel.setButtonType(DrawPanel.BUTTON_TYPE_TEXT);
			} else if(_event.getSource() == mClearButton) {
				mDrawingPanel.clearWindow();
			} else {
				System.out.println("Error in actionPerformed, no button");
			}
	}
}
