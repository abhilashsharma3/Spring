package hw.fileupload.model;

import java.util.Date;

public class GetAllDocuments {
	private String title;
	private Date timeStamp;
	private Integer revNum;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public Integer getRevNum() {
		return revNum;
	}
	public void setRevNum(Integer revNum) {
		this.revNum = revNum;
	}

}
