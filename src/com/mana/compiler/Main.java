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
package com.mana.compiler;

import com.mana.compiler.parser.Parser;
import com.mana.compiler.util.handler.FlagHandler;
import com.mana.compiler.util.structures.ParseTree;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * The {@code Main} class is strictly responsible for starting up the compiler,
 * nothing else.
 * 
 * <p>
 * Part of it's job in starting up the compiler is to allocate Threads to begin
 * lexing and parsing documents passed into the compiler.
 * </p>
 * 
 * @author Mana Tecnologies
 * @version alpha 0.0.0.1
 */
class Main {
    /** The thread factory that will allow our program to run threads tasks. */
    static ExecutorService factory = Executors.newCachedThreadPool();
    
    /**
     * @param args The command line arguments.
     */
    public static void main(String[] args) throws InterruptedException {
        // begin by gathering all flags and files.
        final List<String> files = new ArrayList<>();
        final List<String> flags = new ArrayList<>();
        
        for (String arg : args) {
            if (arg.charAt((0)) == '-')
                flags.add(arg);
            else
                files.add(arg);
        }
        
        // once done send flags to be checked and raised.
        FlagHandler.raiseFlags(flags.toArray(new String[0]));
        
        // once done raising the run time flags then send out threads to run
        // the parsers.
        Collection<Callable<ParseTree>> tasks = new ArrayList<>();
        
        files.forEach((file) -> {
            tasks.add((Callable) () -> {
                return new Parser(file).parse();
            });
        });
        
        List<Future<ParseTree>> returns = factory.invokeAll(tasks);
    }
}
