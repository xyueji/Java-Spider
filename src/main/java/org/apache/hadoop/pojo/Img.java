/**
 * 
 */
package org.apache.hadoop.pojo;

/**
 * @author XIONG
 *
 */
public class Img {
	private String img_name;
	/**
	 * @return the img_name
	 */
	public String getImg_name() {
		return img_name;
	}
	/**
	 * @return the img_path
	 */
	public String getImg_path() {
		return img_path;
	}
	/**
	 * @param img_name the img_name to set
	 */
	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}
	/**
	 * @param img_path the img_path to set
	 */
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	private String img_path;
}
