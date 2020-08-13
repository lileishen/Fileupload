
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;

/**
 * @author nono
 * @Package PACKAGE_NAME
 * @ClassName DownLoadServlet.java
 * @Description 用于获取下载的文件
 * @createTime 2020年08月12日 19:41:00
 */
public class DownLoadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取要下载的文件名
        String downloadFileName ="2.png";
        //2.获取要下载的文件内容(ServletContext可以读取)
        ServletContext servletContext = getServletContext();
        String mimeType = servletContext.getMimeType("/file/" + downloadFileName);
        System.out.println("下载的文件类型"+mimeType);
        //4.在回传前，通过响应头告诉客户端返回的数据类型
        resp.setContentType(mimeType);
        //5.还要告诉客户端收到的数据是用于下载使用（还是使用响应头）
        /**
         * Content-Disposition 响应头 ，表示收到的数据怎么处理
         * attachment 表示附件，表示下载使用
         * filename 表示指定下载的文件名
         */
        resp.setHeader("Content-Disposition","attachment;file="+ URLDecoder.decode(downloadFileName,"utf-8"));

        /**
         * / 被服务器解析表示地址为 http://ip:port /工程名    映射到代码web 目录下
         */
        InputStream is = servletContext.getResourceAsStream("/file/"+downloadFileName);
        OutputStream os =resp.getOutputStream();
        //3.把下载的文件内容回传给客户端
        /**
         * 读取输入流中全部数据，复制给输出流，输出给客户端
         */
        IOUtils.copy(is,os);
    }
}
