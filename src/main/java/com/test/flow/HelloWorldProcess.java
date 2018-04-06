package com.test.flow;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class HelloWorldProcess {

	/**
	 * �ڶ��ν������̴��������
	 * ��ȡĬ����������ʵ�������Զ���ȡactiviti.cfg.xml�ļ�
	 * 
	 * act_ge_property��ϵͳ���ñ�
	 * �������̶����漰���ı�
	 * 	���̲����act_re_deployment�����仯
	 * 	���̶����act_re_procdef�����仯
	 * 	��Դ�ļ���act_ge_bytearray�����仯
	 * 	ϵͳ���ñ�act_ge_property�����仯->������һ������idֵ
	 * 
	 * ��������ʵ���漰���ı�
	 * 	����ʵ������ʱִ�ж����act_ru_execution�����仯
	 * 	����ʵ������ʱ�����ϵ��act_ru_identitylink�����仯
	 *  ����ʵ������ʱ�û������act_ru_task�����仯
	 *  ��ڵ���ʷ��act_hi_actinst�����仯
	 *  �����ϵ��  ��ʷ act_hi_identitylink�����仯
	 *  ����ʵ���� ��ʷ act_hi_procinst�����仯
	 *  ��ʷ����� act_hi_taskinst�����仯
	 *  
	 *  
	 * ��ѯ�����漰���ı�
	 * 	act_ru_task
	 * 
	 * ��������ʵ���漰���ı�
	 * 	����ʱ�ı�����ȫ�����
	 *  ��ʷ�� �������޸Ļ�������������
	 * 	
	 */
	private ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
	
	/**
	 * �������̶���
	 * ��������ʵ��
	 */
	@Test
	public void deploy(){
		//��ȡ�������ļ�����ȡ������ͼ���������̶���֮��������̶���
		Deployment deployment=processEngine.getRepositoryService() // ��ȡ�������Service
				.createDeployment() // ��������
				.addClasspathResource("diagrams/HelloWorld.bpmn") // ������Դ�ļ������̶�������
				.addClasspathResource("diagrams/HelloWorld.png") // ������Դ�ļ�
				.name("HelloWorld����") // ��������
				.deploy(); // ����
		System.out.println("���̲���ID:"+deployment.getId()); 
		System.out.println("���̲���Name:"+deployment.getName());
	}
	
	/**
	 * ��������ʵ��
	 */
	@Test
	public void start(){
		ProcessInstance pi=processEngine.getRuntimeService() // ����ʱService
			.startProcessInstanceByKey("myFirstProcess"); // ���̶�����KEY�ֶ�ֵ
		System.out.println("����ʵ��ID:"+pi.getId());
		System.out.println("���̶���ID:"+pi.getProcessDefinitionId()); 
	}
	
	/**
	 * �鿴����
	 */
	@Test
	public void findTask(){
		List<Task> taskList=processEngine.getTaskService() // �������Service
			.createTaskQuery() // ���������ѯ
			.taskAssignee("shibin") // ָ��ĳ����
			.list();
		for(Task task:taskList){
			System.out.println("����ID:"+task.getId()); 
			System.out.println("��������:"+task.getName());
			System.out.println("���񴴽�ʱ��:"+task.getCreateTime());
			System.out.println("����ί����:"+task.getAssignee());
			System.out.println("����ʵ��ID:"+task.getProcessInstanceId());
		}
	}
	
	/**
	 * �������
	 */
	@Test
	public void completeTask(){
		processEngine.getTaskService() // �������Service
			.complete("2504");
	}
}
