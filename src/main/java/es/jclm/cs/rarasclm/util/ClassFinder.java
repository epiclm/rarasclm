package es.jclm.cs.rarasclm.util;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.jclm.cs.rarasclm.entities.DatosAuxiliaresCacheados;

public class ClassFinder {

	static Log log = LogFactory.getLog(ClassFinder.class.getName());
	
    private static final char PKG_SEPARATOR = '.';

    private static final char DIR_SEPARATOR = '/';

    private static final String CLASS_FILE_SUFFIX = ".class";

    private static final String BAD_PACKAGE_ERROR = "Unable to get resources from path '%s'. Are you sure the package '%s' exists?";

    public static List<Class<?>> find(String scannedPackage) {
    	try {
		    	log.info("paquete: " + scannedPackage);
		        String scannedPath = scannedPackage.replace(PKG_SEPARATOR, DIR_SEPARATOR);
		        log.info("paquete dir: " + scannedPath);
		        URL scannedUrl = Thread.currentThread().getContextClassLoader().getResource(scannedPath);
		        log.info("scannedUrl: " + scannedPath);
		        if (scannedUrl == null) {
		            throw new IllegalArgumentException(String.format(BAD_PACKAGE_ERROR, scannedPath, scannedPackage));
		        }
		        File scannedDir = new File(URLDecoder.decode(scannedUrl.getFile(),"utf-8"));
		        log.info("scannedDir: " + scannedDir.getAbsolutePath());
		        List<Class<?>> classes = new ArrayList<Class<?>>();
		        for (File file : scannedDir.listFiles()) {
		        	log.info("Archivo scannedDir: " + file.getAbsolutePath());
		            classes.addAll(find(file, scannedPackage));
		        }
	        return classes;
    	} catch(Exception ex) {
    		log.error("Error: "+ex.getStackTrace().toString());
    		ex.printStackTrace();
    		return null;
    	}
    }

    private static List<Class<?>> find(File file, String scannedPackage) {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        String resource = scannedPackage + PKG_SEPARATOR + file.getName();
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                classes.addAll(find(child, resource));
            }
        } else if (resource.endsWith(CLASS_FILE_SUFFIX)) {
            int endIndex = resource.length() - CLASS_FILE_SUFFIX.length();
            String className = resource.substring(0, endIndex);
            try {
                classes.add(Class.forName(className));
            } catch (ClassNotFoundException ignore) {
            }
        }
        return classes;
    }

}