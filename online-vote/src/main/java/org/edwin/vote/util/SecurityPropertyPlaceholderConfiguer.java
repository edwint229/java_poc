package org.edwin.vote.util;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class SecurityPropertyPlaceholderConfiguer extends PropertyPlaceholderConfigurer {
	private static final String DEFAULT_SECURITY_KEY = "4UKvr01xOxZhBaJUk88dxA==";
	private String securityKey;
	private List<String> keys;

	public String getSecurityKey() {
		return securityKey;
	}

	public void setSecurityKey(String securityKey) {
		this.securityKey = securityKey;
	}

	public List<String> getKeys() {
		return keys;
	}

	public void setKeys(List<String> keys) {
		this.keys = keys;
	}

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
		try {
			this.decryptKeys(props);
		} catch (Exception e) {
			logger.fatal("decrypt properties failed", e);
		}
		super.processProperties(beanFactoryToProcess, props);
	}

	private void decryptKeys(Properties props) throws Exception {
		securityKey = null == securityKey ? DEFAULT_SECURITY_KEY : securityKey;
		for (String key : keys) {
			String encrypt = (String) props.get(key);
			if (null != encrypt) {
				props.setProperty(key, this.decrypt(encrypt));
			}
		}
	}

	private String decrypt(String encrypt) throws Exception {
		return SecurityUtil.decrypt(encrypt, securityKey);
	}

}
