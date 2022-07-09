package model;

import com.google.gson.annotations.SerializedName;

public class LoginData{

	@SerializedName("nama")
	private String nama;

	@SerializedName("id")
	private String id;

	@SerializedName("username")
	private String username;

	@SerializedName("no_telp")
	private String no_telp;

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public String getNo_telp() {
		return no_telp;
	}

	public void setNo_telp(String no_telp) {
		this.no_telp = no_telp;
	}
}