package activiti.web.workflow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

/**
 * 流程模型控制器
 * 
 * @author henryyan
 * 
 * 
 *         ModelAndView Model ModelMap Map View String Void
 */
@Controller
@RequestMapping(value = "/workflow/instance")
public class InstanceController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	RuntimeService runtimeService;
	@Autowired
	TaskService taskService;

	// @RequestMapping(value = "list")
	// public ModelAndView modelList() {
	//
	// ModelAndView mav = new ModelAndView("workflow/model-list");
	// List<Model> list = repositoryService.createModelQuery().list();
	// mav.addObject("list", list);
	// return mav;
	// }
	/**
	 * 模型列表
	 */
	@RequestMapping(value = "list")
	public void instanceList(HttpServletResponse response) {
		// 启动流程实例
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("employeeName", "Kermit");
		variables.put("numberOfDays", new Integer(4));
		variables.put("vacationMotivation", "I'm really tired!");
		variables.put("vacationApproved", true);

		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey("vacationRequest", variables);

		long count = runtimeService.createProcessInstanceQuery().count();
		List<ProcessInstance> list1 = runtimeService
				.createProcessInstanceQuery()
				.processDefinitionKey("vacationRequest").list();

		List<Task> tasks = taskService.createTaskQuery().list();
		System.out.println(tasks.size());

		List<MyProcessInstance> list = new ArrayList<MyProcessInstance>();
		for (ProcessInstance instance : list1) {
			MyProcessInstance myin = new MyProcessInstance();
			myin.setId(instance.getId());
			myin.setName(instance.getName());
			myin.setBusinessKey(instance.getBusinessKey());
			myin.setProcessDefinitionKey(instance.getProcessDefinitionKey());
			list.add(myin);
		}
		// mav.addObject("list", list);
		try {
			response.getWriter().write(new Gson().toJsonTree(list).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ProcessDefinitionEntity p;
	}
}
