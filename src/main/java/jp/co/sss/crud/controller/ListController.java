package jp.co.sss.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.crud.repository.DepartmentRepository;
import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
public class ListController {
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	DepartmentRepository deptRepository;

	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public String employee(Integer gender, Model model) {
		model.addAttribute("emp", employeeRepository.findByDeleteFlagOrderByEmpIdAsc(0));
		return "/list/list";
	}

	@RequestMapping(path = "/list/empName", method = RequestMethod.GET)
	public String byEmpName(String empName, Model model) {
		if (empName.equals("")) {
			return "redirect:/list";
		} else {
			empName.trim();
		}
		model.addAttribute("emp", employeeRepository.findByEmpNameContainingAndDeleteFlagOrderByEmpId(empName,0));
		return "/list/list";
	}
}
