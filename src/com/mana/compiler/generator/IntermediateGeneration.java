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
package com.mana.compiler.generator;

import com.mana.compiler.util.structures.ParseTree;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the intermediate representation of the source code as deemed by
 * the semantic analysis output, in the form of Syntax Directed Translation.
 * 
 * <p>
 * This class utilizes the {@code ThreeAddressGeneration} class to generate the
 * Assembly representation of the SDT formed by the SemanticAnalyzer. The code
 * that is produced by this should be sent to an assembler to be processed and
 * further compiled.
 * </p>
 * 
 * @author Mana Technologies
 * @version alpha 0.0.0.1
 */

// NOTE: The process of generation can be Threaded, please make use of it.
public class IntermediateGeneration {
    /** The output of the generate() method. */
    private static final List<ThreeAddressGeneration> TAC = new ArrayList<>();
    
    /**
     * @param trees The trees to generate code for.
     */
    public IntermediateGeneration(List<ParseTree> trees) {
        // NOTE: in the future run this in threads.
        trees.forEach((tree) -> {
            TAC.add(generate(tree));
        });
    }
    
    /**
     * @param tree The tree that will have code generated for.
     */
    private static ThreeAddressGeneration generate(ParseTree tree) {
        // using the data of the parse tree and the listed data that the
        // ThreeAddressGeneration class has for the tree data, generate the
        // equivalent ThreeAddressGeneration of that class and return it.
        
        return new ThreeAddressGeneration().generateFor(tree);
    }
}
