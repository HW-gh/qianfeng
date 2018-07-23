package bean;

public class goodsType {
	private long id;
	private String gtype_name;
	private long gtype_parentid;
	private String gtype_pic;
	
	@Override
	public String toString() {
		return "goodsType [id=" + id + ", gtype_name=" + gtype_name + ", gtype_parentid=" + gtype_parentid
				+ ", gtype_pic=" + gtype_pic + "]";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getGtype_name() {
		return gtype_name;
	}
	public void setGtype_name(String gtype_name) {
		this.gtype_name = gtype_name;
	}
	public long getGtype_parentid() {
		return gtype_parentid;
	}
	public void setGtype_parentid(long gtype_parentid) {
		this.gtype_parentid = gtype_parentid;
	}
	public String getGtype_pic() {
		return gtype_pic;
	}
	public void setGtype_pic(String gtype_pic) {
		this.gtype_pic = gtype_pic;
	}
	
}
