package at.fhooe.mc.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The Window Where all draw area and the Buttons are in.
 * @author christian
 *
 */
@SuppressWarnings("serial")
public class DrawWindow extends java.awt.Frame implements ActionListener{
	
	Panel mButtPanel = new Panel();
	DrawPanel mDrawingPanel = new DrawPanel();
	
	Button mRectButton;
	Button mLineButton;
	Button mClearButton;
	
	public DrawWindow(String _name) {
		this.setTitle(_name);
		
		this.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent _e) {
				_e.getWindow().setVisible(false);
				System.exit(0);
			}		
		});
		
		createButtons();
		
		this.setSize(700, 600);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		
		this.add(mButtPanel, BorderLayout.NORTH);
		this.add(mDrawingPanel, BorderLayout.CENTER);
	}
	
	private void createButtons() {	
		mRectButton = new Button("Draw Rect");
		mRectButton.addActionListener(this);
		mButtPanel.add(mRectButton);
		
		mLineButton = new Button("Draw Line");
		mLineButton.addActionListener(this);
		mButtPanel.add(mLineButton);
		
		mClearButton = new Button("Clear");
		mClearButton.addActionListener(this);
		mButtPanel.add(mClearButton);
	}

	@Override
	public void actionPerformed(ActionEvent _event) {	
			if(_event.getSource() == mRectButton) {
				System.out.println("Rect Button");
				mDrawingPanel.setButtonType(DrawPanel.BUTTON_TYPE_RECT);
			} else if(_event.getSource() == mLineButton) {
				System.out.println("Line Button");
				mDrawingPanel.setButtonType(DrawPanel.BUTTON_TYPE_LINE);
			} else if(_event.getSource() == mClearButton) {
				System.out.println("Clear Button");
				mDrawingPanel.clearWindow();
			} else {
				System.out.println("Error in actionPerformed, no button");
			}
	}
}
