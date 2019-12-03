package org.develnext.jphp.fx.jterminal.classes;

import com.jediterm.terminal.HyperlinkStyle;
import com.jediterm.terminal.TerminalColor;
import com.jediterm.terminal.TextStyle;
import com.jediterm.terminal.emulator.ColorPalette;
import com.jediterm.terminal.model.LinesBuffer;
import com.jediterm.terminal.ui.UIUtil;
import com.jediterm.terminal.ui.settings.SettingsProvider;
import org.develnext.jphp.ext.image.classes.PColor;
import org.develnext.jphp.fx.jterminal.JediTermGuiExtension;
import php.runtime.Memory;
import php.runtime.annotation.Reflection;
import php.runtime.env.Environment;
import php.runtime.lang.BaseObject;
import php.runtime.memory.ArrayMemory;
import php.runtime.reflection.ClassEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Reflection.Name("SettingsProvider")
@Reflection.Namespace(JediTermGuiExtension.NS_UI)
public class PSettingsProvider extends BaseObject implements SettingsProvider {
    private Map<String, Memory> mySettings;

    public PSettingsProvider(Environment env) {
        super(env);
    }

    protected PSettingsProvider(ClassEntity entity) {
        super(entity);
    }

    public PSettingsProvider(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }

    @Reflection.Signature
    public void put(String key, Memory value) {
        mySettings.put(key, value);
    }

    @Override
    public KeyStroke[] getNewSessionKeyStrokes() {
        return new KeyStroke[]{UIUtil.isMac
                ? KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.META_DOWN_MASK)
                : KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK)};
    }

    @Override
    public KeyStroke[] getCloseSessionKeyStrokes() {
        return new KeyStroke[]{UIUtil.isMac
                ? KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.META_DOWN_MASK)
                : KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK)};
    }

    @Override
    public KeyStroke[] getCopyKeyStrokes() {
        return new KeyStroke[]{UIUtil.isMac
                ? KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.META_DOWN_MASK)
                // CTRL + C is used for signal; use CTRL + SHIFT + C instead
                : KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK)};
    }

    @Override
    public KeyStroke[] getPasteKeyStrokes() {
        return new KeyStroke[]{UIUtil.isMac
                ? KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.META_DOWN_MASK)
                // CTRL + V is used for signal; use CTRL + SHIFT + V instead
                : KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK)};
    }

    @Override
    public KeyStroke[] getClearBufferKeyStrokes() {
        return new KeyStroke[]{UIUtil.isMac
                ? KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.META_DOWN_MASK)
                : KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK)};
    }

    @Override
    public KeyStroke[] getFindKeyStrokes() {
        return new KeyStroke[]{UIUtil.isMac
                ? KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.META_DOWN_MASK)
                : KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK)};
    }

    @Override
    public KeyStroke[] getPageUpKeyStrokes() {
        return new KeyStroke[]{KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP, InputEvent.SHIFT_DOWN_MASK)};
    }

    @Override
    public KeyStroke[] getPageDownKeyStrokes() {
        return new KeyStroke[]{KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN, InputEvent.SHIFT_DOWN_MASK)};
    }

    @Override
    public ColorPalette getTerminalColorPalette() {
        if (mySettings.containsKey("colorPalette")) {
            List<Color> list = new ArrayList<>();

            mySettings.get("colorPalette")
                    .toValue(ArrayMemory.class)
                    .forEach(referenceMemory -> list.add(fromMemory(referenceMemory)));

            return new ColorPalette() {
                @Override
                public Color[] getIndexColors() {
                    return (Color[]) list.toArray();
                }
            };
        }

        return UIUtil.isWindows ? ColorPalette.WINDOWS_PALETTE : ColorPalette.XTERM_PALETTE;
    }

    @Override
    public Font getTerminalFont() {
        String fontName;
        if (mySettings.containsKey("font")) {
            fontName = mySettings.get("font").toString();
        } else if (UIUtil.isWindows) {
            fontName = "Consolas";
        } else if (UIUtil.isMac) {
            fontName = "Menlo";
        } else {
            fontName = "Monospaced";
        }
        return new Font(fontName, Font.PLAIN, (int)getTerminalFontSize());
    }

    @Override
    public float getTerminalFontSize() {
        if (mySettings.containsKey("fontSize"))
            return mySettings.get("fontSize").toFloat();

        return 14;
    }

    @Override
    public float getLineSpace() {
        if (mySettings.containsKey("lineSpace"))
            return mySettings.get("lineSpace").toFloat();

        return 0;
    }

    @Override
    public TextStyle getDefaultStyle() {
        TerminalColor foreground = TerminalColor.BLACK;
        TerminalColor background = TerminalColor.WHITE;

        if (mySettings.containsKey("defaultStyleForeground"))
            foreground = TerminalColor.awt(fromMemory(mySettings.get("defaultStyleForeground")));

        if (mySettings.containsKey("defaultStyleBackground"))
            foreground = TerminalColor.awt(fromMemory(mySettings.get("defaultStyleBackground")));

        return new TextStyle(foreground, background);
    }

    @Override
    public TextStyle getSelectionColor() {
        TerminalColor foreground = TerminalColor.WHITE;
        TerminalColor background = TerminalColor.rgb(82, 109, 165);

        if (mySettings.containsKey("selectionColorForeground"))
            foreground = TerminalColor.awt(fromMemory(mySettings.get("selectionColorForeground")));

        if (mySettings.containsKey("selectionColorBackground"))
            foreground = TerminalColor.awt(fromMemory(mySettings.get("selectionColorBackground")));

        return new TextStyle(foreground, background);
    }

    @Override
    public TextStyle getFoundPatternColor() {
        TerminalColor foreground = TerminalColor.BLACK;
        TerminalColor background = TerminalColor.rgb(255, 255, 0);

        if (mySettings.containsKey("foundPatternColorForeground"))
            foreground = TerminalColor.awt(fromMemory(mySettings.get("foundPatternColorForeground")));

        if (mySettings.containsKey("foundPatternColorBackground"))
            foreground = TerminalColor.awt(fromMemory(mySettings.get("foundPatternColorBackground")));

        return new TextStyle(foreground, background);
    }

    @Override
    public TextStyle getHyperlinkColor() {
        TerminalColor foreground = TerminalColor.awt(Color.BLUE);
        TerminalColor background = TerminalColor.WHITE;

        if (mySettings.containsKey("hyperlinkColorForeground"))
            foreground = TerminalColor.awt(fromMemory(mySettings.get("hyperlinkColorForeground")));

        if (mySettings.containsKey("hyperlinkColorBackground"))
            foreground = TerminalColor.awt(fromMemory(mySettings.get("hyperlinkColorBackground")));

        return new TextStyle(foreground, background);
    }

    @Override
    public HyperlinkStyle.HighlightMode getHyperlinkHighlightingMode() {
        return HyperlinkStyle.HighlightMode.ALWAYS;
    }

    @Override
    public boolean useInverseSelectionColor() {
        return true;
    }

    @Override
    public boolean copyOnSelect() {
        return emulateX11CopyPaste();
    }

    @Override
    public boolean pasteOnMiddleMouseClick() {
        return emulateX11CopyPaste();
    }

    @Override
    public boolean emulateX11CopyPaste() {
        return false;
    }

    @Override
    public boolean useAntialiasing() {
        return true;
    }

    @Override
    public int maxRefreshRate() {
        return 50;
    }

    @Override
    public boolean audibleBell() {
        return true;
    }

    @Override
    public boolean enableMouseReporting() {
        return true;
    }

    @Override
    public int caretBlinkingMs() {
        return 505;
    }

    @Override
    public boolean scrollToBottomOnTyping() {
        return true;
    }

    @Override
    public boolean DECCompatibilityMode() {
        return true;
    }

    @Override
    public boolean forceActionOnMouseReporting() {
        return false;
    }

    @Override
    public int getBufferMaxLinesCount() {
        return LinesBuffer.DEFAULT_MAX_LINES_COUNT;
    }

    @Override
    public boolean altSendsEscape() {
        return false;
    }

    @Override
    public boolean ambiguousCharsAreDoubleWidth() {
        return false;
    }

    private Color fromMemory(Memory memory) {
        PColor c = memory.toObject(PColor.class);
        return new Color((float) c.getRed(),
                (float) c.getGreen(),
                (float) c.getBlue(),
                (float) c.getAlpha());
    }
}
