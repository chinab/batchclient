package com.vicutu.commons.config;

import java.io.File;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.vicutu.commons.config.support.LicenseDescriptor;
import com.vicutu.commons.config.support.LicenseException;
import com.vicutu.commons.config.support.LicenseReader;
import com.vicutu.commons.config.support.NoSystemPathException;
import com.vicutu.commons.config.support.Path;
import com.vicutu.commons.crypto.DSACipher;
import com.vicutu.commons.lang.DateUtils;
import com.vicutu.commons.lang.FileUtils;
import com.vicutu.commons.logging.Logger;
import com.vicutu.commons.logging.LoggerFactory;
import com.vicutu.services.ServiceManager;

public class Application {

	private static String systemPath;

	private static LicenseDescriptor license;

	private static ApplicationContext applicationContext;

	private static final String LICENSE_FILE = "license.txt";

	private static Logger logger;

	private static boolean INIT_APPLICATION_CONTEXT = false;

	private static boolean INIT_LOG4J = false;

	private static boolean START_SERVICE = false;

	public static String SYSTEM_PROPERTY = "webapp.root";

	public static final String SYSTEM_PROPERTY_CTX_LOCATION = "resource/ctx/**/*.xml";

	private Application() {
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static void initialize() {
		/* load system path */
		if (systemPath == null) {
			systemPath = System.getProperty(SYSTEM_PROPERTY);
		}

		if (systemPath == null) {
			systemPath = Path.getSystemPath();
			System.setProperty(SYSTEM_PROPERTY, systemPath);
		} else {
			Path.setSystemPath(systemPath);
		}

		if (systemPath == null) {
			throw new NoSystemPathException();
		}

		System.out.println("load system path " + systemPath);
	}

	public static void initLog4j() {
		DOMConfigurator.configure(getConfigPath("log4j-config.xml"));
		INIT_LOG4J = true;
	}

	public static LicenseDescriptor getLicense() {
		return license;
	}

	public static String getSystemPath() {
		return systemPath;
	}

	public static void setSystemPath(String systemPath) {
		Application.systemPath = systemPath;
	}

	public static String getPath(String path) {
		return Path.getPath(path);
	}

	public static File getFile() {
		return new File(getSystemPath());
	}

	public static File getFile(String path) {
		if (path == null || path.equals("") || path.equals("/")) {
			return getFile();
		} else {
			return Path.getFile(path);
		}
	}

	public static String getConfigPath(String name) {
		if (name == null || name.equals("") || name.equals("/")) {
			return getPath("config");
		} else {
			return getPath("config/" + name);
		}
	}

	public static File getConfigFile(String name) {
		if (name == null || name.equals("") || name.equals("/")) {
			return getFile("config");
		} else {
			return getFile("config/" + name);
		}
	}

	public static void bootstrap() {
		bootstrap(true, false, true);
	}

	public static void bootstrap(boolean initLog4j, boolean validateLicense, boolean startService) {
		bootstrap(null, initLog4j, validateLicense, startService);
	}

	public static void bootstrap(ApplicationContext context, boolean initLog4j, boolean validateLicense,
			boolean startService) {
		if (initLog4j) {
			initLog4j();
		}

		if (validateLicense) {
			try {
				File licenseFile = Application.getConfigFile(LICENSE_FILE);
				if (!licenseFile.exists() || licenseFile.isDirectory()) {
					throw new LicenseException("cannot found license file : " + LICENSE_FILE);
				}

				license = LicenseReader
						.read(new String(Base64.decodeBase64(FileUtils.readFileToByteArray(licenseFile))));

				if (DSACipher.verify(license.getPublicKey(), license.getLicense(), license.getLicenseSignature())) {
					String expiresDate = license.getExpiresDate();
					if (expiresDate != null && !expiresDate.equals("")) {
						Date date = DateUtils.parseDate(expiresDate, DateUtils.DEFAULT_FORMAT_DATE);
						if (date.getTime() < System.currentTimeMillis()) {
							throw new LicenseException("The license for " + license.getProduct() + " is expired.");
						}
					}
				} else {
					throw new LicenseException("The license for " + license.getProduct() + " is illegal.");
				}
			} catch (LicenseException le) {
				logger.error(le);
				System.exit(0);
			} catch (Throwable ex) {
				logger.error(new LicenseException("license error", ex));
				System.exit(0);
			}
		}

		logger = LoggerFactory.getLogger(Application.class);

		if (context == null) {
			applicationContext = new FileSystemXmlApplicationContext(systemPath + SYSTEM_PROPERTY_CTX_LOCATION);
			INIT_APPLICATION_CONTEXT = true;
			logger.info("init application context with {}", applicationContext.getClass().getName());
		} else {
			applicationContext = context;
			logger.info("init application context with {}", context.getClass().getName());
		}

		if (startService) {
			START_SERVICE = true;
			logger.info("starting services...");
			try {
				ServiceManager serviceManager = (ServiceManager) getBean(ServiceManager.class);
				serviceManager.startAll();
			} catch (Exception e) {
				logger.error("occur error when start service", e);
			}
		}

		logger.info("The Application has been started...");
	}

	public static void shutdown() {
		if (START_SERVICE) {
			logger.info("shutting down services...");
			try {
				ServiceManager serviceManager = (ServiceManager) getBean(ServiceManager.class);
				serviceManager.stopAll();
			} catch (Exception e) {
				logger.error("occur error when stop service", e);
			}
		}

		if (INIT_APPLICATION_CONTEXT && applicationContext != null
				&& applicationContext instanceof ConfigurableApplicationContext) {
			((ConfigurableApplicationContext) applicationContext).close();
			logger.info("shut down application context...");
		}

		if (INIT_LOG4J) {
			LogManager.shutdown();
			System.out.println("shut down log4j...");
		}
		systemPath = null;
		Path.setSystemPath(null);
		System.out.println("The Application has been shut down...");
	}

	public static Object getBean(String beanId) {
		return getApplicationContext().getBean(beanId);
	}

	@SuppressWarnings("unchecked")
	public static Object getBean(Class clazz) {
		return getApplicationContext().getBean(clazz.getName());
	}
}
