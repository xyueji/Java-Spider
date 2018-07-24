/**
 * 
 */
package org.apache.hadoop.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.hadoop.crawler.AnalyzeWeb;
import org.apache.hadoop.crawler.DownLoadTool;
import org.apache.hadoop.crawler.IpProxy;
import org.apache.hadoop.pojo.House;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author xiong
 *
 */
public class Spider extends ActionSupport implements ModelDriven<House>{
	public static void main(String[] args) throws Exception {
		DownLoadTool dTool=new DownLoadTool();
		dTool.downloadPage();
	}
	public String spiderWeb() throws Exception {
		DownLoadTool dTool=new DownLoadTool();
		return "spider";
}

/* (non-Javadoc)
 * @see com.opensymphony.xwork2.ModelDriven#getModel()
 */
@Override
public House getModel() {
	// TODO Auto-generated method stub
	return null;
}
}
