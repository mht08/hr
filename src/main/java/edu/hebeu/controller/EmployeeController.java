package edu.hebeu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import edu.hebeu.common.MessageCode;
import edu.hebeu.common.ResultObject;
import edu.hebeu.entity.Department;
import edu.hebeu.entity.Employee;
import edu.hebeu.entity.History;
import edu.hebeu.entity.Position;
import edu.hebeu.service.DepartmentService;
import edu.hebeu.service.EmployeeService;
import edu.hebeu.service.HistoryService;
import edu.hebeu.service.PositionService;
import edu.hebeu.util.ApiCommonUtil;
import edu.hebeu.util.CookieUtil;
import edu.hebeu.util.CryptoUtil;
import edu.hebeu.util.JsonUtils;
import edu.hebeu.util.MTimeUtil;
import edu.hebeu.util.RedisUtil;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private PositionService positionService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private RedisUtil redisUtil;

	@RequestMapping("/login.do")
	public String toLogin() {
		return "login";
	}

	@RequestMapping("/testTree.do")
	public String testTree() {
		return "tree/testTree";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/treeJson.do")
	@ResponseBody
	public ResultObject treeJson() {
		String json="[{'id':1,'pid':0,'status':1,'name':'用户管理','permissionValue':'open:user:manage'},";
		json+="{'id':2,'pid':0,'status':1,'name':'系统管理','permissionValue':'open:system:manage'},";
		json+="{'id':3,'pid':1,'status':1,'name':'新增用户','permissionValue':'open:user:add'},";
		json+="{'id':4,'pid':1,'status':1,'name':'修改用户','permissionValue':'open:user:edit'},";
		json+="{'id':5,'pid':1,'status':0,'name':'删除用户','permissionValue':'open:user:del'},";
		json+="{'id':6,'pid':2,'status':1,'name':'系统配置管理','permissionValue':'open:systemconfig:manage'},";
		json+="{'id':7,'pid':6,'status':1,'name':'新增配置','permissionValue':'open:systemconfig:add'},";
		json+="{'id':8,'pid':6,'status':1,'name':'修改配置','permissionValue':'open:systemconfig:edit'},";
		json+="{'id':9,'pid':6,'status':0,'name':'删除配置','permissionValue':'open:systemconfig:del'},";
		json+="{'id':10,'pid':2,'status':1,'name':'系统日志管理','permissionValue':'open:log:manage'},";
		json+="{'id':11,'pid':10,'status':1,'name':'新增日志','permissionValue':'open:log:add'},";
		json+="{'id':12,'pid':10,'status':1,'name':'修改日志','permissionValue':'open:log:edit'},";
		json+="{'id':13,'pid':10,'status':0,'name':'删除日志','permissionValue':'open:log:del'}]";
		
		List<Map<String,Object>> lsit = new ArrayList<Map<String,Object>>();
		Map<String,Object> detail = new HashMap<String,Object>();
		detail.put("id", 1);
		detail.put("pid", 0);
		detail.put("status", 1);
		detail.put("name", "用户管理");
		detail.put("permissionValue", "open:user:manage");
//		detail.put("_level","0");
//		detail.put("check",true);
		lsit.add(detail);
		Map<String,Object> detail1 = new HashMap<String,Object>();
		detail1.put("id", 2);
		detail1.put("pid", 0);
		detail1.put("status", 1);
		detail1.put("name", "系统管理");
		detail1.put("permissionValue", "open:system:manage");
//		detail1.put("_level","0");
//		detail1.put("check",true);
		lsit.add(detail1);
		Map<String,Object> detail2 = new HashMap<String,Object>();
		detail2.put("id", 3);
		detail2.put("pid", 1);
		detail2.put("status", 1);
		detail2.put("name", "新增用户");
		detail2.put("permissionValue", "open:user:add");
//		detail.put("_level","1");
//		detail.put("check",true);
		lsit.add(detail2);
		
		MessageCode code = MessageCode.CODE_SUCCESS;
		ResultObject resultObject = new ResultObject(code);
		resultObject.setData(lsit);
		return resultObject;
		
	}


	@RequestMapping("/checkLogin.do")
	public String checkLogin(Employee employee,HttpServletResponse response,Model model) {
		// 生成token
		String token = UUID.randomUUID().toString().replaceAll("-", "");
		// 登陆传入的参数不可以为空
		if (employee != null &&  employee.getEmployeeNumber() != null && StringUtils.isNotBlank(employee.getPassword())) {
			try {
				// 根据用户名密码获取登陆用户
				Employee employee2 = employeeService.checkLogin(employee.getEmployeeNumber(), CryptoUtil.md5(employee.getPassword()));
				String level = employee2.getPosition().getLevel();
				// 把当前登陆用户信息存放在redis中
				redisUtil.set(token, JsonUtils.objectToJson(employee2));
				// 把token值存放到cookie中
				CookieUtil.addCookie(response, ApiCommonUtil.TOKEN_NAME, token, 0);
				// 把用户信息存放到 model employee中
				model.addAttribute("employee", employee2);
				if (level.equals("人事部主任")) {
					return "admin/index1";
				} else if (level.equals("人事部员工")) {
					return "admin/index2";
				} else if (level.equals("部门主任")) {
					return "admin/index3";
				} else {
					return "admin/index4";
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "login";
			}
		}
		return "login";
	}

	@RequestMapping("/welcome.do")
	public String toWelcome(Model model) {
		Employee employee = ApiCommonUtil.getEmployyee(redisUtil);
		model.addAttribute("employee", employee);
		return "welcome";
	}

	@RequestMapping("/listPage.do")
	public String selectList(Model model, int pageNo) {
		Page<Employee> page = employeeService.selectListByPage(pageNo);
		model.addAttribute("page", page);
		return "admin/employee_list";
	}

	@RequestMapping("/{id}/detial.do")
	public String selectEmployee(@PathVariable Integer id, Model model) {
		Employee employee = employeeService.selectEmployee(id);
		model.addAttribute("employee", employee);
		return "admin/employee_detail";
	}

	@RequestMapping("/toAdd.do")
	public String toAdd(Model model) {
		List<History> eList = historyService
				.selectList(new EntityWrapper<History>().orderBy(
						"employee_number", false));
		model.addAttribute("employeeNumber",
				eList.get(0).getEmployeeNumber() + 1);
		List<Department> dList = departmentService
				.selectList(new EntityWrapper<Department>());
		model.addAttribute("dList", dList);
		List<Position> pList = positionService
				.selectList(new EntityWrapper<Position>());
		model.addAttribute("pList", pList);
		return "admin/employee_add";
	}

	@RequestMapping("/add.do")
	public String add(Employee employee, String date) {
		employee.setBirthday(MTimeUtil.stringParse(date));
		employeeService.addEmployee(employee);
		return "forward:/employee/listPage.do?pageNo=1";
	}

	@RequestMapping("/{id}/toUpdate.do")
	public String toUpdate(Model model, @PathVariable Integer id) {
		Employee employee = employeeService.selectById(id);
		model.addAttribute("employee", employee);
		List<Department> dList = departmentService
				.selectList(new EntityWrapper<Department>());
		model.addAttribute("dList", dList);
		List<Position> pList = positionService
				.selectList(new EntityWrapper<Position>());
		model.addAttribute("pList", pList);
		return "admin/employee_update";
	}

	@RequestMapping("/{id}/update.do")
	public String updateById(@PathVariable Integer id, Employee employee,
			String date, String status, HttpSession session) {
		employee.setId(id);
		employee.setBirthday(MTimeUtil.stringParse(date));
		// 得到操作人员的名字
		Employee employee2 = ApiCommonUtil.getEmployyee(redisUtil);

		Employee oldEmployee = employeeService.selectEmployee(id);
		// 判断当前的密码与数据库的密码是否冲突
		if (!employee.getPassword().contentEquals(oldEmployee.getPassword())) {
			// 加密密码
			employee.setPassword(CryptoUtil.md5(employee.getPassword()));
		}
		employeeService.updateEmployee(employee, status, employee2.getName());
		// 判断当前用户是否为修改用户--更新session状态
		if (oldEmployee.getId().equals(employee2.getId())) {
			session.setAttribute("loged",
					employeeService.selectById(employee.getId()));
		}
		return "forward:/employee/listPage.do?pageNo=1";
	}

	@RequestMapping("/{id}/delete.do")
	public String deleteById(@PathVariable Integer id) {
		employeeService.deleteEmployee(id);
		return "forward:/employee/listPage.do?pageNo=1";
	}

	@RequestMapping("/{id}/reset.do")
	public String resetById(@PathVariable Integer id) {
		employeeService.resetEmployee(id);
		;
		return "forward:/employee/listPage.do?pageNo=1";
	}

	@RequestMapping("/oneself/{id}/detial.do")
	public String selectEmployee2(@PathVariable Integer id, Model model) {
		Employee employee = employeeService.selectEmployee(id);
		model.addAttribute("employee", employee);
		return "admin/oneself_detail";
	}

	@RequestMapping("/oneself/{id}/toUpdate.do")
	public String toUpdate2(Model model, @PathVariable Integer id) {
		Employee employee = employeeService.selectById(id);
		model.addAttribute("employee", employee);
		return "admin/oneself_update";
	}

	@RequestMapping("/search")
	public String search(Model model, String input, int pageNo) {
		Page<Employee> page = employeeService.search(input, pageNo);
		model.addAttribute("page", page);
		return "admin/search_result";
	}

	@RequestMapping("/logout.do")
	public String logout(HttpServletResponse response,HttpServletRequest request) {
		// 删除REDIS
		redisUtil.delete(CookieUtil.getUid(request, ApiCommonUtil.TOKEN_NAME));
		// 删除COOKIE
		CookieUtil.removeCookie(response, ApiCommonUtil.TOKEN_NAME);
		return "login";
	}

}
