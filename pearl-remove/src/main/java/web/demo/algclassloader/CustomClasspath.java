package web.demo.algclassloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;


public class CustomClasspath {

    private static final Logger logger = LoggerFactory.getLogger(CustomClasspath.class);

    private Map<String, JarElement> jarElements;

    public CustomClasspath() {
        this.jarElements = new HashMap<String, JarElement>();
    }

    public void loadJarElements(String clusterName, String resourceType, byte[] resource) throws Exception {
        if (StringUtils.isEmpty(resourceType) || resource == null || resource.length == 0) {
            logger.info("clusterName:{} 算法包加载失败,资源类型或者资源为空.", clusterName);
            return;
        }
        if (resourceType.toLowerCase().equals("jar")) {
            InputStream in = null;
            try {
                in = new ByteArrayInputStream(resource);
                loadJarElements(in);
            }
            catch (IOException e) {
                throw e;
            }
            finally {
                if (in != null) {
                    in.close();
                }
            }
        }
        else if (resourceType.toLowerCase().equals("class")){
            throw new UnsupportedOperationException("暂时不支持");
        }
    }

    public byte[] getResourceInByteArray(String name) {
        JarElement jarElement = jarElements.get(name);
        if (jarElement != null) {
            return jarElement.getResourceBytes();
        }
        else { //resource not yet been added; will not try to do anything
            return null;
        }
    }

    public URL getResourceURL(String name) throws MalformedURLException {
        JarElement jarElement = jarElements.get(name);
        if (jarElement != null) {
            if (jarElement.getBaseUrl() == null) {
                return null;
            }
            try {
                return new URL(jarElement.getBaseUrl() + name);
            }
            catch (MalformedURLException e) {
                throw e;
            }
        }
        return null;
    }

    private void loadJarElements(InputStream jarStream) throws IOException {
        BufferedInputStream bis = null;
        JarInputStream jis = null;
        try {
            bis = new BufferedInputStream(jarStream);
            jis = new JarInputStream(bis);
            JarEntry jarEntry;
            while ((jarEntry = jis.getNextJarEntry()) != null) {
                if (jarEntry.isDirectory()) {
                    continue;
                }
                if (jarElements.get(jarEntry.getName()) != null) { //already loaded
                    continue;
                }
                ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
                byte[] b = new byte[2048];
                int readLen;
                while ((readLen = jis.read(b)) > 0) {
                    byteOut.write(b, 0, readLen);
                }
                JarElement jarElement = new JarElement();
//                jarElement.setBaseUrl(baseUrl);
                jarElement.setResourceBytes(byteOut.toByteArray());
                jarElements.put(jarEntry.getName(), jarElement);
                byteOut.close();
            }
        }
        catch (IOException e) {
            throw e;
        }
        finally {
            if (jis != null) {
                try {
                    jis.close();
                }
                catch (IOException e) {
                    throw e;
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                }
                catch (IOException e) {
                    throw e;
                }
            }
        }
    }
}
