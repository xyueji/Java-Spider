/*** 
 * 将hdfs上的文件下载到本地系统上2.0版本 
 */  
package org.apache.hadoop.hdfs;  
  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.net.URI;  
import java.net.URISyntaxException;  
  
import org.apache.hadoop.conf.Configuration;  
import org.apache.hadoop.fs.FileSystem;  
import org.apache.hadoop.fs.Path;  
import org.apache.hadoop.io.IOUtils;  
import org.junit.Before;  
  
public class HDFSDemo {  
    FileSystem fs = null;  
      
    @Before  
    public void init() throws IOException, URISyntaxException{  
        fs = FileSystem.get(new URI("hdfs://master:9000"), new Configuration());  
          
    }  
    public static void download() throws IOException, URISyntaxException {  
        // TODO Auto-generated method stub  
        FileSystem fs = FileSystem.get(new URI("hdfs://master:9000"), new Configuration());  
        InputStream in = fs.open(new Path("/data/jobdata"));  
        OutputStream out = new FileOutputStream("D://jobdata");  
        IOUtils.copyBytes(in, out, 4096, true);  
    }  
}  