<?php

namespace php\intellij\ui;


use php\gui\UXNode;
use php\intellij\pty\PtyProcess;

class JediTermWidget {

    /**
     * JediTermWidget constructor.
     * @param PtyProcess $process
     */
    public function __construct(PtyProcess $process = null) {
    }

    public function requestFocus() {
    }

    public function requestFocusInWindow() {
    }

    public function start() {
    }

    public function stop() {
    }

    /**
     * @param PtyProcess $process
     */
    public function createTerminalSession(PtyProcess $process) {
    }

    /**
     * @return UXNode
     */
    public function getFXNode(): UXNode {
    }
}