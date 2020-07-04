<?php

namespace php\intellij\ui;

use php\gui\UXNode;
use php\intellij\tty\TtyConnector;

class JediTermWidget {

    /**
     * JediTermWidget constructor.
     * @param SettingsProvider|null $settingsProvider
     */
    public function __construct(SettingsProvider $settingsProvider = null) {
        // Some java code ...
    }

    /**
     * @return void
     */
    public function requestFocus() {
        // Some java code ...
    }

    /**
     * @return void
     */
    public function requestFocusInWindow() {
        // Some java code ...
    }

    /**
     * @return void
     */
    public function start() {
        // Some java code ...
    }

    /**
     * @return void
     */
    public function stop() {
        // Some java code ...
    }

    /**
     * @param TtyConnector $connector
     */
    public function createTerminalSession(TtyConnector $connector) {
        // Some java code ...
    }

    /**
     * @return UXNode
     */
    public function getFXNode(): UXNode {
        // Some java code ...
    }

    /**
     * @param string $message
     */
    public function writeString(string $message) {
        // Some java code ...
    }

    /**
     * @return void
     */
    public function nextLine() {
        // Some java code ...
    }
}
