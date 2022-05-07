package edu.wtbu.Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import edu.wtbu.Dao.GoodsDao;
import edu.wtbu.pojo.Goods;
import edu.wtbu.pojo.Result;

/**
 * Servlet implementation class GoodsListServlet
 */
@WebServlet("/goodsList")
public class GoodsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsListServlet() {
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
         if(email==null||email.equals("")) {
         	result.setData("��¼��ϢʧЧ�����¼�����ԣ�");
         	response.getWriter().append(JSON.toJSONString(result));
         	return;
         }
         ArrayList<Goods> goodslist=GoodsDao.findGoodsList();
         if(goodslist!=null) {
        	 result.setFlag("success");
        	 result.setData(goodslist);
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
