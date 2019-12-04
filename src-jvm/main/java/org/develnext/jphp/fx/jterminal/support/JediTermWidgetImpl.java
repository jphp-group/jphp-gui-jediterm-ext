package org.develnext.jphp.fx.jterminal.support;

import com.formdev.flatlaf.ui.FlatScrollBarUI;
import com.jediterm.terminal.SubstringFinder;
import com.jediterm.terminal.ui.JediTermWidget;
import com.jediterm.terminal.ui.TerminalPanel;
import com.jediterm.terminal.ui.settings.SettingsProvider;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class JediTermWidgetImpl extends JediTermWidget {
    public JediTermWidgetImpl(@NotNull SettingsProvider settingsProvider) {
        super(settingsProvider);
    }

    public JediTermWidgetImpl(Dimension dimension, SettingsProvider settingsProvider) {
        super(dimension, settingsProvider);
    }

    public JediTermWidgetImpl(int columns, int lines, SettingsProvider settingsProvider) {
        super(columns, lines, settingsProvider);
    }

    @Override
    protected JScrollBar createScrollBar() {
        JScrollBar scrollBar = new JScrollBar();
        scrollBar.setUI(new ScrollBarUIImpl(scrollBar, myTerminalPanel, mySettingsProvider));
        return scrollBar;
    }

    public static class ScrollBarUIImpl extends FlatScrollBarUI {
        private JScrollBar bar;
        private TerminalPanel myTerminalPanel;
        private SettingsProvider mySettingsProvider;

        public ScrollBarUIImpl(JScrollBar bar, TerminalPanel myTerminalPanel, SettingsProvider mySettingsProvider) {
            this.bar = bar;
            this.myTerminalPanel = myTerminalPanel;
            this.mySettingsProvider = mySettingsProvider;
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            super.paint(g, c);

            SubstringFinder.FindResult result = myTerminalPanel.getFindResult();
            if (result != null) {
                int modelHeight = bar.getModel().getMaximum() - bar.getModel().getMinimum();
                int anchorHeight = Math.max(2, c.getHeight() / modelHeight);

                Color color = mySettingsProvider.getTerminalColorPalette()
                        .getColor(mySettingsProvider.getFoundPatternColor().getBackground());
                g.setColor(color);
                for (SubstringFinder.FindResult.FindItem r : result.getItems()) {
                    int where = c.getHeight() * r.getStart().y / modelHeight;
                    g.fillRect(c.getX(), c.getY() + where, c.getWidth(), anchorHeight);
                }
            }
        }
    }
}
