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
	 * 第二课讲了流程创建到完成
	 * 获取默认流程引擎实例，会自动读取activiti.cfg.xml文件
	 * 
	 * act_ge_property是系统配置表
	 * 部署流程定义涉及到的表：
	 * 	流程部署表act_re_deployment发生变化
	 * 	流程定义表act_re_procdef发生变化
	 * 	资源文件表act_ge_bytearray发生变化
	 * 	系统配置表act_ge_property发生变化->产生下一个主键id值
	 * 
	 * 启动流程实例涉及到的表：
	 * 	流程实例运行时执行对象表act_ru_execution发生变化
	 * 	流程实例运行时身份联系表act_ru_identitylink发生变化
	 *  流程实例运行时用户任务表act_ru_task发生变化
	 *  活动节点历史表act_hi_actinst发生变化
	 *  身份联系表  历史 act_hi_identitylink发生变化
	 *  流程实例表 历史 act_hi_procinst发生变化
	 *  历史任务表 act_hi_taskinst发生变化
	 *  
	 *  
	 * 查询任务涉及到的表：
	 * 	act_ru_task
	 * 
	 * 结束流程实例涉及到的表：
	 * 	运行时的表数据全部清空
	 *  历史表 表数据修改或者增加了数据
	 * 	
	 */
	private ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
	
	/**
	 * 部署流程定义
	 * 创建流程实例
	 */
	@Test
	public void deploy(){
		//读取了配置文件，读取了流程图创建了流程对象，之后操作流程对象
		Deployment deployment=processEngine.getRepositoryService() // 获取部署相关Service
				.createDeployment() // 创建部署
				.addClasspathResource("diagrams/HelloWorld.bpmn") // 加载资源文件，流程定义的添加
				.addClasspathResource("diagrams/HelloWorld.png") // 加载资源文件
				.name("HelloWorld流程") // 流程名称
				.deploy(); // 部署
		System.out.println("流程部署ID:"+deployment.getId()); 
		System.out.println("流程部署Name:"+deployment.getName());
	}
	
	/**
	 * 启动流程实例
	 */
	@Test
	public void start(){
		ProcessInstance pi=processEngine.getRuntimeService() // 运行时Service
			.startProcessInstanceByKey("myFirstProcess"); // 流程定义表的KEY字段值
		System.out.println("流程实例ID:"+pi.getId());
		System.out.println("流程定义ID:"+pi.getProcessDefinitionId()); 
	}
	
	/**
	 * 查看任务
	 */
	@Test
	public void findTask(){
		List<Task> taskList=processEngine.getTaskService() // 任务相关Service
			.createTaskQuery() // 创建任务查询
			.taskAssignee("shibin") // 指定某个人
			.list();
		for(Task task:taskList){
			System.out.println("任务ID:"+task.getId()); 
			System.out.println("任务名称:"+task.getName());
			System.out.println("任务创建时间:"+task.getCreateTime());
			System.out.println("任务委派人:"+task.getAssignee());
			System.out.println("流程实例ID:"+task.getProcessInstanceId());
		}
	}
	
	/**
	 * 完成任务
	 */
	@Test
	public void completeTask(){
		processEngine.getTaskService() // 任务相关Service
			.complete("2504");
	}
}
