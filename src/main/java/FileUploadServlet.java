import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author nono
 * @Package PACKAGE_NAME
 * @ClassName FileUploadServlet.java
 * @Description TODO
 * @createTime 2020年08月12日 18:32:00
 */
public class FileUploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("文件上传了");
        //1.先判断上传的数据是否多段数据（只是多段的数据，才是文件上传）
        if (ServletFileUpload.isMultipartContent(req)) {
            //创建fileItemFactory工厂实现类
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            //创建用于解析上传数据工具类servletFileUpload类
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            //解析上传的数据，得到每一个表单项
            try {
                List<FileItem> list = servletFileUpload.parseRequest(req);
                //循环判断上传的表单项是文件，还是普通类型
                for (FileItem fileItem : list) {

                    if (fileItem.isFormField()) {
                        //普通表单项
                        //获取普通表单项的名称
                        System.out.println("普通表单项name属性：" + fileItem.getFieldName());
                        System.out.println("普通表单项的value：" + fileItem.getString("utf-8"));
                    } else {
                        //上传的文件
                        System.out.println("普通表单项name属性：" + fileItem.getFieldName());
                        System.out.println("上传的文件名：" + fileItem.getName());
                        fileItem.write(new File("d:\\" + fileItem.getName()));
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
