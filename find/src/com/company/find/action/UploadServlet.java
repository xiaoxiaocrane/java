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
		
		
		//�ж����ύ�ı��ǲ����ֽ�����
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(!isMultipart) {
			return;
		}
		//((ServletRequest) request).setCharacterEncoding("UTF-8");//���ñ��� ����������
		//����ģʽ
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
	    factory.setSizeThreshold(1024*1024);
	    factory.setRepository(new File("E:\\j2ee\\eclipse\\apache-tomcat-9.0.80\\temp"));
	    //����һ���ļ��ϴ��Ķ���
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
	    	System.out.println("Ϊʲô�ܱ���");
	    	System.out.println(extName);
	    	String contentType =item.getContentType();
	    	boolean isInMem = item.isInMemory();
	    	long sizeInBytes = item.getSize();

	    	// ��ȡ�ļ�������
	    	int separatorIndex = fileName.lastIndexOf(File.separator); // �����ļ�·���ָ���
	    	String targetFileName = (separatorIndex >= 0) ? fileName.substring(separatorIndex + 1) : fileName;
	    	
	    	String path = getServletContext().getRealPath("")+"\\upload\\";
	    	
	    	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	    	
	    	String url = basePath + "upload/" + targetFileName;
	    	File uploadedFile = new File("E:\\j2ee\\j2ee�ϻ�\\find\\WebContent\\upload\\" + targetFileName);
	    	
	    	String targetFilePath = "E:\\j2ee\\j2ee�ϻ�\\find\\WebContent\\upload\\" + targetFileName;
	    	File targetFile = new File(targetFilePath);
	    	
	    	if (targetFile.exists()) {
	    	    targetFile.delete(); // ɾ��ͬ���ļ�
	    	}


	    	try {
	    		item.write(uploadedFile);
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    	
	    	int lastIndex = targetFileName.lastIndexOf("."); // �������һ�����λ��
	    	String fileNameWithoutExtension = targetFileName.substring(0, lastIndex); // �ӿ�ͷ�����һ�����λ�ã����������һ���㣩
	    	
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
