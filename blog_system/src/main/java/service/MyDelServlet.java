package service;

import dao.ArticleInfoDao;
import utils.DBUtils;
import utils.ResultJSONUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class MyDelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int state = -1;
        String msg = "";

        int id = Integer.parseInt(request.getParameter("id"));
        if (id >= 0) {
            ArticleInfoDao articleInfoDao = new ArticleInfoDao();
            try {
                int num = articleInfoDao.delArticleById(id);
                if (num >= 1) {
                    msg = "删除成功";
                    state = 200;
                } else {
                    msg = "删除数据库文章失败";
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            msg = "参数无效";
        }

        HashMap<String,Object> map = new HashMap<>();
        map.put("msg",msg);
        map.put("state",state);
        ResultJSONUtils.write(response,map);
    }
}
