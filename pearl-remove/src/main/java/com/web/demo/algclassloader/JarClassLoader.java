package com.web.demo.algclassloader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class JarClassLoader extends AbstractClassLoader {

    private Map<String, Class> classes;
    private CustomClasspath customClasspath;

    public JarClassLoader() {
        super();
        this.classes = new ConcurrentHashMap<String, Class>();
        customClasspath = new CustomClasspath();
        setDelegateLoader(new AlgJarClassLoader());
    }
    public JarClassLoader(ClassLoader parent) {
        super(parent);
        this.classes = new ConcurrentHashMap<String, Class>();
        customClasspath = new CustomClasspath();
        setDelegateLoader(new AlgJarClassLoader());
    }

    public void add(String clusterName, String resourceType, byte[] resource) throws Exception {
        customClasspath.loadJarElements(clusterName, resourceType, resource);
    }

    private byte[] loadClassBytes(String name) {
        return customClasspath.getResourceInByteArray(name.replace('.', '/') + ".class");
    }

    public void setCustomClasspath(CustomClasspath customClasspath) {
        this.customClasspath = customClasspath;
    }

    class AlgJarClassLoader extends DelegateClassLoader {
        @Override
        public Class loadClass(String name, boolean resolve) {
            Class result = classes.get(name);
            if (result != null && !name.startsWith("com.jdjr.cds.driver")) {
                return result;
            }

            if (result != null) {
                //重新加载
                byte[] classBytes = loadClassBytes(name);
                if (classBytes == null) {
                    return result;
                } else {
                    JarClassLoader newLoader = new JarClassLoader(getClass().getClassLoader());
                    newLoader.setCustomClasspath(customClasspath);
                    try {
                        result = newLoader.loadClass(name);
                    }
                    catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        return null;
                    }
                    if (result == null) {
                        return null;
                    }
                    if (resolve) {
                        resolveClass(result);
                    }
                    classes.put(name, result);
                    return result;
                }
            } else {
                byte[] classBytes = loadClassBytes(name);
                if (classBytes == null) {
                    try {
                        result = getParent().loadClass(name);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        return null;
                    }
                    classes.put(name, result);
                    return result;
                }
                else {
                    result = defineClass(name, classBytes, 0, classBytes.length);
                }
                if (result == null) {
                    return null;
                }
                if (resolve) {
                    resolveClass(result);
                }
                classes.put(name, result);
                return result;
            }
        }

        @Override
        public URL getResource(String name) {
            try {
                return customClasspath.getResourceURL(name);
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public InputStream loadResourceStream(String name) {
            byte[] resourceBytes = customClasspath.getResourceInByteArray(name);
            if (resourceBytes != null) {
                return new ByteArrayInputStream(resourceBytes);
            }
            return null;
        }
    }
}
