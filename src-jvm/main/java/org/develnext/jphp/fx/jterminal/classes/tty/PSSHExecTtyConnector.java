package org.develnext.jphp.fx.jterminal.classes.tty;

import com.jcraft.jsch.Session;
import org.develnext.jphp.fx.jterminal.JediTermGuiExtension;
import org.develnext.jphp.fx.jterminal.support.ssh.JSchExecTtyConnector;
import php.runtime.annotation.Reflection;
import php.runtime.env.Environment;
import php.runtime.reflection.ClassEntity;

@Reflection.Name("SSHExecTtyConnector")
@Reflection.Namespace(JediTermGuiExtension.NS_SSH)
public class PSSHExecTtyConnector extends PTtyConnector<JSchExecTtyConnector> {
    public PSSHExecTtyConnector(Environment env, JSchExecTtyConnector wrappedObject) {
        super(env, wrappedObject);
    }

    public PSSHExecTtyConnector(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }

    @Reflection.Signature
    public void __construct(Session session, String command) {
        __wrappedObject = new JSchExecTtyConnector(session, command);
    }
}
