package edu.hebeu.controller;

import java.util.ArrayList;
import java.util.Date;
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
		List<Menu> menuList = getSelfMenuList();
		MessageCode code = MessageCode.CODE_SUCCESS;
		ResultObject resultObject = new ResultObject(code);
		resultObject.setData(menuList);
		return resultObject;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/treeMenu.do")
	@ResponseBody
	public ResultObject treeMenu() {
		// 从redis中获取菜单列表
		List<Menu> menuList = getSelfMenuList();
		// 记录顶级菜单
		List<Menu> topMenu = new ArrayList<Menu>();
		// 获取所有菜单 Id为KEY，List<Menu>为值
		Map<Long, List<Menu>> map = new HashMap<Long, List<Menu>>();
		
		for (Menu menu : menuList) {
			// 父节点为0的菜单为
			if(menu.getParentId() == 0L) {
				topMenu.add(menu);
			}
			if(map.containsKey(menu.getParentId())) {
				List<Menu> list = map.get(menu.getParentId());
				list.add(menu);
				map.put(menu.getParentId(), list);
			} else {
				List<Menu> list = new ArrayList<Menu>();
				list.add(menu);
				map.put(menu.getParentId(), list);
			}
		}
		// 获取树结构
		List<Map<String,Object>> returnList = menuTree(map, 0L);
		
		MessageCode code = MessageCode.CODE_SUCCESS;
		ResultObject resultObject = new ResultObject(code);
		resultObject.setData(returnList);
		return resultObject;
	}
	
	private List<Map<String,Object>> menuTree(Map<Long, List<Menu>> map,Long parentId) {
		if(map.containsKey(parentId)) {
			List<Menu> list = map.get(parentId);
			List<Map<String,Object>> mapTree = new ArrayList<Map<String,Object>>();
			for (Menu menu : list) {
				Map<String,Object> detailMenu = new HashMap<String,Object>();
				detailMenu.put("id", menu.getId());
				detailMenu.put("name", menu.getName());
				detailMenu.put("href", menu.getHref());
				detailMenu.put("hrefType",menu.getHrefType());
				List<Map<String,Object>> detail = menuTree(map, menu.getId());
				detailMenu.put("detail",detail);
				mapTree.add(detailMenu);
			}
			return mapTree;
		} else {
			return null;
		}
	}
	
	
	private List<Menu> getSelfMenuList() {
		String string = redisUtil.get("menu"+ApiCommonUtil.getToken());
		List<Menu> menuList = new ArrayList<Menu>();
		if (StringUtils.isBlank(string)) {// 不存在数据库中加载
			try {
				// 获取所有菜单
				Map<Long, Menu> map = new HashMap<Long, Menu>();
				menuList = menuService.selectMenuAll();
				//以menu id为key,值为menu 绑定关系
				for (Menu menu : menuList) {
					map.put(menu.getId(), menu);
				}
				// 遍历菜单集合
				for (Menu menu : menuList) {
					// 父节点不为0时设置 父节点名称
					if (menu.getParentId() != 0) {
						// 获取父节点名称 map.get(menu.getParentId()) 获取父节点菜单信息
						menu.setParentIdStr(map.get(menu.getParentId()).getName());
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
						menu.setParentIdsStr(parentIdsStr);
					}
				}
				// key 以menu+token ，存放到redis中
				redisUtil.set("menu"+ApiCommonUtil.getToken(), JsonUtils.objectToJson(menuList));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			menuList = JsonUtils.jsonToList(string,new TypeReference<List<Menu>>() {});
		}
		return menuList;
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
		menu.setCreateDate(new Date());
		menuService.insert(menu);
		// 删除redis中的缓存
		redisUtil.delete("menu"+ApiCommonUtil.getToken());
		return "redirect:/menu/list.do";
	}
	
}
