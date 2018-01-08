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
package com.mana.compiler.lexer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * This particular reader is tailored to be very resource light, and as fast as
 * it can be possibly made with the data saving technique.
 * 
 * <p>
 * We attempt to save as much data with this class as we can since we have the
 * issue that there will be so many threads running with large packages of data
 * that we don't want to cause blocking because there isn't enough data to
 * support them. We do not want to increase the amount of data that the JVM can
 * use because we want to try to make this compiler usable on systems with less
 * RAM. So we will attempt to utilize the 512MB that the default JVM uses, as
 * much as possible.
 * </p>
 * 
 * @author Mana Technologies
 * @version alpha 0.0.0.1
 */
public class SourceReader implements AutoCloseable {
    /**
     * The buffer size of our reader; equal to half of one integer in size or
     * 32KB - 1b to make it so it fits into a short for data conservation.
     */
    private static final short BUFFER_SIZE = 0x7fff;
    /** Tells if this reader has reached the end of the file. */
    private boolean eof;
    /** Our input stream to read from. */
    private final FileInputStream fisin;
    /** The channel used to maneuver around the file. */
    private final FileChannel channel;
    /** Our buffered array for our input. */
    private final byte[] buffer = new byte[BUFFER_SIZE];
    /** The amount of bytes we have read. */
    private short bytesRead;
    /** A pointer to the byte we will be reading. */
    private short pointer;
    
    /**
     * @param source The source file we will be reading.
     * @throws java.io.FileNotFoundException
     */
    public SourceReader(File source) throws FileNotFoundException {
        fisin = new FileInputStream(source);
        channel = fisin.getChannel();
    }

    /**
     * Converts the {@code FileInputStream.read()} to a byte to be put into the
     * buffer.
     * 
     * @throws IOException if {@code FileInputStream.read()} throws IOException.
     * @return The byte value of the {@code FileInputStream.read()}.
     */
    byte read() throws IOException {
        bytesRead++;
        return (byte)fisin.read();
    }
    
    /**
     * Reads an entire piece of input into the buffer based on the type of input.
     * 
     * @throws IOException
     * @return The string form of the input being read.
     */
    public String next() throws IOException {
        byte input;
        
        while (true) {
            // add the next peice of input into our buffer.
            input = buffer[pointer++] = read();
            
            if (Character.isLetter(input)) {
                while (Character.isLetterOrDigit(input)) {
                    input = buffer[pointer++] = read();
                }
            } else if (Character.isDigit(input)) {
                while (Character.isDigit(input)) {
                    input = buffer[pointer++] = read();
                    
                    switch (input) {
                        case 'x':
                        case 'X':
                        case 'b':
                        case 'B':
                        case 'u':
                        case 'U':
                        case 'L':
                        case '.':
                            continue;
                        default:
                            break;
                    }
                    
                    // If we didn't get an expected character.
                    break;
                }
            } else {
                while (!Character.isLetterOrDigit(input) && !space(input)) {
                    input = buffer[pointer++] = read();
                }
            }
            
            if (space(input) || input == -1) {
                // seek back one position to get the invalid character out of our
                // buffer.
                seek(-1);
                buffer[pointer--] = 0;
                
                if (input == -1) eof = true;
                
                // We finished finding what we were going to, time to finish up
                // and return what we found.
                break;
            }
        }
        
        // clear any un-overwritten bytes in the buffer.
        for (int i = pointer; i < buffer.length; i++)
            // if the buffer is already 0, don't change, otherwise set to 0.
            buffer[i] = (byte)((buffer[i] == 0) ? 0 : 0);
        
        // set pointer back to 0 and bytesRead back to 0.
        pointer = bytesRead = 0;
        
        // return the result.
        return new String(buffer);
    }
    
    /**
     * @param input The input to check.
     * @return {@code true} if the byte is a space character that we are looking
     *      for.
     */
    boolean space(byte input) {
        boolean isSpace = false;
        
        switch (input) {
            case ' ':
            case '\t':
            case '\n':
            case '\r':
                isSpace = true;
        }
        
        return isSpace;
    }
    
    /**
     * Allows us to seek a position in the file.
     * 
     * @param position The position away from where we are.
     * @throws IOException if the position is invalid.
     */
    void seek(int position) throws IOException {
        channel.position(channel.position() + position);
    }
    
    /**
     * Inherited from {@code AutoCloseable}.
     * Re-throws the {@code IOException} from {@code FileInputStream.close()} if
     * it happens to throw an error.
     * 
     * @throws Exception composed of a {@code IOException} if the
     *         {@code FileInputStream} has issues closing itself.
     * @see java.lang.AutoCloseable
     */
    @Override
    public void close() throws Exception {
        try {
            fisin.close();
        } catch (IOException ioe) {
            throw new Exception(ioe);
        }
    }
}
