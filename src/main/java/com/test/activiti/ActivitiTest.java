package com.test.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

public class ActivitiTest {

	/**
	 * ��һ�ν��˴���25�ű�Ͱ�װactiviti�Ĳ��
	 */
	@Test
	public void testCreateTable() {
		// ��������
		ProcessEngineConfiguration pec = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		pec.setJdbcDriver("com.mysql.jdbc.Driver");
		pec.setJdbcUrl("jdbc:mysql://localhost:3306/db_activiti");
		pec.setJdbcUsername("root");
		pec.setJdbcPassword("871255");

		/**
		 * false �����Զ������� create-drop ��ɾ�����ٴ����� true �Զ������͸��±�
		 */
		pec.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

		// ��ȡ�����������
		ProcessEngine processEngine = pec.buildProcessEngine();
	}

	@Test
	public void testCreateTableByConfigurationFile() {
		// ��������
		ProcessEngineConfiguration pec = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
		// ��ȡ�����������
		ProcessEngine processEngine = pec.buildProcessEngine();
	}
}
