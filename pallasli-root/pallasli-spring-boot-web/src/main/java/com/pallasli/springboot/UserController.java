package com.pallasli.springboot;


//@RestController
//@RequestMapping("user")
public class UserController {

	// private static final Logger logger = LoggerFactory
	// .getLogger(UserController.class);

	// @Autowired
	// UserFacade userFacade;
	//
	// @RequestMapping(method = RequestMethod.GET)
	// List<User> selectAllUser() {
	//
	// List<User> userList = userFacade.selectAllUser();
	// return userList;
	// }
	//
	// @RequestMapping(value = "{username}", method = RequestMethod.GET)
	// User selectUser(@PathVariable String username) {
	//
	// User user = userFacade.selectUserByUsername(username);
	// logger.info("###incontroller user=[" + user.toString() + "]###");
	// return user;
	// }
	//
	// @RequestMapping(method = RequestMethod.POST)
	// ResponseEntity<User> insertUser(@RequestBody User user,
	// UriComponentsBuilder uriBuilder) {
	//
	// userFacade.insertUser(user);
	// User insertedUser = userFacade.selectUserByUsername(user.getUsername());
	// URI location = uriBuilder.path("user/{username}")
	// .buildAndExpand(insertedUser.getUsername()).toUri();
	// HttpHeaders headers = new HttpHeaders();
	// headers.setLocation(location);
	// return new ResponseEntity<>(insertedUser, headers, HttpStatus.CREATED);
	// }
	//
	// @RequestMapping(value = "{username}", method = RequestMethod.PUT)
	// User updateUser(@PathVariable String username, @RequestBody User user) {
	//
	// user.setUsername(username);
	// userFacade.updateUserByUsername(user);
	// return userFacade.selectUserByUsername(username);
	// }
	//
	// @RequestMapping(value = "{username}", method = RequestMethod.DELETE)
	// void updateUser(@PathVariable String username) {
	//
	// userFacade.deleteUserByUsername(username);
	// }

}
