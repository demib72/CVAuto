package com.waicool20.cvauto.android.input

import java.awt.event.KeyEvent

/**
 * Represents a key, see
 * [Linux Input Events](https://github.com/torvalds/linux/blob/master/include/uapi/linux/input-event-codes.h)
 * for more information.
 *
 */
enum class Key(val code: Long) {
    //<editor-fold desc="Enums">
    KEY_RESERVED(0),
    KEY_ESC(1),
    KEY_1(2),
    KEY_2(3),
    KEY_3(4),
    KEY_4(5),
    KEY_5(6),
    KEY_6(7),
    KEY_7(8),
    KEY_8(9),
    KEY_9(10),
    KEY_0(11),
    KEY_MINUS(12),
    KEY_EQUAL(13),
    KEY_BACKSPACE(14),
    KEY_TAB(15),
    KEY_Q(16),
    KEY_W(17),
    KEY_E(18),
    KEY_R(19),
    KEY_T(20),
    KEY_Y(21),
    KEY_U(22),
    KEY_I(23),
    KEY_O(24),
    KEY_P(25),
    KEY_LEFTBRACE(26),
    KEY_RIGHTBRACE(27),
    KEY_ENTER(28),
    KEY_LEFTCTRL(29),
    KEY_A(30),
    KEY_S(31),
    KEY_D(32),
    KEY_F(33),
    KEY_G(34),
    KEY_H(35),
    KEY_J(36),
    KEY_K(37),
    KEY_L(38),
    KEY_SEMICOLON(39),
    KEY_APOSTROPHE(40),
    KEY_GRAVE(41),
    KEY_LEFTSHIFT(42),
    KEY_BACKSLASH(43),
    KEY_Z(44),
    KEY_X(45),
    KEY_C(46),
    KEY_V(47),
    KEY_B(48),
    KEY_N(49),
    KEY_M(50),
    KEY_COMMA(51),
    KEY_DOT(52),
    KEY_SLASH(53),
    KEY_RIGHTSHIFT(54),
    KEY_KPASTERISK(55),
    KEY_LEFTALT(56),
    KEY_SPACE(57),
    KEY_CAPSLOCK(58),
    KEY_F1(59),
    KEY_F2(60),
    KEY_F3(61),
    KEY_F4(62),
    KEY_F5(63),
    KEY_F6(64),
    KEY_F7(65),
    KEY_F8(66),
    KEY_F9(67),
    KEY_F10(68),
    KEY_NUMLOCK(69),
    KEY_SCROLLLOCK(70),
    KEY_KP7(71),
    KEY_KP8(72),
    KEY_KP9(73),
    KEY_KPMINUS(74),
    KEY_KP4(75),
    KEY_KP5(76),
    KEY_KP6(77),
    KEY_KPPLUS(78),
    KEY_KP1(79),
    KEY_KP2(80),
    KEY_KP3(81),
    KEY_KP0(82),
    KEY_KPDOT(83),

    KEY_ZENKAKUHANKAKU(85),
    KEY_102ND(86),
    KEY_F11(87),
    KEY_F12(88),
    KEY_RO(89),
    KEY_KATAKANA(90),
    KEY_HIRAGANA(91),
    KEY_HENKAN(92),
    KEY_KATAKANAHIRAGANA(93),
    KEY_MUHENKAN(94),
    KEY_KPJPCOMMA(95),
    KEY_KPENTER(96),
    KEY_RIGHTCTRL(97),
    KEY_KPSLASH(98),
    KEY_SYSRQ(99),
    KEY_RIGHTALT(100),
    KEY_LINEFEED(101),
    KEY_HOME(102),
    KEY_UP(103),
    KEY_PAGEUP(104),
    KEY_LEFT(105),
    KEY_RIGHT(106),
    KEY_END(107),
    KEY_DOWN(108),
    KEY_PAGEDOWN(109),
    KEY_INSERT(110),
    KEY_DELETE(111),
    KEY_MACRO(112),
    KEY_MUTE(113),
    KEY_VOLUMEDOWN(114),
    KEY_VOLUMEUP(115),
    KEY_POWER(116),
    KEY_KPEQUAL(117),
    KEY_KPPLUSMINUS(118),
    KEY_PAUSE(119),
    KEY_SCALE(120),

    KEY_KPCOMMA(121),
    KEY_HANGEUL(122),
    KEY_HANGUEL(KEY_HANGEUL.code),
    KEY_HANJA(123),
    KEY_YEN(124),
    KEY_LEFTMETA(125),
    KEY_RIGHTMETA(126),
    KEY_COMPOSE(127),

    KEY_STOP(128),
    KEY_AGAIN(129),
    KEY_PROPS(130),
    KEY_UNDO(131),
    KEY_FRONT(132),
    KEY_COPY(133),
    KEY_OPEN(134),
    KEY_PASTE(135),
    KEY_FIND(136),
    KEY_CUT(137),
    KEY_HELP(138),
    KEY_MENU(139),
    KEY_CALC(140),
    KEY_SETUP(141),
    KEY_SLEEP(142),
    KEY_WAKEUP(143),
    KEY_FILE(144),
    KEY_SENDFILE(145),
    KEY_DELETEFILE(146),
    KEY_XFER(147),
    KEY_PROG1(148),
    KEY_PROG2(149),
    KEY_WWW(150),
    KEY_MSDOS(151),
    KEY_COFFEE(152),
    KEY_SCREENLOCK(KEY_COFFEE.code),
    KEY_ROTATE_DISPLAY(153),
    KEY_DIRECTION(KEY_ROTATE_DISPLAY.code),
    KEY_CYCLEWINDOWS(154),
    KEY_MAIL(155),
    KEY_BOOKMARKS(156),
    KEY_COMPUTER(157),
    KEY_BACK(158),
    KEY_FORWARD(159),
    KEY_CLOSECD(160),
    KEY_EJECTCD(161),
    KEY_EJECTCLOSECD(162),
    KEY_NEXTSONG(163),
    KEY_PLAYPAUSE(164),
    KEY_PREVIOUSSONG(165),
    KEY_STOPCD(166),
    KEY_RECORD(167),
    KEY_REWIND(168),
    KEY_PHONE(169),
    KEY_ISO(170),
    KEY_CONFIG(171),
    KEY_HOMEPAGE(172),
    KEY_REFRESH(173),
    KEY_EXIT(174),
    KEY_MOVE(175),
    KEY_EDIT(176),
    KEY_SCROLLUP(177),
    KEY_SCROLLDOWN(178),
    KEY_KPLEFTPAREN(179),
    KEY_KPRIGHTPAREN(180),
    KEY_NEW(181),
    KEY_REDO(182),

    KEY_F13(183),
    KEY_F14(184),
    KEY_F15(185),
    KEY_F16(186),
    KEY_F17(187),
    KEY_F18(188),
    KEY_F19(189),
    KEY_F20(190),
    KEY_F21(191),
    KEY_F22(192),
    KEY_F23(193),
    KEY_F24(194),

    KEY_PLAYCD(200),
    KEY_PAUSECD(201),
    KEY_PROG3(202),
    KEY_PROG4(203),
    KEY_DASHBOARD(204),
    KEY_SUSPEND(205),
    KEY_CLOSE(206),
    KEY_PLAY(207),
    KEY_FASTFORWARD(208),
    KEY_BASSBOOST(209),
    KEY_PRINT(210),
    KEY_HP(211),
    KEY_CAMERA(212),
    KEY_SOUND(213),
    KEY_QUESTION(214),
    KEY_EMAIL(215),
    KEY_CHAT(216),
    KEY_SEARCH(217),
    KEY_CONNECT(218),
    KEY_FINANCE(219),
    KEY_SPORT(220),
    KEY_SHOP(221),
    KEY_ALTERASE(222),
    KEY_CANCEL(223),
    KEY_BRIGHTNESSDOWN(224),
    KEY_BRIGHTNESSUP(225),
    KEY_MEDIA(226),

    KEY_SWITCHVIDEOMODE(227),
    KEY_KBDILLUMTOGGLE(228),
    KEY_KBDILLUMDOWN(229),
    KEY_KBDILLUMUP(230),

    KEY_SEND(231),
    KEY_REPLY(232),
    KEY_FORWARDMAIL(233),
    KEY_SAVE(234),
    KEY_DOCUMENTS(235),

    KEY_BATTERY(236),

    KEY_BLUETOOTH(237),
    KEY_WLAN(238),
    KEY_UWB(239),

    KEY_UNKNOWN(240),

    KEY_VIDEO_NEXT(241),
    KEY_VIDEO_PREV(242),
    KEY_BRIGHTNESS_CYCLE(243),
    KEY_BRIGHTNESS_AUTO(244),
    KEY_BRIGHTNESS_ZERO(KEY_BRIGHTNESS_AUTO.code),
    KEY_DISPLAY_OFF(245),

    KEY_WWAN(246),
    KEY_WIMAX(KEY_WWAN.code),
    KEY_RFKILL(247),

    KEY_MICMUTE(248),
    KEY_OK(0x160),
    KEY_SELECT(0x161),
    KEY_GOTO(0x162),
    KEY_CLEAR(0x163),
    KEY_POWER2(0x164),
    KEY_OPTION(0x165),
    KEY_INFO(0x166),
    KEY_TIME(0x167),
    KEY_VENDOR(0x168),
    KEY_ARCHIVE(0x169),
    KEY_PROGRAM(0x16a),
    KEY_CHANNEL(0x16b),
    KEY_FAVORITES(0x16c),
    KEY_EPG(0x16d),
    KEY_PVR(0x16e),
    KEY_MHP(0x16f),
    KEY_LANGUAGE(0x170),
    KEY_TITLE(0x171),
    KEY_SUBTITLE(0x172),
    KEY_ANGLE(0x173),
    KEY_ZOOM(0x174),
    KEY_MODE(0x175),
    KEY_KEYBOARD(0x176),
    KEY_SCREEN(0x177),
    KEY_PC(0x178),
    KEY_TV(0x179),
    KEY_TV2(0x17a),
    KEY_VCR(0x17b),
    KEY_VCR2(0x17c),
    KEY_SAT(0x17d),
    KEY_SAT2(0x17e),
    KEY_CD(0x17f),
    KEY_TAPE(0x180),
    KEY_RADIO(0x181),
    KEY_TUNER(0x182),
    KEY_PLAYER(0x183),
    KEY_TEXT(0x184),
    KEY_DVD(0x185),
    KEY_AUX(0x186),
    KEY_MP3(0x187),
    KEY_AUDIO(0x188),
    KEY_VIDEO(0x189),
    KEY_DIRECTORY(0x18a),
    KEY_LIST(0x18b),
    KEY_MEMO(0x18c),
    KEY_CALENDAR(0x18d),
    KEY_RED(0x18e),
    KEY_GREEN(0x18f),
    KEY_YELLOW(0x190),
    KEY_BLUE(0x191),
    KEY_CHANNELUP(0x192),
    KEY_CHANNELDOWN(0x193),
    KEY_FIRST(0x194),
    KEY_LAST(0x195),
    KEY_AB(0x196),
    KEY_NEXT(0x197),
    KEY_RESTART(0x198),
    KEY_SLOW(0x199),
    KEY_SHUFFLE(0x19a),
    KEY_BREAK(0x19b),
    KEY_PREVIOUS(0x19c),
    KEY_DIGITS(0x19d),
    KEY_TEEN(0x19e),
    KEY_TWEN(0x19f),
    KEY_VIDEOPHONE(0x1a0),
    KEY_GAMES(0x1a1),
    KEY_ZOOMIN(0x1a2),
    KEY_ZOOMOUT(0x1a3),
    KEY_ZOOMRESET(0x1a4),
    KEY_WORDPROCESSOR(0x1a5),
    KEY_EDITOR(0x1a6),
    KEY_SPREADSHEET(0x1a7),
    KEY_GRAPHICSEDITOR(0x1a8),
    KEY_PRESENTATION(0x1a9),
    KEY_DATABASE(0x1aa),
    KEY_NEWS(0x1ab),
    KEY_VOICEMAIL(0x1ac),
    KEY_ADDRESSBOOK(0x1ad),
    KEY_MESSENGER(0x1ae),
    KEY_DISPLAYTOGGLE(0x1af),
    KEY_BRIGHTNESS_TOGGLE(KEY_DISPLAYTOGGLE.code),
    KEY_SPELLCHECK(0x1b0),
    KEY_LOGOFF(0x1b1),

    KEY_DOLLAR(0x1b2),
    KEY_EURO(0x1b3),

    KEY_FRAMEBACK(0x1b4),
    KEY_FRAMEFORWARD(0x1b5),
    KEY_CONTEXT_MENU(0x1b6),
    KEY_MEDIA_REPEAT(0x1b7),
    KEY_10CHANNELSUP(0x1b8),
    KEY_10CHANNELSDOWN(0x1b9),
    KEY_IMAGES(0x1ba),

    KEY_DEL_EOL(0x1c0),
    KEY_DEL_EOS(0x1c1),
    KEY_INS_LINE(0x1c2),
    KEY_DEL_LINE(0x1c3),

    KEY_FN(0x1d0),
    KEY_FN_ESC(0x1d1),
    KEY_FN_F1(0x1d2),
    KEY_FN_F2(0x1d3),
    KEY_FN_F3(0x1d4),
    KEY_FN_F4(0x1d5),
    KEY_FN_F5(0x1d6),
    KEY_FN_F6(0x1d7),
    KEY_FN_F7(0x1d8),
    KEY_FN_F8(0x1d9),
    KEY_FN_F9(0x1da),
    KEY_FN_F10(0x1db),
    KEY_FN_F11(0x1dc),
    KEY_FN_F12(0x1dd),
    KEY_FN_1(0x1de),
    KEY_FN_2(0x1df),
    KEY_FN_D(0x1e0),
    KEY_FN_E(0x1e1),
    KEY_FN_F(0x1e2),
    KEY_FN_S(0x1e3),
    KEY_FN_B(0x1e4),

    KEY_BRL_DOT1(0x1f1),
    KEY_BRL_DOT2(0x1f2),
    KEY_BRL_DOT3(0x1f3),
    KEY_BRL_DOT4(0x1f4),
    KEY_BRL_DOT5(0x1f5),
    KEY_BRL_DOT6(0x1f6),
    KEY_BRL_DOT7(0x1f7),
    KEY_BRL_DOT8(0x1f8),
    KEY_BRL_DOT9(0x1f9),
    KEY_BRL_DOT10(0x1fa),

    KEY_NUMERIC_0(0x200),
    KEY_NUMERIC_1(0x201),
    KEY_NUMERIC_2(0x202),
    KEY_NUMERIC_3(0x203),
    KEY_NUMERIC_4(0x204),
    KEY_NUMERIC_5(0x205),
    KEY_NUMERIC_6(0x206),
    KEY_NUMERIC_7(0x207),
    KEY_NUMERIC_8(0x208),
    KEY_NUMERIC_9(0x209),
    KEY_NUMERIC_STAR(0x20a),
    KEY_NUMERIC_POUND(0x20b),
    KEY_NUMERIC_A(0x20c),
    KEY_NUMERIC_B(0x20d),
    KEY_NUMERIC_C(0x20e),
    KEY_NUMERIC_D(0x20f),

    KEY_CAMERA_FOCUS(0x210),
    KEY_WPS_BUTTON(0x211),

    KEY_TOUCHPAD_TOGGLE(0x212),
    KEY_TOUCHPAD_ON(0x213),
    KEY_TOUCHPAD_OFF(0x214),

    KEY_CAMERA_ZOOMIN(0x215),
    KEY_CAMERA_ZOOMOUT(0x216),
    KEY_CAMERA_UP(0x217),
    KEY_CAMERA_DOWN(0x218),
    KEY_CAMERA_LEFT(0x219),
    KEY_CAMERA_RIGHT(0x21a),

    KEY_ATTENDANT_ON(0x21b),
    KEY_ATTENDANT_OFF(0x21c),
    KEY_ATTENDANT_TOGGLE(0x21d),
    KEY_LIGHTS_TOGGLE(0x21e),

    BTN_DPAD_UP(0x220),
    BTN_DPAD_DOWN(0x221),
    BTN_DPAD_LEFT(0x222),
    BTN_DPAD_RIGHT(0x223),

    KEY_ALS_TOGGLE(0x230),
    KEY_ROTATE_LOCK_TOGGLE(0x231),

    KEY_BUTTONCONFIG(0x240),
    KEY_TASKMANAGER(0x241),
    KEY_JOURNAL(0x242),
    KEY_CONTROLPANEL(0x243),
    KEY_APPSELECT(0x244),
    KEY_SCREENSAVER(0x245),
    KEY_VOICECOMMAND(0x246),
    KEY_ASSISTANT(0x247),

    KEY_BRIGHTNESS_MIN(0x250),
    KEY_BRIGHTNESS_MAX(0x251),

    KEY_KBDINPUTASSIST_PREV(0x260),
    KEY_KBDINPUTASSIST_NEXT(0x261),
    KEY_KBDINPUTASSIST_PREVGROUP(0x262),
    KEY_KBDINPUTASSIST_NEXTGROUP(0x263),
    KEY_KBDINPUTASSIST_ACCEPT(0x264),
    KEY_KBDINPUTASSIST_CANCEL(0x265),
    KEY_RIGHT_UP(0x266),
    KEY_RIGHT_DOWN(0x267),
    KEY_LEFT_UP(0x268),
    KEY_LEFT_DOWN(0x269),

    KEY_ROOT_MENU(0x26a),
    KEY_MEDIA_TOP_MENU(0x26b),
    KEY_NUMERIC_11(0x26c),
    KEY_NUMERIC_12(0x26d),
    KEY_AUDIO_DESC(0x26e),
    KEY_3D_MODE(0x26f),
    KEY_NEXT_FAVORITE(0x270),
    KEY_STOP_RECORD(0x271),
    KEY_PAUSE_RECORD(0x272),
    KEY_VOD(0x273),
    KEY_UNMUTE(0x274),
    KEY_FASTREVERSE(0x275),
    KEY_SLOWREVERSE(0x276);

    //</editor-fold>
    companion object {
        //<editor-fold desc="AWT Mappings">
        private val awtMappings = mapOf(
                KeyEvent.VK_ENTER to KEY_ENTER,
                KeyEvent.VK_BACK_SPACE to KEY_BACKSPACE,
                KeyEvent.VK_TAB to KEY_TAB,
                KeyEvent.VK_CANCEL to KEY_CANCEL,
                KeyEvent.VK_CLEAR to KEY_UNKNOWN,
                KeyEvent.VK_SHIFT to KEY_LEFTSHIFT,
                KeyEvent.VK_CONTROL to KEY_LEFTCTRL,
                KeyEvent.VK_ALT to KEY_LEFT,
                KeyEvent.VK_PAUSE to KEY_PAUSE,
                KeyEvent.VK_CAPS_LOCK to KEY_CAPSLOCK,
                KeyEvent.VK_ESCAPE to KEY_ESC,
                KeyEvent.VK_SPACE to KEY_SPACE,
                KeyEvent.VK_PAGE_UP to KEY_PAGEUP,
                KeyEvent.VK_PAGE_DOWN to KEY_PAGEDOWN,
                KeyEvent.VK_END to KEY_END,
                KeyEvent.VK_HOME to KEY_HOME,
                KeyEvent.VK_LEFT to KEY_LEFT,
                KeyEvent.VK_UP to KEY_UP,
                KeyEvent.VK_RIGHT to KEY_RIGHT,
                KeyEvent.VK_DOWN to KEY_DOWN,
                KeyEvent.VK_COMMA to KEY_COMMA,
                KeyEvent.VK_MINUS to KEY_MINUS,
                KeyEvent.VK_PERIOD to KEY_DOT,
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
                KeyEvent.VK_EQUALS to KEY_EQUAL,
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
                KeyEvent.VK_OPEN_BRACKET to KEY_LEFTBRACE,
                KeyEvent.VK_BACK_SLASH to KEY_BACKSLASH,
                KeyEvent.VK_CLOSE_BRACKET to KEY_RIGHTBRACE,
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
                KeyEvent.VK_MULTIPLY to KEY_KPASTERISK,
                KeyEvent.VK_ADD to KEY_KPPLUS,
                KeyEvent.VK_SEPARATER to KEY_DOT,
                KeyEvent.VK_SEPARATOR to KEY_DOT,
                KeyEvent.VK_SUBTRACT to KEY_MINUS,
                KeyEvent.VK_DECIMAL to KEY_DOT,
                KeyEvent.VK_DIVIDE to KEY_SLASH,
                KeyEvent.VK_DELETE to KEY_DELETE,
                KeyEvent.VK_NUM_LOCK to KEY_NUMLOCK,
                KeyEvent.VK_SCROLL_LOCK to KEY_SCROLLLOCK,
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
                KeyEvent.VK_F13 to KEY_F13,
                KeyEvent.VK_F14 to KEY_F14,
                KeyEvent.VK_F15 to KEY_F15,
                KeyEvent.VK_F16 to KEY_F16,
                KeyEvent.VK_F17 to KEY_F17,
                KeyEvent.VK_F18 to KEY_F18,
                KeyEvent.VK_F19 to KEY_F19,
                KeyEvent.VK_F20 to KEY_F20,
                KeyEvent.VK_F21 to KEY_F21,
                KeyEvent.VK_F22 to KEY_F22,
                KeyEvent.VK_F23 to KEY_F23,
                KeyEvent.VK_F24 to KEY_F24,
                KeyEvent.VK_PRINTSCREEN to KEY_UNKNOWN, // Qt doesn't allow printscreen events
                KeyEvent.VK_INSERT to KEY_INSERT,
                KeyEvent.VK_HELP to KEY_HELP,
                KeyEvent.VK_META to KEY_LEFTMETA,
                KeyEvent.VK_BACK_QUOTE to KEY_GREEN,
                KeyEvent.VK_QUOTE to KEY_APOSTROPHE,
                KeyEvent.VK_KP_UP to KEY_UP,
                KeyEvent.VK_KP_DOWN to KEY_DOWN,
                KeyEvent.VK_KP_LEFT to KEY_LEFT,
                KeyEvent.VK_KP_RIGHT to KEY_RIGHT,
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
                KeyEvent.VK_ASTERISK to KEY_KPASTERISK,
                KeyEvent.VK_QUOTEDBL to KEY_APOSTROPHE,
                KeyEvent.VK_LESS to KEY_UNKNOWN,
                KeyEvent.VK_GREATER to KEY_UNKNOWN,
                KeyEvent.VK_BRACELEFT to KEY_LEFTBRACE,
                KeyEvent.VK_BRACERIGHT to KEY_RIGHTBRACE,
                KeyEvent.VK_AT to KEY_UNKNOWN,
                KeyEvent.VK_COLON to KEY_SEMICOLON,
                KeyEvent.VK_CIRCUMFLEX to KEY_UNKNOWN,
                KeyEvent.VK_DOLLAR to KEY_DOLLAR,
                KeyEvent.VK_EURO_SIGN to KEY_EURO,
                KeyEvent.VK_EXCLAMATION_MARK to KEY_UNKNOWN,
                KeyEvent.VK_INVERTED_EXCLAMATION_MARK to KEY_UNKNOWN,
                KeyEvent.VK_LEFT_PARENTHESIS to KEY_KPLEFTPAREN,
                KeyEvent.VK_NUMBER_SIGN to KEY_NUMERIC_POUND,
                KeyEvent.VK_PLUS to KEY_KPPLUS,
                KeyEvent.VK_RIGHT_PARENTHESIS to KEY_KPRIGHTPAREN,
                KeyEvent.VK_UNDERSCORE to KEY_UNKNOWN,
                KeyEvent.VK_WINDOWS to KEY_LEFTMETA,
                KeyEvent.VK_CONTEXT_MENU to KEY_MENU,
                KeyEvent.VK_FINAL to KEY_UNKNOWN,
                KeyEvent.VK_CONVERT to KEY_UNKNOWN,
                KeyEvent.VK_NONCONVERT to KEY_UNKNOWN,
                KeyEvent.VK_ACCEPT to KEY_UNKNOWN,
                KeyEvent.VK_MODECHANGE to KEY_UNKNOWN,
                KeyEvent.VK_KANA to KEY_UNKNOWN,
                KeyEvent.VK_KANJI to KEY_UNKNOWN,
                KeyEvent.VK_ALPHANUMERIC to KEY_UNKNOWN,
                KeyEvent.VK_KATAKANA to KEY_KATAKANA,
                KeyEvent.VK_HIRAGANA to KEY_HIRAGANA,
                KeyEvent.VK_FULL_WIDTH to KEY_UNKNOWN,
                KeyEvent.VK_HALF_WIDTH to KEY_UNKNOWN,
                KeyEvent.VK_ROMAN_CHARACTERS to KEY_UNKNOWN,
                KeyEvent.VK_ALL_CANDIDATES to KEY_UNKNOWN,
                KeyEvent.VK_PREVIOUS_CANDIDATE to KEY_UNKNOWN,
                KeyEvent.VK_CODE_INPUT to KEY_UNKNOWN,
                KeyEvent.VK_JAPANESE_KATAKANA to KEY_KATAKANA,
                KeyEvent.VK_JAPANESE_HIRAGANA to KEY_HIRAGANA,
                KeyEvent.VK_JAPANESE_ROMAN to KEY_UNKNOWN,
                KeyEvent.VK_KANA_LOCK to KEY_UNKNOWN,
                KeyEvent.VK_INPUT_METHOD_ON_OFF to KEY_UNKNOWN,
                KeyEvent.VK_CUT to KEY_CUT,
                KeyEvent.VK_COPY to KEY_COPY,
                KeyEvent.VK_PASTE to KEY_PASTE,
                KeyEvent.VK_UNDO to KEY_UNDO,
                KeyEvent.VK_AGAIN to KEY_UNKNOWN,
                KeyEvent.VK_FIND to KEY_FIND,
                KeyEvent.VK_PROPS to KEY_UNKNOWN,
                KeyEvent.VK_STOP to KEY_STOP,
                KeyEvent.VK_COMPOSE to KEY_UNKNOWN,
                KeyEvent.VK_ALT_GRAPH to KEY_UNKNOWN,
                KeyEvent.VK_BEGIN to KEY_PLAY,
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
        fun requiresShift(char: Char) = shiftChars.contains(char)

        private val puncMapping = mapOf(
                '~' to KEY_GREEN,
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
                '+' to KEY_EQUAL,
                '=' to KEY_EQUAL,
                '{' to KEY_LEFTBRACE,
                '[' to KEY_LEFTBRACE,
                '}' to KEY_RIGHTBRACE,
                ']' to KEY_RIGHTBRACE,
                '|' to KEY_BACKSLASH,
                '\\' to KEY_BACKSLASH,
                ':' to KEY_SEMICOLON,
                ';' to KEY_SEMICOLON,
                '\'' to KEY_APOSTROPHE,
                '"' to KEY_APOSTROPHE,
                '<' to KEY_COMMA,
                ',' to KEY_COMMA,
                '.' to KEY_DOT,
                '>' to KEY_DOT,
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
            valueOf("KEY_${name.toUpperCase()}")
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
