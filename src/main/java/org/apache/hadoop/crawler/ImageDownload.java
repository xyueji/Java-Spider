/**
 * 
 */
package org.apache.hadoop.crawler;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.hadoop.dao.ImgDao;
import org.apache.hadoop.pojo.Img;

/**
 * @author XIONG
 *
 */
public class ImageDownload implements Runnable{
	private String strImg;
	private String imgPath;
	private String imgCount;

	public ImageDownload(String strImg,String imgPath,String imgCount){
		this.strImg=strImg;
		this.imgPath=imgPath;
		this.imgCount=imgCount;
	}
	public void run() {
		// TODO Auto-generated method stub
			 URL url=null;
			 try {
				url=new URL(strImg);
				URLConnection con = (URLConnection) url.openConnection();
				con.setConnectTimeout(1000);
				con.setReadTimeout(5000);
				InputStream in = con.getInputStream();
				DataInputStream dStream=new DataInputStream(in);
				File file=new File(imgPath);
				if(!file.exists()){
					file.mkdirs();
				}
				file=new File(imgPath+"/"+imgCount+".jpg");
				Img img=new Img();
				img.setImg_name(imgPath);
				img.setImg_path(imgPath+"/"+imgCount+".jpg");
				ImgDao iDao=new ImgDao();
				iDao.saveData(img);
				FileOutputStream fileOutputStream=new FileOutputStream(file);
				ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
				byte[] buffer=new byte[1024];
				int length;
				while((length=dStream.read(buffer))>0){
					outputStream.write(buffer,0,length);
				}
				fileOutputStream.write(outputStream.toByteArray());
				dStream.close();
				fileOutputStream.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}	
}
