package edu.wtbu.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import edu.wtbu.Dao.UserDao;
import edu.wtbu.geth.geth;
import edu.wtbu.pojo.Result;
import edu.wtbu.pojo.User;

/**
 * Servlet implementation class GetBlanceServlet
 */
@WebServlet("/getBalance")
public class GetBlanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBlanceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf8");
        String email=request.getParameter("email");
        Result result=new Result("fail", null);
        User user=null;
        if(email==null||email.equals("")) {
        	result.setData("��¼��ϢʧЧ�����¼�����ԣ�");
        	response.getWriter().append(JSON.toJSONString(result));
        	return;
        }
        user =UserDao.findEmail(email);
        if(user!=null) {
        String	blance=geth.getbalance(user.getAddress());
        result.setFlag("success");
        result.setData(blance);
        }
    	response.getWriter().append(JSON.toJSONString(result));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
