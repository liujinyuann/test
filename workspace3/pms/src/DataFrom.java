
import java.util.List;

public class DataFrom {

	// ユーザID
	private String strUserId;

	// パスワード
	private String strPwd;

	// プロジェクト名
	private String strPjName;

	// 入力ファイル
	private String strInFile;

	// メッセージ
	private String strMsg;

	// コード
	private String strCode = "0";

	// テスト情報
	private List<List> list;

	// テスト情報
	private List<String> listSheetName;

	/**
	 * @return strUserId
	 */
	public String getStrUserId() {
		return strUserId;
	}

	/**
	 * @param strUserId セットする strUserId
	 */
	public void setStrUserId(String strUserId) {
		this.strUserId = strUserId;
	}

	/**
	 * @return strPwd
	 */
	public String getStrPwd() {
		return strPwd;
	}

	/**
	 * @param strPwd セットする strPwd
	 */
	public void setStrPwd(String strPwd) {
		this.strPwd = strPwd;
	}

	/**
	 * @return strPjName
	 */
	public String getStrPjName() {
		return strPjName;
	}

	/**
	 * @param strPjName セットする strPjName
	 */
	public void setStrPjName(String strPjName) {
		this.strPjName = strPjName;
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
