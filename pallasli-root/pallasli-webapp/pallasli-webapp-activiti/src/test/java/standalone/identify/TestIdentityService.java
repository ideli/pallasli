package standalone.identify;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

@Ignore
public class TestIdentityService {
	@Rule
	public ActivitiRule activitiRule = new ActivitiRule("/standalone/activiti.cfg.xml");

	@Test
	public void testUser() {
		IdentityService identityService = activitiRule.getIdentityService();
		User user = identityService.newUser("PallasLi");
		user.setFirstName("Pallas");
		user.setLastName("Li");
		user.setEmail("ytli1987@163.com");
		identityService.saveUser(user);

		User userInDb = identityService.createUserQuery().userId("PallasLi").singleResult();
		assertNotNull(userInDb);
		identityService.deleteUser("PallasLi");
		userInDb = identityService.createUserQuery().userId("PallasLi").singleResult();
		assertNull(userInDb);
	}

	@Test
	public void testGroup() {
		IdentityService identityService = activitiRule.getIdentityService();
		User user = identityService.newUser("PallasLi");
		user.setFirstName("Pallas");
		user.setLastName("Li");
		user.setEmail("ytli1987@163.com");
		identityService.saveUser(user);
		Group group = identityService.newGroup("PallasLyt");
		group.setName("Pallas");
		group.setType("assignment");
		identityService.saveGroup(group);

		identityService.createMembership(user.getId(), group.getId());

		List<Group> groupList = identityService.createGroupQuery().groupMember("PallasLi").list();
		assertEquals(1, groupList.size());
		List<User> userList = identityService.createUserQuery().memberOfGroup("PallasLyt").list();
		assertEquals(1, userList.size());
		identityService.deleteGroup("PallasLyt");
		userList = identityService.createUserQuery().memberOfGroup("PallasLyt").list();
		assertEquals(0, userList.size());
	}

	@Test
	public void testUserAndGroupMemership() {
		IdentityService identityService = activitiRule.getIdentityService();
		Group group = identityService.newGroup("PallasLyt");
		group.setName("Pallas");
		group.setType("assignment");
		identityService.saveGroup(group);

		Group groupInDb = identityService.createGroupQuery().groupId("PallasLyt").singleResult();
		assertNotNull(groupInDb);
		identityService.deleteGroup("PallasLyt");
		groupInDb = identityService.createGroupQuery().groupId("PallasLyt").singleResult();
		assertNull(groupInDb);
	}
}
