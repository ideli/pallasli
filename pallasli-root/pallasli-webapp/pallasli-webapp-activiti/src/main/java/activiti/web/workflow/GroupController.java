package activiti.web.workflow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
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
@RequestMapping(value = "/workflow/group")
public class GroupController {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	IdentityService identityService;

	@RequestMapping(value = "listUser")
	public void listUser(HttpServletResponse response) {
		List<User> list = identityService.createUserQuery().list();

		try {
			response.getWriter().write(new Gson().toJsonTree(list).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "listGroup")
	public void listGroup(HttpServletResponse response) {
		List<Group> list = identityService.createGroupQuery().list();

		try {
			response.getWriter().write(new Gson().toJsonTree(list).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "selectUsers")
	public void selectUsers(HttpServletResponse response) {
		List<User> list = identityService.createUserQuery()
				.memberOfGroup("admin").list();

		try {
			response.getWriter().write(new Gson().toJsonTree(list).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "unSelectUsers")
	public void unSelectUsers(HttpServletResponse response) {
		List<User> list = identityService.createUserQuery().list();
		List<User> list1 = identityService.createUserQuery()
				.memberOfGroup("admin").list();
		List<User> removelist = new ArrayList<User>();
		for (User u : list) {
			for (User u1 : list1) {
				if (u1.getId().equals(u.getId()))
					removelist.add(u);
			}
		}
		list.removeAll(removelist);
		try {
			response.getWriter().write(new Gson().toJsonTree(list).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "selectGroups")
	public void selectGroups(HttpServletResponse response) {
		List<Group> list = identityService.createGroupQuery()
				.groupMember("fozzie").list();

		try {
			response.getWriter().write(new Gson().toJsonTree(list).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "unSelectGroups")
	public void unSelectGroups(HttpServletResponse response) {
		List<Group> list = identityService.createGroupQuery().list();
		List<Group> list1 = identityService.createGroupQuery()
				.groupMember("fozzie").list();
		List<Group> removelist = new ArrayList<Group>();
		for (Group u : list) {
			for (Group u1 : list1) {
				if (u1.getId().equals(u.getId()))
					removelist.add(u);
			}
		}
		list.removeAll(removelist);
		try {
			response.getWriter().write(new Gson().toJsonTree(list).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
