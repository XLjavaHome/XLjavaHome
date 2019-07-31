/*
 * Copyright (C) 2014 hu
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.xl;

import cn.edu.hfut.dmic.webcollector.util.CharsetDetector;
import java.nio.charset.Charset;
import org.junit.Test;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @version 1.0 2019-07-31 16:48
 * To change this template use File | Settings | File Templates.
 * @date 2019-07-31
 * @time 16:48
 */
public class CharsetDetectorTest {
    @Test
    public void testGuessEncodingByMozilla() {
        String привет = CharsetDetector.guessEncodingByMozilla(encode("KOI8-R", "привет"));
        System.out.println(привет);
        System.out.println(CharsetDetector.guessEncodingByMozilla(encode("Windows-1251", "привет")));
        System.out.println(CharsetDetector.guessEncodingByMozilla(encode("ISO-8859-7", "Πάντ' ἀγαθὰ πράττω, ὦ φίλε.")));
        System.out.println(CharsetDetector.guessEncodingByMozilla(encode("Windows-1252", "hello")));
    }
    
    private byte[] encode(String charset, String text) {
        return Charset.forName(charset).encode(text).array();
    }
    
    @Test
    public void testGuessEncoding() {
        System.out.println(CharsetDetector.guessEncoding(encode("KOI8-R", "привет")));
        System.out.println(CharsetDetector.guessEncoding(encode("Windows-1251", "привет")));
        System.out.println(CharsetDetector.guessEncoding(encode("ISO-8859-7", "Πάντ' ἀγαθὰ πράττω, ὦ φίλε.")));
        System.out.println(CharsetDetector.guessEncoding(encode("Windows-1252", "hello")));
    }
}
