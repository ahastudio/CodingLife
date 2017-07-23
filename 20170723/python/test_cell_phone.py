import pytest


class Screen:
    def __init__(self):
        self.text = ''

    def display_digit(self, n):
        self.text += str(n)

    def display_end(self):
        self.text = 'END'


class Dialer:
    def __init__(self, screen):
        self.screen = screen

    def digit(self, n):
        self.screen.display_digit(n)


class ButtonListener:
    def button_pressed(self, token):
        pass


class ButtonDialerAdapter(ButtonListener):
    def __init__(self, digit, dialer):
        self.digit = digit
        self.dialer = dialer

    def button_pressed(self, token):
        self.dialer.digit(self.digit)


class EndButtonListener(ButtonListener):
    def __init__(self, screen):
        self.screen = screen

    def button_pressed(self, token):
        self.screen.display_end()


class Button:
    def __init__(self, button_listener):
        self.button_listener = button_listener

    def press(self):
        self.button_listener.button_pressed(id(self))


def test_button():
    screen = Screen()
    dialer = Dialer(screen)
    # button_listener = ButtonDialerAdapter(1, dialer)
    # button = Button(button_listener)
    button1 = Button(ButtonDialerAdapter(1, dialer))
    button2 = Button(ButtonDialerAdapter(2, dialer))
    button1.press()
    assert screen.text == '1'
    button2.press()
    assert screen.text == '12'
    # 버튼에 의존하지 않는 다이얼러
    dialer.digit(9)
    assert screen.text == '129'
    # 다이얼러에 의존하지 않는 버튼
    button = Button(EndButtonListener(screen))
    button.press()
    assert screen.text == 'END'
