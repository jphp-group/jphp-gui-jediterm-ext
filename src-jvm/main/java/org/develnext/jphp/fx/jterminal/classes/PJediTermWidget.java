package org.develnext.jphp.fx.jterminal.classes;

import com.jediterm.terminal.TtyConnector;
import javafx.embed.swing.SwingNode;
import org.develnext.jphp.ext.javafx.classes.UXNode;
import org.develnext.jphp.fx.jterminal.JediTermGuiExtension;
import org.develnext.jphp.fx.jterminal.support.JediTermWidgetImpl;
import php.runtime.annotation.Reflection;
import php.runtime.env.Environment;
import php.runtime.lang.BaseWrapper;
import php.runtime.reflection.ClassEntity;

@Reflection.Name("JediTermWidget")
@Reflection.Namespace(JediTermGuiExtension.NS_UI)
public class PJediTermWidget extends BaseWrapper<JediTermWidgetImpl> {
    public PJediTermWidget(Environment env, JediTermWidgetImpl wrappedObject) {
        super(env, wrappedObject);
    }

    public PJediTermWidget(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }

    @Reflection.Signature
    public void __construct() {
        __construct(new PSettingsProvider(__env__));
    }

    @Reflection.Signature
    public void __construct(PSettingsProvider settingsProvider) {
        __wrappedObject = new JediTermWidgetImpl(settingsProvider);
    }

    @Reflection.Signature
    public void requestFocus() {
        getWrappedObject().requestFocus();
    }

    @Reflection.Signature
    public void requestFocusInWindow() {
        getWrappedObject().requestFocusInWindow();
    }

    @Reflection.Signature
    public void start() {
        getWrappedObject().start();
    }

    @Reflection.Signature
    public void stop() {
        getWrappedObject().stop();
    }

    @Reflection.Signature
    public void createTerminalSession(TtyConnector connector) {
        getWrappedObject().createTerminalSession(connector);
    }

    @Reflection.Signature
    public void writeString(String message) {
        getWrappedObject().getTerminal().writeUnwrappedString(message);
    }

    @Reflection.Signature
    public void nextLine() {
        getWrappedObject().getTerminal().nextLine();
    }

    @Reflection.Signature
    public UXNode<SwingNode> getFXNode() {
        SwingNode node = new SwingNode();
        node.setContent(getWrappedObject());

        return new UXNode<SwingNode>(__env__, node);
    }
}
