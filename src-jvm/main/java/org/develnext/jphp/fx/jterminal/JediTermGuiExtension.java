package org.develnext.jphp.fx.jterminal;

import com.jediterm.pty.PtyProcessTtyConnector;
import com.jediterm.terminal.TtyConnector;
import com.jediterm.terminal.ui.JediTermWidget;
import com.pty4j.PtyProcess;
import org.develnext.jphp.fx.jterminal.classes.PJediTermWidget;
import org.develnext.jphp.fx.jterminal.classes.PPtyProcess;
import org.develnext.jphp.fx.jterminal.classes.PSettingsProvider;
import org.develnext.jphp.fx.jterminal.classes.tty.*;
import org.develnext.jphp.fx.jterminal.operations.DimensionMemoryOperation;
import org.develnext.jphp.fx.jterminal.support.UnImplTtyConnector;
import org.develnext.jphp.fx.jterminal.support.ssh.JSchExecTtyConnector;
import org.develnext.jphp.fx.jterminal.support.ssh.JSchShellTtyConnector;
import php.runtime.env.CompileScope;
import php.runtime.ext.support.Extension;

public class JediTermGuiExtension extends Extension {
    public static final String NS = "php\\intellij";
    public static final String NS_TTY = "php\\intellij\\tty";
    public static final String NS_SSH = "php\\intellij\\tty\\ssh";
    public static final String NS_UI = "php\\intellij\\ui";

    @Override
    public Status getStatus() {
        return Status.EXPERIMENTAL;
    }
    
    @Override
    public void onRegister(CompileScope scope) {
        registerMemoryOperation(DimensionMemoryOperation.class);
        registerClass(scope, PSettingsProvider.class);
        registerWrapperClass(scope, JediTermWidget.class, PJediTermWidget.class);
        registerWrapperClass(scope, PtyProcess.class, PPtyProcess.class);
        registerWrapperClass(scope, TtyConnector.class, PTtyConnector.class);
        registerWrapperClass(scope, PtyProcessTtyConnector.class, PPtyProcessConnector.class);
        registerWrapperClass(scope, UnImplTtyConnector.class, PUnImplTtyConnector.class);
        registerWrapperClass(scope, JSchShellTtyConnector.class, PSSHShellTtyConnector.class);
        registerWrapperClass(scope, JSchExecTtyConnector.class, PSSHExecTtyConnector.class);
    }
}
