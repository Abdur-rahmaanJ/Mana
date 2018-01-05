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

import com.mana.compiler.generator.IntermediateGeneration;
import com.mana.compiler.util.structures.ParseTree;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Responsible for checking the parse trees provided by the parsing process.
 * 
 * <p>
 * It takes each parse tree from each file and attempts to join them together
 * by keeping track of data types, variable declarations, and method
 * declarations.
 * </p>
 * 
 * <p>
 * The semantic analyzer is only responsible for checking the parse trees. It
 * must sent those parse trees to the {@code IntermediateGeneration} class to
 * receive the full three address code representation of the parse trees.
 * </p>
 * 
 * @author Mana Technologies
 * @version alpha 0.0.0.1
 */

// NOTE: The check() method has processes that can be threaded.
public class SemanticAnalyzer {
    /// PLACEHOLDER
    public static IntermediateGeneration check(List<Future<ParseTree>> returns) {
        List<ParseTree> parseTrees = new ArrayList<>();
        
        // collect each parse tree from returns, put check them and then add
        // them to parseTrees and return the IntermediateGeneration of those
        // parse trees.
        
        // NOTE: The process of checking the parse trees can be threaded, please
        //      do this in the future.
        
        return new IntermediateGeneration(parseTrees);
    }
}
