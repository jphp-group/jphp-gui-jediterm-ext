package org.develnext.jphp.fx.jterminal;

import com.jediterm.terminal.ui.JediTermWidget;
import com.pty4j.PtyProcess;
import org.develnext.jphp.fx.jterminal.classes.PJediTermWidget;
import org.develnext.jphp.fx.jterminal.classes.PPtyProcess;
import org.develnext.jphp.fx.jterminal.classes.PSettingsProvider;
import php.runtime.env.CompileScope;
import php.runtime.ext.support.Extension;

public class JediTermGuiExtension extends Extension {
    public static final String NS = "php\\intellij";
    public static final String NS_PTY = "php\\intellij\\pty";
    public static final String NS_UI = "php\\intellij\\ui";

    @Override
    public Status getStatus() {
        return Status.EXPERIMENTAL;
    }
    
    @Override
    public void onRegister(CompileScope scope) {
        registerClass(scope, PSettingsProvider.class);
        registerWrapperClass(scope, JediTermWidget.class, PJediTermWidget.class);
        registerWrapperClass(scope, PtyProcess.class, PPtyProcess.class);
    }
}