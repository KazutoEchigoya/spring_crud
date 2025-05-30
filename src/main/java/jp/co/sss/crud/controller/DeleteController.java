package jp.co.sss.crud.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.crud.bean.EmployeeBean;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.form.EmployeeForm;
import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
public class DeleteController {
	@Autowired
	EmployeeRepository empRepository;

	@RequestMapping(path = "/regist/delete/check/{empId}", method = RequestMethod.POST)
	public String deleteChech(@PathVariable Integer empId, Model model) {
		Employee emp = empRepository.getReferenceById(empId);
		EmployeeBean bean = new EmployeeBean();
		BeanUtils.copyProperties(emp, bean);
		model.addAttribute("emp", bean);
		return "/delete/delete_check";
	}
	
	@RequestMapping(path="/regist/delete/result/",method=RequestMethod.POST)
	public String deleteResult() {
		return "/list";
	}

	@RequestMapping(path = "/regist/delete/complete/{empId}", method = RequestMethod.POST)
	public String deleteComplete(@PathVariable Integer empId, EmployeeForm form,Model model) {
		Employee emp = empRepository.getReferenceById(empId);
		emp.setDeleteFlag(1);
		emp = empRepository.save(emp);
		model.addAttribute("emp",emp);
		return "/delete/delete_complete";
	}
}
