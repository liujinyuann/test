import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MakeFileCopy {
	static Map<String, ArrayList<String>> mapStrArr = new HashMap<String, ArrayList<String>>();
	static String strOutPath = "";

	private static JFrame jf_1; // フレーム

	private static JLabel jl_1; // ラベル

	private static JLabel jl_title; // タイトルラベル
	private static JLabel jl_date; // システム日付
	private static JLabel jl_inFolder; // 入力フォルダラベル
	private static JLabel jl_outFolder; // 出力フォルダラベル

	private static JTextField jt_inFolder; // 入力フォルダ
	private static JTextField jt_outFolder; // 出力フォルダ

	private static JButton jb_start; // テスト開始ボタンー
	private static JButton jb_clear; // クリアボタンー
	private static JButton jb_inFolder; // 入力フォルダ選択ボタンー
	private static JButton jb_outFolder; // 出力フォルダ選択ボタンー

	public static void main(String[] args) {

		jl_1 = new JLabel();
		Font font = new Font("ＭＳ Ｐゴシック", Font.PLAIN, 20); // フォントの設定
		Font titleFont = new Font("ＭＳ ゴシック", Font.BOLD, 22); // フォントの設定
		jf_1 = new JFrame("makeファイルコピー");
		jf_1.setSize(600, 300);

		jl_title = new JLabel("makeファイルコピー");
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

		jl_inFolder = new JLabel("入力フォルダ：");
		jl_inFolder.setHorizontalAlignment(JTextField.RIGHT);
		jl_inFolder.setBounds(25, 50, 160, 30);
		jl_inFolder.setFont(font);
		jl_1.add(jl_inFolder);

		jt_inFolder = new JTextField("");
		jt_inFolder.setBounds(200, 50, 260, 30);
		jt_inFolder.setFont(font);
//		jt_inFolder.setEditable(false);
		jt_inFolder.setBackground(Color.WHITE);
		jl_1.add(jt_inFolder);

		jb_inFolder = new JButton("選択");
		jb_inFolder.setBounds(470, 50, 75, 30);
		jb_inFolder.setFont(font);
		jl_1.add(jb_inFolder);

		jl_outFolder = new JLabel("出力フォルダ：");
		jl_outFolder.setHorizontalAlignment(JTextField.RIGHT);
		jl_outFolder.setBounds(25, 90, 160, 30);
		jl_outFolder.setFont(font);
		jl_1.add(jl_outFolder);

		jt_outFolder = new JTextField("");
		jt_outFolder.setBounds(200, 90, 260, 30);
		jt_outFolder.setFont(font);
//		jt_outFolder.setEditable(false);
		jt_outFolder.setBackground(Color.WHITE);
		jl_1.add(jt_outFolder);

		jb_outFolder = new JButton("選択");
		jb_outFolder.setBounds(470, 90, 75, 30);
		jb_outFolder.setFont(font);
		jl_1.add(jb_outFolder);

		jb_start = new JButton("コピー開始");
		jb_start.setBounds(120, 150, 160, 40);
		jb_start.setFont(font);
		jl_1.add(jb_start);

		jb_clear = new JButton("クリア");
		jb_clear.setBounds(320, 150, 160, 40);
		jb_clear.setFont(font);
		jl_1.add(jb_clear);

		jf_1.add(jl_1);
		jf_1.setVisible(true);
		jf_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf_1.setResizable(false);
		jf_1.setLocation(400, 200);

		// 読み込み開始ボタンーを押下
		ActionListener jb_start_ls = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mapStrArr = new HashMap<String, ArrayList<String>>();
				String strPath = jt_inFolder.getText();
				strOutPath = jt_outFolder.getText();
				try {
					getFile(strPath, 0);
					JOptionPane.showMessageDialog(null, "makeファイルコピーしました。", "information", JOptionPane.PLAIN_MESSAGE);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "makeファイルコピー失敗。", "information", JOptionPane.PLAIN_MESSAGE);
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}

			}
		};

		// クリアボタンーを押下
		ActionListener jb_clear_ls = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				jt_inFolder.setText(""); // 入力フォルダ
				jt_outFolder.setText(""); // 出力フォルダ
			}
		};

		JFileChooser jfcInFolder = new JFileChooser();
		// 入力フォルダの選択ボタンーを押下
		ActionListener jb_inFolder_ls = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				// 分割の場合
				jfcInFolder.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				int intRetNum = jfcInFolder.showDialog(new JLabel(), "選択");
				// 選択ボタンーを押下の場合
				if (intRetNum == 0) {
					// ファイルを取得する
					File file = jfcInFolder.getSelectedFile();
					if (file != null) {
						jt_inFolder.setText(file.getAbsolutePath());
						jfcInFolder.setCurrentDirectory(jfcInFolder.getSelectedFile());
					}
				}
			}
		};

		JFileChooser jfcOutfile = new JFileChooser();
		// 出力フォルダの選択ボタンーを押下
		ActionListener jb_outFolder_ls = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				jfcOutfile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int intRetNum = jfcOutfile.showDialog(new JLabel(), "選択");
				// 選択ボタンーを押下の場合
				if (intRetNum == 0) {
					// ファイルを取得する
					File file = jfcOutfile.getSelectedFile();
					if (file != null) {
						jt_outFolder.setText(file.getAbsolutePath());
						jfcOutfile.setCurrentDirectory(jfcOutfile.getSelectedFile());
					}
				}
			}
		};

		// リスナーを追加する
		jb_start.addActionListener(jb_start_ls);
		jb_clear.addActionListener(jb_clear_ls);
		jb_inFolder.addActionListener(jb_inFolder_ls);
		jb_outFolder.addActionListener(jb_outFolder_ls);

	}

	private static void getFile(String path, int deep) throws IOException {
		File file = new File(path);
		File[] array = file.listFiles();
		File inFile;
		File outFile;
		File chkFile;
		String strpath;
		String strFileName;

		for (int i = 0; i < array.length; i++) {

			if (array[i].isFile()) {
				strFileName = array[i].getName();
				if (strFileName.contains(".mk") || (!strFileName.contains(".") && strFileName.contains("_make"))) {
					strpath = array[i].getPath();
					// フォルダの存在チェック
					chkFile = new File(strOutPath.concat(strpath.substring(strpath.indexOf("\\"), strpath.length()))
							.replace(array[i].getName(), ""));
					// フォルダ存在なしの場合、新規する
					if (!chkFile.exists()) {
						chkFile.mkdirs();
					}

					inFile = new File(strpath);

					outFile = new File(strOutPath.concat(strpath.substring(strpath.indexOf("\\"), strpath.length())));

					Files.copy(inFile.toPath(), outFile.toPath(),StandardCopyOption.REPLACE_EXISTING);

				}

			} else if (array[i].isDirectory()) {
				getFile(array[i].getPath(), deep + 1);

			}

		}
	}

}
