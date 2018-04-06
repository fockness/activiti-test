package com.test.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

public class ActivitiTest {

	/**
	 * 第一课将了创建25张表和安装activiti的插件
	 */
	@Test
	public void testCreateTable() {
		// 引擎配置
		ProcessEngineConfiguration pec = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		pec.setJdbcDriver("com.mysql.jdbc.Driver");
		pec.setJdbcUrl("jdbc:mysql://localhost:3306/db_activiti");
		pec.setJdbcUsername("root");
		pec.setJdbcPassword("871255");

		/**
		 * false 不能自动创建表 create-drop 先删除表再创建表 true 自动创建和更新表
		 */
		pec.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

		// 获取流程引擎对象
		ProcessEngine processEngine = pec.buildProcessEngine();
	}

	@Test
	public void testCreateTableByConfigurationFile() {
		// 引擎配置
		ProcessEngineConfiguration pec = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
		// 获取流程引擎对象
		ProcessEngine processEngine = pec.buildProcessEngine();
	}
}
