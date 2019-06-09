package edu.hebeu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.type.TypeReference;

import edu.hebeu.entity.Menu;
import edu.hebeu.service.MenuService;
import edu.hebeu.util.JsonUtils;
import edu.hebeu.util.RedisUtil;

@Controller
@RequestMapping("/menu")
public class MenuController {
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private MenuService menuService;

	public String toListPage(Model model) {

		// 从redis中获取菜单列表
		String string = redisUtil.get("menu");
		List<Menu> menuList = null;
		if (StringUtils.isBlank(string)) {// 不存在数据库中加载
			try {
				menuList = menuService.selectMenuAll();
				Map<Long, Menu> map = new HashMap<Long, Menu>();
				for (Menu menu : menuList) {
					map.put(menu.getId(), menu);
				}
				for (Menu menu : menuList) {
					if (menu.getParentId() != 0) {
						menu.setParentIdStr(map.get(
								String.valueOf(menu.getParentId())).getName());
						String[] p = menu.getParentIds().split(",");
						String parentIdsStr = "";
						for (String string2 : p) {
							if (!string2.equals("0")) {
								if (!parentIdsStr.equals("")) {
									parentIdsStr += "/";
								}
								parentIdsStr += map.get(string2).getName();
							}

						}
						menu.setParentIdsStr(parentIdsStr);
					}
				}
				redisUtil.set("menu", JsonUtils.objectToJson(menuList));

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			menuList = JsonUtils.jsonToList(string,
					new TypeReference<List<Menu>>() {
					});

		}
		model.addAttribute("menuList", menuList);
		return "backend/menuList";

	}

}
