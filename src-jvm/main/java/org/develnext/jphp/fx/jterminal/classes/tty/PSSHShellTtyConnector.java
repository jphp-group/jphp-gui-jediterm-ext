package org.develnext.jphp.fx.jterminal.classes.tty;

import com.jcraft.jsch.Session;
import org.develnext.jphp.fx.jterminal.JediTermGuiExtension;
import org.develnext.jphp.fx.jterminal.support.ssh.JSchShellTtyConnector;
import php.runtime.annotation.Reflection;
import php.runtime.env.Environment;
import php.runtime.reflection.ClassEntity;

@Reflection.Name("SSHShellTtyConnector")
@Reflection.Namespace(JediTermGuiExtension.NS_SSH)
public class PSSHShellTtyConnector extends PTtyConnector<JSchShellTtyConnector> {
    public PSSHShellTtyConnector(Environment env, JSchShellTtyConnector wrappedObject) {
        super(env, wrappedObject);
    }

    public PSSHShellTtyConnector(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }

    @Reflection.Signature
    public void __construct(Session session) {
        __wrappedObject = new JSchShellTtyConnector(session);
    }
}
