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
package com.mana.compiler.parser;

import com.mana.compiler.util.structures.ParseTree;

/**
 * This class defines all the necessary combinators used in parsing, all a
 * parser needs to do is grab one of these and call parse on the tokens that
 * that parser holds.
 * 
 * @author Mana Technologies
 * @version alpha 0.0.0.1
 */
class StemParser {    
    static final ParserCombinator PLACEHOLDER = tokens -> {
        synchronized (tokens) {
            ParseTree parseTree = new ParseTree();
            // do work.
            return parseTree;
        }
    };
}
