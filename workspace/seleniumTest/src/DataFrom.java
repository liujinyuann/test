
import java.util.List;

public class DataFrom {

	// 現新環境
	private String strGensin;

	// 座標起始X
	private String strIdxStartX;

	// 座標起始Y
	private String strIdxStartY;

	// テスト種類
	private String strTestType;

	// 出力ファイル種類
	private String strOutfileType;

	// メッセージ
	private String strMsg;

	// コード
	private String strCode = "0";

	// コントロール
	private String strControl;

	// 入力ファイル
	private String strInFile;

	// 出力フォルダ
	private String strOutFolder;

	// テスト情報
	private List<List> list;

	// テスト情報
	private List<String> listSheetName;

	/**
	 * @return strGensin
	 */
	public String getStrGensin() {
		return strGensin;
	}

	/**
	 * @param strGensin セットする strGensin
	 */
	public void setStrGensin(String strGensin) {
		this.strGensin = strGensin;
	}

	/**
	 * @return strIdxStartX
	 */
	public String getStrIdxStartX() {
		return strIdxStartX;
	}

	/**
	 * @param strIdxStartX セットする strIdxStartX
	 */
	public void setStrIdxStartX(String strIdxStartX) {
		this.strIdxStartX = strIdxStartX;
	}

	/**
	 * @return strIdxStartY
	 */
	public String getStrIdxStartY() {
		return strIdxStartY;
	}

	/**
	 * @param strIdxStartY セットする strIdxStartY
	 */
	public void setStrIdxStartY(String strIdxStartY) {
		this.strIdxStartY = strIdxStartY;
	}

	/**
	 * @return strTestType
	 */
	public String getStrTestType() {
		return strTestType;
	}

	/**
	 * @param strTestType セットする strTestType
	 */
	public void setStrTestType(String strTestType) {
		this.strTestType = strTestType;
	}

	/**
	 * @return strOutfileType
	 */
	public String getStrOutfileType() {
		return strOutfileType;
	}

	/**
	 * @param strOutfileType セットする strOutfileType
	 */
	public void setStrOutfileType(String strOutfileType) {
		this.strOutfileType = strOutfileType;
	}

	/**
	 * @return strMsg
	 */
	public String getStrMsg() {
		return strMsg;
	}

	/**
	 * @param strMsg セットする strMsg
	 */
	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}

	/**
	 * @return strCode
	 */
	public String getStrCode() {
		return strCode;
	}

	/**
	 * @param strCode セットする strCode
	 */
	public void setStrCode(String strCode) {
		this.strCode = strCode;
	}

	/**
	 * @return strControl
	 */
	public String getStrControl() {
		return strControl;
	}

	/**
	 * @param strControl セットする strControl
	 */
	public void setStrControl(String strControl) {
		this.strControl = strControl;
	}

	/**
	 * @return strInFile
	 */
	public String getStrInFile() {
		return strInFile;
	}

	/**
	 * @param strInFile セットする strInFile
	 */
	public void setStrInFile(String strInFile) {
		this.strInFile = strInFile;
	}

	/**
	 * @return strOutFolder
	 */
	public String getStrOutFolder() {
		return strOutFolder;
	}

	/**
	 * @param strOutFolder セットする strOutFolder
	 */
	public void setStrOutFolder(String strOutFolder) {
		this.strOutFolder = strOutFolder;
	}

	/**
	 * @return list
	 */
	public List<List> getList() {
		return list;
	}

	/**
	 * @param list セットする list
	 */
	public void setList(List<List> list) {
		this.list = list;
	}

	/**
	 * @return listSheetName
	 */
	public List<String> getListSheetName() {
		return listSheetName;
	}

	/**
	 * @param listSheetName セットする listSheetName
	 */
	public void setListSheetName(List<String> listSheetName) {
		this.listSheetName = listSheetName;
	}

}
