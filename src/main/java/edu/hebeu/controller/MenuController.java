package edu.hebeu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;

import edu.hebeu.common.MessageCode;
import edu.hebeu.common.ResultObject;
import edu.hebeu.entity.Employee;
import edu.hebeu.entity.Menu;
import edu.hebeu.entity.format.MenuFormat;
import edu.hebeu.service.MenuService;
import edu.hebeu.util.ApiCommonUtil;
import edu.hebeu.util.JsonUtils;
import edu.hebeu.util.RedisUtil;

@Controller
@RequestMapping("/menu")
public class MenuController {
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private MenuService menuService;
	
	@RequestMapping("/list.do")
	public String testTree() {
		return "menu/list";
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/toListPage.do")
	@ResponseBody
	public ResultObject toListPage(Model model) {
		// 从redis中获取菜单列表
		String string = redisUtil.get("menu"+ApiCommonUtil.getToken());
		List<MenuFormat> menuFormatList = new ArrayList<MenuFormat>();
		if (StringUtils.isBlank(string)) {// 不存在数据库中加载
			try {
				// 获取所有菜单
				List<Menu> menuList = menuService.selectMenuAll();
				//以menu id为key,值为menu 绑定关系
				Map<Long, Menu> map = new HashMap<Long, Menu>();
				for (Menu menu : menuList) {
					map.put(menu.getId(), menu);
				}
				// 遍历菜单集合
				for (Menu menu : menuList) {
					MenuFormat menuFormat = JsonUtils.jsonToPoJo(JsonUtils.objectToJson(menu), MenuFormat.class);
					// 父节点不为0时设置 父节点名称
					if (menu.getParentId() != 0) {
						
						// 获取父节点名称 map.get(menu.getParentId()) 获取父节点菜单信息
						menuFormat.setParentIdStr(map.get(menu.getParentId()).getName());
						// 获取所有父级编号，并转为数组
						String[] p = menu.getParentIds().split(",");
						// 定义所有父级名称
						String parentIdsStr = "";
						// 遍历所有父级节点
						for (String string2 : p) {
							// 排除 为0的节点
							if (!string2.equals("0")) {
								// 如果定义所有父级名称不为空，在后面增加 “/”
								if (!parentIdsStr.equals("")) {
									parentIdsStr += "/";
								}
								// 根据节点ID从map中获取菜单对象，获取菜单名称
								parentIdsStr += map.get(Long.valueOf(string2)).getName();
							}

						}
						// 设置所有父级节点名称
						menuFormat.setParentIdsStr(parentIdsStr);
					}
					menuFormatList.add(menuFormat);
				}
				// key 以menu+token ，存放到redis中
				redisUtil.set("menu"+ApiCommonUtil.getToken(), JsonUtils.objectToJson(menuFormatList));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			menuFormatList = JsonUtils.jsonToList(string,new TypeReference<List<MenuFormat>>() {});
		}
		MessageCode code = MessageCode.CODE_SUCCESS;
		ResultObject resultObject = new ResultObject(code);
		resultObject.setData(menuFormatList);
		return resultObject;
	}
	
	@RequestMapping("/{parentId}/addPage.do")
	public String selectHistory(@PathVariable Long parentId, Model model){
		model.addAttribute("parentId", parentId);
		return "menu/add";
	}
	
	@RequestMapping("/add.do")
	public String add(Menu menu){
		// 获取父节信息
		Menu parentMenu = menuService.selectById(menu.getParentId());
		// 设置创建人
		Employee employee = ApiCommonUtil.getEmployyee(redisUtil);
		menu.setCreateBy(employee.getName());
		//设置所有父级编号
		String parentIds = parentMenu != null ? parentMenu.getParentIds() : "";
		menu.setParentIds(parentIds + menu.getParentId() + ",");
		menuService.insert(menu);
		// 删除redis中的缓存
		redisUtil.delete("menu"+ApiCommonUtil.getToken());
		return "redirect:/menu/list.do";
	}
	
}
