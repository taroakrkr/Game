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
//		JFrame f = new JFrame("��� ǥ�� ��");
//		f.setSize(300, 200); // âũ��
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//X�� ������ ������ ������ ���ÿ� ���α׷��� ����
//		f.setVisible(true);//â�� ���̳� �Ⱥ��̳�
//	}
//}

class Frame2 extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Frame2() {
		super("���ǥ�ñ�");
		setBounds(1000, 500, 300, 200);//â�� ������ ��ġ x, y, ����, ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);//â ũ�� ���� �Ұ�
		//setLocation(1000,500);//â�� ������ ��ġ

		// --------------------------------------------------------------------------
		// setLayout(new FlowLayout());//�⺻����BoderLayout ���� FlowLayout���� ����
		// JButton button = new JButton("��ư");
		// this.add(button);
		// --------------------------------------------------------------------------
		Container contentPane = this.getContentPane();// ������Panel
		JPanel pane = new JPanel();
		pane.setBackground(Color.lightGray);

		JButton buttonStart = new JButton("Start");
		buttonStart.setMnemonic('s');// ��ư ����Ű����
		buttonStart.setBackground(Color.CYAN);

		final JTextField textPeriod = new JTextField(5);// �ؽ�Ʈ�ʵ�
		JLabel labelPeriod = new JLabel("Input period : ");// ��

		JCheckBox checkboxIsRandom = new JCheckBox("Fire randomly");//üũ�ڽ�
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

		pane.add(buttonStart);// ��ư
		pane.add(labelPeriod);// ��
		pane.add(textPeriod);// �ؽ�Ʈ�ʵ�
		pane.add(checkboxIsRandom);//üũ�ڽ�
		
		
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
