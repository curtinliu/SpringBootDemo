package com.xiaour.spring.boot.controller;

import com.xiaour.spring.boot.service.RedisService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiaour.spring.boot.entity.UserInfo;
import com.xiaour.spring.boot.mapper.UserInfoMapper;
import com.xiaour.spring.boot.utils.JsonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaour on 2017/4/19.
 */
@RestController
@RequestMapping(value = "/helloSpring")
public class TestCtrl {
 
	private static final Logger log = LoggerFactory.getLogger(TestCtrl.class);

	@Autowired
	private RedisService redisService;

	@Autowired
	private UserInfoMapper userInfoMapper;

	@RequestMapping(value = "/index")
	public String index() {
		return "hello world";
	}

	/**
	 * 向redis存储值
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/set")
	public String set(String key, String value) throws Exception {

		redisService.set(key, value);
		return "success";
	}

	/**
	 * 获取redis中的值
	 * 
	 * @param key
	 * @return
	 */
	@RequestMapping("/get")
	public String get(String key) {
		try {
			return redisService.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 添加用户信息
	 * @param name
	 * @param age
	 * @return
	 */
	@RequestMapping("/addUser/{name},{age}")
	public String addUserInfo(@PathVariable("name") String name,@PathVariable("age") int age) {
		try {
			UserInfo userInfo = new UserInfo();
			userInfo.setName(name);
			userInfo.setAge(age);
			userInfo.setCreateDate(new Date());
			userInfo.setCreateUser("curtin");
			userInfo.setUpdateDate(new Date());
			userInfo.setUpdateUser("curtin");
			String jsonUserInfo =JsonUtil.getJsonString(userInfo);		
			Integer addUserInfoCount = userInfoMapper.addUserInfo(userInfo);
			if(addUserInfoCount>0) {
				jsonUserInfo="添加成功"+addUserInfoCount+"条数据，"+"添加的用户信息:"+jsonUserInfo;
			}else {
				jsonUserInfo="添加失败，添加了"+addUserInfoCount+"条数据，"+"添加的用户信息:"+jsonUserInfo;
			}
			log.info(jsonUserInfo);
			return jsonUserInfo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 获取数据库中的用户
	 * 
	 * @return
	 */
	@RequestMapping("/getAllUser")
	public List<UserInfo> getAllUserInfo() {
		List<UserInfo> userList =new ArrayList<UserInfo>();
		try {
			userList = userInfoMapper.selectAllUserInfo();
			for (UserInfo userInfo : userList) {
				log.info("获取数据库中的用户信息:"+JsonUtil.getJsonString(userInfo));
			}
//			String jsonUserInfo ="";
//			int count =0;
//			for (UserInfo userInfo : userList) {
//				count =count+1;
//				jsonUserInfo =JsonUtil.getJsonString(userInfo);
//				jsonUserInfo="用户"+count+":"+jsonUserInfo;
//			}
//			log.info("获取数据库中的用户信息:"+jsonUserInfo);
//			return jsonUserInfo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}
	/**
	 * 获取数据库中的用户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getUser/{id}")
	public String get(@PathVariable("id") int id) {
		try {
			UserInfo user = userInfoMapper.selectByPrimaryKey(id);
			String jsonUserInfo =JsonUtil.getJsonString(user);
			log.info("获取数据库中的用户信息:"+jsonUserInfo);
			return jsonUserInfo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void main(String[] args) {
		Map<String, Object> keyMap = new HashMap<>();
		keyMap.put("id", "编号");
		keyMap.put("name", "名称");

		String[] cnCloumn = { "编号", "名称" };
		// 打印key
		System.out.println(Arrays.asList(convertMap(keyMap, cnCloumn)));
		log.info(Arrays.asList(convertMap(keyMap, cnCloumn)).toString());
	}

	/**
	 * 通过value获取key
	 * 
	 * @param keyMap
	 * @param dataList
	 * @return
	 */
	public static String[] convertMap(Map<String, Object> keyMap, String[] dataList) {

		for (int i = 0; i < dataList.length; i++) {

			for (Map.Entry<String, Object> m : keyMap.entrySet()) {
				if (m.getValue().equals(dataList[i])) {
					dataList[i] = m.getKey();
				}
			}
		}

		return dataList;
	}

	public static String getName(String name, String add) {
		return null;
	}

	public static void testGetClassName() {
		// 方法1：通过SecurityManager的保护方法getClassContext()
		String clazzName = new SecurityManager() {
			public String getClassName() {
				return getClassContext()[1].getName();
			}
		}.getClassName();
		System.out.println(clazzName);
		// 方法2：通过Throwable的方法getStackTrace()
		String clazzName2 = new Throwable().getStackTrace()[1].getClassName();
		System.out.println(clazzName2);
		// 方法3：通过分析匿名类名称()
		String clazzName3 = new Object() {
			public String getClassName() {
				String clazzName = this.getClass().getName();
				return clazzName.substring(0, clazzName.lastIndexOf('$'));
			}
		}.getClassName();
		System.out.println(clazzName3);
		// 方法4：通过Thread的方法getStackTrace()
		String clazzName4 = Thread.currentThread().getStackTrace()[2].getClassName();
		System.out.println(clazzName4);
	}

}
