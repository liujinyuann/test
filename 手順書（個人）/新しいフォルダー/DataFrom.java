package seleniumTest;

import java.util.List;
import java.util.Map;

public class DataFrom {

	// 現新環境
	private String strGensin;

	// 座標起始
	private double strIndexStart  ;

	// テスト種類
	private String strTestType   ;

	// 出力ファイル種類
	private String strOutfileType   ;

	// テスト情報
	private List<Map> list  ;

	public String getStrGensin() {
		return strGensin;
	}

	public void setStrGensin(String strGensin) {
		this.strGensin = strGensin;
	}

	public double getStrIndexStart() {
		return strIndexStart;
	}

	public void setStrIndexStart(double strIndexStart) {
		this.strIndexStart = strIndexStart;
	}

	public String getStrTestType() {
		return strTestType;
	}

	public void setStrTestType(String strTestType) {
		this.strTestType = strTestType;
	}

	public String getStrOutfileType() {
		return strOutfileType;
	}

	public void setStrOutfileType(String strOutfileType) {
		this.strOutfileType = strOutfileType;
	}

	public List<Map> getList() {
		return list;
	}

	public void setList(List<Map> list) {
		this.list = list;
	}


}
