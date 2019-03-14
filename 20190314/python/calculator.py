import tkinter as tk

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


class Buffer(tk.StringVar):
    def __init__(self):
        super().__init__()
        self.clear()

    def clear(self):
        self.set('')

    def append(self, value):
        self.set(self.get() + value)

    def delete(self, value):
        self.set(self.get().replace(value, ''))


class Display(tk.Frame):
    def __init__(self, master, buffer):
        super().__init__(master)
        self.grid(row=0, column=0, columnspan=2)
        display = tk.Entry(self, width=35, bg='light green')
        display['textvariable'] = buffer
        display.pack()


class Buttons(tk.Frame):
    def __init__(self, master, column, symbols, click):
        super().__init__(master)
        self.grid(row=1, column=column)
        self.create_widgets(symbols)
        self.click = click

    def create_widgets(self, symbols):
        for row, row_symbols in enumerate(symbols):
            for column, symbol in enumerate(row_symbols):
                button = self.create_button(symbol)
                button.grid(row=row, column=column)

    def create_button(self, symbol):
        return tk.Button(self, text=symbol, width=5,
                         command=lambda: self.click(symbol))



# 참고: https://docs.python.org/3/library/tkinter.html
class Application(tk.Frame):
    def __init__(self, master=None):
        super().__init__(master)
        self.pack()
        self.buffer = Buffer()
        self.create_widgets()
        self.init_handlers()

    def create_widgets(self):
        Display(self, buffer=self.buffer)
        Buttons(self, column=0, symbols=NUMBER_SYMBOLS, click=self.click)
        Buttons(self, column=1, symbols=OPERATOR_SYMBOLS, click=self.click)

    def click(self, text):
        self.buffer.delete(ERROR_MESSAGE)
        if text in self.handlers:
            return self.handlers[text]()
        self.buffer.append(text)

    def init_handlers(self):
        self.handlers = {
            '=': self.do_run,
            'C': self.do_clear,
            'CA': self.do_clear
        }

    def do_run(self):
        try:
            result = self.calculate(self.buffer.get())
            value = round(result, 2)
            self.buffer.set(value)
        except:
            self.buffer.append(ERROR_MESSAGE)

    def do_clear(self):
        self.buffer.clear()

    def calculate(self, expression):
        # TODO: write this in right way!
        return eval(expression)


def main():
    root = tk.Tk()
    root.title('Calculator Ver. 1.0')
    app = Application(master=root)
    app.mainloop()

if __name__ == '__main__':
    main()
