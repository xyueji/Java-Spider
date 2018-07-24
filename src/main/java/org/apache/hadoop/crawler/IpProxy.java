/**
 * 
 */
package org.apache.hadoop.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author XIONG
 *
 */
public class IpProxy {
	public List<String> getHtml() {
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.xicidaili.com/nn")
             .data("query", "Java")
                    .userAgent("Mozilla")
                     .cookie("auth", "token")
                     .timeout(3000)
                    .get();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<String> list = new ArrayList<String>();
        Elements elements = doc.select("tr.odd");
        int len = elements.size();
        Element element = null;
        for (int i = 0; i < len; i++) {
            element = elements.get(i);
            StringBuilder sBuilder = new StringBuilder(20);
            sBuilder.append(element.child(1).text());
            sBuilder.append(":");
            sBuilder.append(element.child(2).text());
            list.add(sBuilder.toString());
        }
        // System.out.println(doc.html());
        doc = null;
        elements.clear();
        elements = null;
        return list;
    }
	public void setIP() {
		IpProxy ipProxy=new IpProxy();
		List<String> list=ipProxy.getHtml();
		Random random=new Random();
		int index=Math.abs(random.nextInt()%list.size());
		String[] r=list.get(index).split(":");
		System.getProperties().setProperty("http.proxyHost", r[0]);
        System.getProperties().setProperty("http.proxyPort", r[1]);
	}
}
