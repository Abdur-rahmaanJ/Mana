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
package com.mana.compiler.grammar;

/**
 * A singular piece of the input, made into machine understandable data.
 * 
 * @author Mana Technologies
 * @version alpha 0.0.0.1
 */
public class Token {
    /**
     * Represents the types of possible tokens.
     */
    public enum Type {
        type;
        
        public static int length = values().length;
        public int index = ordinal();
    }
    
    /** The type of this token. */
    final Type type;
    /** The value of this token. */
    final String lexeme,
    /** The name of the file this token comes from. */             
                 fileName;
    /** The line in the file of which this token was found. */
    final int line,
    /** The column in the line of which this tokens was found. */
              column;
    
    /**
     * @param type The type of this token.
     * @param lexeme The value of this token.
     * @param fileName The name of the file this token was found in.
     * @param line The line of the file in which this token was found.
     * @param column The column of the line in which this token was found.
     */
    public Token (Type type, String lexeme, String fileName, int line, int column) {
        this.type = type;
        this.lexeme = lexeme;
        this.fileName = fileName;
        this.line = line;
        this.column = column;
    }
    
    /**
     * @return The type of this token.
     */
    public Type type() { return type; }
    
    /**
     * @return The lexeme of this token.
     */
    public String lexeme() { return lexeme; }
    
    /**
     * @return The name of the file this token was found in.
     */
    public String fileName() { return fileName; }
    
    /**
     * @return The line in which this token was found.
     */
    public int line() { return line; }
    
    /**
     * @return The column in which this token was found.
     */
    public int column() { return column; }
}
