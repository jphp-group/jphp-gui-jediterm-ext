package org.develnext.jphp.fx.jterminal.classes.tty;

import org.develnext.jphp.fx.jterminal.JediTermGuiExtension;
import org.develnext.jphp.fx.jterminal.support.UnImplTtyConnector;
import php.runtime.annotation.Reflection;
import php.runtime.env.Environment;
import php.runtime.reflection.ClassEntity;

@Reflection.Name("UnImplTtyConnector")
@Reflection.Namespace(JediTermGuiExtension.NS_TTY)
public class PUnImplTtyConnector extends PTtyConnector<UnImplTtyConnector> {
    public PUnImplTtyConnector(Environment env, UnImplTtyConnector wrappedObject) {
        super(env, wrappedObject);
    }

    public PUnImplTtyConnector(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }

    @Reflection.Signature
    public void __construct() {
        __wrappedObject = new UnImplTtyConnector();
    }
}
