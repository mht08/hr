package edu.hebeu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.hebeu.entity.Attendance;
import edu.hebeu.service.AttendanceService;
import edu.hebeu.util.ApiCommonUtil;
import edu.hebeu.util.RedisUtil;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@RequestMapping("/addStart.do")
	public String addStart(Integer employeeNumber){
		attendanceService.addStart(employeeNumber);
		return "forward:/employee/welcome.do";
	}
	
	@RequestMapping("/addEnd.do")
	public String addEnd(Integer employeeNumber){
		attendanceService.addEnd(employeeNumber);
		return "forward:/employee/welcome.do";
	}
	
	@RequestMapping("/list.do")
	public String selectList(Model model){
		List<Attendance> list = attendanceService.selectList();
		model.addAttribute("aList",list);
		return "admin/attendance_list";
	}
	
	@RequestMapping("/oneself.do")
	public String select(Model model){
		List<Attendance> list = attendanceService.selectByEmployee(ApiCommonUtil.getEmployyee(redisUtil).getEmployeeNumber());
		model.addAttribute("aList",list);
		return "admin/oneself_attendance";
	}
}
