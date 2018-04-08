package com.test.variable;


import java.util.Date;
import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import com.test.model.Student;

/**
 * ���̱�������
 * @author user
 *
 */
public class VariableTest2 {

	/**
	 * ��ȡĬ����������ʵ�������Զ���ȡactiviti.cfg.xml�ļ�
	 */
	private ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
	
	/**
	 * �������̶���
	 */
	@Test
	public void deploy(){
		Deployment deployment=processEngine.getRepositoryService() // ��ȡ�������Service
				.createDeployment() // ��������
				.addClasspathResource("diagrams/StudentLeaveProcess.bpmn") // ������Դ�ļ�
				.addClasspathResource("diagrams/StudentLeaveProcess.png") // ������Դ�ļ�
				.name("ѧ���������") // ��������
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
			.startProcessInstanceByKey("studentLevaeProcess"); // ���̶������KEY�ֶ�ֵ
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
			.taskAssignee("����") // ָ��ĳ����
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
			.complete("97502");
	}
	
	/**
	 * �������̱�������
	 */
	@Test
	public void setVariableValues(){
		RuntimeService runtimeService=processEngine.getRuntimeService(); // ����Service
		String executionId="90001";//ִ�ж����id��act_ru_execution
		runtimeService.setVariable(executionId, "days", 2);
		runtimeService.setVariable(executionId, "date", new Date());
		runtimeService.setVariable(executionId, "reason", "����");
		Student student=new Student();
		student.setId(1);
		student.setName("����");
		runtimeService.setVariable(executionId, "student", student); // �����л�����
	}
	
	/**
	 * ��ȡ���̱�������
	 */
	@Test
	public void getVariableValues(){
		RuntimeService runtimeService=processEngine.getRuntimeService(); // ����Service
		String executionId="90001";
		Integer days=(Integer) runtimeService.getVariable(executionId, "days");
		Date date=(Date) runtimeService.getVariable(executionId, "date");
		String reason=(String) runtimeService.getVariable(executionId, "reason");
		Student student=(Student) runtimeService.getVariable(executionId, "student"); 
		System.out.println("���������"+days);
		System.out.println("������ڣ�"+date);
		System.out.println("���ԭ��"+reason);
		System.out.println("��ٶ���"+student.getId()+","+student.getName());
	}
	
	
	
	
}