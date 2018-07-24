/**
 * 
 */
package org.apache.hadoop.crawler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.hadoop.dao.HouseDao;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.pojo.House;

/**
 * @author xiong
 *
 */
public class DownLoadTool {
	public  void downloadPage() throws Exception {
				AnalyzeWeb aWeb=new AnalyzeWeb();
				ArrayList<String> list=aWeb.getPlace("http://zu.gz.fang.com/cities.aspx");
				//download(list,1);
				list=aWeb.getCity("http://esf.gz.fang.com/newsecond/esfcities.aspx");
				download(list,0);
			}
	public void download(ArrayList<String> lrs,int type) throws Exception {
		AnalyzeWeb aWeb=new AnalyzeWeb();
		for (int i = 0; i < lrs.size(); i++) {
			int page=aWeb.getPage(lrs.get(i));
			Integer index=1;
			while(index<=page){
				ArrayList< String> list=aWeb.analyzeHtml(lrs.get(i)+"house/i3"+index.toString()+"/", "gb2312");
				if(lrs.isEmpty())
					break;
				System.out.println("i="+i+"   "+"page="+index+"每页房源个数："+list.size());
				String url=lrs.get(i);
				String typeHouse=url.substring(7, 9);
				String place;
				try {
					place=url.substring(10,url.indexOf(".fang"));
				} catch (Exception e) {
					// TODO: handle exception
					place="beijing";
				}	
				String imgPathString="D:/xiongzhigang/eclipse/yiju/src/main/webapp/img/"+typeHouse+"/"+place+"/"+index.toString();
				aWeb.analyzeInfo(list,imgPathString, type);
	    		index++;
			}
			if(lrs.isEmpty())
				break;
		}
	}
}
