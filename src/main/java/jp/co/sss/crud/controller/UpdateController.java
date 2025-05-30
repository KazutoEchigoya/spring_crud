package jp.co.sss.crud.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.crud.bean.EmployeeBean;
import jp.co.sss.crud.entity.Department;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.form.EmployeeForm;
import jp.co.sss.crud.repository.DepartmentRepository;
import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
public class UpdateController {
	@Autowired
	EmployeeRepository empRepository;
	@Autowired
	DepartmentRepository deptRepository;

	@RequestMapping(path = "/regist/update/input/{empId}", method = RequestMethod.POST)
	public String update(@PathVariable Integer empId, EmployeeForm form, Model model) {
		Employee emp = empRepository.getReferenceById(empId);
		EmployeeBean bean = new EmployeeBean();
		BeanUtils.copyProperties(emp, bean);
		model.addAttribute("emp", emp);
		return "/update/update_input";
	}

	@RequestMapping(path = "/regist/update/check/{empId}", method = RequestMethod.POST)
	public String updateCheck(@PathVariable Integer empId, Integer deptId, EmployeeForm form, Model model) {
		Employee emp = new Employee();
		BeanUtils.copyProperties(form, emp, "empId");
		Department dept = deptRepository.getReferenceById(deptId);
		emp.setDept(dept);
		model.addAttribute("emp", emp);
		return "/update/update_check";
	}

	@RequestMapping(path = "/regist/update/result/{empId}", method = RequestMethod.POST)
	public String doUpdateResult(@PathVariable Integer empId, EmployeeForm form,Model model) {
		Employee emp = empRepository.getReferenceById(empId);
		EmployeeBean bean = new EmployeeBean();
		BeanUtils.copyProperties(emp, bean);
		model.addAttribute("emp", emp);
		return "/update/update_input";
	}

	@RequestMapping(path = "/regist/update/complete/{empId}", method = RequestMethod.POST)
	public String updateComplete(@PathVariable Integer empId, Integer deptId, EmployeeForm form, Model model) {
		Employee emp = empRepository.getReferenceById(empId);
		BeanUtils.copyProperties(form, emp, "empId");
		Department dept = deptRepository.getReferenceById(deptId);
		emp.setDept(dept);
		emp = empRepository.save(emp);
		EmployeeBean bean = new EmployeeBean();
		BeanUtils.copyProperties(emp, bean);
		model.addAttribute("emp", bean);
		return "/update/update_complete";
	}
}
