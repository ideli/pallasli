package activiti.web.workflow;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
@RequestMapping(value = "/workflow/task")
public class TaskController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	TaskService taskService;

	/**
	 * 模型列表
	 */
	@RequestMapping(value = "taskDoingList")
	public void taskDoingList(@RequestParam("userId") String userId,
			HttpServletResponse response) {
		List<Task> tasks = taskService.createTaskQuery()
				.taskAssignee("USER(" + userId + ")").active().list();
		try {
			response.getWriter().write(new Gson().toJsonTree(tasks).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 模型列表
	 */
	@RequestMapping(value = "taskDoneList")
	public List<Task> taskDoneList(@RequestParam("userId") String userId) {
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId)
				.active().list();
		System.out.println(tasks.size());

		// ModelAndView mav = new ModelAndView();
		// mav.setViewName("workbanch/task_done"); // 设置返回的文件名
		// mav.addObject("mav", tasks);
		return tasks;
	}
}
