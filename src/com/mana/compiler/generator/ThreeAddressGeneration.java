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

import com.mana.compiler.parser.Parser;
import com.mana.compiler.util.structures.ParseTree;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains an enum {@code GenerationType}; this enum is meant to be
 * used in accordance to this class's generation policy. It is meant to receive
 * the type of generation that it's supposed to return based on the type of
 * generation that it is told to do. So when sending generation requests from
 * the {@code IntermediateGeneration} class, make sure that the right request is
 * sent otherwise it will break the policy and the compiler will generate
 * incorrect assembly code.
 * 
 * @author Mana Technologies
 * @version alpha 0.0.0.1
 */
public class ThreeAddressGeneration {
    /***/
    enum GenerationType {
        placeholder, finished;
        // insert types here.
        
        public static final int length = values().length;
        public final int index = ordinal();
    }
    
    /** The string of assembly code generated by this class. */
    private String asm;
    /** The table used by the Generator to generate data. */
    private static final GenerationType[][] GENTABLE;
    
    static {
        GENTABLE = generationTable();
    }
    
    /** Default constructor. */
    public ThreeAddressGeneration() {}
    
    /**
     * Each parser state represents the possible node on the parse tree since
     * the nodes of the parse tree are equivalent to the non-terminals, and the
     * parser states are also equivalent to the non-terminals which is what we
     * base our generation off of.
     * 
     * @return The filled generation table.
     */
    private static GenerationType[][] generationTable() {
        GenerationType[][] result = new GenerationType[GenerationType.length][Parser.State.length];
        
        // TODO: fill table.
        
        return result;
    }
    
    /**
     * @param tree The tree being generated for.
     * @return The {@code ThreeAddressGeneration} for the type.
     */
    public ThreeAddressGeneration generateFor(ParseTree tree) {
        GenerationType type = GenerationType.placeholder;
        List<String> generatedASM = new ArrayList<>();
        
        while (type != GenerationType.finished) {
            switch (type) {
                case placeholder:
                    generatedASM.add(placeHolder(tree));
                    // DEFAULT FOR NOW.
                    type = GenerationType.finished;
                    break;
                default:
                    // throw error.
                    break;
            }
            
            // pick next generation state.
            // type = GENTABLE[type.index][tree.get(0).index];
        }
        
        return null;
    }
    
    /**
     * Place holder method. Would naturally take the tree and generate all
     * three address code before returning the singular three address generation.
     * 
     * @return nothing.
     */
    private String placeHolder(ParseTree tree) {
        return null;
    }
}
