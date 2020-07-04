<?php

namespace php\intellij\tty;

use php\io\File;
use php\io\Stream;
use php\lang\IllegalStateException;

abstract class PtyProcess {

    /**
     * @param string[] $command
     * @param string[] $env
     * @param File|null $workDir
     * @return PtyProcess
     */
    public static function exec(array $command, array $env = [], File $workDir = null): PtyProcess {

    }

    /**
     * Tests whether the subprocess represented by this {@code Process} is
     * alive.
     *
     * @return bool
     */
    public function isAlive(): bool
    {
    }

    /**
     * Causes the current thread to wait, if necessary, until the
     * process represented by this {@code Process} object has
     * terminated.
     *
     * @return int
     */
    public function waitFor(): int { }

    /**
     * Returns the exit value for the subprocess.
     *
     * @return int|null - null if process is working
     * @throws IllegalStateException
     */
    public function getExitValue(): int { return 0; }

    public function destroy() { }

    /**
     * Returns the input stream connected to the normal output of the
     * subprocess.  The stream obtains data piped from the standard
     * output of the process represented by this `Process` object.
     *
     * @return Stream
     * @throws IllegalStateException
     */
    public function getInput() { return Stream::of(''); }

    /**
     * Returns the output stream connected to the normal input of the
     * subprocess.  Output to the stream is piped into the standard
     * input of the process represented by this `Process` object.
     *
     * @return Stream
     * @throws IllegalStateException
     */
    public function getOutput() { return Stream::of(''); }

    /**
     * Returns the input stream connected to the error output of the
     * subprocess.  The stream obtains data piped from the error output
     * of the process represented by this `Process` object.
     *
     * @return Stream
     * @throws IllegalStateException
     */
    public function getError() { return Stream::of(''); }
}