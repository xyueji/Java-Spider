/**
 * 
 */
package org.apache.hadoop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.hadoop.pojo.House;
import org.apache.hadoop.pojo.Img;

/**
 * @author XIONG
 *
 */
public class ImgDao extends BaseDao{
	public int saveData(Img img) {
		int i=0;
		String sql="INSERT INTO `test`.`img_info` (`img_name`, `img_path`) VALUES  (? ,?) ";
		Connection connection=null;
		PreparedStatement statement=null;
		try {
			connection=getConnection();
			statement=connection.prepareStatement(sql);
			statement.setObject(1, img.getImg_name());
			statement.setObject(2, img.getImg_path());
			i=statement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally{
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return i;
	}

}
