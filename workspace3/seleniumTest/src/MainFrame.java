
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.Timer;

public class MainFrame extends JFrame {

	private static JFrame jf_1; // フレーム

	private static JLabel jl_1; // ラベル

	private static JButton jb_start; // テスト開始ボタンー
	private static JButton jb_clear; // クリアボタンー
	private static JButton jb_inFile; // 入力ファイル選択ボタンー
	private static JButton jb_outFolder; // 出力フォルダ選択ボタンー

	private static JTextField jt_kinouId; // 機能ID
	private static JTextField jt_pjName; // PJ名
	private static JTextField jt_idxStartX; // 座標起始X
	private static JTextField jt_idxStartY; // 座標起始Y
	private static JTextField jt_inFile; // 入力ファイル
	private static JTextField jt_outFolder; // 出力フォルダ

	private static JComboBox jC_gensin; // 現新環境
	private static JComboBox jC_testType; // テスト種類
	private static JComboBox jC_outfileType; // 出力ファイル種類

	private static JLabel jl_title; // タイトルラベル
	private static JLabel jl_kinouId; // 機能IDラベル
	private static JLabel jl_pjName; // PJ名ラベル
	private static JLabel jl_gensin; // 現新環境ラベル
	private static JLabel jl_idxStart; // 座標起始ラベル
	private static JLabel jl_testType; // テスト種類ラベル
	private static JLabel jl_outFileType; // 出力ファイル種類ラベル
	private static JLabel jl_inFile; // 入力ファイルラベル
	private static JLabel jl_outFolder; // 出力フォルダラベル
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
		jf_1 = new JFrame("自動化テスト");
		jf_1.setSize(600, 510);

		jl_title = new JLabel("自動化テスト");
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

		jl_kinouId = new JLabel("機能ID：");
		jl_kinouId.setHorizontalAlignment(JTextField.RIGHT);
		jl_kinouId.setBounds(30, 50, 160, 30);
		jl_kinouId.setFont(font);
		jl_1.add(jl_kinouId);

		jt_kinouId = new JTextField("OHC");
		jt_kinouId.setBounds(200, 50, 260, 30);
		jt_kinouId.setFont(font);
		jl_1.add(jt_kinouId);

		jl_pjName = new JLabel("PJ名：");
		jl_pjName.setHorizontalAlignment(JTextField.RIGHT);
		jl_pjName.setBounds(30, 90, 160, 30);
		jl_pjName.setFont(font);
		jl_1.add(jl_pjName);

		jt_pjName = new JTextField("OHC社内案件");
		jt_pjName.setBounds(200, 90, 260, 30);
		jt_pjName.setFont(font);
		jl_1.add(jt_pjName);

		jl_gensin = new JLabel("現新環境：");
		jl_gensin.setHorizontalAlignment(JTextField.RIGHT);
		jl_gensin.setBounds(30, 130, 160, 30);
		jl_gensin.setFont(font);
		jl_1.add(jl_gensin);

		String[] gensin = new String[] { "現", "新" };
		jC_gensin = new JComboBox(gensin);
		jC_gensin.setBounds(200, 130, 60, 30);
		jC_gensin.setFont(font);
		jl_1.add(jC_gensin);

		jl_idxStart = new JLabel("座標起始（X、Y）：");
		jl_idxStart.setHorizontalAlignment(JTextField.RIGHT);
		jl_idxStart.setBounds(30, 170, 160, 30);
		jl_idxStart.setFont(font);
		jl_1.add(jl_idxStart);

		jt_idxStartX = new JTextField("1");
		jt_idxStartX.setBounds(200, 170, 30, 30);
		jt_idxStartX.setFont(font);
		jl_1.add(jt_idxStartX);

		jt_idxStartY = new JTextField("3");
		jt_idxStartY.setBounds(240, 170, 30, 30);
		jt_idxStartY.setFont(font);
		jl_1.add(jt_idxStartY);

		jl_testType = new JLabel("テスト種類：");
		jl_testType.setHorizontalAlignment(JTextField.RIGHT);
		jl_testType.setBounds(30, 210, 160, 30);
		jl_testType.setFont(font);
		jl_1.add(jl_testType);

		String[] testType = new String[] { "単体テスト" };
		jC_testType = new JComboBox(testType);
		jC_testType.setBounds(200, 210, 160, 30);
		jC_testType.setFont(font);
		jl_1.add(jC_testType);

		jl_outFileType = new JLabel("出力ファイル種類：");
		jl_outFileType.setHorizontalAlignment(JTextField.RIGHT);
		jl_outFileType.setBounds(30, 250, 160, 30);
		jl_outFileType.setFont(font);
		jl_1.add(jl_outFileType);

		String[] outfileType = new String[] { "分割", "纏め" };
		jC_outfileType = new JComboBox(outfileType);
		jC_outfileType.setBounds(200, 250, 100, 30);
		jC_outfileType.setFont(font);
		jl_1.add(jC_outfileType);

		jl_inFile = new JLabel("入力ファイル：");
		jl_inFile.setHorizontalAlignment(JTextField.RIGHT);
		jl_inFile.setBounds(30, 290, 160, 30);
		jl_inFile.setFont(font);
		jl_1.add(jl_inFile);

		jt_inFile = new JTextField("D:\\selenium\\入力データ.xlsx");
		jt_inFile.setBounds(200, 290, 260, 30);
		jt_inFile.setFont(font);
		jt_inFile.setEditable(false);
		jt_inFile.setBackground(Color.WHITE);
		jl_1.add(jt_inFile);

		jb_inFile = new JButton("選択");
		jb_inFile.setBounds(470, 290, 75, 30);
		jb_inFile.setFont(font);
		jl_1.add(jb_inFile);

		jl_outFolder = new JLabel("出力フォルダ：");
		jl_outFolder.setHorizontalAlignment(JTextField.RIGHT);
		jl_outFolder.setBounds(30, 330, 160, 30);
		jl_outFolder.setFont(font);
		jl_1.add(jl_outFolder);

		jt_outFolder = new JTextField("D:\\selenium\\Output");
		jt_outFolder.setBounds(200, 330, 260, 30);
		jt_outFolder.setFont(font);
		jt_outFolder.setEditable(false);
		jt_outFolder.setBackground(Color.WHITE);
		jl_1.add(jt_outFolder);

		jb_outFolder = new JButton("選択");
		jb_outFolder.setBounds(470, 330, 75, 30);
		jb_outFolder.setFont(font);
		jl_1.add(jb_outFolder);

		jb_start = new JButton("テスト開始");
		jb_start.setBounds(120, 380, 150, 40);
		jb_start.setFont(font);
		jl_1.add(jb_start);

		jb_clear = new JButton("クリア");
		jb_clear.setBounds(320, 380, 150, 40);
		jb_clear.setFont(font);
		jl_1.add(jb_clear);

		jl_msg = new JLabel();
		jl_msg.setBounds(50, 425, 520, 45);
		jl_msg.setFont(font);
		jl_1.add(jl_msg);

		progressBar = new JProgressBar();
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progressBar.setValue(intProgress);
		progressBar.setStringPainted(true);
		progressBar.setString("テスト中......");
		progressBar.setBounds(70, 425, 500, 30);
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
				// 機能IDのチェック
				if (isEmptyCheck(jt_kinouId, jl_kinouId.getText(), "0")) {
					return;
				}
				// PJ名のチェック
				if (isEmptyCheck(jt_pjName, jl_pjName.getText(), "0")) {
					return;
				}
				// 座標起始Xのチェック
				if (isEmptyCheck(jt_idxStartX, jl_idxStart.getText(), "0")) {
					return;
				}
				// 座標起始Yのチェック
				if (isEmptyCheck(jt_idxStartY, jl_idxStart.getText(), "0")) {
					return;
				}
				// 入力ファイルのチェック
				if (isEmptyCheck(jt_inFile, jl_inFile.getText(), "1")) {
					return;
				}
				// 出力フォルダのチェック
				if ("出力フォルダ：".equals(jl_outFolder.getText())) {
					if (isEmptyCheck(jt_outFolder, jl_outFolder.getText(), "2")) {
						return;
					}
				} else {
					if (isEmptyCheck(jt_outFolder, jl_outFolder.getText(), "1")) {
						return;
					}
				}

				DataFrom dataFrom = new DataFrom();
				// 現新環境を設定する
				dataFrom.setStrGensin(jC_gensin.getSelectedItem().toString());
				// 座標起始Xを設定する
				dataFrom.setStrIdxStartX(jt_idxStartX.getText());
				// 座標起始Xを設定する
				dataFrom.setStrIdxStartY(jt_idxStartY.getText());
				// テスト種類を設定する
				dataFrom.setStrTestType(jC_testType.getSelectedItem().toString());
				// 出力ファイル種類を設定する
				dataFrom.setStrOutfileType(jC_outfileType.getSelectedItem().toString());
				// 入力ファイルを設定する
				dataFrom.setStrInFile(jt_inFile.getText());
				// 出力フォルダを設定する
				dataFrom.setStrOutFolder(jt_outFolder.getText());

				jb_start.setEnabled(false);
				progressBar.setVisible(true);
				setDataFrom(dataFrom);
				AspNetTest r1 = new AspNetTest();
				r1.start();

			}
		};

		// クリアボタンーを押下
		ActionListener jb_clear_ls = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clearColor();
				jt_kinouId.setText(""); // 機能ID
				jt_pjName.setText(""); // PJ名
				jC_gensin.setSelectedIndex(0);
				jt_idxStartX.setText("");  // 座標起始X
				jt_idxStartY.setText(""); // 座標起始Y
				jC_testType.setSelectedIndex(0);
				jC_outfileType.setSelectedIndex(0);
				jt_inFile.setText(""); // 入力ファイル
				jt_outFolder.setText(""); // 出力フォルダ
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

		JFileChooser jfcOutFolder = new JFileChooser();
		// 出力フォルダの選択ボタンーを押下
		ActionListener jb_outFolder_ls = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if ("出力フォルダ：".equals(jl_outFolder.getText())) {
					// 分割の場合
					jfcOutFolder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				} else {
					// 纏めの場合
					jfcOutFolder.setFileSelectionMode(JFileChooser.FILES_ONLY);
				}

				int intRetNum = jfcOutFolder.showDialog(new JLabel(), "選択");
				// 選択ボタンーを押下の場合
				if (intRetNum == 0) {
					// ファイルを取得する
					File file = jfcOutFolder.getSelectedFile();
					if (file != null) {
						jt_outFolder.setText(file.getAbsolutePath());
						jfcOutFolder.setCurrentDirectory(jfcOutFolder.getSelectedFile());
					}
				}
			}
		};

		// 出力ファイル種類を変更
		ActionListener jC_outfileType_ls = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				jt_outFolder.setText("");
				if ("分割".equals(jC_outfileType.getSelectedItem())) {
					// 分割の場合
					jl_outFolder.setText("出力フォルダ：");
				} else {
					// 纏めの場合
					jl_outFolder.setText("纏め元ファイル：");
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
		jb_outFolder.addActionListener(jb_outFolder_ls);
		jC_outfileType.addActionListener(jC_outfileType_ls);
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

		jt_kinouId.setBackground(Color.WHITE); // 機能ID
		jt_pjName.setBackground(Color.WHITE); // PJ名
		jt_idxStartX.setBackground(Color.WHITE); // 座標起始X
		jt_idxStartY.setBackground(Color.WHITE); // 座標起始Y
		jt_inFile.setBackground(Color.WHITE); // 入力ファイル
		jt_outFolder.setBackground(Color.WHITE); // 出力フォルダ
		jl_msg.setText("");
		jf_1.validate();
	}
}