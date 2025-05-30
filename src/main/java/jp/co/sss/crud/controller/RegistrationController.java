package jp.co.sss.crud.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.validation.Valid;
import jp.co.sss.crud.bean.EmployeeBean;
import jp.co.sss.crud.entity.Department;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.form.EmployeeForm;
import jp.co.sss.crud.repository.DepartmentRepository;
import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
public class RegistrationController {
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	DepartmentRepository deptRepository;

	@RequestMapping(path = "/regist/input", method = RequestMethod.GET)
	public String RegistInput(@ModelAttribute EmployeeForm form) {
		return "/regist/regist_input";
	}

	@RequestMapping(path = "/regist/input/check", method = RequestMethod.POST)
	public String inpugCheck(@Valid @ModelAttribute EmployeeForm form, BindingResult result,Integer deptId, 
			Model model) {
		if (result.hasErrors()) {
			return "/regist/regist_input";
		}
			Employee entity = new Employee();
			BeanUtils.copyProperties(form, entity);
			Department dept = deptRepository.getReferenceById(deptId);
			entity.setDept(dept);
			model.addAttribute("emp", entity);
		return "/regist/regist_check";
	}

	@RequestMapping(path = "/regist/input/result", method = RequestMethod.POST)
	public String inputResult(@ModelAttribute EmployeeForm form) {
		return "/regist/regist_input";
	}

	@RequestMapping(path = "/regist/input/complete", method = RequestMethod.POST)
	public String inputComplete(EmployeeForm form, Integer deptId, Model model) {
		Employee entity = new Employee();
		BeanUtils.copyProperties(form, entity);
		Department dept = deptRepository.getReferenceById(deptId);
		entity.setDept(dept);
		entity = employeeRepository.save(entity);
		EmployeeBean bean = new EmployeeBean();
		BeanUtils.copyProperties(dept, bean);
		model.addAttribute("emp", bean);
		return "/regist/regist_complete";
	}
}
