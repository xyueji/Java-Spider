/**
 * 
 */
package org.apache.hadoop.crawler;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.hadoop.dao.HouseDao;
import org.apache.hadoop.dao.ImgDao;
import org.apache.hadoop.pojo.House;
import org.apache.hadoop.pojo.Img;
import org.apache.struts2.views.jsp.ElseTag;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author xiong
 *
 */
public class AnalyzeWeb {
	private String userAgent="Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)";
	public ArrayList<String > getCity(String url) throws Exception {
		ArrayList<String> lrs=new ArrayList<>();
		Document document=Jsoup.connect(url).userAgent(userAgent).get();
		Elements elements;
		if(document.select("div.searchcity").isEmpty())
			elements=null;
		else {
			elements=document.select("div.searchcity").first().select("a");
		}
		for(Element e:elements){
			String urlString=new String();
			urlString=e.attr("href");
			lrs.add(urlString);
		}
		return lrs;
	}
	public ArrayList<String> getPlace(String url) throws Exception {
		ArrayList<String> lrs=new ArrayList<>();
		Document document=Jsoup.connect(url).userAgent(userAgent).get();
		Elements elements;
		if(document.select("div.onCont").isEmpty())
			elements=null;
		else {
			elements=document.select("div.onCont").first().select("a");
		}
		for(Element e:elements){
			String urlString=new String();
			urlString=e.attr("href");
			lrs.add(urlString);
		}
		return lrs;
	}
	public int getPage(String url) throws Exception {
		Document document = Jsoup.connect(url).userAgent(userAgent).get();
		Elements elements =document.getElementsByClass("fanye");
		Element element=elements.first();
		int page=0;
		try {
			String pageNum=element.select("span.txt").text();
			page=Integer.parseInt(pageNum.substring(1,pageNum.length()-1));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return page;
	}
	 public  ArrayList< String> analyzeHtml(String url, String encoding) throws Exception{
		 ArrayList<String> list=new ArrayList<String>();
		 try {
			 	Document document = Jsoup.connect(url).userAgent(userAgent).get();
			 	Elements elements = document.select("p.title");
			 	for (Element e : elements) {
	                String href = e.select("a").attr("href");
		            String htm=href.substring(href.length()-3, href.length());
		            if(htm.equals("htm")){
		            	String urlString=url+href;
		            	list.add(urlString.substring(0,urlString.indexOf("/house/"))+href);
		            }
				} 
		 		}catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
	        return list;
	    }
	 public  void analyzeInfo(List<String> url, String imgPath,int j) throws Exception{
	        Integer count=1;
	        String imgurlPathString=imgPath;
	        List<House> lrs=new ArrayList<House>();
	        for (int i = 0; i <url.size(); i++) {
	        	try {
	        		System.out.println("正在爬取："+url.get(i));
		        	Document document = Jsoup.connect(url.get(i)).userAgent(userAgent).get();
			        imgPath=imgurlPathString+"/"+count.toString();
		        	count++;
		        	House house=new House();
		        	String href=url.get(i);
			        String title;
			        double total_price;
			        int unit_price;
			        double area;
			        String layout_house;
			        String community;
			        String place;
			        String city=document.select("div.s4Box").first().select("a").text();
			        String from_web="房天下";
			        String phone;
			        if(document.getElementsByClass("tjcont-list-cline3").isEmpty()){
			        	phone=null;
			        }else {
						phone=document.getElementsByClass("tjcont-list-cline3").first().text();
					}
			        if(j==0){
			        	title=document.select("div.floatl").first().text();
			        	total_price=Double.parseDouble(document.getElementsByClass("trl-item price_esf  sty1").first().select("i").text());
			        	String s=document.getElementsByClass("trl-item1 w132").first().select("div.tt").text();
				        unit_price=Integer.parseInt(s.substring(0, s.length()-4));
				        s=document.getElementsByClass("trl-item1 w182").first().select("div.tt").text();
				        area=Double.parseDouble(s.substring(0,s.length()-2));
				        layout_house=document.getElementsByClass("trl-item1 w146").first().select("div.tt").text();
				        community=document.getElementsByClass("rcont").first().select("a.blue").text();
				        place=document.getElementsByClass("rcont").get(1).select("a.blue").text();
			        }else{
			        	title=document.select("div.title").first().text();
			        	total_price=Double.parseDouble(document.getElementsByClass("trl-item").first().select("i").text());
			        	unit_price=0;
			        	String  s=document.getElementsByClass("trl-item1 w132").first().select("div.tt").text();
				        area=Double.parseDouble(s.substring(0,s.length()-2));
				        layout_house=document.getElementsByClass("trl-item1 w182").first().select("div.tt").text();
				        community=document.getElementsByClass("rcont").first().select("a.link-under").text();
				        place=document.getElementsByClass("rcont").get(1).select("a").text();
			        }
			        String floor_Info1=document.getElementsByClass("trl-item1 w182").get(1).select("div.tt").text();
			        String floor_Info2=document.getElementsByClass("trl-item1 w182").get(1).select("div.font14").text();
			        String floor_Info=floor_Info1+floor_Info2;
			        String orientation=document.getElementsByClass("trl-item1 w146").get(1).select("div.tt").text();
			        String decoration=document.getElementsByClass("trl-item1 w132").get(1).select("div.tt").text();	
			        house.setHref(href);
			        house.setTitle(title);
			        house.setTotal_price(total_price);
			        house.setUnit_price(unit_price);
			        house.setArea(area);
			        house.setLayout_house(layout_house);
			        house.setFloor_info(floor_Info);
			        house.setOrientation(orientation);
			        house.setDecoration(decoration);
			        house.setCity(city);
			        house.setCommunity(community);
			        house.setPlace(place);
			        house.setImg_name(imgPath);
			        house.setFrom_web(from_web);
			        house.setPhone(phone);
			        house.setType(j);
			        lrs.add(house);
			        HouseDao hDao=new HouseDao();
			        int flag=hDao.saveData(house);
			        if(flag!=0){
			        Elements elements=document.getElementsByClass("bigImg").first().select("img");
			        ExecutorService pool=Executors.newCachedThreadPool();
			        pool=Executors.newFixedThreadPool(10);
			        Integer imgCount=1;
			        for(Element e:elements){
			        	String strImg=e.attr("src").toString();
			        	pool.execute(new ImageDownload(strImg, imgPath,imgCount.toString()));
			        	imgCount++;
			        }
			        pool.shutdown();
			        }
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
	    }
}
