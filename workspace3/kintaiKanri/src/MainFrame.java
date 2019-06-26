
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.Timer;

public class MainFrame extends JFrame {

	private static JFrame jf_1; // フレーム

	private static JLabel jl_1; // ラベル

	private static JButton jb_start; // テスト開始ボタンー
	private static JButton jb_clear; // クリアボタンー
	private static JButton jb_inFile; // 入力ファイル選択ボタンー

	private static JTextField jt_userId; // ユーザID
	private static JTextField jt_pwd; // パスワード
	private static JTextField jt_pjName; // プロジェクト名
	private static JTextField jt_inFile; // 入力ファイル

	private static JLabel jl_title; // タイトルラベル
	private static JLabel jl_userId; // ユーザIDラベル
	private static JLabel jl_pwd; // パスワードラベル
	private static JLabel jl_pjName; // プロジェクト名ラベル
	private static JLabel jl_inFile; // 入力ファイルラベル
	private static JLabel jl_msg; // メッセージラベル
	private static JLabel jl_date; // システム日付

	// 進捗状況
	private static JProgressBar progressBar;

	// 進捗
	public static int intProgress = 0;

	// 進捗を設定
	public static void setIntProgress(int intPro) {
		intProgress = intPro;
	}

	// 進捗
	public static DataFrom dataFrom;

	public static void setDataFrom(DataFrom dtFrom) {
		dataFrom = dtFrom;
	}

	public static void main(String[] args) {

		jl_1 = new JLabel();
		Font font = new Font("ＭＳ Ｐゴシック", Font.PLAIN, 20); // フォントの設定
		Font titleFont = new Font("ＭＳ ゴシック", Font.BOLD, 22); // フォントの設定
		jf_1 = new JFrame("勤怠管理入力");
		jf_1.setSize(600, 400);

		jl_title = new JLabel("勤怠管理入力");
		jl_title.setHorizontalAlignment(JTextField.CENTER);
		jl_title.setBounds(0, 10, 600, 30);
		jl_title.setFont(titleFont);
		jl_1.add(jl_title);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		jl_date = new JLabel(format.format(new Date()));
		jl_date.setHorizontalAlignment(JTextField.CENTER);
		jl_date.setBounds(490, 10, 100, 15);
		jl_date.setFont(font);
		jl_1.add(jl_date);

		jl_userId = new JLabel("ユーザID：");
		jl_userId.setHorizontalAlignment(JTextField.RIGHT);
		jl_userId.setBounds(30, 50, 160, 30);
		jl_userId.setFont(font);
		jl_1.add(jl_userId);

		jt_userId = new JTextField("");
		jt_userId.setBounds(200, 50, 260, 30);
		jt_userId.setFont(font);
		jl_1.add(jt_userId);

		jl_pwd = new JLabel("パスワード：");
		jl_pwd.setHorizontalAlignment(JTextField.RIGHT);
		jl_pwd.setBounds(30, 90, 160, 30);
		jl_pwd.setFont(font);
		jl_1.add(jl_pwd);

		jt_pwd = new JPasswordField();
		jt_pwd.setBounds(200, 90, 260, 30);
		jt_pwd.setFont(font);
		jl_1.add(jt_pwd);

		jl_pjName = new JLabel("プロジェクト名：");
		jl_pjName.setHorizontalAlignment(JTextField.RIGHT);
		jl_pjName.setBounds(30, 130, 160, 30);
		jl_pjName.setFont(font);
		jl_1.add(jl_pjName);

		jt_pjName = new JTextField("");
		jt_pjName.setBounds(200, 130, 260, 30);
		jt_pjName.setFont(font);
		jl_1.add(jt_pjName);

		jl_inFile = new JLabel("勤務表：");
		jl_inFile.setHorizontalAlignment(JTextField.RIGHT);
		jl_inFile.setBounds(30, 170, 160, 30);
		jl_inFile.setFont(font);
		jl_1.add(jl_inFile);

		jt_inFile = new JTextField();
		jt_inFile.setBounds(200, 170, 260, 30);
		jt_inFile.setFont(font);
//		jt_inFile.setEditable(false);
		jt_inFile.setBackground(Color.WHITE);
		jl_1.add(jt_inFile);

		jb_inFile = new JButton("選択");
		jb_inFile.setBounds(470, 170, 75, 30);
		jb_inFile.setFont(font);
		jl_1.add(jb_inFile);

		jb_start = new JButton("入力開始");
		jb_start.setBounds(120, 260, 150, 40);
		jb_start.setFont(font);
		jl_1.add(jb_start);

		jb_clear = new JButton("クリア");
		jb_clear.setBounds(320, 260, 150, 40);
		jb_clear.setFont(font);
		jl_1.add(jb_clear);

		jl_msg = new JLabel();
		jl_msg.setBounds(50, 305, 520, 45);
		jl_msg.setFont(font);
		jl_1.add(jl_msg);

		progressBar = new JProgressBar();
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progressBar.setValue(intProgress);
		progressBar.setStringPainted(true);
		progressBar.setString("入力中......");
		progressBar.setBounds(70, 305, 500, 30);
		progressBar.setVisible(false);
		jl_1.add(progressBar);

		jf_1.add(jl_1);
		jf_1.setVisible(true);
		jf_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf_1.setResizable(false);
		jf_1.setLocation(400, 200);

		// テストボタンーを押下
		ActionListener jb_start_ls = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				// 色をクリアする
				clearColor();
				// ユーザIDのチェック
				if (isEmptyCheck(jt_userId, jl_userId.getText(), "0")) {
					return;
				}
				// パスワードのチェック
				if (isEmptyCheck(jt_pwd, jl_pwd.getText(), "0")) {
					return;
				}
				// PJ名のチェック
				if (isEmptyCheck(jt_pjName, jl_pjName.getText(), "0")) {
					return;
				}

				// 入力ファイルのチェック
				if (isEmptyCheck(jt_inFile, jl_inFile.getText(), "1")) {
					return;
				}

				DataFrom dataFrom = new DataFrom();
				// ユーザIDを設定する
				dataFrom.setStrUserId(jt_userId.getText());
				// パスワードを設定する
				dataFrom.setStrPwd(jt_pwd.getText());
				// プロジェクト名を設定する
				dataFrom.setStrPjName(jt_pjName.getText());
				// 入力ファイルを設定する
				dataFrom.setStrInFile(jt_inFile.getText());

				jb_start.setEnabled(false);
				progressBar.setVisible(true);
				setDataFrom(dataFrom);
				KintaiKanriNyuuryoku r1 = new KintaiKanriNyuuryoku();
				r1.start();

			}
		};

		// クリアボタンーを押下
		ActionListener jb_clear_ls = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clearColor();
				jt_userId.setText(""); // ユーザID
				jt_pwd.setText(""); // パスワード
				jt_pjName.setText(""); // PJ名
				jt_inFile.setText(""); // 入力ファイル
				jl_msg.setText(""); // メッセージ
			}
		};

		JFileChooser jfcInfile = new JFileChooser();
		// 入力ファイルの選択ボタンーを押下
		ActionListener jb_inFile_ls = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				jfcInfile.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int intRetNum = jfcInfile.showDialog(new JLabel(), "選択");
				// 選択ボタンーを押下の場合
				if (intRetNum == 0) {
					// ファイルを取得する
					File file = jfcInfile.getSelectedFile();
					if (file != null) {
						jt_inFile.setText(file.getAbsolutePath());
						jfcInfile.setCurrentDirectory(jfcInfile.getSelectedFile());
					}
				}
			}
		};

		new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (progressBar.isVisible()) {
					// 進捗を設定する
					progressBar.setValue(intProgress);
					// テスト正常終了の場合
					if (intProgress == 100) {
						progressBar.setValue(0);
						progressBar.setVisible(false);
						if ("0".equals(dataFrom.getStrCode())) {
							jb_start.setEnabled(true);
							jl_msg.setText(dataFrom.getStrMsg());
							jl_msg.setForeground(Color.BLUE);
						}
					} else {
						// テスト異常終了の場合
						if (!"0".equals(dataFrom.getStrCode())) {
							progressBar.setValue(0);
							progressBar.setVisible(false);
							jb_start.setEnabled(true);
							jl_msg.setText("<html>" + dataFrom.getStrMsg() + "</html>");
							jl_msg.setForeground(Color.RED);
						}
					}
				}

			}
		}).start();

		// リスナーを追加する
		jb_start.addActionListener(jb_start_ls);
		jb_clear.addActionListener(jb_clear_ls);
		jb_inFile.addActionListener(jb_inFile_ls);
	}

	/*
	 * 入力内容をチェックする
	 */
	public static boolean isEmptyCheck(JTextField chkText, String strCtlName, String fileFlag) {

		// 空のチェックする
		if (chkText.getText().isEmpty()) {
			// 背景色を設定する
			chkText.setBackground(Color.RED);
			// メッセージを設定する
			jl_msg.setText(strCtlName.replace("：", "") + "を入力ください。");
			// フォントの色設定する
			jl_msg.setForeground(Color.RED);
			// フォーカスを設定する
			chkText.requestFocus();
			return true;
		}

		// ファイルの場合
		if ("1".equals(fileFlag) || "2".equals(fileFlag)) {
			File file = new File(chkText.getText());
			// ファイル存在チェック
			if (!file.exists()) {
				chkText.setBackground(Color.RED);
				if ("1".equals(fileFlag)) {
					jl_msg.setText("指定されたファイルが見つかりません。");
				} else {
					jl_msg.setText("指定されたフォルダが見つかりません。");
				}
				jl_msg.setForeground(Color.RED);
				return true;
			}
		}

		return false;
	}

	/*
	 * 色をクリアする
	 */
	public static void clearColor() {

		jt_userId.setBackground(Color.WHITE); // ユーザID
		jt_pwd.setBackground(Color.WHITE); // パスワード
		jt_pjName.setBackground(Color.WHITE); // PJ名
		jt_inFile.setBackground(Color.WHITE); // 入力ファイル
		jl_msg.setText("");
		jf_1.validate();
	}
}