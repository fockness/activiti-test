package com.test.procdef;


import java.io.InputStream;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

public class DeployProcdef {

	/**
	 * �����ν��˴�zip�л�ȡ���̶���
	 * ��ȡĬ����������ʵ�������Զ���ȡactiviti.cfg.xml�ļ�
	 */
	private ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
	
	/**
	 * �������̶���
	 */
	@Test
	public void deployWithClassPath(){
		Deployment deployment=processEngine.getRepositoryService() // ��ȡ�������Service
				.createDeployment() // ��������
				.addClasspathResource("diagrams/HelloWorld.bpmn") // ������Դ�ļ�
				.addClasspathResource("diagrams/HelloWorld.png") // ������Դ�ļ�
				.name("HelloWorld����") // ��������
				.deploy(); // ����
		System.out.println("���̲���ID:"+deployment.getId()); 
		System.out.println("���̲���Name:"+deployment.getName());
	}

	/**
	 * �������̶���ʹ��zip��ʽ
	 */
	@Test
	public void deployWithZip(){
		InputStream inputStream=this.getClass() // ȡ�õ�ǰclass����
			.getClassLoader() // ��ȡ�������
			.getResourceAsStream("diagrams/helloWorld.zip"); // ��ȡָ���ļ���Դ��
		
		ZipInputStream zipInputStream=new ZipInputStream(inputStream); // ʵ����zip������
		Deployment deployment=processEngine.getRepositoryService() // ��ȡ�������Service
				.createDeployment() // ��������
				.addZipInputStream(zipInputStream) // ���zip������
				.name("HelloWorld����") // ��������
				.deploy(); // ����
		System.out.println("���̲���ID:"+deployment.getId()); 
		System.out.println("���̲���Name:"+deployment.getName());
	}
}
