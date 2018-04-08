package com.test.procdef;

import java.io.File;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class ProcessDefinitionTest {

	/**
	 * ���Ŀ�
	 * ��ȡĬ����������ʵ�������Զ���ȡactiviti.cfg.xml�ļ�
	 */
	private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

	/**
	 * ��ѯ���̶��� �������̶��弯�� ��Ӧ�� act_re_procdef
	 */
	@Test
	public void list() {
		// ͨ��key��ѯ
		String processDefinitionKey = "myFirstProcess";
		List<ProcessDefinition> pdList = processEngine.getRepositoryService() // ��ȡservice
				.createProcessDefinitionQuery() // �������̶����ѯ
				.processDefinitionKey(processDefinitionKey) // ͨ��key��ѯ
				.list(); // ����һ������
		for (ProcessDefinition pd : pdList) {
			System.out.println("ID_" + pd.getId());
			System.out.println("NAME_" + pd.getName());
			System.out.println("KEY_" + pd.getKey());
			System.out.println("VERSION_" + pd.getVersion());
			System.out.println("=========");
		}
	}

	/**
	 * ͨ��ID��ѯĳ�����̶���
	 */
	@Test
	public void getById() {
		// ͨ��id��ѯ
		String processDefinitionId = "myFirstProcess:2:7504";
		ProcessDefinition pd = processEngine.getRepositoryService() // ��ȡservice
				.createProcessDefinitionQuery() // �������̶����ѯ
				.processDefinitionId(processDefinitionId) // ͨ��id��ѯ
				.singleResult();

		System.out.println("ID_" + pd.getId());
		System.out.println("NAME_" + pd.getName());
		System.out.println("KEY_" + pd.getKey());
		System.out.println("VERSION_" + pd.getVersion());

	}

	/**
	 * ��act_re_procdef�У��������̲���id����Դ�ļ���������ѯ����ͼƬ
	 * 
	 * @throws Exception
	 */
	@Test
	public void getImageById() throws Exception {
		InputStream inputStream = processEngine.getRepositoryService() // ��ȡsevice
				.getResourceAsStream("7501", "helloWorld/HelloWorld.png");
		FileUtils.copyInputStreamToFile(inputStream, new File("C:\\Users\\Administrator\\Desktop\\helloWorld.png"));
	}

	/**
	 * ��ѯ���°汾�����̶���
	 * 
	 * @throws Exception
	 *             ��һ��������ͨ��Activiti�ӿ�����ȡ�������̶���Version������������̶���ļ��ϣ�
	 * 
	 *             �ڶ���������һ�������Map�� Map��key�����������̶����Key��Map��ֵ�������̶������
	 * 
	 *             �����������Ǳ�����һ���ļ��ϣ�put(key,value) ����Key��ͬ�����߻Ḳ��ǰ�ߣ�
	 * 
	 *             ���Ĳ������ǻ�ȡMap��values����������Ҫ�����°汾�����̶���ļ��ϣ�
	 */
	@Test
	public void listLastVersion() throws Exception {

		List<ProcessDefinition> listAll = processEngine.getRepositoryService() // ��ȡservice
				.createProcessDefinitionQuery() // �������̶����ѯ
				.orderByProcessDefinitionVersion().asc() // �������̶���汾����
				.list(); // ����һ������

		// ��������Map����ͬ��Key���������map��ֵ ���ߵ�ֵ�Ḳ��ǰ����ͬ��key��ֵ
		Map<String, ProcessDefinition> map = new LinkedHashMap<String, ProcessDefinition>();
		// �������ϣ�����key������ǰ���ֵ������֤���µ�key����ǰ�������ϵ�key��ֵ
		for (ProcessDefinition pd : listAll) {
			map.put(pd.getKey(), pd);
		}

		List<ProcessDefinition> pdList = new LinkedList<ProcessDefinition>(map.values());
		for (ProcessDefinition pd : pdList) {
			System.out.println("ID_" + pd.getId());
			System.out.println("NAME_" + pd.getName());
			System.out.println("KEY_" + pd.getKey());
			System.out.println("VERSION_" + pd.getVersion());
			System.out.println("=========");
		}
	}

	/**
	 * ɾ������key��ͬ�����̶���
	 * 
	 * @throws Exception
	 */
	@Test
	public void deleteByKey() throws Exception {
		String processDefinitionKey = "helloWorld2";
		List<ProcessDefinition> pdList = processEngine.getRepositoryService() // ��ȡservice
				.createProcessDefinitionQuery() // �������̶����ѯ
				.processDefinitionKey(processDefinitionKey) // ����key��ѯ
				.list(); // ����һ������
		for (ProcessDefinition pd : pdList) {
			processEngine.getRepositoryService().deleteDeployment(pd.getDeploymentId(), true);
		}
	}
	
	/**
	 * ���̵��޸ľ��൱�ڷ���һ���°汾���ɰ汾��Ϊ��ʹ�������Բ����޸�
	 */

}
