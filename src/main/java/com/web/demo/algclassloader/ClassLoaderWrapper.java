package com.web.demo.algclassloader;

public class ClassLoaderWrapper {

    private JarClassLoader driverJarClassLoader;

    private ClassLoaderWrapper() {
        this.driverJarClassLoader = new JarClassLoader(getClass().getClassLoader());
    }

    private static class InstanceHolder {
        static final ClassLoaderWrapper INSTANCE = new ClassLoaderWrapper();
    }

    public static ClassLoaderWrapper getClassLoaderWrapper() {
        return InstanceHolder.INSTANCE;
    }

    public JarClassLoader getDriverJarClassLoader() {
        return driverJarClassLoader;
    }


}
