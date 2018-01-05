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
package com.mana.compiler.util.handler;

import com.mana.compiler.util.exceptions.FlagDoesNotExistException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mana Technologies
 * @version alpha 0.0.0.1
 */
public class FlagHandler {
    /**
     * Represents the possible flags that can be given in by the command line.
     */
    public enum RuntimeFlag {
        /** Place holder flag. */
        flag("--flag");
        
        /** The command line equivalent to this flag. */
        String flagName;
        /** The flags mapped by their command line equivalents. */
        static final Map<String, RuntimeFlag> mappedFlags = new HashMap<>();
        
        /** Populates the mapped flags. */
        static {
            for (RuntimeFlag flag : values()) {
                mappedFlags.put(flag.flagName, flag);
            }
        }
        
        /**
         * @param flagName The command line equivalent to this flag.
         */
        RuntimeFlag(String flagName) {
            this.flagName = flagName;
        }
        
        /**
         * Checks if the flags with this name exists.
         * 
         * @param name The command line representation of the flag.
         * @return {@code true} if the mapped flags contains the flag name.
         */
        public static boolean exists(String name) {
            return mappedFlags.containsKey(name);
        }
        
        /**
         * @param name The command line representation of the flag.
         * @return The flag with this name.
         */
        public static RuntimeFlag getFlag(String name) {
            return mappedFlags.get(name);
        }
    }
    
    /** A place to put all of our raised flags. */
    private static final EnumSet<RuntimeFlag> RAISEDFLAGS = EnumSet.noneOf(RuntimeFlag.class);
    
    /**
     * Adds a flag to the {@code raisedFlags} enum set if they exist.
     * 
     * @param flags The flags to raise.
     */
    public static void raiseFlags(String[] flags) {
        for (String flag : flags) {
            if (RuntimeFlag.exists(flag))
                RAISEDFLAGS.add(RuntimeFlag.getFlag(flag));
            else
                try {
                    throw new FlagDoesNotExistException(flag);
                } catch (FlagDoesNotExistException ex) {
                    Logger.getLogger(FlagHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
}
