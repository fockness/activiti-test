package com.test.groupAssign;


import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.junit.Test;

public class IdentityTest {

	/**
	 * ��ȡĬ����������ʵ�������Զ���ȡactiviti.cfg.xml�ļ�
	 */
	private ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
	
	/**
	 * ����û�����
	 */
	@Test
	public void testSaveUser(){
		IdentityService indentityService=processEngine.getIdentityService();
		User user=new UserEntity(); // ʵ�����û�ʵ��
		user.setId("wangwu");
		user.setPassword("123");
		user.setEmail("1234@qq.com");
		indentityService.saveUser(user); 
	}

	/**
	 * ɾ���û�
	 */
	@Test
	public void testDeleteUser(){
		IdentityService indentityService=processEngine.getIdentityService();
		indentityService.deleteUser("lisi");
	}
	
	/**
	 * �����
	 */
	@Test
	public void testSaveGroup(){
		IdentityService indentityService=processEngine.getIdentityService();
		Group group=new GroupEntity();
		group.setId("test");
		indentityService.saveGroup(group);
	}
	
	/**
	 * ����ɾ����
	 */
	@Test
	public void testDeleteGroup(){
		IdentityService indentityService=processEngine.getIdentityService();
		indentityService.deleteGroup("test");
	}
	
	/**
	 * ��������û�����Ĺ�����ϵ
	 */
	@Test
	public void testSaveMembership(){
		IdentityService indentityService=processEngine.getIdentityService();
		indentityService.createMembership("wangwu", "test");
	}
	
	/**
	 * ����ɾ���û�����Ĺ�����ϵ
	 */
	@Test
	public void testDeleteMembership(){
		IdentityService indentityService=processEngine.getIdentityService();
		indentityService.deleteMembership("lisi", "dev");
	}
}
