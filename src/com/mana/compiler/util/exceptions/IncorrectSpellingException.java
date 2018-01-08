/*
 * The MIT License
 *
 * Copyright 2018 Mana Technologies.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.mana.compiler.util.exceptions;

/**
 *
 * @author Mana Technologies
 * @version alpha 0.0.0.1
 */
public class IncorrectSpellingException extends Exception {

    /**
     * Creates a new instance of <code>IncorrectSpellingException</code> without
     * detail message.
     */
    public IncorrectSpellingException() {
    }

    /**
     * Constructs an instance of <code>IncorrectSpellingException</code> with
     * the specified detail message.
     *
     * @param type The type of incorrect spelling.
     * @param word The misspelled word.
     * @param fileName The name of the file this comes from.
     * @param line The line this is in.
     * @param column The column this is in.
     */
    public IncorrectSpellingException(String type, String word, String fileName, int line, int column) {
        super(type + " \"" + word + "\" is spelled incorrectly at " + fileName + "#" + line + ":" + column + ".");
    }
}
