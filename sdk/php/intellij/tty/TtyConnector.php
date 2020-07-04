<?php

namespace php\intellij\tty;

abstract class TtyConnector {

    /**
     * @return void
     */
    abstract public function close();

    /**
     * @param array $termSize
     * @param array $pixelSize
     */
    abstract public function resize(array $termSize, array $pixelSize);

    /**
     * @return string
     */
    abstract public function getName(): string;

    /**
     * @param string $buf
     * @param int $offset
     * @param int $length
     * @return int
     */
    abstract public function read(string $buf, int $offset, int $length): int;

    /**
     * @param string $buf
     */
    abstract public function write(string $buf);

    /**
     * @return bool
     */
    abstract public function isConnected(): bool;

    /**
     * @return int
     */
    abstract public function waitFor(): int;
}
