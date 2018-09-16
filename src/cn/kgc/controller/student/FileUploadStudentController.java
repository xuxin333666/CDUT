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
		  //ʵ�����ϴ����
	        SmartUpload su = new SmartUpload();
	        //��ʼ��SmartUpload
	        su.initialize(getServletConfig(), req, resp);
	        //�����ϴ��ļ�����10M
	        su.setMaxFileSize(MAX_FILE_SIZE);
	        //���������ļ���С100M
	        su.setTotalMaxFileSize(TOTAL_MAX_FILE_SIZE);
	        //���������ϴ��ļ�����
	        su.setAllowedFilesList(ALLOWED_FILES_LIST);
	        String result = "";
	        String id = null;
	        try {
	            //���ý�ֹ�ϴ��ļ�����
	            su.setDeniedFilesList(DENIED_FILES_LIST);
	            //�ϴ��ļ�
	            su.upload();
	            //ȡ�ñ���������
	            id = su.getRequest().getParameter("id"); 
	            for(int i=0;i<su.getFiles().getCount();i++) {
	            	if(StringUtils.isNotEmpty(su.getFiles().getFile(i).getFileExt())) {
	            		//�����ļ�����
	            		String fileName = id + "touxiang." + su.getFiles().getFile(i).getFileExt();
	            		//�����ļ�
	            		su.getFiles().getFile(i).saveAs(FILE_PATH + File.separator + fileName);
	            		
	            		result += "http://127.0.0.1:8080/upload/" + fileName + "\n";
	            	}
	            	
	            }
	        } catch (Exception e) {
	            result = "false";
	            logger.error("[FileUploadStudentController:�ϴ��ļ�ʧ�ܡ�" + e.getMessage());
				e.printStackTrace();
	        }
	        
	        
	        resp.getWriter().print(result);
	}
	
	

}
