<?php


namespace php\intellij\tty;


class PtyProcessConnector extends TtyConnector {

    /**
     * PtyProcessConnector constructor.
     * @param PtyProcess $process
     */
    public function __construct(PtyProcess $process) {
        // Some java code ...
    }

    /**
     * @return PtyProcess
     */
    public function getPtyProcess(): PtyProcess {
        // Some java code ...
    }

    /**
     * @return void
     */
    public function close() {
        // Some java code ...
    }

    /**
     * @param array $termSize
     * @param array $pixelSize
     */
    public function resize(array $termSize, array $pixelSize) {
        // Some java code ...
    }

    /**
     * @return string
     */
    public function getName(): string {
        // Some java code ...
    }

    /**
     * @param string $buf
     * @param int $offset
     * @param int $length
     * @return int
     */
    public function read(string $buf, int $offset, int $length): int {
        // Some java code ...
    }

    /**
     * @param string $buf
     */
    public function write(string $buf) {
        // Some java code ...
    }

    /**
     * @return bool
     */
    public function isConnected(): bool {
        // Some java code ...
    }

    /**
     * @return int
     */
    public function waitFor(): int {
        // Some java code ...
    }
}
