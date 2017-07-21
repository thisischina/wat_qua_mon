package com.hd.ibus.controller;

import com.hd.ibus.pojo.News;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.NewsService;
import com.hd.ibus.util.Config;
import com.hd.ibus.util.PageBean;
import com.hd.ibus.util.PropertiesUtils;
import com.hd.ibus.util.shenw.PageHelp;
import com.hd.ibus.util.shenw.PageStr;
import com.hd.ibus.util.shenw.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by GitHub:thisischina .
 * Controller
 * 消息
 */

@Controller
@RequestMapping("news")
public class NewsController {
	@Resource
	private NewsService newsService;

	private PageHelp pageHelp=PageHelp.getInstance();

	@RequestMapping("tolist")
	public String toNewsList(Model model,Integer pageNow){
		System.out.println("№tolist");

		pageHelp.getInit(model,pageNow);

		return "news/news_list";
	}

	@RequestMapping("toadd")
	public String toAddNews(){
		System.out.println("№toadd");
		return "news/news_add";
	}

	/**
	 * 带可查询的分页列表
	 * @param request
	 * @param pageNow
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("getlist")
	public @ResponseBody DataGridResultInfo getSelectListPage(HttpServletRequest request,HttpServletResponse response,Integer pageNow,Model model)
			throws IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");

		String selectStr= PageStr.getParameterStr("title",request);

		/**
		 * 查询条件为空设置对象为空
		 * 查询条件不为空，将参数设置到对象
		 */
		News news=new News();
		if(!selectStr.equals("")){
			news.setTitle(selectStr);

			pageHelp.setObject(news);
			pageHelp.setSelectStr(selectStr);
			model.addAttribute(pageHelp);
		}else {
			pageHelp.setObject(null);
		}
		return newsService.findList(pageHelp,pageNow);
	}

	@ResponseBody
	@RequestMapping("addnews")
	public int addNews(HttpServletRequest request,Model model){
		String title=PageStr.getParameterStr("title",request);

		News news=new News();
		if(!title.equals("")){
			news.setTitle(title);
		}

		newsService.insertNews(news);

		return Value.IntNumOne;
	}

}
