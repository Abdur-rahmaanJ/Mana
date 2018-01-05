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

import com.mana.compiler.grammar.Token;
import com.mana.compiler.grammar.Token.Type;
import com.mana.compiler.lexer.Lexer;
import com.mana.compiler.util.structures.ParseTree;
import java.io.File;
import java.util.List;

/**
 *
 * @author Mana Technologies
 * @version alpha 0.0.0.1
 */
public final class Parser {
    /***/
    public enum State {
        start, finished;
        
        public static int length = values().length;
        public int index = ordinal();
    }
    
    /** The file this Parser is parsing. */
    final File file; 
    /** The current state of this Parser. */
    State state = State.start;
    /** The parsing table. */
    static final State[][] PARSETABLE;
    
    // responsible for filling the parse table.
    static {
        PARSETABLE = fillParseTable();
    }
    
    /**
     * @param fileName The name of the file to parse.
     */
    public Parser(String fileName) {
        file = new File(fileName);
    }
    
    /**
     * @return The filled parse table.
     */
    static final State[][] fillParseTable() {
        State[][] result = new State[State.length][Type.length];
        
        // fill table here.
        
        return result;
    }
    
    /**
     * Parses the file constructed by the constructor and returns the Parse Tree
     * equivalent to that file.
     * 
     * @return The parse tree equivalent to the file constructed by this parser.
     */
    public final ParseTree parse() {
        ParseTree result = new ParseTree();
        
        Lexer lexer = new Lexer(file);
        List<Token> tokens = lexer.lex();
        
        while(state != State.finished) {
            switch (state) {
                case start:
                    // default for now.
                    state = State.finished;
                    break;
                default:
                    // throw error
                    break;
            }
            
            // pick new state.
            state = PARSETABLE[state.index][tokens.get(0).type().index];
        }
        
        return result;
    }
}
