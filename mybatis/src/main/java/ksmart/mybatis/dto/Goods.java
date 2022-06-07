package ksmart.mybatis.dto;

public class Goods {
	private String gCode;
	private String gName;
	private String gPrice;
	private String gSellerId;
	private String gRegDate;
	
	public String getgCode() {
		return gCode;
	}
	public void setgCode(String gCode) {
		this.gCode = gCode;
	}
	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public String getgPrice() {
		return gPrice;
	}
	public void setgPrice(String gPrice) {
		this.gPrice = gPrice;
	}
	public String getgSellerId() {
		return gSellerId;
	}
	public void setgSellerId(String gSellerId) {
		this.gSellerId = gSellerId;
	}
	public String getgRegDate() {
		return gRegDate;
	}
	public void setgRegDate(String gRegDate) {
		this.gRegDate = gRegDate;
	}
	
	@Override
	public String toString() {
		return "Goods [gCode=" + gCode + ", gName=" + gName + ", gPrice=" + gPrice + ", gSellerId=" + gSellerId
				+ ", gRegDate=" + gRegDate + "]";
	}
	
	
}
