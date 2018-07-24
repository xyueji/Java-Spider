/**
 * 
 */
package org.apache.hadoop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.hadoop.pojo.House;

/**
 * @author XIONG
 *
 */
public class HouseDao extends BaseDao{
	public int saveData(House house) {
		int i=0;
		String sql="INSERT INTO `test`.`house_info` ( `href`,  `title`, `total_price`,"
				+ "  `unit_price`,  `area`,  `layout_house`,  `floor_info`,  `orientation`,  `decoration`,"
				+ "  `community`,  `place`,  `city`,  `img_name`,  `from_web`,  `phone`,  `type`) VALUES"
				+ "  (  ?,  ?,    ? ,    ?,    ?,   ?,   ?,    ?,   ?,    ?,    ?,    ?,    ?,    ?,    ?,    ?  ) ";
		Connection connection=null;
		PreparedStatement statement=null;
		try {
			connection=getConnection();
			statement=connection.prepareStatement(sql);
			statement.setObject(1, house.getHref());
			statement.setObject(2, house.getTitle());
			statement.setObject(3, house.getTotal_price());
			statement.setObject(4, house.getUnit_price());
			statement.setObject(5,house.getArea() );
			statement.setObject(6, house.getLayout_house());
			statement.setObject(7, house.getFloor_info());
			statement.setObject(8, house.getOrientation());
			statement.setObject(9, house.getDecoration());
			statement.setObject(10, house.getCommunity());
			statement.setObject(11,house.getPlace());
			statement.setObject(12, house.getCity());
			statement.setObject(13, house.getImg_name());
			statement.setObject(14,house.getFrom_web() );
			statement.setObject(15, house.getPhone());
			statement.setObject(16,house.getType());
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
