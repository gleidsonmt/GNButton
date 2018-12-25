/*
 * Copyright (C) Gleidson Neves da Silveira
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gn.lab;

import com.sun.javafx.css.StyleConverterImpl;
import com.sun.javafx.css.converters.PaintConverter;
import javafx.css.ParsedValue;
import javafx.css.StyleConverter;
import javafx.scene.text.Font;

import java.util.logging.Logger;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  20/12/2018
 */
public class ButtonTypeConverter extends StyleConverterImpl<String, ButtonType> {

    private ButtonTypeConverter() {
    }

    public static StyleConverter<String, ButtonType> getInstance() {
        return ButtonTypeConverter.Holder.INSTANCE;
    }

    public ButtonType convert(ParsedValue<String, ButtonType> value, Font notUsedFont) {
        String string = (String) value.getValue();

        try {
            return ButtonType.valueOf(string.toUpperCase());
        } catch (NullPointerException | IllegalArgumentException var5) {
            Logger.getLogger(ButtonTypeConverter.class.getName()).info(String.format("Invalid button type value '%s'", string));
            return ButtonType.SWIPE;
        }
    }

    public String toString() {
        return "ButtonTypeConverter";
    }

    private static class Holder {
        static final ButtonTypeConverter INSTANCE = new ButtonTypeConverter();

        private Holder() {
            throw new IllegalAccessError("Holder class");
        }
    }
}
