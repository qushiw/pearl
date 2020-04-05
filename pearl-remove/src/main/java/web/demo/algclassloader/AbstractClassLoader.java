package web.demo.algclassloader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;


public class AbstractClassLoader extends ClassLoader {

    protected DelegateClassLoader loader;

    protected AbstractClassLoader(ClassLoader parent) {
        super(parent);
    }

    protected AbstractClassLoader() {
        super();
    }

    public void setDelegateLoader(DelegateClassLoader loader) {
        this.loader = loader;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return loadClass(name, true);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if (name == null || name.trim().length() == 0) {
            return null;
        }
        Class clazz = loader.loadClass(name, resolve);
        if (clazz == null) {
            throw new ClassNotFoundException(name);
        }
        return clazz;
    }

    @Override
    public URL getResource(String name) {
        if (name == null || name.trim().length() == 0) {
            return null;
        }
        return loader.getResource(name);
    }

    @Override
    public Enumeration<URL> getResources(String name) throws IOException {
        return null;
    }

    @Override
    public InputStream getResourceAsStream(String name) {
        if (name == null || name.trim().length() == 0) {
            return null;
        }
        return loader.loadResourceStream(name);
    }

}
