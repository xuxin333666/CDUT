package cn.kgc.controller.student;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jspsmart.upload.SmartUpload;

import cn.kgc.utils.StringUtils;

@WebServlet("/admin/permissions/student/fileUpload")
public class FileUploadStudentController extends HttpServlet  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
private static final Logger logger = LoggerFactory.getLogger(FileUploadStudentController.class);
	
	private static final String FILE_PATH = "c:/upload";
	private static final String ALLOWED_FILES_LIST = "jpg,gif,png";
	private static final String DENIED_FILES_LIST = "rar,jsp,js";
	private static final int MAX_FILE_SIZE = 1024*1024*10;
	private static final int TOTAL_MAX_FILE_SIZE = 1024*1024*100;
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  File file = new File(FILE_PATH);
		    if (!file.exists()) {
		        file.mkdir();
		    }
		  //实例化上传组件
	        SmartUpload su = new SmartUpload();
	        //初始化SmartUpload
	        su.initialize(getServletConfig(), req, resp);
	        //设置上传文件对象10M
	        su.setMaxFileSize(MAX_FILE_SIZE);
	        //设置所有文件大小100M
	        su.setTotalMaxFileSize(TOTAL_MAX_FILE_SIZE);
	        //设置允许上传文件类型
	        su.setAllowedFilesList(ALLOWED_FILES_LIST);
	        String result = "";
	        String id = null;
	        try {
	            //设置禁止上传文件类型
	            su.setDeniedFilesList(DENIED_FILES_LIST);
	            //上传文件
	            su.upload();
	            //取得表单其他参数
	            id = su.getRequest().getParameter("id"); 
	            for(int i=0;i<su.getFiles().getCount();i++) {
	            	if(StringUtils.isNotEmpty(su.getFiles().getFile(i).getFileExt())) {
	            		//构造文件名字
	            		String fileName = id + "touxiang." + su.getFiles().getFile(i).getFileExt();
	            		//保存文件
	            		su.getFiles().getFile(i).saveAs(FILE_PATH + File.separator + fileName);
	            		
	            		result += "http://127.0.0.1:8080/upload/" + fileName + "\n";
	            	}
	            	
	            }
	        } catch (Exception e) {
	            result = "false";
	            logger.error("[FileUploadStudentController:上传文件失败。" + e.getMessage());
				e.printStackTrace();
	        }
	        
	        
	        resp.getWriter().print(result);
	}
	
	

}
