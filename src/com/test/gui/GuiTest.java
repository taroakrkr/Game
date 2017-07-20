package com.test.gui;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//public class GuiTest {
//	public static void main(String[] args){
//		JFrame f = new JFrame("상단 표시 글");
//		f.setSize(300, 200); // 창크기
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//X를 누르면 프레임 닫힘과 동시에 프로그램도 종료
//		f.setVisible(true);//창이 보이나 안보이나
//	}
//}

class Frame2 extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Frame2() {
		super("상단표시글");
		setBounds(1000, 500, 300, 200);//창이 열리는 위치 x, y, 넓이, 높이
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);//창 크기 변경 불가
		//setLocation(1000,500);//창이 열리는 위치

		// --------------------------------------------------------------------------
		// setLayout(new FlowLayout());//기본설정BoderLayout 에서 FlowLayout으로 변경
		// JButton button = new JButton("버튼");
		// this.add(button);
		// --------------------------------------------------------------------------
		Container contentPane = this.getContentPane();// 콘텐츠Panel
		JPanel pane = new JPanel();
		pane.setBackground(Color.lightGray);

		JButton buttonStart = new JButton("Start");
		buttonStart.setMnemonic('s');// 버튼 단축키설정
		buttonStart.setBackground(Color.CYAN);

		final JTextField textPeriod = new JTextField(5);// 텍스트필드
		JLabel labelPeriod = new JLabel("Input period : ");// 라벨

		JCheckBox checkboxIsRandom = new JCheckBox("Fire randomly");//체크박스
		checkboxIsRandom.setBackground(Color.pink);
		checkboxIsRandom.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if (((JCheckBox) e.getSource()).isSelected()) {
					textPeriod.setText("Random");
					textPeriod.setEnabled(false);
					textPeriod.setBackground(Color.DARK_GRAY);
				} else {
					textPeriod.setText("");
					textPeriod.setEnabled(true);
					textPeriod.setBackground(Color.white);
				}
			}
		});

		pane.add(buttonStart);// 버튼
		pane.add(labelPeriod);// 라벨
		pane.add(textPeriod);// 텍스트필드
		pane.add(checkboxIsRandom);//체크박스
		
		
		contentPane.add(pane);
		// --------------------------------------------------------------------------

		setVisible(true);
	}
}

public class GuiTest {
	public static void main(String[] args) {
		new Frame2();
	}
}
