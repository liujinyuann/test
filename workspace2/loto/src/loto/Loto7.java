package loto;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;

import javax.naming.ldap.Control;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Loto7 {
	private static JFrame jf_1; // �t���[��

	private static JLabel jl_1; // ���x��

	private static JLabel jl_title; // �^�C�g�����x��
	private static JLabel jl_date; // �V�X�e�����t
	private static JLabel jl_lbl1; // �\�z����

	private static JTextField jt_txt1; // �\�z����1
	private static JTextField jt_txt2; // �\�z����2
	private static JTextField jt_txt3; // �\�z����3
	private static JTextField jt_txt4; // �\�z����4
	private static JTextField jt_txt5; // �\�z����5
	private static JTextField jt_txt6; // �\�z����6

	private static JTextField jt_txt7; // �\�z����7

	private static JButton jb_start; // �e�X�g�J�n�{�^���[
	private static JButton jb_clear; // �N���A�{�^���[

	static ArrayList<String> strChkArray = new ArrayList<String>();

	public static void main(String[] args) {

		jl_1 = new JLabel();
		Font font = new Font("�l�r �o�S�V�b�N", Font.PLAIN, 20); // �t�H���g�̐ݒ�
		Font titleFont = new Font("�l�r �S�V�b�N", Font.BOLD, 22); // �t�H���g�̐ݒ�
		jf_1 = new JFrame("LOTO7�̗\�z");
		jf_1.setSize(600, 300);

		jl_title = new JLabel("LOTO7�̗\�z");
		jl_title.setHorizontalAlignment(JTextField.CENTER);
		jl_title.setBounds(0, 10, 600, 30);
		jl_title.setFont(titleFont);
		jl_1.add(jl_title);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		jl_date = new JLabel(format.format(new Date()));
		jl_date.setHorizontalAlignment(JTextField.CENTER);
		jl_date.setBounds(480, 10, 100, 15);
		jl_date.setFont(font);
		jl_1.add(jl_date);

		jl_lbl1 = new JLabel("�\�z���ʁF");
		jl_lbl1.setHorizontalAlignment(JTextField.RIGHT);
		jl_lbl1.setBounds(25, 50, 160, 30);
		jl_lbl1.setFont(font);
		jl_1.add(jl_lbl1);

		jt_txt1 = new JTextField("");
		jt_txt1.setBounds(200, 50, 30, 30);
		jt_txt1.setFont(font);
		jt_txt1.setEditable(false);
		jt_txt1.setBackground(Color.WHITE);
		jl_1.add(jt_txt1);

		jt_txt2 = new JTextField("");
		jt_txt2.setBounds(230, 50, 30, 30);
		jt_txt2.setFont(font);
		jt_txt2.setEditable(false);
		jt_txt2.setBackground(Color.WHITE);
		jl_1.add(jt_txt2);

		jt_txt3 = new JTextField("");
		jt_txt3.setBounds(260, 50, 30, 30);
		jt_txt3.setFont(font);
		jt_txt3.setEditable(false);
		jt_txt3.setBackground(Color.WHITE);
		jl_1.add(jt_txt3);

		jt_txt4 = new JTextField("");
		jt_txt4.setBounds(290, 50, 30, 30);
		jt_txt4.setFont(font);
		jt_txt4.setEditable(false);
		jt_txt4.setBackground(Color.WHITE);
		jl_1.add(jt_txt4);

		jt_txt5 = new JTextField("");
		jt_txt5.setBounds(320, 50, 30, 30);
		jt_txt5.setFont(font);
		jt_txt5.setEditable(false);
		jt_txt5.setBackground(Color.WHITE);
		jl_1.add(jt_txt5);

		jt_txt6 = new JTextField("");
		jt_txt6.setBounds(350, 50, 30, 30);
		jt_txt6.setFont(font);
		jt_txt6.setEditable(false);
		jt_txt6.setBackground(Color.WHITE);
		jl_1.add(jt_txt6);

		jt_txt7 = new JTextField("");
		jt_txt7.setBounds(390, 50, 30, 30);
		jt_txt7.setFont(font);
		jt_txt7.setEditable(false);
		jt_txt7.setBackground(Color.WHITE);
		jl_1.add(jt_txt7);

		jb_start = new JButton("�\�z�J�n");
		jb_start.setBounds(120, 150, 160, 40);
		jb_start.setFont(font);
		jl_1.add(jb_start);

		jb_clear = new JButton("�N���A");
		jb_clear.setBounds(320, 150, 160, 40);
		jb_clear.setFont(font);
		jl_1.add(jb_clear);

		jf_1.add(jl_1);
		jf_1.setVisible(true);
		jf_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf_1.setResizable(false);
		jf_1.setLocation(400, 200);

		// �ǂݍ��݊J�n�{�^���[������
		ActionListener jb_start_ls = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int i = 1;
				int intOut = 0;
				String strOut = "";
				strChkArray = new ArrayList<String>();
				while (i < 8) {
					intOut = (int) (Math.random() * 32);
					strOut = String.valueOf(intOut);

					if (!strChkArray.contains(strOut) && intOut > 0) {
						strChkArray.add(strOut);

						switch (i) {
						case 1:
							jt_txt1.setText(strOut);
							break;
						case 2:
							jt_txt2.setText(strOut);
							break;
						case 3:
							jt_txt3.setText(strOut);
							break;
						case 4:
							jt_txt4.setText(strOut);
							break;
						case 5:
							jt_txt5.setText(strOut);
							break;
						case 6:
							jt_txt6.setText(strOut);
							break;
						case 7:
							jt_txt7.setText(strOut);
							break;

						default:
							break;
						}

						i++;
					}

				}

			}
		};

		// �N���A�{�^���[������
		ActionListener jb_clear_ls = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				jt_txt1.setText(""); // �\�z����1
				jt_txt2.setText(""); // �\�z����2
				jt_txt3.setText(""); // �\�z����3
				jt_txt4.setText(""); // �\�z����4
				jt_txt5.setText(""); // �\�z����5
				jt_txt6.setText(""); // �\�z����6
				jt_txt7.setText(""); // �\�z����7
			}
		};

		// ���X�i�[��ǉ�����
		jb_start.addActionListener(jb_start_ls);
		jb_clear.addActionListener(jb_clear_ls);

	}
}
