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

import com.mana.compiler.lexer.Lexer;
import com.mana.compiler.util.structures.ParseTree;
import java.io.File;

/**
 *
 * @author Mana Technologies
 * @version alpha 0.0.0.1
 */
public class Parser {
    /** The file this Parser is parsing. */
    final File file; 
    
    /**
     * @param fileName The name of the file to parse.
     */
    public Parser(String fileName) {
        file = new File(fileName);
    }
    
    /**
     * Parses the file constructed by the constructor and returns the Parse Tree
     * equivalent to that file.
     * 
     * @return The parse tree equivalent to the file constructed by this parser.
     */
    public ParseTree parse() {
        Lexer lexer = new Lexer(file);
        lexer.lex();
        
        return null;
    }
}
