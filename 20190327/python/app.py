NUMBER_SYMBOLS = [
    ['7', '8', '9'],
    ['4', '5', '6'],
    ['1', '2', '3'],
    ['0', '.', '=']
]

OPERATOR_SYMBOLS = [
    ['*', '/'],
    ['+', '-'],
    ['(', ')'],
    ['C', 'AC']
]

ERROR_MESSAGE = ' -> 오류'


class Command:
    def __init__(self, buffer):
        self.buffer = buffer

    def do(self):
        pass


class CommandRun(Command):
    def do(self):
        try:
            result = self.calculate(self.buffer.get())
            value = round(result, 2)
            self.buffer.set(value)
        except:
            self.buffer.append(ERROR_MESSAGE)

    def calculate(self, expression):
        # TODO: write this in right way!
        return eval(expression)


class CommandClear(Command):
    def do(self):
        self.buffer.clear()


class Handler:
    def __init__(self, buffer):
        self.buffer = buffer
        self.commands = {
            '=': CommandRun(buffer),
            'C': CommandClear(buffer),
            'CA': CommandClear(buffer)
        }

    def command(self, value):
        self.buffer.delete(ERROR_MESSAGE)
        if value in self.commands:
            return self.commands[value].do()
        self.buffer.append(value)


# general buffer

class Buffer:
    def __init__(self):
        self.value = ''

    def get(self):
        return self.value

    def set(self, value):
        self.value = str(value)

    def clear(self):
        self.set('')

    def append(self, value):
        self.set(self.get() + value)

    def delete(self, value):
        self.set(self.get().replace(value, ''))


# for Web

buffer = Buffer()
handler = Handler(buffer)


def on_click(value):
    handler.command(value)
    this.text = buffer.get()


initial_data = {
    'text': '',
}

methods = {
    'onClick': on_click,
}

app = __new__(Vue(dict(
    el='#app',
    data=initial_data,
    methods=methods,
)))
