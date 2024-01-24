package com.company.find.action;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import com.company.find.service.UserService;


/**
 * Servlet implementation class UploadServlet
 */
@WebServlet({ "/UploadServlet", "/upload.do" })
public class UploadServlet extends HttpServlet {
	
	private UserService userService;
	public void init() {
		userService = new UserService();
	}
	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		//判读你提交的表单是不是字节流表单
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(!isMultipart) {
			return;
		}
		//((ServletRequest) request).setCharacterEncoding("UTF-8");//设置编码 基本不好用
		//工厂模式
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
	    factory.setSizeThreshold(1024*1024);
	    factory.setRepository(new File("E:\\j2ee\\eclipse\\apache-tomcat-9.0.80\\temp"));
	    //创建一个文件上传的对象
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    
	    //upload.setProgressListener(new FileUploadProgressListener);
	    

	   
	    
	    List<?> items = null;
	    try {
	    	items = upload.parseRequest(request);
	    }catch(FileUploadException e1) {
	    	e1.printStackTrace();
	    }
	
	    Iterator<?> iter = items.iterator();
	    while(iter.hasNext()) {
	    	FileItem item = (FileItem)iter.next();
	    
	    if(item.isFormField()) {
	    	String name = item.getFieldName();
	    	String value = new String(item.getString().getBytes("ISO-8859-1"),"UTF-8");
	    	System.out.println(name + ":" + value);
	    }else {
	    	String fieldName = item.getFieldName();
	    	String fileName = item.getName();
	    	int start = fileName.indexOf(".");
	    	String extName = fileName.substring(start);
	    	System.out.println(extName);
	    	String newFileName = "";
	    	System.out.println("为什么总报错啊");
	    	System.out.println(extName);
	    	String contentType =item.getContentType();
	    	boolean isInMem = item.isInMemory();
	    	long sizeInBytes = item.getSize();

	    	// 提取文件名部分
	    	int separatorIndex = fileName.lastIndexOf(File.separator); // 查找文件路径分隔符
	    	String targetFileName = (separatorIndex >= 0) ? fileName.substring(separatorIndex + 1) : fileName;
	    	
	    	String path = getServletContext().getRealPath("")+"\\upload\\";
	    	
	    	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	    	
	    	String url = basePath + "upload/" + targetFileName;
	    	File uploadedFile = new File("E:\\j2ee\\j2ee上机\\find\\WebContent\\upload\\" + targetFileName);
	    	
	    	String targetFilePath = "E:\\j2ee\\j2ee上机\\find\\WebContent\\upload\\" + targetFileName;
	    	File targetFile = new File(targetFilePath);
	    	
	    	if (targetFile.exists()) {
	    	    targetFile.delete(); // 删除同名文件
	    	}


	    	try {
	    		item.write(uploadedFile);
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    	
	    	int lastIndex = targetFileName.lastIndexOf("."); // 查找最后一个点的位置
	    	String fileNameWithoutExtension = targetFileName.substring(0, lastIndex); // 从开头到最后一个点的位置（不包括最后一个点）
	    	
		    try {
				userService.update(fileNameWithoutExtension,extName);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	      }
	    }
	    
	    
	    response.sendRedirect("main2.jsp");
	}

}
