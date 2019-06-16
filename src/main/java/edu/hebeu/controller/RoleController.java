package edu.hebeu.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.hebeu.common.MessageCode;
import edu.hebeu.common.ResultObject;
import edu.hebeu.entity.Role;
import edu.hebeu.service.RoleService;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/role")
public class RoleController {
	private Logger logger = Logger.getLogger(RoleController.class);
	@Autowired
	private RoleService roleService;

	// 角色信息列表的请求处理
	@RequestMapping("roleList.do")
	public String toRoleListPage(Model model, int pageNo) {
		List<Role> roleList;
		try {
			roleList = roleService.selectRoleAll();
			model.addAttribute("roleList", roleList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "role/list";
	}

	// 添加角色信息的请求处理
	@RequestMapping(value = "addRole.do", method = RequestMethod.POST)
	public String addTrainPage(Role role) {

		try {
			roleService.insertRole(role);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/role/roleList.html";

	}

	// 角色信息详情的请求处理
	@RequestMapping(value = "getRole.do", produces = { "text/html;charset=UTF-8" })
	@ResponseBody
	public Object getRole(@RequestParam(value = "id", required = false) String id) {
		logger.info("getRole id=" + id);
		String cjson = "";
		if (null == id || "".equals(id)) {
			return "nodata";
		} else {
			try {
				Role train = new Role();
				train = roleService.selectRoleById(Long.parseLong(id));
				JSONObject jo = JSONObject.fromObject(train);
				cjson = jo.toString();
			} catch (Exception e) {
				e.printStackTrace();
				return "failed";
			}
		}
		return cjson;
	}

	// 删除角色信息的请求处理
	// @RequestMapping("delete.html")
	// @ResponseBody
	// public String doDeleteRole(Model model, @RequestParam String ids) {
	//
	// int flag = 0;
	// if (null != ids && !"".equals(ids)) {
	// String[] selectTrainNos = ids.split(" ");
	// try {
	// flag = roleService.delRoleByIds(selectTrainNos);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	// if (flag > 0) {
	// return "success";
	// } else {
	// return "failed";
	// }
	// }

	// 修改角色列表信息的请求处理
	@RequestMapping(value = "/{id}/toUpdateRole.do")
	public String updaterole(Model model, @PathVariable Integer id) {
		Role role = roleService.selectById(Long.valueOf(id));
		model.addAttribute("role", role);
		return "role/update";
	}

	@RequestMapping("/{id}/update.do")
	public String updateById(@PathVariable Integer id, Role role) {
		role.setId(Long.valueOf(id));
		roleService.updateById(role);

		return "forward:/role/roleList.do?pageNo=1";
	}

	// 删除
	@RequestMapping("/{id}/delete.do")
	public String deleteById(@PathVariable Integer id) {
		roleService.delRole(Long.valueOf(id));
		return "forward:/role/roleList.do?pageNo=1";
	}

	@RequestMapping("/toAdd.do")
	public String toAdd(Model model,Long id) {
		model.addAttribute("id", id);
		return "role/add";
	}
	

	@RequestMapping("/assignEmployees.do")
	public String assignEmployees(Model model,Long id) {
		model.addAttribute("id", id);
		return "role/assignEmployees";
	}
	
	

	@RequestMapping("/add.do")
	public String add(Role role) {
		roleService.insertRole(role);
		return "forward:/role/roleList.do?pageNo=1";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/getById.do")
	@ResponseBody
	public ResultObject getById(Long id) {
		MessageCode code = MessageCode.CODE_SUCCESS;
		Role role = roleService.selectById(id);
		
		ResultObject resultObject = new ResultObject(code);
		resultObject.setData(role);
		return resultObject;
	}
	
	

}
