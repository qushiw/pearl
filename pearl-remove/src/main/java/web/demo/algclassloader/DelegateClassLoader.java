package web.demo.algclassloader;

import java.io.InputStream;
import java.net.URL;


public abstract class DelegateClassLoader {

    public abstract Class loadClass(String name, boolean resolve);

    public abstract URL getResource(String name);

    public abstract InputStream loadResourceStream(String name);


}
