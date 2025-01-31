/*
 * The MIT License (MIT)
 *
 * Copyright (c) waicool20
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

package com.waicool20.cvauto.android.input

import java.awt.event.KeyEvent
import java.util.*

/**
 * Represents a key, see
 * [Linux Input Events](https://github.com/torvalds/linux/blob/master/include/uapi/linux/input-event-codes.h)
 * for more information.
 *
 */
enum class Key(val code: Long) {
    //<editor-fold desc="Enums">
    KEY_UNKNOWN(0),
    KEY_SOFT_LEFT(1),
    KEY_SOFT_RIGHT(2),
    KEY_HOME(3),
    KEY_BACK(4),
    KEY_CALL(5),
    KEY_ENDCALL(6),
    KEY_0(7),
    KEY_1(8),
    KEY_2(9),
    KEY_3(10),
    KEY_4(11),
    KEY_5(12),
    KEY_6(13),
    KEY_7(14),
    KEY_8(15),
    KEY_9(16),
    KEY_STAR(17),
    KEY_POUND(18),
    KEY_DPAD_UP(19),
    KEY_DPAD_DOWN(20),
    KEY_DPAD_LEFT(21),
    KEY_DPAD_RIGHT(22),
    KEY_DPAD_CENTER(23),
    KEY_VOLUME_UP(24),
    KEY_VOLUME_DOWN(25),
    KEY_POWER(26),
    KEY_CAMERA(27),
    KEY_CLEAR(28),
    KEY_A(29),
    KEY_B(30),
    KEY_C(31),
    KEY_D(32),
    KEY_E(33),
    KEY_F(34),
    KEY_G(35),
    KEY_H(36),
    KEY_I(37),
    KEY_J(38),
    KEY_K(39),
    KEY_L(40),
    KEY_M(41),
    KEY_N(42),
    KEY_O(43),
    KEY_P(44),
    KEY_Q(45),
    KEY_R(46),
    KEY_S(47),
    KEY_T(48),
    KEY_U(49),
    KEY_V(50),
    KEY_W(51),
    KEY_X(52),
    KEY_Y(53),
    KEY_Z(54),
    KEY_COMMA(55),
    KEY_PERIOD(56),
    KEY_ALT_LEFT(57),
    KEY_ALT_RIGHT(58),
    KEY_SHIFT_LEFT(59),
    KEY_SHIFT_RIGHT(60),
    KEY_TAB(61),
    KEY_SPACE(62),
    KEY_SYM(63),
    KEY_EXPLORER(64),
    KEY_ENVELOPE(65),
    KEY_ENTER(66),
    KEY_DEL(67),
    KEY_GRAVE(68),
    KEY_MINUS(69),
    KEY_EQUALS(70),
    KEY_LEFT_BRACKET(71),
    KEY_RIGHT_BRACKET(72),
    KEY_BACKSLASH(73),
    KEY_SEMICOLON(74),
    KEY_APOSTROPHE(75),
    KEY_SLASH(76),
    KEY_AT(77),
    KEY_NUM(78),
    KEY_HEADSETHOOK(79),
    KEY_FOCUS(80),
    KEY_PLUS(81),
    KEY_MENU(82),
    KEY_NOTIFICATION(83),
    KEY_SEARCH(84),
    KEY_MEDIA_PLAY_PAUSE(85),
    KEY_MEDIA_STOP(86),
    KEY_MEDIA_NEXT(87),
    KEY_MEDIA_PREVIOUS(88),
    KEY_MEDIA_REWIND(89),
    KEY_MEDIA_FAST_FORWARD(90),
    KEY_MUTE(91),
    KEY_PAGE_UP(92),
    KEY_PAGE_DOWN(93),
    KEY_PICTSYMBOLS(94),
    KEY_SWITCH_CHARSET(95),
    KEY_BUTTON_A(96),
    KEY_BUTTON_B(97),
    KEY_BUTTON_C(98),
    KEY_BUTTON_X(99),
    KEY_BUTTON_Y(100),
    KEY_BUTTON_Z(101),
    KEY_BUTTON_L1(102),
    KEY_BUTTON_R1(103),
    KEY_BUTTON_L2(104),
    KEY_BUTTON_R2(105),
    KEY_BUTTON_THUMBL(106),
    KEY_BUTTON_THUMBR(107),
    KEY_BUTTON_START(108),
    KEY_BUTTON_SELECT(109),
    KEY_BUTTON_MODE(110),
    KEY_ESCAPE(111),
    KEY_FORWARD_DEL(112),
    KEY_CTRL_LEFT(113),
    KEY_CTRL_RIGHT(114),
    KEY_CAPS_LOCK(115),
    KEY_SCROLL_LOCK(116),
    KEY_META_LEFT(117),
    KEY_META_RIGHT(118),
    KEY_FUNCTION(119),
    KEY_SYSRQ(120),
    KEY_BREAK(121),
    KEY_MOVE_HOME(122),
    KEY_MOVE_END(123),
    KEY_INSERT(124),
    KEY_FORWARD(125),
    KEY_MEDIA_PLAY(126),
    KEY_MEDIA_PAUSE(127),
    KEY_MEDIA_CLOSE(128),
    KEY_MEDIA_EJECT(129),
    KEY_MEDIA_RECORD(130),
    KEY_F1(131),
    KEY_F2(132),
    KEY_F3(133),
    KEY_F4(134),
    KEY_F5(135),
    KEY_F6(136),
    KEY_F7(137),
    KEY_F8(138),
    KEY_F9(139),
    KEY_F10(140),
    KEY_F11(141),
    KEY_F12(142),
    KEY_NUM_LOCK(143),
    KEY_NUMPAD_0(144),
    KEY_NUMPAD_1(145),
    KEY_NUMPAD_2(146),
    KEY_NUMPAD_3(147),
    KEY_NUMPAD_4(148),
    KEY_NUMPAD_5(149),
    KEY_NUMPAD_6(150),
    KEY_NUMPAD_7(151),
    KEY_NUMPAD_8(152),
    KEY_NUMPAD_9(153),
    KEY_NUMPAD_DIVIDE(154),
    KEY_NUMPAD_MULTIPLY(155),
    KEY_NUMPAD_SUBTRACT(156),
    KEY_NUMPAD_ADD(157),
    KEY_NUMPAD_DOT(158),
    KEY_NUMPAD_COMMA(159),
    KEY_NUMPAD_ENTER(160),
    KEY_NUMPAD_EQUALS(161),
    KEY_NUMPAD_LEFT_PAREN(162),
    KEY_NUMPAD_RIGHT_PAREN(163),
    KEY_VOLUME_MUTE(164),
    KEY_INFO(165),
    KEY_CHANNEL_UP(166),
    KEY_CHANNEL_DOWN(167),
    KEY_ZOOM_IN(168),
    KEY_ZOOM_OUT(169),
    KEY_TV(170),
    KEY_WINDOW(171),
    KEY_GUIDE(172),
    KEY_DVR(173),
    KEY_BOOKMARK(174),
    KEY_CAPTIONS(175),
    KEY_SETTINGS(176),
    KEY_TV_POWER(177),
    KEY_TV_INPUT(178),
    KEY_STB_POWER(179),
    KEY_STB_INPUT(180),
    KEY_AVR_POWER(181),
    KEY_AVR_INPUT(182),
    KEY_PROG_RED(183),
    KEY_PROG_GREEN(184),
    KEY_PROG_YELLOW(185),
    KEY_PROG_BLUE(186),
    KEY_APP_SWITCH(187),
    KEY_BUTTON_1(188),
    KEY_BUTTON_2(189),
    KEY_BUTTON_3(190),
    KEY_BUTTON_4(191),
    KEY_BUTTON_5(192),
    KEY_BUTTON_6(193),
    KEY_BUTTON_7(194),
    KEY_BUTTON_8(195),
    KEY_BUTTON_9(196),
    KEY_BUTTON_10(197),
    KEY_BUTTON_11(198),
    KEY_BUTTON_12(199),
    KEY_BUTTON_13(200),
    KEY_BUTTON_14(201),
    KEY_BUTTON_15(202),
    KEY_BUTTON_16(203),
    KEY_LANGUAGE_SWITCH(204),
    KEY_MANNER_MODE(205),
    KEY_3D_MODE(206),
    KEY_CONTACTS(207),
    KEY_CALENDAR(208),
    KEY_MUSIC(209),
    KEY_CALCULATOR(210),
    KEY_ZENKAKU_HANKAKU(211),
    KEY_EISU(212),
    KEY_MUHENKAN(213),
    KEY_HENKAN(214),
    KEY_KATAKANA_HIRAGANA(215),
    KEY_YEN(216),
    KEY_RO(217),
    KEY_KANA(218),
    KEY_ASSIST(219),
    KEY_BRIGHTNESS_DOWN(220),
    KEY_BRIGHTNESS_UP(221),
    KEY_MEDIA_AUDIO_TRACK(222),
    KEY_SLEEP(223),
    KEY_WAKEUP(224),
    KEY_PAIRING(225),
    KEY_MEDIA_TOP_MENU(226),
    KEY_11(227),
    KEY_12(228),
    KEY_LAST_CHANNEL(229),
    KEY_TV_DATA_SERVICE(230),
    KEY_VOICE_ASSIST(231),
    KEY_TV_RADIO_SERVICE(232),
    KEY_TV_TELETEXT(233),
    KEY_TV_NUMBER_ENTRY(234),
    KEY_TV_TERRESTRIAL_ANALOG(235),
    KEY_TV_TERRESTRIAL_DIGITAL(236),
    KEY_TV_SATELLITE(237),
    KEY_TV_SATELLITE_BS(238),
    KEY_TV_SATELLITE_CS(239),
    KEY_TV_SATELLITE_SERVICE(240),
    KEY_TV_NETWORK(241),
    KEY_TV_ANTENNA_CABLE(242),
    KEY_TV_INPUT_HDMI_1(243),
    KEY_TV_INPUT_HDMI_2(244),
    KEY_TV_INPUT_HDMI_3(245),
    KEY_TV_INPUT_HDMI_4(246),
    KEY_TV_INPUT_COMPOSITE_1(247),
    KEY_TV_INPUT_COMPOSITE_2(248),
    KEY_TV_INPUT_COMPONENT_1(249),
    KEY_TV_INPUT_COMPONENT_2(250),
    KEY_TV_INPUT_VGA_1(251),
    KEY_TV_AUDIO_DESCRIPTION(252),
    KEY_TV_AUDIO_DESCRIPTION_MIX_UP(253),
    KEY_TV_AUDIO_DESCRIPTION_MIX_DOWN(254),
    KEY_TV_ZOOM_MODE(255),
    KEY_TV_CONTENTS_MENU(256),
    KEY_TV_MEDIA_CONTEXT_MENU(257),
    KEY_TV_TIMER_PROGRAMMING(258),
    KEY_HELP(259),
    KEY_NAVIGATE_PREVIOUS(260),
    KEY_NAVIGATE_NEXT(261),
    KEY_NAVIGATE_IN(262),
    KEY_NAVIGATE_OUT(263),
    KEY_STEM_PRIMARY(264),
    KEY_STEM_1(265),
    KEY_STEM_2(266),
    KEY_STEM_3(267),
    KEY_DPAD_UP_LEFT(268),
    KEY_DPAD_DOWN_LEFT(269),
    KEY_DPAD_UP_RIGHT(270),
    KEY_DPAD_DOWN_RIGHT(271),
    KEY_MEDIA_SKIP_FORWARD(272),
    KEY_MEDIA_SKIP_BACKWARD(273),
    KEY_MEDIA_STEP_FORWARD(274),
    KEY_MEDIA_STEP_BACKWARD(275),
    KEY_SOFT_SLEEP(276),
    KEY_CUT(277),
    KEY_COPY(278),
    KEY_PASTE(279),
    KEY_SYSTEM_NAVIGATION_UP(280),
    KEY_SYSTEM_NAVIGATION_DOWN(281),
    KEY_SYSTEM_NAVIGATION_LEFT(282),
    KEY_SYSTEM_NAVIGATION_RIGHT(283),
    KEY_ALL_APPS(284),
    KEY_REFRESH(285),
    KEY_THUMBS_UP(286),
    KEY_THUMBS_DOWN(287),
    KEY_PROFILE_SWITCH(288),

    // Mask
    MASK_META_SHIFT_ON(0x1),
    MASK_META_ALT_ON(0x02),
    MASK_META_CTRL_ON(0x1000),
    MASK_META_META_ON(0x10000);

    //</editor-fold>
    companion object {
        //<editor-fold desc="AWT Mappings">
        private val awtMappings = mapOf(
            KeyEvent.VK_ENTER to KEY_ENTER,
            KeyEvent.VK_BACK_SPACE to KEY_DEL,
            KeyEvent.VK_TAB to KEY_TAB,
            KeyEvent.VK_CANCEL to KEY_UNKNOWN,
            KeyEvent.VK_CLEAR to KEY_UNKNOWN,
            KeyEvent.VK_SHIFT to KEY_SHIFT_LEFT,
            KeyEvent.VK_CONTROL to KEY_CTRL_LEFT,
            KeyEvent.VK_ALT to KEY_ALT_LEFT,
            KeyEvent.VK_PAUSE to KEY_MEDIA_PAUSE,
            KeyEvent.VK_CAPS_LOCK to KEY_CAPS_LOCK,
            KeyEvent.VK_ESCAPE to KEY_ESCAPE,
            KeyEvent.VK_SPACE to KEY_SPACE,
            KeyEvent.VK_PAGE_UP to KEY_PAGE_UP,
            KeyEvent.VK_PAGE_DOWN to KEY_PAGE_DOWN,
            KeyEvent.VK_END to KEY_MOVE_END,
            KeyEvent.VK_HOME to KEY_MOVE_HOME,
            KeyEvent.VK_LEFT to KEY_DPAD_LEFT,
            KeyEvent.VK_UP to KEY_DPAD_UP,
            KeyEvent.VK_RIGHT to KEY_DPAD_RIGHT,
            KeyEvent.VK_DOWN to KEY_DPAD_DOWN,
            KeyEvent.VK_COMMA to KEY_COMMA,
            KeyEvent.VK_MINUS to KEY_MINUS,
            KeyEvent.VK_PERIOD to KEY_PERIOD,
            KeyEvent.VK_SLASH to KEY_SLASH,
            KeyEvent.VK_0 to KEY_0,
            KeyEvent.VK_1 to KEY_1,
            KeyEvent.VK_2 to KEY_2,
            KeyEvent.VK_3 to KEY_3,
            KeyEvent.VK_4 to KEY_4,
            KeyEvent.VK_5 to KEY_5,
            KeyEvent.VK_6 to KEY_6,
            KeyEvent.VK_7 to KEY_7,
            KeyEvent.VK_8 to KEY_8,
            KeyEvent.VK_9 to KEY_9,
            KeyEvent.VK_SEMICOLON to KEY_SEMICOLON,
            KeyEvent.VK_EQUALS to KEY_EQUALS,
            KeyEvent.VK_A to KEY_A,
            KeyEvent.VK_B to KEY_B,
            KeyEvent.VK_C to KEY_C,
            KeyEvent.VK_D to KEY_D,
            KeyEvent.VK_E to KEY_E,
            KeyEvent.VK_F to KEY_F,
            KeyEvent.VK_G to KEY_G,
            KeyEvent.VK_H to KEY_H,
            KeyEvent.VK_I to KEY_I,
            KeyEvent.VK_J to KEY_J,
            KeyEvent.VK_K to KEY_K,
            KeyEvent.VK_L to KEY_L,
            KeyEvent.VK_M to KEY_M,
            KeyEvent.VK_N to KEY_N,
            KeyEvent.VK_O to KEY_O,
            KeyEvent.VK_P to KEY_P,
            KeyEvent.VK_Q to KEY_Q,
            KeyEvent.VK_R to KEY_R,
            KeyEvent.VK_S to KEY_S,
            KeyEvent.VK_T to KEY_T,
            KeyEvent.VK_U to KEY_U,
            KeyEvent.VK_V to KEY_V,
            KeyEvent.VK_W to KEY_W,
            KeyEvent.VK_X to KEY_X,
            KeyEvent.VK_Y to KEY_Y,
            KeyEvent.VK_Z to KEY_Z,
            KeyEvent.VK_OPEN_BRACKET to KEY_LEFT_BRACKET,
            KeyEvent.VK_BACK_SLASH to KEY_BACKSLASH,
            KeyEvent.VK_CLOSE_BRACKET to KEY_RIGHT_BRACKET,
            KeyEvent.VK_NUMPAD0 to KEY_0,
            KeyEvent.VK_NUMPAD1 to KEY_1,
            KeyEvent.VK_NUMPAD2 to KEY_2,
            KeyEvent.VK_NUMPAD3 to KEY_3,
            KeyEvent.VK_NUMPAD4 to KEY_4,
            KeyEvent.VK_NUMPAD5 to KEY_5,
            KeyEvent.VK_NUMPAD6 to KEY_6,
            KeyEvent.VK_NUMPAD7 to KEY_7,
            KeyEvent.VK_NUMPAD8 to KEY_8,
            KeyEvent.VK_NUMPAD9 to KEY_9,
            KeyEvent.VK_MULTIPLY to KEY_STAR,
            KeyEvent.VK_ADD to KEY_PLUS,
            KeyEvent.VK_SEPARATER to KEY_PERIOD,
            KeyEvent.VK_SEPARATOR to KEY_PERIOD,
            KeyEvent.VK_SUBTRACT to KEY_MINUS,
            KeyEvent.VK_DECIMAL to KEY_PERIOD,
            KeyEvent.VK_DIVIDE to KEY_SLASH,
            KeyEvent.VK_DELETE to KEY_DEL,
            KeyEvent.VK_NUM_LOCK to KEY_NUM_LOCK,
            KeyEvent.VK_SCROLL_LOCK to KEY_SCROLL_LOCK,
            KeyEvent.VK_F1 to KEY_F1,
            KeyEvent.VK_F2 to KEY_F2,
            KeyEvent.VK_F3 to KEY_F3,
            KeyEvent.VK_F4 to KEY_F4,
            KeyEvent.VK_F5 to KEY_F5,
            KeyEvent.VK_F6 to KEY_F6,
            KeyEvent.VK_F7 to KEY_F7,
            KeyEvent.VK_F8 to KEY_F8,
            KeyEvent.VK_F9 to KEY_F9,
            KeyEvent.VK_F10 to KEY_F10,
            KeyEvent.VK_F11 to KEY_F11,
            KeyEvent.VK_F12 to KEY_F12,
            KeyEvent.VK_F13 to KEY_UNKNOWN,
            KeyEvent.VK_F14 to KEY_UNKNOWN,
            KeyEvent.VK_F15 to KEY_UNKNOWN,
            KeyEvent.VK_F16 to KEY_UNKNOWN,
            KeyEvent.VK_F17 to KEY_UNKNOWN,
            KeyEvent.VK_F18 to KEY_UNKNOWN,
            KeyEvent.VK_F19 to KEY_UNKNOWN,
            KeyEvent.VK_F20 to KEY_UNKNOWN,
            KeyEvent.VK_F21 to KEY_UNKNOWN,
            KeyEvent.VK_F22 to KEY_UNKNOWN,
            KeyEvent.VK_F23 to KEY_UNKNOWN,
            KeyEvent.VK_F24 to KEY_UNKNOWN,
            KeyEvent.VK_PRINTSCREEN to KEY_SYSRQ,
            KeyEvent.VK_INSERT to KEY_INSERT,
            KeyEvent.VK_HELP to KEY_HELP,
            KeyEvent.VK_META to KEY_META_LEFT,
            KeyEvent.VK_BACK_QUOTE to KEY_GRAVE,
            KeyEvent.VK_QUOTE to KEY_APOSTROPHE,
            KeyEvent.VK_KP_UP to KEY_DPAD_UP,
            KeyEvent.VK_KP_DOWN to KEY_DPAD_DOWN,
            KeyEvent.VK_KP_LEFT to KEY_DPAD_LEFT,
            KeyEvent.VK_KP_RIGHT to KEY_DPAD_RIGHT,
            KeyEvent.VK_DEAD_GRAVE to KEY_GRAVE,
            KeyEvent.VK_DEAD_ACUTE to KEY_UNKNOWN,
            KeyEvent.VK_DEAD_CIRCUMFLEX to KEY_UNKNOWN,
            KeyEvent.VK_DEAD_TILDE to KEY_UNKNOWN,
            KeyEvent.VK_DEAD_MACRON to KEY_UNKNOWN,
            KeyEvent.VK_DEAD_BREVE to KEY_UNKNOWN,
            KeyEvent.VK_DEAD_ABOVEDOT to KEY_UNKNOWN,
            KeyEvent.VK_DEAD_DIAERESIS to KEY_UNKNOWN,
            KeyEvent.VK_DEAD_ABOVERING to KEY_UNKNOWN,
            KeyEvent.VK_DEAD_DOUBLEACUTE to KEY_UNKNOWN,
            KeyEvent.VK_DEAD_CARON to KEY_UNKNOWN,
            KeyEvent.VK_DEAD_CEDILLA to KEY_UNKNOWN,
            KeyEvent.VK_DEAD_OGONEK to KEY_UNKNOWN,
            KeyEvent.VK_DEAD_IOTA to KEY_UNKNOWN,
            KeyEvent.VK_DEAD_VOICED_SOUND to KEY_UNKNOWN,
            KeyEvent.VK_DEAD_SEMIVOICED_SOUND to KEY_UNKNOWN,
            KeyEvent.VK_AMPERSAND to KEY_UNKNOWN,
            KeyEvent.VK_ASTERISK to KEY_STAR,
            KeyEvent.VK_QUOTEDBL to KEY_APOSTROPHE,
            KeyEvent.VK_LESS to KEY_UNKNOWN,
            KeyEvent.VK_GREATER to KEY_UNKNOWN,
            KeyEvent.VK_BRACELEFT to KEY_LEFT_BRACKET,
            KeyEvent.VK_BRACERIGHT to KEY_RIGHT_BRACKET,
            KeyEvent.VK_AT to KEY_UNKNOWN,
            KeyEvent.VK_COLON to KEY_SEMICOLON,
            KeyEvent.VK_CIRCUMFLEX to KEY_UNKNOWN,
            KeyEvent.VK_DOLLAR to KEY_UNKNOWN,
            KeyEvent.VK_EURO_SIGN to KEY_UNKNOWN,
            KeyEvent.VK_EXCLAMATION_MARK to KEY_UNKNOWN,
            KeyEvent.VK_INVERTED_EXCLAMATION_MARK to KEY_UNKNOWN,
            KeyEvent.VK_LEFT_PARENTHESIS to KEY_NUMPAD_LEFT_PAREN,
            KeyEvent.VK_NUMBER_SIGN to KEY_POUND,
            KeyEvent.VK_PLUS to KEY_PLUS,
            KeyEvent.VK_RIGHT_PARENTHESIS to KEY_NUMPAD_RIGHT_PAREN,
            KeyEvent.VK_UNDERSCORE to KEY_UNKNOWN,
            KeyEvent.VK_WINDOWS to KEY_META_LEFT,
            KeyEvent.VK_CONTEXT_MENU to KEY_MENU,
            KeyEvent.VK_FINAL to KEY_UNKNOWN,
            KeyEvent.VK_CONVERT to KEY_UNKNOWN,
            KeyEvent.VK_NONCONVERT to KEY_UNKNOWN,
            KeyEvent.VK_ACCEPT to KEY_UNKNOWN,
            KeyEvent.VK_MODECHANGE to KEY_UNKNOWN,
            KeyEvent.VK_KANA to KEY_UNKNOWN,
            KeyEvent.VK_KANJI to KEY_UNKNOWN,
            KeyEvent.VK_ALPHANUMERIC to KEY_UNKNOWN,
            KeyEvent.VK_KATAKANA to KEY_UNKNOWN,
            KeyEvent.VK_HIRAGANA to KEY_UNKNOWN,
            KeyEvent.VK_FULL_WIDTH to KEY_UNKNOWN,
            KeyEvent.VK_HALF_WIDTH to KEY_UNKNOWN,
            KeyEvent.VK_ROMAN_CHARACTERS to KEY_UNKNOWN,
            KeyEvent.VK_ALL_CANDIDATES to KEY_UNKNOWN,
            KeyEvent.VK_PREVIOUS_CANDIDATE to KEY_UNKNOWN,
            KeyEvent.VK_CODE_INPUT to KEY_UNKNOWN,
            KeyEvent.VK_JAPANESE_KATAKANA to KEY_UNKNOWN,
            KeyEvent.VK_JAPANESE_HIRAGANA to KEY_UNKNOWN,
            KeyEvent.VK_JAPANESE_ROMAN to KEY_UNKNOWN,
            KeyEvent.VK_KANA_LOCK to KEY_UNKNOWN,
            KeyEvent.VK_INPUT_METHOD_ON_OFF to KEY_UNKNOWN,
            KeyEvent.VK_CUT to KEY_CUT,
            KeyEvent.VK_COPY to KEY_COPY,
            KeyEvent.VK_PASTE to KEY_PASTE,
            KeyEvent.VK_UNDO to KEY_UNKNOWN,
            KeyEvent.VK_AGAIN to KEY_UNKNOWN,
            KeyEvent.VK_FIND to KEY_UNKNOWN,
            KeyEvent.VK_PROPS to KEY_UNKNOWN,
            KeyEvent.VK_STOP to KEY_MEDIA_STOP,
            KeyEvent.VK_COMPOSE to KEY_UNKNOWN,
            KeyEvent.VK_ALT_GRAPH to KEY_UNKNOWN,
            KeyEvent.VK_BEGIN to KEY_MEDIA_PLAY,
            KeyEvent.VK_UNDEFINED to KEY_UNKNOWN
        )
        //</editor-fold>

        private val shiftChars = "~!@#$%^&*()_+{}|:\"<>?"

        /**
         * Checks if a given character needs shift to be pressed to be typed
         *
         * @param char Character to check
         * @return true if shift is needed
         */
        fun requiresShift(char: Char) = char.isUpperCase() || shiftChars.contains(char)

        private val puncMapping = mapOf(
            ' ' to KEY_SPACE,
            '~' to KEY_GRAVE,
            '!' to KEY_1,
            '@' to KEY_2,
            '#' to KEY_3,
            '$' to KEY_4,
            '%' to KEY_5,
            '^' to KEY_6,
            '&' to KEY_7,
            '*' to KEY_8,
            '(' to KEY_9,
            ')' to KEY_0,
            '_' to KEY_MINUS,
            '-' to KEY_MINUS,
            '+' to KEY_EQUALS,
            '=' to KEY_EQUALS,
            '{' to KEY_LEFT_BRACKET,
            '[' to KEY_LEFT_BRACKET,
            '}' to KEY_RIGHT_BRACKET,
            ']' to KEY_RIGHT_BRACKET,
            '|' to KEY_BACKSLASH,
            '\\' to KEY_BACKSLASH,
            ':' to KEY_SEMICOLON,
            ';' to KEY_SEMICOLON,
            '\'' to KEY_APOSTROPHE,
            '"' to KEY_APOSTROPHE,
            '<' to KEY_COMMA,
            ',' to KEY_COMMA,
            '.' to KEY_PERIOD,
            '>' to KEY_PERIOD,
            '?' to KEY_SLASH,
            '/' to KEY_SLASH
        )

        /**
         * Returns the corresponding key code needed to type this character
         *
         * @param name Name of character, eg. A, B, COMMA, ALT
         * @return [Key]
         */
        fun findByName(name: String) = try {
            valueOf("KEY_${name.uppercase()}")
        } catch (e: IllegalArgumentException) {
            puncMapping.getOrDefault(name[0], KEY_UNKNOWN)
        }

        /**
         * Finds a Key with the given code.
         *
         * @param code The code to look for
         * @return [Key]
         */
        fun findByCode(code: Long) = values().find { it.code == code }

        /**
         * Gets the corresponding [Key] from the type of [java.awt.event.KeyEvent] given.
         *
         * @param event The [java.awt.event.KeyEvent]
         * @return Corresponding [Key], if no mapping was found then [Key_unknown] is returned
         */
        fun fromAwtKeyEvent(event: Int): Key = awtMappings.getOrDefault(event, KEY_UNKNOWN)
    }
}
